package portfolio.board.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import portfolio.dao.board.BoardDao;
import portfolio.dao.board.BoardDaoImpl;

@Configuration
public class BoardTestConfiguration {

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/notice_board?useUnicode=true&characterEncoding=utf8");
		dataSource.setUsername("root");
		dataSource.setPassword("0000");
		return dataSource;
	}

	@Bean("BoardDaoImpl")
	public BoardDao boardDao() {
		return new BoardDaoImpl(dataSource());
	}

}
