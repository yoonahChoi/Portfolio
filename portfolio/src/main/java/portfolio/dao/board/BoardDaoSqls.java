package portfolio.dao.board;

public class BoardDaoSqls {
	public static final String SELECT_LIST = "SELECT board.id, name AS category, title, content, writer, reg_date, hits, likes, dislikes "
			+ "FROM board JOIN category ON board.categoryId = category.id ORDER BY id DESC limit :start, :limit;";

	public static final String SELECT_LIST_BY_CATEGORY_ID = "SELECT board.id, name AS category, title, content, writer, reg_date, hits, likes, dislikes "
			+ "FROM board JOIN category ON board.categoryId = category.id WHERE category.id = :categoryId ORDER BY id DESC limit :start, :limit;";

	public static final String SELECT_BOARD = "SELECT board.id, name as category, title, content, writer, reg_date, hits, likes, dislikes "
			+ "FROM board JOIN category ON board.categoryId = category.id WHERE board.id = :boardNo;";

	public static final String SELECT_COUNT = "SELECT count(*) FROM board;";

	public static final String SELECT_COUNT_BY_CATEGORY_ID = "SELECT count(*) FROM board WHERE categoryId = :categoryId;";

	public static final String INSERT_BOARD = "INSERT INTO board (categoryId, title, content, writer, password, reg_date)"
			+ "VALUES (:categoryId, :title, :content, :writer, :password, now())";

	public static final String UPDATE_BOARD = "UPDATE board SET categoryId=:categoryId, title=:title, content=:content, writer=:writer, reg_date=now() "
			+ "WHERE id=:id AND password=:password;";

	public static final String UPDATE_BOARD_HITS = "UPDATE board SET hits = hits + 1 WHERE id=:boardNo;";

	public static final String UPDATE_BOARD_LIKES = "UPDATE board SET likes = likes + 1 WHERE id=:boardNo;";
	
	public static final String UPDATE_BOARD_DISLIKES = "UPDATE board SET dislikes = dislikes + 1 WHERE id=:boardNo;";

	public static final String DELETE_BOARD = "DELETE FROM board WHERE id=:id AND password=:password;";
	
	public static final String DELETE_ALL = "DELETE FROM board";
}
