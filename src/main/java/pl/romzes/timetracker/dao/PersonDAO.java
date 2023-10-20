package pl.romzes.TimeTracker.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.romzes.TimeTracker.models.Person;

import java.util.List;


@Component
public class PersonDAO {

	private final JdbcTemplate jdbcTemplate;
	@Autowired
	public PersonDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public List<Person> index() {
		return jdbcTemplate.query("select * from Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
		return jdbcTemplate.query("select * from Person where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
		// return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
		String sqlStatement = "INSERT INTO Person (name, age, email) values (?, ?, ?)";
		jdbcTemplate.update(sqlStatement, person.getName(), person.getAge(), person.getEmail());

    }

    public void update(int id, Person updatedPerson) {

		jdbcTemplate.update("update Person set name=?, age=?, email=? where id=?" , updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);

    }

    public void delete(int id) {
		jdbcTemplate.update("delete from Person where id=?", id);
	}
}