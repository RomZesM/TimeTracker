package pl.romzes.timetracker.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.romzes.timetracker.models.Objective;
import pl.romzes.timetracker.utils.GlobalTaskDaoException;

import java.util.List;

@Component
public class ObjectiveDAO {

	private final JdbcTemplate jdbcTemplate;
	@Autowired
	public ObjectiveDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Objective> index(){
		String sql = "SELECT * FROM Objective";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Objective.class));
	}

	public Objective show(int id) {
		String sql = "SELECT * FROM Objective WHERE objective_id=?";
		return jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Objective.class)).stream().findAny().orElseThrow(GlobalTaskDaoException::new); //todo what is the SUPPLIER is? why Exception::new?

	}


	public void save(Objective objective) {
		String sql = "INSERT INTO Objective (name) VALUES (?)";
		jdbcTemplate.update(sql, objective.getName());

	}

	public void update(Objective objective) {
		String sql = "UPDATE Objective set name=? WHERE objective_id=?";
		jdbcTemplate.update(sql, objective.getName(), objective.getObjectiveId());
	}

	public void delete(Objective objective) {
		System.out.println("del on dao");
		String sql = "DELETE from Objective WHERE objective_id=?";
		jdbcTemplate.update(sql, objective.getObjectiveId());
	}
}
