package portfolio.dao.project;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import portfolio.model.project.Project;

@Repository
public class ProjectDaoImpl implements ProjectDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Project> rowMapper = BeanPropertyRowMapper.newInstance(Project.class);

	public ProjectDaoImpl(DataSource datasource) {
		this.jdbc = new NamedParameterJdbcTemplate(datasource);
	}

	@Override
	public List<Project> listAll() {
		String sql = "SELECT * FROM projects ORDER BY start_date DESC;";
		return jdbc.query(sql, rowMapper);
	}

}
