package connection;

import exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {
    private static PostgresConnection INSTANCE;
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    private PostgresConnection() {
    }

    public static PostgresConnection getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PostgresConnection();
        }

        return INSTANCE;
    }

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new DatabaseConnectionException(e);
        }
    }
}
