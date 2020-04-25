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
import portfolio.model.board.File;

import static portfolio.dao.board.BoardDaoSqls.*;

@Repository
public class BoardDaoImpl implements BoardDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Board> rowMapper = BeanPropertyRowMapper.newInstance(Board.class);
	private RowMapper<File> rowMapperFile = BeanPropertyRowMapper.newInstance(File.class);

	public BoardDaoImpl(DataSource datasource) {
		this.jdbc = new NamedParameterJdbcTemplate(datasource);
	}

	@Override
	public List<Board> listAll(Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_LIST, params, rowMapper);
	}

	@Override
	public List<Board> listByCategory(Integer start, Integer limit, Integer category_id) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		params.put("category_id", category_id);

		return jdbc.query(SELECT_LIST_BY_CATEGORY_ID, params, rowMapper);
	}

	@Override
	public Board select(Integer boardNo) {
		Map<String, Integer> params = new HashMap<>();
		params.put("boardNo", boardNo);
		try {
			return jdbc.queryForObject(SELECT_BOARD, params, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public int getCount() {
		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	}

	@Override
	public int getCountByCategory(Integer category_id) {
		Map<String, Integer> params = new HashMap<>();
		params.put("category_id", category_id);
		try {
			return jdbc.queryForObject(SELECT_COUNT_BY_CATEGORY_ID, params, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
	
	@Override
	public int getLastId(String table) {
		Map<String, String> params = new HashMap<>();
		params.put("table", table);
		return jdbc.queryForObject(SELECT_LAST_INSERT_ID, params, Integer.class);
	}

	@Override
	public int insert(Board board) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(board);
		return jdbc.update(INSERT_BOARD, params);
	}

	@Override
	public int update(Board board) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(board);
		return jdbc.update(UPDATE_BOARD, params);
	}

	@Override
	public int updateReadCount(Integer boardNo) {
		Map<String, Integer> params = new HashMap<>();
		params.put("boardNo", boardNo);
		return jdbc.update(UPDATE_BOARD_HITS, params);
	}

	@Override
	public int updateLikeCount(Integer boardNo) {
		Map<String, Integer> params = new HashMap<>();
		params.put("boardNo", boardNo);
		return jdbc.update(UPDATE_BOARD_LIKES, params);
	}
	
	@Override
	public int updateDislikeCount(Integer boardNo) {
		Map<String, Integer> params = new HashMap<>();
		params.put("boardNo", boardNo);
		return jdbc.update(UPDATE_BOARD_DISLIKES, params);
	}

	@Override
	public int delete(Board board) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(board);
		return jdbc.update(DELETE_BOARD, params);
	}

	@Override
	public int deleteAll() {
		return jdbc.update(DELETE_ALL, Collections.emptyMap());
	}

	@Override
	public File selectFile(Integer board_id) {
		Map<String, Integer> params = new HashMap<>();
		params.put("board_id", board_id);
		try {
			return jdbc.queryForObject(SELECT_FILE, params, rowMapperFile);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public int insertFile(File file) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(file);
		return jdbc.update(INSERT_FILE, params);
	}

	@Override
	public int deleteFile(Integer id) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		return jdbc.update(DELETE_FILE, params);
	}

}
