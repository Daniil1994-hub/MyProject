package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static Connection connection = null;
    private static final String URL = "jdbc:mysql://localhost:3306/sys";
    private static String USERNAME = "root";
    private static final String PASSWORD = "test";


    private Util (){}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Соединение с базой данных установлено");

            } catch (SQLException e) {
                throw new SQLException("Драйвер базы данных не найден", e);
            }
        }
        return connection;
    }

    private static SessionFactory sessionFactory; // КЛАСС КОТОРЫЙ СОЗДАЕТ СОЕДИНЕНИЕ "ФАБРИКА СОЕДИНЕНИЯ"
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration(); // СОЗДАЕМ КОНФИГУРАЦИЮ - НАСТРОЙКИ

                // Настройка свойств Hibernate
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/sys?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "test");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);

                // Добавление класса сущности
                configuration.addAnnotatedClass(User.class);

                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e){
            System.out.println(e);
        }
    }

    // реализуйте настройку соеденения с БД
}
