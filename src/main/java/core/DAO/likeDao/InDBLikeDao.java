package core.DAO.likeDao;

import connection.PostgresConnection;
import core.model.Account;
import core.model.Like;
import core.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class InDBLikeDao implements LikeDAO<Integer> {
    private static InDBLikeDao instance;

    public InDBLikeDao() {
    }

    public static InDBLikeDao getInstance() {
        if (instance == null) {
            instance = new InDBLikeDao();
        }
        return instance;
    }

    @Override
    public Optional<Integer> saveForPost(Like like) {
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into \"post_like\" (author_id, post_id, created_at) values (?, ?, ?)")) {

            preparedStatement.setInt(1, like.getAccount().getId());
            preparedStatement.setInt(2, like.getPost().getId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(like.getCreatedAt()));

            preparedStatement.execute();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next())
                    return Optional.of(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Like> findByAccountAndPost(Account account, Post post) {
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from \"post_like\" where author_id = ? and post_id = ?")) {

            preparedStatement.setInt(1, account.getId());
            preparedStatement.setInt(2, post.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Like like = Like
                        .builder()
                        .account(account)
                        .post(post)
                        .createdAt(resultSet.getTimestamp(3).toLocalDateTime())
                        .build();

                return Optional.of(like);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
    public List<Like> findAllForPost() {
        List<Like> allLikes = new ArrayList<>();

        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("""
            select h.*, p.id, p.photo, p.description, p.created_at, pl.created_at from "post_like" pl
            join "human" h
            on pl.author_id = h.id
            join post p
            on pl.post_id = p.id""")) {

            ResultSet resultSet = preparedStatement.executeQuery();

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


                Post post = Post
                        .builder()
                        .id(resultSet.getInt(8))
                        .account(account)
                        .filePathPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(9)))
                        .description(resultSet.getString(10))
                        .createdAt(resultSet.getTimestamp(11).toLocalDateTime())
                        .build();

                Like like = Like
                        .builder()
                        .account(account)
                        .post(post)
                        .createdAt(resultSet.getTimestamp(12).toLocalDateTime())
                        .build();

                allLikes.add(like);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allLikes;
    }

    @Override
    public void removeByAccountAndPost(Account account, Post post) {
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from \"post_like\" where author_id = ? and post_id = ?")) {

            preparedStatement.setInt(1, account.getId());
            preparedStatement.setInt(2, post.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int findAllByPost(Post post) {
        int countOfLike = 0;

        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement( "select count (*) from \"post_like\" where post_id = ?");
            preparedStatement.setInt(1, post.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                countOfLike = resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countOfLike;
    }
}

