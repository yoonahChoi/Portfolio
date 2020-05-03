package portfolio.board.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import portfolio.controller.BoardController;
import portfolio.dao.board.BoardDao;
import portfolio.dao.board.BoardDaoImpl;
import portfolio.service.board.BoardService;
import portfolio.service.board.BoardServiceImpl;

@Configuration
public class BoardTestConfiguration {
/*
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/notice_board?useUnicode=true&characterEncoding=utf8");
		dataSource.setUsername("root");
		dataSource.setPassword("0000");
		return dataSource;
	}

	@Bean("BoardController")
	public BoardController boardController() {
		return new BoardController();
	}

	@Bean("BoardService")
	public BoardService boardService() {
		return new BoardServiceImpl();
	}

	@Bean("BoardDao")
	public BoardDao boardDao() {
		return new BoardDaoImpl(dataSource());
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
*/
}
