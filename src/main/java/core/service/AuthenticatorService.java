package core.service;

import core.model.Account;

import java.util.Optional;

public interface AuthenticatorService {
    public Optional<Account> login(String identifier, String password);
}
