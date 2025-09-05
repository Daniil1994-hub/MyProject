package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), last_name VARCHAR(45), age INT)";
        System.out.println(sqlCommand);
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "test")) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }

    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE users";
        System.out.println(sqlCommand);
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "test")) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = String.format("insert into users (name, last_name, age) values ('%s', '%s', %d);", name, lastName, age);
        System.out.println(sqlCommand);
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "test")) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println("User с именем - " + name + " добавлен в базу данных");


    }

    public void removeUserById(long id) {
        String sqlCommand = String.format("DELETE FROM users WHERE id = %d;", id);
        System.out.println(sqlCommand);
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "test")) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println("User с id - " + id + " удален из базы данных");

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sqlCommand = "SELECT * FROM users";
        System.out.println(sqlCommand);
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "test")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand); // резалтсет хранит данные таблицы полученные благодаря комманде селект фром юзерс
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                int age = resultSet.getInt("age");
                User user = new User(name, lastName, (byte)age);
                list.add(user);
                System.out.println(user.toString());
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return list;

    }

    public void cleanUsersTable() {
        String sqlCommand = "DELETE FROM users";
        System.out.println(sqlCommand);
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "test")) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }
}
