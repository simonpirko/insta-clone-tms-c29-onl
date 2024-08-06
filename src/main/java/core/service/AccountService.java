package core.service;

import connection.PostgresConnection;
import core.model.Account;
import core.repository.AccountRepository;
import exceptions.DatabaseConnectionException;
import storage.account.InDBAccountStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public Optional<Account> login(String identifier, String password) {
        try (Connection connection = PostgresConnection.getConnection()) {
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

