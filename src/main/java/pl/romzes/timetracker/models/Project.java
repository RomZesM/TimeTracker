package pl.romzes.timetracker.models;

public class Project {
	private int projectId;

	private String name;

	private int objectiveId;

	public Project(){

	}
	public Project(int objectiveId, int parentSubTaskId, String name) {
		this.objectiveId = objectiveId;
		this.name = name;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getObjectiveId() {
		return objectiveId;
	}

	public void setObjectiveId(int objectiveId) {
		this.objectiveId = objectiveId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Project{" +
				"projectId=" + projectId +
				", name='" + name + '\'' +
				", objectiveId=" + objectiveId +
				'}';
	}
}
