package storage.account;

import core.model.Account;
import storage.Storage;

import java.util.List;
import java.util.Optional;

public interface AccountStorage extends Storage<Account> {
    Optional<Account> getById(int id);

    List<Account> getAll();
}
