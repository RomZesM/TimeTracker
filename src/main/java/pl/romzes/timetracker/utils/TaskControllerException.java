package pl.romzes.timetracker.utils;

public class TaskControllerException extends RuntimeException{
	private String message;

	public TaskControllerException(String message){
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "TaskControllerException{" +
				"message='" + message + '\'' +
				'}';
	}
}
