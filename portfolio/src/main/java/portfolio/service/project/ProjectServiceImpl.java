package portfolio.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.dao.project.ProjectDao;
import portfolio.model.project.Project;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao dao;

	@Override
	public List<Project> list() {
		List<Project> list = dao.listAll();

		for (Project project : list) {
			project.setStart_date(project.getStart_date().substring(0, 10));
			project.setEnd_date(project.getEnd_date().substring(0, 10));
		}
		
		return list;
	}

}
