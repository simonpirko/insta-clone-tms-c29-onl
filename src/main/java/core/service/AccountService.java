package core.service;

import core.model.Account;
import core.DAO.accountDao.AccountDao;
import exceptions.account.InvalidAccountPasswordException;
import org.mindrot.jbcrypt.BCrypt;
import core.DAO.accountDao.InDBAccountDao;

import java.util.Optional;

public class AccountService {
    private static AccountService INSTANCE;
    private final AccountDao<Integer> accountDAO = InDBAccountDao.getInstance();

    private AccountService() {
    }

    public static AccountService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountService();
        }

        return INSTANCE;
    }

    public Optional<Integer> save(Account account) {
       return accountDAO.save(account);
    }

    public Optional<Account> login(String username, String password) throws InvalidAccountPasswordException {

        Optional<Account> accountByUsername = accountDAO.getByUsername(username);
        if (accountByUsername.isPresent()) {
            if (BCrypt.checkpw(password, accountByUsername.get().getPassword())) {
                return accountByUsername;
            } else {
                throw new InvalidAccountPasswordException();
            }
        }

        return Optional.empty();
    }

    public void update(Account account) {
        accountDAO.update(account);
    }
    public Optional<Account> findAccountByName(String accountName) {
        return accountDAO.getByUsername(accountName);
    }
}

