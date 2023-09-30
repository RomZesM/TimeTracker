package pl.romzes.timetracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

		System.out.println(taskDao.index().get(0));
		return "/tasks/index";
	}

	@GetMapping("/new")
	public String createNewPerson(Model model){


		taskDao.save(createFakeTask());

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

		Task task = new Task(id, "Task-"+id, startDate, finDate, duration);

		return task;
	}

}
