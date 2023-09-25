package pl.romzes.timetracker.dao;

import org.springframework.stereotype.Component;
import pl.romzes.timetracker.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

	private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "barabunga787";

	private Connection connection;
	{//init connection in static section
		try {
			Class.forName("org.postgresql.Driver"); //load postgres driver into memory, it  doesn't loaded automaticly
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); //get connection for DB with suitable driver
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

     public List<Person> index() {
       List<Person> peopleList = new ArrayList<>();
		try {
			Statement statement = connection.createStatement(); //statement interface for creating ONE sql query to database
			ResultSet resultSet = statement.executeQuery("select * from Person");//result set => object containing answer from db (in some sort of table)
			while(resultSet.next()){ //iterate through this statement with intern method
				Person person = new Person();
				person.setId(resultSet.getInt("id"));
				person.setName(resultSet.getString("name"));
				person.setAge(resultSet.getInt("age"));
				person.setEmail(resultSet.getString("email"));
				peopleList.add(person);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return peopleList;
    }

    public Person show(int id) {
		Person person = null;
		String sql = "select id, name, age, email from Person where id=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id); //insert id into query

			ResultSet resultSet = statement.executeQuery();
			resultSet.next(); //important to do this once, even if you wait only one string
			person = new Person();
			person.setId(resultSet.getInt("id"));
			person.setName(resultSet.getString("name"));
			person.setAge(resultSet.getInt("age"));
			person.setEmail(resultSet.getString("email"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return person;
		// return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
		String sqlStatement = "INSERT INTO Person values (1, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement =
					connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, person.getName());
			preparedStatement.setInt(2, person.getAge());
			preparedStatement.setString(3, person.getEmail());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

    }

    public void update(int id, Person updatedPerson) {

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update Person set name=?, age=?, email=? where id=?");
			preparedStatement.setString(1, updatedPerson.getName());
			preparedStatement.setInt(2, updatedPerson.getAge());
			preparedStatement.setString(3, updatedPerson.getEmail());
			preparedStatement.setInt(4, id);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}



    }

    public void delete(int id) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("delete from Person where id=?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		//people.removeIf(p -> p.getId() == id);
    }



}