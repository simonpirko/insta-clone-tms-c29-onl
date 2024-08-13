package exceptions.account;

public class GetAccountByUsernameException extends RuntimeException {
    public GetAccountByUsernameException(Throwable cause) {
        super("Ошибка при получении аккаунта по Username", cause);
    }
}
