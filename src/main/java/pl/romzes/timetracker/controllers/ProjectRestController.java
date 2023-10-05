package pl.romzes.timetracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.romzes.timetracker.dao.ProjectDAO;
import pl.romzes.timetracker.models.Project;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectRestController {

	private ProjectDAO projectDAO;

	@Autowired
	public ProjectRestController(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	@GetMapping("/index")
	public List<Project> index(){
		return projectDAO.index();
	}

	@PostMapping
	public ResponseEntity createNewSubTasks(@RequestBody Project project){
		projectDAO.save(project);
		return ResponseEntity.ok(HttpStatus.OK);
	}

}
