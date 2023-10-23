package pl.romzes.timetracker.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.romzes.timetracker.models.TaskRunnable;

@RestController
@RequestMapping("/test")
public class TaskExecution {

	public static int counter = 0;

	private Thread threadWithTask;
	private TaskRunnable tr;

	@GetMapping("/start")
	public ResponseEntity startTimer() {
		System.out.println("start task");
		tr = new TaskRunnable();
		threadWithTask = new Thread(tr);
		threadWithTask.start();
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping("/stop")
	public ResponseEntity stopTimer() {
		System.out.println("stop task");
		threadWithTask.interrupt();
		System.out.println("Task duration: " + tr.getCounterTest());
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping("/show")
	public TaskRunnable showCurrentTask() {
		System.out.println("show task");
		return tr;
	}



//	public static void main(String[] args) throws InterruptedException {
//
//		TaskRunnable tr = new TaskRunnable();
//		Thread thread = new Thread(tr);
//
////		Thread t = new Thread(new Runnable() {
////
////			@Override
////			public void run() {
////				int i = 0;
////				while(!Thread.currentThread().isInterrupted()){
////					try {
////						System.out.println("tick");
////						Thread.sleep(1000);
////
////						System.out.println(i);
////						i++;
////					} catch (InterruptedException e) {
////						e.printStackTrace();
////						System.out.println("I was interupted");
////						Thread.currentThread().interrupt();
////					}
////
////				}
////
////			}
////		});
//
//		thread.start();
//		Thread.sleep(5000);
//		thread.interrupt();
//		System.out.println("Task duration: " + tr.getCounterTest());
//	}


}
