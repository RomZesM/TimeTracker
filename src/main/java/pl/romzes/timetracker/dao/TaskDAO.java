package pl.romzes.timetracker.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.romzes.timetracker.models.Task;

import java.util.List;

@Component
public class TaskDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public TaskDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public List<Task> index() {
		return jdbcTemplate.query("select * from Tasks", new BeanPropertyRowMapper<>(Task.class));
	}
	public Object show(int id) {
		String sql = "SELECT * from Tasks where task_id=?";
		return jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Task.class)).stream().findAny().orElse(null);
	}


	public void save(Task task){

		String sql = "INSERT INTO Tasks values (?,?,?,?,?)"; //prepared statement
		jdbcTemplate.update(sql, task.getTaskId(), task.getName(), task.getStartedAt(), task.getFinishedAt(), task.getDuration());
	}


	public void update(int id, Task task) {

		String sql = "UPDATE Tasks set name=?, started_at=?, finished_at=?, duration=? WHERE task_id=?";
		jdbcTemplate.update(sql, task.getName(), task.getStartedAt(), task.getFinishedAt(), task.getDuration(), id);
	}
}
