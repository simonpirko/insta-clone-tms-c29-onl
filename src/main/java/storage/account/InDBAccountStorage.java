package storage.account;

import connection.PostgresConnection;
import core.model.Account;
import core.repository.AccountRepository;
import exceptions.account.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InDBAccountStorage implements AccountRepository {
    private static InDBAccountStorage INSTANCE;

    private InDBAccountStorage() {
    }

    public static InDBAccountStorage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InDBAccountStorage();
        }

        return INSTANCE;
    }

    public void save(Account account) {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO accounts VALUES (default, ?, ?, ?)");
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SaveAccountException(e);
        }
    }

    public Optional<Account> getById(int id) {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Account account = new Account();
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setEmail(resultSet.getString("email"));
                account.setId(id);
                return Optional.of(account);
            }
        } catch (SQLException e) {
            throw new GetAccountByIdException(e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Account> getByUsername(String username) {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Account account = new Account();
                account.setUsername(username);
                account.setPassword(resultSet.getString("password"));
                account.setEmail(resultSet.getString("email"));
                account.setId(resultSet.getInt("id"));
                return Optional.of(account);
            }
        } catch (SQLException e) {
            throw new GetAccountByUsernameException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Account> getByEmail(String email) {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Account account = new Account();
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setEmail(email);
                account.setId(resultSet.getInt("id"));
                return Optional.of(account);
            }
        } catch (SQLException e) {
            throw new GetAccountByEmailException(e);
        }
        return Optional.empty();
    }

    public List<Account> getAll() {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while (resultSet.next()) {
                Account account = new Account();
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setEmail(resultSet.getString("email"));
                account.setId(resultSet.getInt("id"));
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            throw new GetAllAccountsException(e);
        }
    }
}
