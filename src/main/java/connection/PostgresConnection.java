package connection;

import lombok.Getter;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnection {
    @Getter
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    @Getter
    private static final String USER = "postgres";
    @Getter
    private static final String PASSWORD = "root";

    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}