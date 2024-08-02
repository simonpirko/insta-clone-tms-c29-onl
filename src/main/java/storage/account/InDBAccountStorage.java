package storage.account;

import connection.PostgresConnection;
import core.model.Account;
import exceptions.GetAccountByIdException;
import exceptions.GetAllAccountsException;
import exceptions.SaveAccountException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InDBAccountStorage implements AccountStorage {
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
        try (Connection connection = PostgresConnection.getInstance().getConnection()) {
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
        try (Connection connection = PostgresConnection.getInstance().getConnection()) {
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
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new GetAccountByIdException(e);
        }

        return Optional.empty();
    }

    public List<Account> getAll() {
        try (Connection connection = PostgresConnection.getInstance().getConnection()) {
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

            preparedStatement.execute();
            return accounts;
        } catch (SQLException e) {
            throw new GetAllAccountsException(e);
        }
    }
}
