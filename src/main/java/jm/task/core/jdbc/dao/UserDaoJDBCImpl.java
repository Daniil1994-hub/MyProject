package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), last_name VARCHAR(45), age INT)";
        System.out.println(sqlCommand);
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sqlCommand);
        } catch (SQLException e) {
            Util.rollbackTransaction();
            System.err.println("Ошибка при создании таблицы: " + e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE users";
        System.out.println(sqlCommand);
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sqlCommand);
        } catch (SQLException e) {
            Util.rollbackTransaction();
            System.err.println("Ошибка при создании таблицы: " + e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = "insert into users (name, last_name, age) values (?, ?, ?);";
        System.out.println(sqlCommand);
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            Util.rollbackTransaction();
            System.out.println("Ошибка при сохранении пользователя: " + e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        String sqlCommand = "DELETE FROM users WHERE id = ?;";
        System.out.println(sqlCommand);
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            Util.rollbackTransaction();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sqlCommand = "SELECT * FROM users";
        System.out.println(sqlCommand);
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                int age = resultSet.getInt("age");
                User user = new User(name, lastName, (byte) age);
                list.add(user);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        String sqlCommand = "DELETE FROM users";
        System.out.println(sqlCommand);
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sqlCommand);
        } catch (SQLException e) {
            Util.rollbackTransaction();
            System.err.println("Ошибка при очистке таблицы: " + e.getMessage());
        }
    }
}
