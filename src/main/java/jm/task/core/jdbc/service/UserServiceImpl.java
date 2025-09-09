package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDaoJDBCImpl userDaoJDBC;


    public UserServiceImpl()  {
        this.userDaoJDBC = new UserDaoJDBCImpl();
    }

    @Override
    public void createUsersTable() {
        userDaoJDBC.createUsersTable();
        System.out.println("Таблица 'users' создана или уже существует");
    }

    @Override
    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();
        System.out.println("Таблица 'users' удалена");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name, lastName, age);
        System.out.println("User с именем - " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);
        System.out.println("User с id - " + id + " удален из базы данных");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = userDaoJDBC.getAllUsers();
        for(User user: list){
            System.out.println(user);
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();
        System.out.println("Таблица 'users' очищена");
    }
}
