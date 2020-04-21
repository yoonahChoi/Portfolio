package portfolio.board;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import portfolio.board.config.BoardTestConfiguration;
import portfolio.dao.board.BoardDaoImpl;
import portfolio.model.board.Board;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BoardTestConfiguration.class)
public class BoardDaoTests {
	@Autowired
	private BoardDaoImpl dao;

	private List<Board> list;

	@Before
	public void setUp() {
		dao.deleteAll();

		dao.insert(new Board(1, "junit 테스트 제목", "junit 테스트 내용", "작성자", "1234"));
		dao.insert(new Board(2, "junit 테스트 제목", "junit 테스트 내용", "작성자", "1234"));
		dao.insert(new Board(3, "junit 테스트 제목", "junit 테스트 내용", "작성자", "1234"));
		dao.insert(new Board(4, "junit 테스트 제목", "junit 테스트 내용", "작성자", "1234"));
		dao.insert(new Board(4, "junit 테스트 제목", "junit 테스트 내용", "작성자", "1234"));

		list = dao.listAll(0, 10);

	}

	@Test
	public void listAll() {
		assertThat(list.size(), is(5));

		Board board;

		for (Board item : list) {
			board = dao.select(item.getId());
			compareVO(board, item);
		}
	}

	@Test
	public void listByCategory() {
		list = dao.listByCategory(0, 10, 4);

		assertThat(list.size(), is(2));

		Board board;

		for (Board item : list) {
			board = dao.select(item.getId());
			compareVO(board, item);
		}
	}

	@Test
	public void select() {
		Board board;

		board = dao.select(list.get(0).getId());
		compareVO(board, list.get(0));

		board = dao.select(list.get(1).getId());
		compareVO(board, list.get(1));
	}

	@Test
	public void getCount() {
		int count = dao.getCount();

		assertThat(count, is(5));
	}

	@Test
	public void getCountByCategory() {
		int count = dao.getCountByCategory(4);

		assertThat(count, is(2));
	}

	@Test
	public void insert() {
		Board board;

		board = new Board(2, "insert 제목", "insert 내용", "작성자", "1234");
		int size = dao.listAll(0, 10).size();
		dao.insert(board);

		assertThat(dao.listAll(0, 10).size(), is(size + 1));
	}

	@Test
	public void update() {
		Board board = list.get(0);

		board.setCategoryId(3);
		board.setTitle("update 제목");
		board.setContent("update 내용");
		board.setWriter("update 작성자");
		board.setPassword("1234");

		dao.update(board);

		assertThat(dao.select(board.getId()).getTitle(), is("update 제목"));
	}

	@Test
	public void updateReadCount() {
		int readCount;
		Board dbBoard;

		dbBoard = dao.select(list.get(0).getId());
		readCount = dbBoard.getHits();
		dao.updateReadCount(dbBoard.getId());
		dbBoard = dao.select(list.get(0).getId());
		assertThat(dbBoard.getHits(), is(readCount + 1));
	}

	@Test
	public void updatelikeCount() {
		int likeCount;
		Board dbBoard;

		dbBoard = dao.select(list.get(0).getId());
		likeCount = dbBoard.getLikes();
		dao.updateLikeCount(dbBoard.getId());
		dbBoard = dao.select(list.get(0).getId());
		assertThat(dbBoard.getLikes(), is(likeCount + 1));
	}
	
	@Test
	public void updateDislikeCount() {
		int dislikeCount;
		Board dbBoard;

		dbBoard = dao.select(list.get(0).getId());
		dislikeCount = dbBoard.getDislikes();
		dao.updateDislikeCount(dbBoard.getId());
		dbBoard = dao.select(list.get(0).getId());
		assertThat(dbBoard.getDislikes(), is(dislikeCount + 1));
	}

	@Test
	public void delete() {
		int id = list.get(0).getId();
		Board board = list.get(0);
		board.setPassword("1234");
		dao.delete(board);

		list = dao.listAll(0, 10);

		assertThat(list.size(), is(4));
		assertThat(dao.select(id), is(nullValue()));
	}

	@Test
	public void deleteAll() {
		dao.deleteAll();

		list = dao.listAll(0, 0);

		assertThat(list.size(), is(0));
	}

	private void compareVO(Board vo1, Board vo2) {
		assertThat(vo1.getId(), is(vo2.getId()));
		assertThat(vo1.getCategoryId(), is(vo2.getCategoryId()));
		assertThat(vo1.getTitle(), is(vo2.getTitle()));
		assertThat(vo1.getContent(), is(vo2.getContent()));
		assertThat(vo1.getWriter(), is(vo2.getWriter()));
		assertThat(vo1.getPassword(), is(vo2.getPassword()));
		assertThat(vo1.getReg_date(), is(vo2.getReg_date()));
		assertThat(vo1.getHits(), is(vo2.getHits()));
		assertThat(vo1.getLikes(), is(vo2.getLikes()));
	}

}
