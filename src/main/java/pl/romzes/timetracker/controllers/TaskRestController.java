package pl.romzes.TimeTracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import pl.romzes.TimeTracker.dao.TaskDAO;
import pl.romzes.TimeTracker.models.Task;
import pl.romzes.timetracker.utils.TaskDAOException;
import pl.romzes.timetracker.utils.TaskErrorResponse;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskRestController {

	private TaskDAO taskDAO;
	@Autowired
	public TaskRestController(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}
	@GetMapping("/index")
	public List<Task> index(){
		return taskDAO.index();
	}

	@GetMapping("/{id}")
	public Task show(@PathVariable("id") int id){
		return taskDAO.show(id);
	}

	//3 save new task, which we get from rest API
	@PostMapping("/new") //wait for POST request to /task/api
	public ResponseEntity createTask(@RequestBody Task task){
			taskDAO.save(task);
		return ResponseEntity.ok(HttpStatus.OK); //standard answer from server if everything is fine
	}

	@PostMapping("/edit")
	public ResponseEntity changeObjectiveTask(@RequestBody Task task){
		taskDAO.updateRest(task);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PostMapping("/del")
	public ResponseEntity deleteObjectiveTask(@RequestBody Task task){
		taskDAO.deleteREST(task);
		return ResponseEntity.ok(HttpStatus.OK);
	}



	////////////////
	// Exception Handlers //todo can it be moved to another class?
	///////////////
	@ExceptionHandler
	private ResponseEntity<TaskErrorResponse> exceptionHandler(TaskDAOException exception){
		ResponseEntity response = new ResponseEntity<>(new TaskErrorResponse("Task with this id wasn't found in database"),
				HttpStatus.NOT_FOUND);
		return response;
	}





}
