package portfolio.dao.board;

import java.util.List;

import portfolio.model.board.Board;

public interface BoardDao {
	
	//Read
	public abstract List<Board> listAll(Integer start, Integer limit);
	
	public abstract List<Board> listByCategory(Integer start, Integer limit, Integer categoryId);
	
	public abstract Board select(Integer boardNo);
	
	public abstract int getCount();
	
	public abstract int getCountByCategory(Integer categoryId);
	
	//Create
	public abstract int insert(Board board);
	
	//Update
	public abstract int update(Board board);
	
	public abstract int updateReadCount(Integer boardNo);
	
	public abstract int updateLikeCount(Integer boardNo);
	
	public abstract int updateDislikeCount(Integer boardNo);
	
	//Delete
	public abstract int delete(Board board);
	
	public abstract int deleteAll();
	

}
