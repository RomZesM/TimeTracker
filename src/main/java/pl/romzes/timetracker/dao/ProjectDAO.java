package pl.romzes.timetracker.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.romzes.timetracker.models.Project;

import java.util.List;

@Component
public class ProjectDAO {

	final private JdbcTemplate jdbcTemplate;
	@Autowired
	public ProjectDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public List<Project> index() {
		String sql = "SELECT * FROM Project";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Project.class));
	}

	public void save(Project project) {
		String sql = "INSERT INTO Project (name, objective_id) VALUES (?,?)";
		jdbcTemplate.update(sql, project.getName(), project.getObjectiveId());
	}
}