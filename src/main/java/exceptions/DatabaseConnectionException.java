package exceptions;

public class DatabaseConnectionException extends RuntimeException {
    public DatabaseConnectionException(Throwable cause) {
        super("Ошибка при подключении к базе данных", cause);
    }
}
