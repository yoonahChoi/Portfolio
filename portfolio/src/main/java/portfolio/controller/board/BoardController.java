package portfolio.controller.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import portfolio.model.board.Board;
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

	@GetMapping({ "/", "/{cid}" })
	public ResponseEntity<Map<String, Object>> list(@PathVariable Optional<Integer> cid,
			@RequestParam(name = "start", required = false, defaultValue = "0") int start) {

		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> resultMap = new HashMap<>();
		List<Board> list = null;
		List<Integer> pageStartList = new ArrayList<>();
		int count = 0;

		try {
			if (cid.isPresent()) {
				list = service.list(start, cid.get());
				count = service.count(cid.get());
			} else {
				list = service.list(start);
				count = service.count();
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

	private ListDto convertToListDto(Board board) {
		ListDto listDto = modelMapper.map(board, ListDto.class);
		return listDto;
	}

//	private Board convertListDtoToEntity(ListDto listDto) throws ParseException {
//		Board board = modelMapper.map(listDto, Board.class);
//		return board;
//	}

}
