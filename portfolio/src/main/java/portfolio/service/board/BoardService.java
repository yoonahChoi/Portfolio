package portfolio.service.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import portfolio.model.board.Board;
import portfolio.model.board.File;

public interface BoardService {
	public static final Integer LIMIT = 10;

	public abstract List<Board> list(Integer start);

	public abstract List<Board> list(Integer start, Integer category_id);

	public abstract int count();

	public abstract int count(Integer category_id);

	public abstract void write(Board board, MultipartFile file);

	public abstract int edit(Board board, MultipartFile file);

	public abstract int delete(Board board);

	public abstract Board read(int id);
	
	public abstract void hits (int id);

	public abstract int like(int id);

	public abstract int dislike(int id);
	
	public abstract void upload(MultipartFile file, int board_id);
	
	public abstract File getFile(int board_id);
}
