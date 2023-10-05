package pl.romzes.timetracker.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.romzes.timetracker.dao.GlobalTaskDAO;
import pl.romzes.timetracker.models.GlobalTask;
import pl.romzes.timetracker.utils.GlobalTaskDaoException;
import pl.romzes.timetracker.utils.TaskDAOException;
import pl.romzes.timetracker.utils.TaskErrorResponse;

import java.util.List;

@RestController
@RequestMapping ("/api/globaltask")
public class GlobalTaskRestController {


	private GlobalTaskDAO globalTaskDAO;
	@Autowired
	public GlobalTaskRestController(GlobalTaskDAO globalTaskDAO) {
		this.globalTaskDAO = globalTaskDAO;
	}


	@GetMapping("/index")
	public List<GlobalTask> createGlobalTask(){
		return globalTaskDAO.index();
	}

	@GetMapping("/{id}")
	public GlobalTask show(@PathVariable("id") int id){
		return globalTaskDAO.show(id);
	}


	@PostMapping()
	public ResponseEntity createNewGlobalTask(@RequestBody GlobalTask globalTask){
		globalTaskDAO.save(globalTask);
		return ResponseEntity.ok(HttpStatus.OK);
	}


	@ExceptionHandler
	private ResponseEntity<GlobalTaskDaoException> exception(GlobalTaskDaoException exception){
		ResponseEntity response = new ResponseEntity<>(new TaskErrorResponse("GlobalTask with this id wasn't found in db"), HttpStatus.NOT_FOUND);
		return response;


	}
}
