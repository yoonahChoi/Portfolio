package portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import portfolio.model.project.Project;
import portfolio.service.project.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectService service;

	@GetMapping("/")
	public ResponseEntity<List<Project>> list() {

		ResponseEntity<List<Project>> entity = null;
		List<Project> list = null;

		try {
			list = service.list();
			entity = new ResponseEntity<List<Project>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<Project>>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

}
