package portfolio.dao.board;

public class BoardDaoSqls {
	public static final String SELECT_LIST = "SELECT board.id, name AS category, title, content, writer, reg_date, hits, likes, dislikes "
			+ "FROM board JOIN category ON board.category_id = category.id ORDER BY id DESC limit :start, :limit;";

	public static final String SELECT_LIST_BY_CATEGORY_ID = "SELECT board.id, name AS category, title, content, writer, reg_date, hits, likes, dislikes "
			+ "FROM board JOIN category ON board.category_id = category.id WHERE category.id = :category_id ORDER BY id DESC limit :start, :limit;";

	public static final String SELECT_BOARD = "SELECT board.id, category_id, name as category, title, content, writer, password, reg_date, hits, likes, dislikes "
			+ "FROM board JOIN category ON board.category_id = category.id WHERE board.id = :boardNo;";

	public static final String SELECT_COUNT = "SELECT count(*) FROM board;";

	public static final String SELECT_COUNT_BY_CATEGORY_ID = "SELECT count(*) FROM board WHERE category_id = :category_id;";
	
	public static final String SELECT_FILE = "SELECT * FROM file WHERE board_id = :board_id;";
	
	public static final String SELECT_LAST_INSERT_ID = "SELECT AUTO_INCREMENT - 1 FROM information_schema.tables WHERE table_name=:table AND table_schema = DATABASE();";

	public static final String INSERT_BOARD = "INSERT INTO board (category_id, title, content, writer, password, reg_date)"
			+ "VALUES (:category_id, :title, :content, :writer, :password, now());";
	
	public static final String INSERT_FILE = "INSERT INTO file (board_id, originname, filename, type, reg_date)"
			+ "VALUES (:board_id, :originname, :filename, :type, now());";

	public static final String UPDATE_BOARD = "UPDATE board SET category_id=:category_id, title=:title, content=:content, writer=:writer, reg_date=now() "
			+ "WHERE id=:id AND password=:password;";

	public static final String UPDATE_BOARD_HITS = "UPDATE board SET hits = hits + 1 WHERE id=:boardNo;";

	public static final String UPDATE_BOARD_LIKES = "UPDATE board SET likes = likes + 1 WHERE id=:boardNo;";
	
	public static final String UPDATE_BOARD_DISLIKES = "UPDATE board SET dislikes = dislikes + 1 WHERE id=:boardNo;";

	public static final String DELETE_BOARD = "DELETE FROM board WHERE id=:id AND password=:password;";
	
	public static final String DELETE_FILE = "DELETE FROM file WHERE id=:id";
	
	public static final String DELETE_ALL = "DELETE FROM board";
}
