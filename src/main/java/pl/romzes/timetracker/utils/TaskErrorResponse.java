package pl.romzes.timetracker.utils;

//object contain custom error message
public class TaskErrorResponse {
	private String message;

	public TaskErrorResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
