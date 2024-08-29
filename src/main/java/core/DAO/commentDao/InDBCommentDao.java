package core.DAO.commentDao;

import connection.PostgresConnection;
import core.model.Account;
import core.model.Comment;
import core.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class InDBCommentDao implements CommentDao<Integer> {

    private static InDBCommentDao INSTANCE;

    public InDBCommentDao() {
    }

    public static InDBCommentDao getInstance() {
        if (INSTANCE == null)
            INSTANCE = new InDBCommentDao();

        return INSTANCE;
    }

    @Override
    public Optional<Integer> saveForPost(Comment comment) {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into \"post_comment\" (author_id, post_id, text) values (?, ?, ?)");

            preparedStatement.setInt(1, comment.getAccount().getId());
            preparedStatement.setInt(2, comment.getPost().getId());
            preparedStatement.setString(3, comment.getText());

            preparedStatement.execute();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next())
                    return Optional.of(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Comment> findAllForPostByAccount(Account account) {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from \"post_comment\" join \"post\" on \"post_comment\".post_id = \"post\".id where \"post_comment\".author_id = ?");
            preparedStatement.setInt(1, account.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Comment comment = Comment
                        .builder()
                        .account(account)
                        .text(resultSet.getString(3))
                        .build();

                Post post = Post
                        .builder()
                        .id(resultSet.getInt(2))
                        .account(account)
                        .filePathPhoto(
                                Base64.getEncoder().encodeToString(resultSet.getBytes(6))
                        )
                        .description(resultSet.getString(7))
                        .createdAt(resultSet.getTimestamp(8).toLocalDateTime())
                        .build();

                comment.setPost(post);

                return Optional.of(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


    @Override
    public Iterable<Comment> findAllByPost(Post post) {
        List<Comment> comments = new ArrayList<>();

        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from \"post_comment\" join \"human\" on \"post_comment\".author_id = \"human\".id  where \"post_comment\".post_id = ?");
            preparedStatement.setInt(1, post.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Comment comment = Comment
                        .builder()
                        .id(resultSet.getInt(1))
                        .post(post)
                        .text(resultSet.getString(4))
                        .build();

                Account account=Account
                        .builder()
                        .id(resultSet.getInt(5))
                        .name(resultSet.getString(6))
                        .surname(resultSet.getString(7))
                        .username(resultSet.getString(8))
                        .filePathPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(9)))
                        .email(resultSet.getString(10))
                        .password(resultSet.getString(11))
                        .build();


                comment.setAccount(account);

                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comments;
    }

    @Override
    public void removeForPostById(Integer id) {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from \"post_comment\" where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
