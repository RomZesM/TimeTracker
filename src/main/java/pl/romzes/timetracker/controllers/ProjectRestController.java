package pl.romzes.TimeTracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.romzes.TimeTracker.dao.ProjectDAO;
import pl.romzes.TimeTracker.models.Project;
import pl.romzes.timetracker.utils.GlobalTaskDaoException;
import pl.romzes.timetracker.utils.TaskErrorResponse;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectRestController {

	private ProjectDAO projectDAO;

	@Autowired
	public ProjectRestController(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
	//1 show all Projects from db
	@GetMapping("/index")
	public List<Project> index(){
		return projectDAO.index();
	}

	//2 show one Projects from db
	@GetMapping("/{id}")
	public Project show(@PathVariable("id") int id){
		return projectDAO.show(id);
	}
	//3 save new Project, which we get from rest API
	@PostMapping
	public ResponseEntity createNewSubTasks(@RequestBody Project project){
		projectDAO.save(project);
		return ResponseEntity.ok(HttpStatus.OK);
	}


	//4 change current existing Project, which we get from rest API
	@PostMapping("/edit")
	public ResponseEntity changeObjectiveTask(@RequestBody Project project){
		projectDAO.update(project);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PostMapping("/del")
	public ResponseEntity deleteObjectiveTask(@RequestBody Project project){
		projectDAO.delete(project);
		return ResponseEntity.ok(HttpStatus.OK);
	}


	// handle esxception from db, we use own custom Exception, and return Response Entity, which
	// spring convert into JSON
	@ExceptionHandler
	private ResponseEntity<GlobalTaskDaoException> exception (GlobalTaskDaoException exception){
		ResponseEntity response = new ResponseEntity<>(new TaskErrorResponse("Project with this id wasn't found in db"), HttpStatus.NOT_FOUND);
		return response;


	}


}
