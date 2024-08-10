package core.service;

import core.model.Account;
import core.repository.AccountRepository;
import exceptions.account.InvalidPasswordException;
import org.mindrot.jbcrypt.BCrypt;
import storage.account.InDBAccountStorage;

import java.util.Optional;

public class AccountService {
    private static AccountService INSTANCE;
    private final AccountRepository storage = InDBAccountStorage.getInstance();

    private AccountService() {
    }

    public static AccountService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountService();
        }

        return INSTANCE;
    }

    public void save(Account account) {
        storage.save(account);
    }

    public Optional<Account> login(String usernameOrEmail, String password) throws InvalidPasswordException {
        Optional<Account> accountByUsername = storage.getByUsername(usernameOrEmail);
        if (accountByUsername.isPresent()) {
            if (BCrypt.checkpw(password, accountByUsername.get().getPassword())) {
                return accountByUsername;
            } else {
                throw new InvalidPasswordException();
            }
        }
        Optional<Account> accountByEmail = storage.getByEmail(usernameOrEmail);
        if (accountByEmail.isPresent()) {
            if (BCrypt.checkpw(password, accountByEmail.get().getPassword())) {
                return accountByEmail;
            } else {
                throw new InvalidPasswordException();
            }
        }
        return Optional.empty();
    }
}

