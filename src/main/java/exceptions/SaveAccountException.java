package exceptions;

public class SaveAccountException extends RuntimeException {
    public SaveAccountException(Throwable cause) {
        super("Ошибка при сохранении аккаунта", cause);
    }
}
