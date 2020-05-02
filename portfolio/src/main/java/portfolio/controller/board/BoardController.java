package portfolio.controller.board;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mysql.jdbc.StringUtils;

import portfolio.model.board.Board;
import portfolio.model.board.DetailDto;
import portfolio.model.board.BoardFile;
import portfolio.model.board.ListDto;
import portfolio.service.board.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService service;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> list(
			@RequestParam(name = "cid", required = false, defaultValue = "0") int cid,
			@RequestParam(name = "start", required = false, defaultValue = "0") int start) {

		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> resultMap = new HashMap<>();
		List<Board> list = null;
		List<Integer> pageStartList = new ArrayList<>();
		int count = 0;

		try {
			if (cid == 0) {
				list = service.list(start);
				count = service.count();
			} else {
				list = service.list(start, cid);
				count = service.count(cid);
			}

			int pageCount = count / BoardService.LIMIT;

			if (count % BoardService.LIMIT > 0)
				pageCount++;

			for (int i = 0; i < pageCount; i++) {
				pageStartList.add(i * BoardService.LIMIT);
			}

			resultMap.put("pageStartList", pageStartList);
			resultMap.put("list", list.stream().map(this::convertToListDto).collect(Collectors.toList()));

			entity = new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailDto> detail(@PathVariable int id,
			@CookieValue(value = "HITCOOKIE", defaultValue = "", required = false) String cookieValue,
			HttpServletResponse res) {
		ResponseEntity<DetailDto> entity = null;
		Board board = null;
		DetailDto detailDto = null;

		String bid = "|" + id;

		if (StringUtils.indexOfIgnoreCase(cookieValue, bid) == -1) {
			Cookie cookie = new Cookie("HITCOOKIE", cookieValue + bid);
			res.addCookie(cookie);
			service.hits(id);
		}

		try {

			board = service.read(id);

			BoardFile file = service.getFile(board.getId());
			detailDto = convertToDetailDto(board, file);

			entity = new ResponseEntity<DetailDto>(detailDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<DetailDto>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	@GetMapping("/like")
	public ResponseEntity<HttpStatus> like(@RequestParam(name = "bid") int bid,
			@CookieValue(value = "LIKECOOKIE", defaultValue = "", required = false) String cookieValue,
			HttpServletResponse res) {

		String id = "|" + bid;

		if (StringUtils.indexOfIgnoreCase(cookieValue, id) == -1) {
			Cookie cookie = new Cookie("LIKECOOKIE", cookieValue + id);
			cookie.setMaxAge(60 * 60 * 24);
			res.addCookie(cookie);
			service.like(bid);
		} else {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@GetMapping("/dislike")
	public ResponseEntity<HttpStatus> dislike(@RequestParam(name = "bid") int bid,
			@CookieValue(value = "DISLIKECOOKIE", defaultValue = "", required = false) String cookieValue,
			HttpServletResponse res) {

		String id = "|" + bid;

		if (StringUtils.indexOfIgnoreCase(cookieValue, id) == -1) {
			Cookie cookie = new Cookie("DISLIKECOOKIE", cookieValue + id);
			cookie.setMaxAge(60 * 60 * 24);
			res.addCookie(cookie);
			service.dislike(bid);
		} else {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_MODIFIED);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<HttpStatus> write(MultipartHttpServletRequest req) {
		Board board = new Board();
		Board dbBoard = null;

		board.setId(Integer.parseInt(req.getParameter("boardId")));
		board.setCategory_id(Integer.parseInt(req.getParameter("categoryId")));
		board.setWriter(req.getParameter("writer"));
		board.setPassword(req.getParameter("password"));
		board.setTitle(req.getParameter("title"));
		board.setContent(req.getParameter("content"));
		MultipartFile file = req.getFile("file");

		if (board.getId() > 0)
			dbBoard = service.read(board.getId());

		if (dbBoard != null) {
			if (board.getPassword().equals(dbBoard.getPassword())) {
				service.edit(board, file);
				return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<HttpStatus>(HttpStatus.FORBIDDEN);
			}
		} else {
			service.write(board, file);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}

	@PostMapping("/{bid}")
	public ResponseEntity<HttpStatus> remove(@PathVariable int bid, HttpServletRequest req) {

		Board board = service.read(bid);
		String password = board.getPassword();

		if (password.equals(req.getParameter("password"))) {
			service.delete(board);
		} else {
			return new ResponseEntity<HttpStatus>(HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@GetMapping("/download")
	public ResponseEntity<ByteArrayResource> download(@RequestParam(name = "id") int id, HttpServletResponse res) {
		BoardFile dbFile = service.getFile(id);

		String fileName = dbFile.getOriginname() + dbFile.getType();
		StringBuilder sb = new StringBuilder("c:/upload/");
		sb.append(dbFile.getFilename() + dbFile.getType());
		String saveFileName = sb.toString();

		File file = new File(saveFileName);
		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		headers.add("Content-Transfer-Encoding", "binary");
		headers.add("Pragma", "no-cache;");
		headers.add("Expires", "-1;");

		Path path = Paths.get(saveFileName);
		ByteArrayResource resource = null;

		try {
			resource = new ByteArrayResource(Files.readAllBytes(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
	}

	private ListDto convertToListDto(Board board) {
		ListDto listDto = modelMapper.map(board, ListDto.class);
		return listDto;
	}

	private DetailDto convertToDetailDto(Board board, BoardFile file) {
		DetailDto detailDto = null;

		if (file != null) {
			detailDto = new DetailDto(board.getId(), board.getCategory_id(), board.getCategory(), board.getTitle(),
					board.getContent(), board.getWriter(), board.getReg_date().substring(0, 16), board.getHits(),
					board.getLikes(), board.getDislikes(), file.getId(), file.getOriginname() + file.getType());
		} else {
			detailDto = new DetailDto(board.getId(), board.getCategory_id(), board.getCategory(), board.getTitle(),
					board.getContent(), board.getWriter(), board.getReg_date().substring(0, 16), board.getHits(),
					board.getLikes(), board.getDislikes());
		}

		return detailDto;

	}

}
