package exceptions.account;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException() {
        super("Invalid Password");
    }
}
