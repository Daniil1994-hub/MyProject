package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("Test", "Testov", (byte) 25);
        userServiceImpl.saveUser("Ivan", "ivanov", (byte) 30);
        userServiceImpl.saveUser("Petr", "Petrov", (byte) 35);
        userServiceImpl.saveUser("Danil", "Danilov", (byte) 31);
        userServiceImpl.getAllUsers();
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();

    }

}
