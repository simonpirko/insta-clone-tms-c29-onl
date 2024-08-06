package core.service;

import connection.PostgresConnection;
import core.model.Account;
import core.repository.AccountRepository;
import exceptions.DatabaseConnectionException;
import storage.account.AccountStorage;
import storage.account.InDBAccountStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AccountService implements AccountRepository, AuthenticatorService {
    private static AccountService INSTANCE;
    private final AccountStorage storage = InDBAccountStorage.getInstance();

    private AccountService() {
    }

    public static AccountService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountService();
        }

        return INSTANCE;
    }


    @Override
    public void save(Account account) {
        storage.save(account);
    }

    @Override
    public Optional<Account> login(String identifier, String password) {
        try (Connection connection = PostgresConnection.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM accounts WHERE (username = ? OR email = ?) AND password = ?");
            preparedStatement.setString(1, identifier);
            preparedStatement.setString(2, identifier);
            preparedStatement.setString(3, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                return Optional.of(account);
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
        return Optional.empty();
    }
}

