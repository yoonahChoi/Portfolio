package portfolio.board;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.modelmapper.ModelMapper;

import portfolio.model.board.Board;
import portfolio.model.board.ListDto;

public class BoardModelMapperTests {

	private ModelMapper modelMapper = new ModelMapper();

	@Test
	public void convertBoardEntityToListDto() {
		Board board = new Board();
		board.setId(1);
		board.setCategory("잡담");
		board.setTitle("mapperTest 제목");
		board.setWriter("작성자");
		board.setReg_date("2020.04.21");
		board.setHits(1);
		board.setLikes(1);

		ListDto listDto = modelMapper.map(board, ListDto.class);
		assertEquals(board.getId(), listDto.getId());
		assertEquals(board.getCategory(), listDto.getCategory());
		assertEquals(board.getTitle(), listDto.getTitle());
		assertEquals(board.getWriter(), listDto.getWriter());
		assertEquals(board.getReg_date(), listDto.getReg_date());
		assertEquals(board.getHits(), listDto.getHits());
		assertEquals(board.getLikes(), listDto.getLikes());
	}
}
