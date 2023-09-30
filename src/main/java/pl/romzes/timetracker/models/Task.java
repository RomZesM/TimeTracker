package pl.romzes.timetracker.models;

import java.util.Date;

public class Task {
	private int taskId;
	private String name;

	private Date startedAt;
	private Date finishedAt;
	private int duration; //in second

	public Task() {

	}

	public Task(int taskId, String name, Date startedAt, Date finishedAt, int duration) {
		this.taskId = taskId;
		this.name = name;
		this.startedAt = startedAt;
		this.finishedAt = finishedAt;
		this.duration = duration;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	public Date getFinishedAt() {
		return finishedAt;
	}

	public void setFinishedAt(Date finishedAt) {
		this.finishedAt = finishedAt;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Task{" +
				"taskId=" + taskId +
				", name='" + name + '\'' +
				", startedAt=" + startedAt +
				", finishedAt=" + finishedAt +
				", duration=" + duration +
				'}';
	}
}





