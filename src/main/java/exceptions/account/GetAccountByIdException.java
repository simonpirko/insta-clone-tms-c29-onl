package exceptions.account;

public class GetAccountByIdException extends RuntimeException {
    public GetAccountByIdException(Throwable cause) {
        super("Ошибка при получении аккаунта по ID", cause);
    }
}
