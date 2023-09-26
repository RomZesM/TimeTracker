package pl.romzes.timetracker.dao;

import org.springframework.jdbc.core.RowMapper;
import pl.romzes.timetracker.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRawMapper implements RowMapper<Person> {
	@Override
	public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		Person person = new Person();

		person.setId(resultSet.getInt("id"));
		person.setName(resultSet.getString("name"));
		person.setAge(resultSet.getInt("age"));
		person.setEmail(resultSet.getString("email"));

		return person;
	}
}
