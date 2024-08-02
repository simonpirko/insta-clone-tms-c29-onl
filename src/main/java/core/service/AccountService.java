package core.service;

import core.model.Account;
import core.repository.AccountRepository;
import storage.account.AccountStorage;
import storage.account.InDBAccountStorage;

public class AccountService implements AccountRepository {
    private static AccountService INSTANCE;

    private AccountService() {
    }

    public static AccountService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountService();
        }

        return INSTANCE;
    }

    AccountStorage storage = InDBAccountStorage.getInstance();

    @Override
    public void save(Account account) {
        storage.save(account);
    }
}
