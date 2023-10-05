package pl.romzes.timetracker.models;

public class GlobalTask {

	private int gtaskId;
	private String name;


	public GlobalTask(){

	}
	public GlobalTask(String name) {
		this.name = name;
	}

	public int getGtaskId() {
		return gtaskId;
	}

	public void setGtaskId(int gtaskId) {
		this.gtaskId = gtaskId;
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
				"gtaskId=" + gtaskId +
				", name='" + name + '\'' +
				'}';
	}
}
