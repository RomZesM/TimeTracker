package pl.romzes.timetracker.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.romzes.timetracker.dao.ObjectiveDAO;
import pl.romzes.timetracker.models.Objective;
import pl.romzes.timetracker.utils.GlobalTaskDaoException;
import pl.romzes.timetracker.utils.TaskErrorResponse;

import java.util.List;
//main global task we want to obtain
@RestController
@RequestMapping ("/api/objective")
public class ObjectiveRestController {


	private ObjectiveDAO objectiveDAO;
	@Autowired
	public ObjectiveRestController(ObjectiveDAO objectiveDAO) {
		this.objectiveDAO = objectiveDAO;
	}

	//1 show all Objectives from db
	@GetMapping("/index")
	public List<Objective> createGlobalTask(){
		return objectiveDAO.index();
	}

	//2 show 1 objective from db
	@GetMapping("/{id}")
	public Objective show(@PathVariable("id") int id){
		return objectiveDAO.show(id);
	}

	//3 save new objective, which we get from rest API
	@PostMapping("/new")
	public ResponseEntity createNewObjectiveTask(@RequestBody Objective objective){
		objectiveDAO.save(objective);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	//4 change current existing objective, which we get from rest API
	@PostMapping("/edit")
	public ResponseEntity changeObjectiveTask(@RequestBody Objective objective){
		objectiveDAO.update(objective);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PostMapping("/del")
	public ResponseEntity deleteObjectiveTask(@RequestBody Objective objective){
		objectiveDAO.delete(objective);
		return ResponseEntity.ok(HttpStatus.OK);
	}


	// handle exception from db, we use own custom Exception, and return Response Entity, which
	// spring convert into JSON
	@ExceptionHandler
	private ResponseEntity<GlobalTaskDaoException> exception (GlobalTaskDaoException exception){
		ResponseEntity response = new ResponseEntity<>(new TaskErrorResponse("Object with this id wasn't found in db"), HttpStatus.NOT_FOUND);
		return response;


	}
}
