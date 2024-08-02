package exceptions;

public class GetAllAccountsException extends RuntimeException {
    public GetAllAccountsException(Throwable cause) {
        super("Ошибка при получении списка аккаунтов", cause);
    }
}
