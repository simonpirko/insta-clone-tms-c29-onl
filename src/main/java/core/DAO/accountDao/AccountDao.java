package core.DAO.accountDao;

import core.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountDao<ID> {
    Optional<ID> save(Account account);
    Optional<Account> getById(ID id);
    Optional<Account> getByEmail(String email);
    Optional<Account> getByUsername(String username);

    List<Account> getAll();
    void update(Account account);
}
