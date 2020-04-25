package portfolio.controller.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import portfolio.model.board.Board;
import portfolio.model.board.DetailDto;
import portfolio.model.board.File;
import portfolio.model.board.ListDto;
import portfolio.service.board.BoardService;

@CrossOrigin(origins = "http://localhost:3000")
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
	public ResponseEntity<DetailDto> detail(@PathVariable int id) {
		ResponseEntity<DetailDto> entity = null;
		Board board = null;
		DetailDto detailDto = null;

		try {
			board = service.read(id);
			File file = service.getFile(board.getId());
			detailDto = convertToDetailDto(board, file);

			entity = new ResponseEntity<DetailDto>(detailDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<DetailDto>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	@PostMapping("/")
	public HttpStatus write(MultipartHttpServletRequest request) {
		Board board = new Board();

		board.setCategory_id(Integer.parseInt(request.getParameter("categoryId")));
		board.setWriter(request.getParameter("writer"));
		board.setPassword(request.getParameter("password"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		MultipartFile file = request.getFile("file");

		service.write(board, file);

		return HttpStatus.CREATED;
	}

	private ListDto convertToListDto(Board board) {
		ListDto listDto = modelMapper.map(board, ListDto.class);
		return listDto;
	}

	private DetailDto convertToDetailDto(Board board, File file) {
		DetailDto detailDto = null;

		if (file != null) {
			detailDto = new DetailDto(board.getId(), board.getCategory(), board.getTitle(), board.getTitle(),
					board.getWriter(), board.getReg_date().substring(0, 16), board.getHits(), board.getLikes(),
					board.getDislikes(), file.getId(), file.getOriginname());
		} else {
			detailDto = new DetailDto(board.getId(), board.getCategory(), board.getTitle(), board.getTitle(),
					board.getWriter(), board.getReg_date().substring(0, 16), board.getHits(), board.getLikes(),
					board.getDislikes());
		}

		return detailDto;

	}

}
