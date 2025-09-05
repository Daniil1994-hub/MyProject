package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Connection connection = null;
    private static final String URL = "jdbc:mysql://localhost:3306/sys";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "test";


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

    public static void rollbackTransaction() {
        if (connection != null) {
            try {
                connection.rollback();
                System.out.println("Транзакция откачена");
            } catch (SQLException e) {
                System.err.println("Ошибка при откате транзакции: " + e.getMessage());
            }
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Соединение с базой данных закрыто");
            } catch (SQLException e) {
                System.err.println("Ошибка при закрытии соединения: " + e.getMessage());
            }
        }
    }

    public static boolean isConnectionOpen() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
