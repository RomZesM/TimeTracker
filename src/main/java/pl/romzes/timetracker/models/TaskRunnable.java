package pl.romzes.timetracker.models;

public class TaskRunnable implements Runnable{
	private int taskId;

	private int projectId;
	private String name;

	private long startedAt;
	private long finishedAt;
	private int duration; //in second

	private int counterTest = 0;
	public TaskRunnable() {

	}

	public TaskRunnable(int projectId, String name, long startedAt, long finishedAt, int duration) {
		this.projectId = projectId;
		this.name = name;
		this.startedAt = startedAt;
		this.finishedAt = finishedAt;
		this.duration = duration;
	}

	@Override
	public void run() {
		Thread current = Thread.currentThread();
		while(!current.isInterrupted()){
			try {
				Thread.sleep(1000);
				counterTest++;

			} catch (InterruptedException e) {
				//e.printStackTrace();
				current.interrupt(); //interrupt current thread
			}
		}
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

	public long getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(long startedAt) {
		this.startedAt = startedAt;
	}

	public long getFinishedAt() {
		return finishedAt;
	}

	public void setFinishedAt(long finishedAt) {
		this.finishedAt = finishedAt;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getCounterTest() { //!del
		return counterTest;
	}

	public void setCounterTest(int counterTest) {//!del
		this.counterTest = counterTest;
	}

	@Override
	public String toString() {
		return "Task{" +
				"taskId=" + taskId +
				", subtaskId=" + projectId +
				", name='" + name + '\'' +
				", startedAt=" + startedAt +
				", finishedAt=" + finishedAt +
				", duration=" + duration +
				'}';
	}
}





