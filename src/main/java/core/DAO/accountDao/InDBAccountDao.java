package core.DAO.accountDao;

import connection.PostgresConnection;
import core.model.Account;
import exceptions.account.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class InDBAccountDao implements AccountDao<Integer> {
    private static InDBAccountDao INSTANCE;

    private InDBAccountDao() {
    }

    public static InDBAccountDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InDBAccountDao();
        }

        return INSTANCE;
    }

    public Optional<Integer> save(Account account) {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO \"human\" (name, surname, username, FilePathPhoto, email, password) VALUES (?, ?, ?, ?, ?,?)");

            preparedStatement.setString(1, account.getName());
            preparedStatement.setString(2, account.getSurname());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.setBytes(4, Base64.getDecoder().decode(account.getFilePathPhoto()));
            preparedStatement.setString(5, account.getEmail());
            preparedStatement.setString(6, account.getPassword());

            preparedStatement.execute();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next())
                    return Optional.of(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new SaveAccountException(e);
        }
        return Optional.empty();
    }

    public Optional<Account> getById(Integer id) {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement( "SELECT * FROM \"human\"  WHERE \"human\".id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Account account = Account
                        .builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .username(resultSet.getString(4))
                        .filePathPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(5)))
                        .email(resultSet.getString(6))
                        .password(resultSet.getString(7))
                        .build();
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"human\" WHERE \"human\".username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Account account = Account
                        .builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .username(resultSet.getString(4))
                        .filePathPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(5)))
                        .email(resultSet.getString(6))
                        .password(resultSet.getString(7))
                        .build();
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"human\" WHERE \"human\".email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Account account = Account
                        .builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .username(resultSet.getString(4))
                        .filePathPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(5)))
                        .email(resultSet.getString(6))
                        .password(resultSet.getString(7))
                        .build();
                return Optional.of(account);
            }
        } catch (SQLException e) {
            throw new GetAccountByEmailException(e);
        }
        return Optional.empty();
    }

    public List<Account> getAll() {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM human");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while (resultSet.next()) {
                Account account = Account
                        .builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .username(resultSet.getString(4))
                        .filePathPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(5)))
                        .email(resultSet.getString(6))
                        .password(resultSet.getString(7))
                        .build();
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            throw new GetAllAccountsException(e);
        }
    }

    @Override
    public void update(Account account) {
        try (Connection connection = PostgresConnection.getConnection()){
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE human SET name = ?, surname = ?, username = ?, FilePathPhoto = ?, email = ?, password = ? WHERE id = ?");

            preparedStatement.setString(1, account.getName());
            preparedStatement.setString(2, account.getSurname());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.setBytes(4, Base64.getDecoder().decode(account.getFilePathPhoto()));
            preparedStatement.setString(5, account.getEmail());
            preparedStatement.setString(6, account.getPassword());
            preparedStatement.setInt(7, account.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
