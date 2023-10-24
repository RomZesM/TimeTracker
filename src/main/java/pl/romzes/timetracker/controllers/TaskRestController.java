package pl.romzes.timetracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import pl.romzes.timetracker.dao.TaskDAO;
import pl.romzes.timetracker.models.Task;
import pl.romzes.timetracker.utils.TaskControllerException;
import pl.romzes.timetracker.utils.TaskDAOException;
import pl.romzes.timetracker.utils.TaskErrorResponse;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskRestController {

	private TaskDAO taskDAO;
	private Task cureentRunningTask;
	private Thread threadWithCurrentTask;


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

	//start, stop, thread with task, show current tusk
	@PostMapping("/start")
	public ResponseEntity startTimer(@RequestBody Task task) {
		System.out.println("start task");
		if(cureentRunningTask == null){

			cureentRunningTask = task;
			task.setStartedAt(new Date().getTime());//insert start time
			threadWithCurrentTask = new Thread(cureentRunningTask);
			threadWithCurrentTask.start();
			return ResponseEntity.ok(HttpStatus.OK);
		}
		else
			throw new TaskControllerException("The other task is still running");
	}

	@GetMapping("/stop")
	public ResponseEntity stopTimer() {
		if(threadWithCurrentTask.isAlive()){
			//System.out.println("stop task");
			threadWithCurrentTask.interrupt();//stop current task
			cureentRunningTask.setFinishedAt(new Date().getTime());
			taskDAO.save(cureentRunningTask);//save finished task into db
			cureentRunningTask = null; //clear current task
			return ResponseEntity.ok(HttpStatus.OK);
		}
		else throw new TaskControllerException("There is no running task");

	}

	@GetMapping("/show")
	public Task showCurrentTask() {
		System.out.println("show task");
		return cureentRunningTask;
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

	@ExceptionHandler
	private ResponseEntity<TaskErrorResponse> exceptionHandler(TaskControllerException exception){
		ResponseEntity response = new ResponseEntity<>(new TaskErrorResponse(exception.getMessage()),
				HttpStatus.NOT_FOUND);
		return response;
	}





}
