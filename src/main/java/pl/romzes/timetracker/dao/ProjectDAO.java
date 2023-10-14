package pl.romzes.timetracker.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.romzes.timetracker.models.Objective;
import pl.romzes.timetracker.models.Project;
import pl.romzes.timetracker.utils.GlobalTaskDaoException;

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
	public Project show(int id) {
		String sql = "SELECT * FROM Project WHERE project_id=?";
		return jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Project.class)).stream().findAny().orElseThrow(GlobalTaskDaoException::new);
	}
	public void save(Project project) {
		String sql = "INSERT INTO Project (name, objective_id) VALUES (?,?)";
		jdbcTemplate.update(sql, project.getName(), project.getObjectiveId());
	}

	public void update(Project project) {
		String sql = "UPDATE Project set name=?, objective_id=? WHERE project_id=?";
		jdbcTemplate.update(sql, project.getName(), project.getObjectiveId(), project.getProjectId());
	}

	public void delete(Project project) {
		String sql = "DELETE FROM Project WHERE project_id=?";
		jdbcTemplate.update(sql, project.getProjectId());
	}
}
