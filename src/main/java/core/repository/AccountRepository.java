package core.repository;

import core.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends Repository<Account> {
    Optional<Account> getById(int id);
    Optional<Account> getByEmail(String email);
    Optional<Account> getByUsername(String username);

    List<Account> getAll();
}
