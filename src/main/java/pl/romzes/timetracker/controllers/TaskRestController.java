package pl.romzes.timetracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.romzes.timetracker.dao.TaskDAO;
import pl.romzes.timetracker.models.Task;

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





}
