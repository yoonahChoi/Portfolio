package portfolio.service.board;

import java.util.List;

import portfolio.model.board.Board;

public interface BoardService {
	public static final Integer LIMIT = 10;

	public abstract List<Board> list(Integer start);

	public abstract List<Board> list(Integer start, Integer categoryId);

	public abstract int count();

	public abstract int count(Integer categoryId);

	public abstract void write(Board board);

	public abstract int edit(Board board);

	public abstract int delete(Board board);

	public abstract Board read(int id);

	public abstract int like(int id);

	public abstract int dislike(int id);
}
