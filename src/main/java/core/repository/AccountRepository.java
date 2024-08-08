package core.repository;

import core.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends Repository<Account> {
    Optional<Account> getById(int id);

    List<Account> getAll();
}
