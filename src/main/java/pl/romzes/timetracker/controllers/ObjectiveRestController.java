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

@RestController
@RequestMapping ("/api/objective")
public class ObjectiveRestController {


	private ObjectiveDAO objectiveDAO;
	@Autowired
	public ObjectiveRestController(ObjectiveDAO objectiveDAO) {
		this.objectiveDAO = objectiveDAO;
	}


	@GetMapping("/index")
	public List<Objective> createGlobalTask(){
		return objectiveDAO.index();
	}

	@GetMapping("/{id}")
	public Objective show(@PathVariable("id") int id){
		return objectiveDAO.show(id);
	}


	@PostMapping()
	public ResponseEntity createNewGlobalTask(@RequestBody Objective objective){
		objectiveDAO.save(objective);
		return ResponseEntity.ok(HttpStatus.OK);
	}


	@ExceptionHandler
	private ResponseEntity<GlobalTaskDaoException> exception(GlobalTaskDaoException exception){
		ResponseEntity response = new ResponseEntity<>(new TaskErrorResponse("GlobalTask with this id wasn't found in db"), HttpStatus.NOT_FOUND);
		return response;


	}
}
