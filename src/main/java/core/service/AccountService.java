package core.service;

import core.model.Account;
import core.DAO.AccountDAO;
import exceptions.account.InvalidAccountPasswordException;
import org.mindrot.jbcrypt.BCrypt;
import storage.account.InDBAccountDAO;

import java.util.Optional;

public class AccountService {
    private static AccountService INSTANCE;
    private final AccountDAO storage = InDBAccountDAO.getInstance();

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

    public Optional<Account> login(String usernameOrEmail, String password) throws InvalidAccountPasswordException {
        Optional<Account> accountByUsername = storage.getByUsername(usernameOrEmail);
        if (accountByUsername.isPresent()) {
            if (BCrypt.checkpw(password, accountByUsername.get().getPassword())) {
                return accountByUsername;
            } else {
                throw new InvalidAccountPasswordException();
            }
        }
        Optional<Account> accountByEmail = storage.getByEmail(usernameOrEmail);
        if (accountByEmail.isPresent()) {
            if (BCrypt.checkpw(password, accountByEmail.get().getPassword())) {
                return accountByEmail;
            } else {
                throw new InvalidAccountPasswordException();
            }
        }
        return Optional.empty();
    }

    public void saveProfile(Account account) { storage.saveProfile(account);}

    public void saveAvatar(int id, byte[] bytes){storage.saveAvatar(id, bytes);}
}

