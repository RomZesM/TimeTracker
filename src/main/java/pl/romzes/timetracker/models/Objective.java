package pl.romzes.timetracker.models;

public class Objective {

	private int objectiveId;
	private String name;


	public Objective(){

	}
	public Objective(String name) {
		this.name = name;
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
		return "GlobalTaskModel{" +
				"gtaskId=" + objectiveId +
				", name='" + name + '\'' +
				'}';
	}
}
