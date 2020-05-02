package portfolio.dao.board;

import java.util.List;

import portfolio.model.board.Board;
import portfolio.model.board.BoardFile;

public interface BoardDao {
	
	//Read
	public abstract List<Board> listAll(Integer start, Integer limit);
	
	public abstract List<Board> listByCategory(Integer start, Integer limit, Integer category_id);
	
	public abstract Board select(Integer boardNo);
	
	public abstract int getCount();
	
	public abstract int getCountByCategory(Integer category_id);
	
	public abstract BoardFile selectFile(Integer board_id);
	
	public abstract int getLastId(String table);
	
	//Create
	public abstract int insert(Board board);
	
	public abstract int insertFile(BoardFile file);
	
	//Update
	public abstract int update(Board board);
	
	public abstract int updateReadCount(Integer boardNo);
	
	public abstract int updateLikeCount(Integer boardNo);
	
	public abstract int updateDislikeCount(Integer boardNo);
	
	//Delete
	public abstract int delete(Board board);
	
	public abstract int deleteFile(Integer id);
	
	public abstract int deleteAll();
	

}
