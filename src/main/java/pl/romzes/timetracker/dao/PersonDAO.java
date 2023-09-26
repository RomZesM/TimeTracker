package pl.romzes.timetracker.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.romzes.timetracker.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

	private final JdbcTemplate jdbcTemplate;
	@Autowired
	public PersonDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public List<Person> index() {
		return jdbcTemplate.query("select * from Person", new PersonRawMapper());
    }

    public Person show(int id) {
		return jdbcTemplate.query("select * from Person where id=?", new Object[]{id}, new PersonRawMapper()).stream().findAny().orElse(null);
		// return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
		String sqlStatement = "INSERT INTO Person values (1, ?, ?, ?)";
		jdbcTemplate.update(sqlStatement, person.getName(), person.getAge(), person.getEmail());

    }

    public void update(int id, Person updatedPerson) {

		jdbcTemplate.update("update Person set name=?, age=?, email=? where id=?" , updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);

    }

    public void delete(int id) {
		jdbcTemplate.update("delete from Person where id=?", id);
	}
}