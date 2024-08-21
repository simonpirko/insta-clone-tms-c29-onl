package storage.account;

import connection.PostgresConnection;
import core.model.Account;
import core.DAO.AccountDAO;
import exceptions.account.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class InDBAccountDAO implements AccountDAO {
    private static InDBAccountDAO INSTANCE;

    private InDBAccountDAO() {
    }

    public static InDBAccountDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InDBAccountDAO();
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
                account.setName(resultSet.getString("name"));
                account.setWebsite(resultSet.getString("website"));
                account.setAbout(resultSet.getString("about"));
                account.setGender(resultSet.getString("gender"));

                byte[] bytes = resultSet.getBytes("avatar");
                account.setAvatar(Base64.getEncoder().encodeToString(bytes));


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
                account.setName(resultSet.getString("name"));
                account.setWebsite(resultSet.getString("website"));
                account.setAbout(resultSet.getString("about"));
                account.setGender(resultSet.getString("gender"));
                try {
                    byte[] bytes = resultSet.getBytes("avatar");
                    account.setAvatar(Base64.getEncoder().encodeToString(bytes));
                } catch (Exception e) {

                }



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
                account.setName(resultSet.getString("name"));
                account.setWebsite(resultSet.getString("website"));
                account.setAbout(resultSet.getString("about"));
                account.setGender(resultSet.getString("gender"));
                byte[] bytes = resultSet.getBytes("avatar");
                account.setAvatar(Base64.getEncoder().encodeToString(bytes));


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

    public void saveProfile(Account account) {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE accounts SET name=?, website=?, about=?, avatar=?, gender=? WHERE id = ?");
            preparedStatement.setString(1, account.getName());
            preparedStatement.setString(2, account.getWebsite());
            preparedStatement.setString(3, account.getAbout());

            String stringAvatar = account.getAvatar();
            byte[] bytes = Base64.getDecoder().decode(stringAvatar);
            preparedStatement.setBytes(4, bytes);
            preparedStatement.setString(5, account.getGender());
            preparedStatement.setInt(6, account.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SaveAccountException(e);
        }
    }

    public void saveAvatar(int id, byte[] bytes) {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE accounts SET avatar=? WHERE id = ?");
            preparedStatement.setBytes(1, bytes);
            preparedStatement.setInt(6, id);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new SaveAccountException(e);
        }
    }
}
