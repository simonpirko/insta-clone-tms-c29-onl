package exceptions.account;

public class InvalidAccountPasswordException extends Exception {
    public InvalidAccountPasswordException() {
        super("Invalid Password");
    }
}
