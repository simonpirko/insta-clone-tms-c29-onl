package exceptions.account;

public class GetAccountByEmailException extends RuntimeException {
    public GetAccountByEmailException(Throwable cause) {
        super("Ошибка при получении аккаунта по Email", cause);
    }
}
