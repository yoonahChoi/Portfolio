package portfolio.service.board;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.dao.board.BoardDao;
import portfolio.model.board.Board;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;

	@Override
	public List<Board> list(Integer start) {
		List<Board> list = dao.listAll(start, BoardService.LIMIT);
		ifTodayChangeRegDate(list);
		return list;
	}

	@Override
	public List<Board> list(Integer start, Integer categoryId) {
		List<Board> list = dao.listByCategory(start, BoardService.LIMIT, categoryId);
		ifTodayChangeRegDate(list);
		return list;
	}

	@Override
	public int count() {
		return dao.getCount();
	}

	@Override
	public int count(Integer categoryId) {
		return dao.getCountByCategory(categoryId);
	}

	@Override
	public void write(Board board) {
		dao.insert(board);
	}

	@Override
	public int edit(Board board) {
		return dao.update(board);
	}

	@Override
	public int delete(Board board) {
		return dao.delete(board);
	}

	@Override
	public Board read(int id) {
		dao.updateReadCount(id);
		return dao.select(id);
	}
	
	@Override
	public int like(int id) {
		return dao.updateLikeCount(id);
	}
	
	@Override
	public int dislike(int id) {
		return dao.updateDislikeCount(id);
	}

	private void ifTodayChangeRegDate(List<Board> list) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(new Date());

		for (Board board : list) {
			String date = board.getReg_date().substring(0, 10);
			String time = board.getReg_date().substring(11, 16);

			if (today.equals(date)) {
				board.setReg_date(time);
			} else {
				board.setReg_date(date);
			}
		}
	}

}
