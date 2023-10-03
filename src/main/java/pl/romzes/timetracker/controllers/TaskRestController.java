package pl.romzes.timetracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.romzes.timetracker.dao.TaskDAO;
import pl.romzes.timetracker.models.Task;
import pl.romzes.timetracker.utils.TaskDAOException;
import pl.romzes.timetracker.utils.TaskErrorResponse;

import java.util.List;

@RestController
@RequestMapping("/api")
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

	@ExceptionHandler
	private ResponseEntity<TaskErrorResponse> exceptionHandler(TaskDAOException exception){
		ResponseEntity response = new ResponseEntity<>(new TaskErrorResponse("Task with this id wasn't found in database"),
				HttpStatus.NOT_FOUND);
		return response;
	}





}
