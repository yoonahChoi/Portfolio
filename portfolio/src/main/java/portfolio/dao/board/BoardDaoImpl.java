package portfolio.dao.board;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import portfolio.model.board.Board;
import portfolio.model.board.BoardFile;

@Repository
public class BoardDaoImpl implements BoardDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Board> rowMapper = BeanPropertyRowMapper.newInstance(Board.class);
	private RowMapper<BoardFile> rowMapperFile = BeanPropertyRowMapper.newInstance(BoardFile.class);

	public BoardDaoImpl(DataSource datasource) {
		this.jdbc = new NamedParameterJdbcTemplate(datasource);
	}

	@Override
	public List<Board> listAll(Integer start, Integer limit) {
		String sql = "SELECT board.id, name AS category, title, content, writer, reg_date, hits, likes, dislikes "
				+ "FROM board JOIN category ON board.category_id = category.id ORDER BY id DESC limit :start, :limit;";
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(sql, params, rowMapper);
	}

	@Override
	public List<Board> listByCategory(Integer start, Integer limit, Integer category_id) {
		String sql = "SELECT board.id, name AS category, title, content, writer, reg_date, hits, likes, dislikes "
				+ "FROM board JOIN category ON board.category_id = category.id WHERE category.id = :category_id ORDER BY id DESC limit :start, :limit;";
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		params.put("category_id", category_id);

		return jdbc.query(sql, params, rowMapper);
	}

	@Override
	public Board select(Integer boardNo) {
		String sql = "SELECT board.id, category_id, name as category, title, content, writer, password, reg_date, hits, likes, dislikes "
				+ "FROM board JOIN category ON board.category_id = category.id WHERE board.id = :boardNo;";
		Map<String, Integer> params = new HashMap<>();
		params.put("boardNo", boardNo);
		try {
			return jdbc.queryForObject(sql, params, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public int getCount() {
		String sql = "SELECT count(*) FROM board;";
		return jdbc.queryForObject(sql, Collections.emptyMap(), Integer.class);
	}

	@Override
	public int getCountByCategory(Integer category_id) {
		String sql = "SELECT count(*) FROM board WHERE category_id = :category_id;";
		Map<String, Integer> params = new HashMap<>();
		params.put("category_id", category_id);
		try {
			return jdbc.queryForObject(sql, params, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

	@Override
	public int getLastId(String table) {
		String sql = "SELECT AUTO_INCREMENT - 1 FROM information_schema.tables WHERE table_name=:table AND table_schema = DATABASE();";
		Map<String, String> params = new HashMap<>();
		params.put("table", table);
		return jdbc.queryForObject(sql, params, Integer.class);
	}

	@Override
	public int insert(Board board) {
		String sql = "INSERT INTO board (category_id, title, content, writer, password, reg_date)"
				+ "VALUES (:category_id, :title, :content, :writer, :password, now());";
		SqlParameterSource params = new BeanPropertySqlParameterSource(board);
		return jdbc.update(sql, params);
	}

	@Override
	public int update(Board board) {
		String sql = "UPDATE board SET category_id=:category_id, title=:title, content=:content, writer=:writer, reg_date=now() "
				+ "WHERE id=:id AND password=:password;";
		SqlParameterSource params = new BeanPropertySqlParameterSource(board);
		return jdbc.update(sql, params);
	}

	@Override
	public int updateReadCount(Integer boardNo) {
		String sql = "UPDATE board SET hits = hits + 1 WHERE id=:boardNo;";
		Map<String, Integer> params = new HashMap<>();
		params.put("boardNo", boardNo);
		return jdbc.update(sql, params);
	}

	@Override
	public int updateLikeCount(Integer boardNo) {
		String sql = "UPDATE board SET likes = likes + 1 WHERE id=:boardNo;";
		Map<String, Integer> params = new HashMap<>();
		params.put("boardNo", boardNo);
		return jdbc.update(sql, params);
	}

	@Override
	public int updateDislikeCount(Integer boardNo) {
		String sql = "UPDATE board SET dislikes = dislikes + 1 WHERE id=:boardNo;";
		Map<String, Integer> params = new HashMap<>();
		params.put("boardNo", boardNo);
		return jdbc.update(sql, params);
	}

	@Override
	public int delete(Board board) {
		String sql = "DELETE FROM board WHERE id=:id AND password=:password;";
		SqlParameterSource params = new BeanPropertySqlParameterSource(board);
		return jdbc.update(sql, params);
	}

	@Override
	public int deleteAll() {
		String sql = "DELETE FROM board";
		return jdbc.update(sql, Collections.emptyMap());
	}

	@Override
	public BoardFile selectFile(Integer board_id) {
		String sql = "SELECT * FROM file WHERE board_id = :board_id;";
		Map<String, Integer> params = new HashMap<>();
		params.put("board_id", board_id);
		try {
			return jdbc.queryForObject(sql, params, rowMapperFile);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public int insertFile(BoardFile file) {
		String sql = "INSERT INTO file (board_id, originname, filename, type, reg_date)"
				+ "VALUES (:board_id, :originname, :filename, :type, now());";
		SqlParameterSource params = new BeanPropertySqlParameterSource(file);
		return jdbc.update(sql, params);
	}

	@Override
	public int deleteFile(Integer id) {
		String sql = "DELETE FROM file WHERE id=:id";
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		return jdbc.update(sql, params);
	}

	@Override
	public List<Board> searchList(String column, String keyword, Integer start, Integer limit) {
		String sql = "SELECT board.id, name AS category, title, content, writer, reg_date, hits, likes, dislikes "
				+ "FROM board JOIN category ON board.category_id = category.id " + "WHERE " + column
				+ " LIKE :keyword ORDER BY id DESC limit :start, :limit;";

		Map<String, Object> params = new HashMap<>();
		params.put("keyword", keyword);
		params.put("start", start);
		params.put("limit", limit);

		return jdbc.query(sql, params, rowMapper);
	}

	@Override
	public List<Board> searchAllList(String keyword, Integer start, Integer limit) {
		String sql = "SELECT board.id, name AS category, title, content, writer, reg_date, hits, likes, dislikes "
				+ "FROM board JOIN category ON board.category_id = category.id WHERE title LIKE :keyword OR content LIKE :keyword ORDER BY id DESC limit :start, :limit;";
		Map<String, Object> params = new HashMap<>();
		params.put("keyword", keyword);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(sql, params, rowMapper);
	}

	@Override
	public int getSearchCount(String column, String keyword) {
		String sql = "SELECT count(*) FROM board JOIN category ON board.category_id = category.id " + "WHERE " + column
				+ " LIKE :keyword;";
		Map<String, String> params = new HashMap<>();
		params.put("keyword", keyword);

		try {
			return jdbc.queryForObject(sql, params, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

	@Override
	public int getSearchAllCount(String keyword) {
		String sql = "SELECT count(*) FROM board JOIN category ON board.category_id = category.id "
				+ "WHERE title LIKE :keyword OR content LIKE :keyword;";
		Map<String, String> params = new HashMap<>();
		params.put("keyword", keyword);
		try {
			return jdbc.queryForObject(sql, params, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

}
