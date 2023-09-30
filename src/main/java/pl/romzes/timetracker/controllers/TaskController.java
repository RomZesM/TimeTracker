package pl.romzes.timetracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.romzes.timetracker.dao.TaskDAO;
import pl.romzes.timetracker.models.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@RequestMapping("/tasks")
public class TaskController {

	private TaskDAO taskDao;
	@Autowired
	public TaskController(TaskDAO taskDao){
		this.taskDao = taskDao;
	}


	@GetMapping()
	public String index(Model model){
		model.addAttribute("tasksList", taskDao.index());
		return "/tasks/index";
	}
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model){
		model.addAttribute("task", taskDao.show(id));
		return "tasks/show";
	}

	@GetMapping("/new")
	public String createNewPerson(Model model){
		taskDao.save(createFakeTask());
		return "redirect:/tasks";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("task", taskDao.show(id));
		return "tasks/edit";
	}

	@PatchMapping("/{id}")
	public String updateTask(@PathVariable("id") int id, @ModelAttribute("task") Task task){
		taskDao.update(id, task);
		return  "redirect:/tasks";
	}

	@DeleteMapping("{id}")
	public String deleteTask(@PathVariable("id") int id) {
		taskDao.delete(id);
		return "redirect:/tasks";
	}

	public Task createFakeTask(){

		Calendar calendar = new GregorianCalendar();
		Date startDate = calendar.getTime();
		int taskSpentTime = ThreadLocalRandom.current().nextInt(1, 60 + 1);

		calendar.add(Calendar.MINUTE, taskSpentTime);

		Date finDate = calendar.getTime();
		//count duration
		int duration = (int) ( finDate.getTime() - startDate.getTime() ) / 1000;


		int id = ThreadLocalRandom.current().nextInt(1, 1000000 + 1);

		Task task = new Task(id, "Task-"+id, startDate.getTime(), finDate.getTime(), duration);

		return task;
	}

}
