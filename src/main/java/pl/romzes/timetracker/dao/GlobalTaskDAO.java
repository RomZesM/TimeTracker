package pl.romzes.timetracker.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.romzes.timetracker.models.GlobalTask;
import pl.romzes.timetracker.utils.GlobalTaskDaoException;
import pl.romzes.timetracker.utils.TaskDAOException;
import pl.romzes.timetracker.utils.TaskErrorResponse;

import java.util.List;

@Component
public class GlobalTaskDAO {

	private final JdbcTemplate jdbcTemplate;
	@Autowired
	public GlobalTaskDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<GlobalTask> index(){
		String sql = "SELECT * FROM Global_tasks";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GlobalTask.class));
	}

	public GlobalTask show(int id) {
		String sql = "SELECT * FROM Global_tasks WHERE gtask_id=?";
		return jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(GlobalTask.class)).stream().findAny().orElseThrow(GlobalTaskDaoException::new); //todo what is the SUPPLIER is? why Exception::new?

	}


	public void save(GlobalTask globalTask) {
		String sql = "INSERT INTO Global_tasks (name) VALUES (?)";
		jdbcTemplate.update(sql, globalTask.getName());

	}
}
