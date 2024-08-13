package exceptions.account;

public class AccountNotFoundException extends Exception{
    public AccountNotFoundException(){
        super("Account not found");
    }
}
