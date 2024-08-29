package core.DAO.postDao;

import connection.PostgresConnection;
import core.model.Account;
import core.model.Page;
import core.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class InDBPostDao implements PostDao<Integer> {
    private static InDBPostDao instance;

    public InDBPostDao() {
    }

    public static InDBPostDao getInstance() {
        if (instance == null)
            instance = new InDBPostDao();

        return instance;
    }

    @Override
    public Optional<Integer> save(Post post) {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into post(author_id, photo, description, created_at) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, post.getAccount().getId());
            preparedStatement.setBytes(2, Base64.getDecoder().decode(post.getFilePathPhoto()));
            preparedStatement.setString(3, post.getDescription());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(post.getCreatedAt()));

            preparedStatement.execute();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    return Optional.of(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Post> findById(Integer id) {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from post join human on post.author_id = human.id where post.id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Post post = Post
                        .builder()
                        .id(resultSet.getInt(1))
                        .filePathPhoto((Base64.getEncoder().encodeToString(resultSet.getBytes(3))))
                        .description(resultSet.getString(4))
                        .createdAt(resultSet.getTimestamp(5).toLocalDateTime())
                        .build();

                Account account= Account
                        .builder()
                        .id(resultSet.getInt(6))
                        .name(resultSet.getString(7))
                        .surname(resultSet.getString(8))
                        .username(resultSet.getString(9))
                        .filePathPhoto((Base64.getEncoder().encodeToString(resultSet.getBytes(10)))
                        .email(resultSet.getString(11))
                        .password(resultSet.getString(12))
                        .build());

                post.setAccount(account);

                return Optional.of(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Post> findAllByAccount (Account account) {
        List<Post> allPostsByUser = new ArrayList<>();

        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement( "select * from post join human on post.author_id = human.id where post.author_id = ?");
            preparedStatement.setInt(1, account.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post = Post
                        .builder()
                        .id(resultSet.getInt(1))
                        .account(account)
                        .filePathPhoto((Base64.getEncoder().encodeToString(resultSet.getBytes(3))))
                        .description(resultSet.getString(4))
                        .createdAt(resultSet.getTimestamp(5).toLocalDateTime())
                        .build();

                allPostsByUser.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allPostsByUser;
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();

        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from post join human on post.author_id = human.id");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Post post = Post
                        .builder()
                        .id(resultSet.getInt(1))
                        .filePathPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(3)))
                        .description(resultSet.getString(4))
                        .createdAt(resultSet.getTimestamp(5).toLocalDateTime())
                        .build();

                Account account=Account
                        .builder()
                        .id(resultSet.getInt(6))
                        .name(resultSet.getString(7))
                        .surname(resultSet.getString(8))
                        .username(resultSet.getString(9))
                        .filePathPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(10)))
                        .email(resultSet.getString(11))
                        .password(resultSet.getString(12))
                        .build();

                post.setAccount(account);

                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Iterable<Post> findAllWithPageable(Page page) {
        List<Post> postsForPageList = new ArrayList<>();

        try {
            Connection connection = PostgresConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("""
            SELECT * FROM post p  
            JOIN human h  
            ON p.author_id = h.id   
            LIMIT ? OFFSET ?
            """);

            preparedStatement.setInt(1, page.getLimit());
            preparedStatement.setInt(2, page.getOffset());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Post post = Post
                        .builder()
                        .id(resultSet.getInt(1))
                        .filePathPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(3)))
                        .description(resultSet.getString(4))
                        .createdAt(resultSet.getTimestamp(5).toLocalDateTime())
                        .build();

                Account account = Account.builder()
                        .id(resultSet.getInt(6))
                        .name(resultSet.getString(7))
                        .surname(resultSet.getString(8))
                        .username(resultSet.getString(9))
                        .filePathPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(10)))
                        .build();

                post.setAccount(account);

                postsForPageList.add(post);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return postsForPageList;
    }

    @Override
    public int countAll() {
        int sum = 0;

        try (Connection connection =PostgresConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS total FROM post")) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                sum = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sum;
    }

    @Override
    public void removeById(Integer id) {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM post WHERE id = ?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeByAccount(Account account) {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Post WHERE id = ?");
            preparedStatement.setInt(1, account.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePost(Integer id, Post newPost) {
        Optional<Post> post = findById(id);

        if (post.isPresent()) {
            try (Connection connection = PostgresConnection.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Post SET photo = ?, description = ?, created_at = ? WHERE id = ?");
                preparedStatement.setBytes(1, Base64.getDecoder().decode(newPost.getFilePathPhoto()));
                preparedStatement.setString(2, newPost.getDescription());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(newPost.getCreatedAt()));
                preparedStatement.setInt(4, id);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<Integer> saveFavorite(Account account, Post post) {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into \"favorite\" (user_id, post_id) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, account.getId());
            preparedStatement.setInt(2, post.getId());

            preparedStatement.executeUpdate();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next())
                    return Optional.of(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Post> findFavorite(Account account) {
        List<Post> allFavoritePosts = new ArrayList<>();

        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from \"favorite\" join \"post\" on \"favorite\".post_id = \"post\".id where \"favorite\".user_id = ?");
            preparedStatement.setInt(1, account.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post = buildPostEntityFromResultSet(resultSet);
                allFavoritePosts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allFavoritePosts;
    }

    @Override
    public void removeFavoriteByAccount(Account account) {
        try (Connection connection = PostgresConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from \"favorite\" where user_id = ?");
            preparedStatement.setInt(1, account.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Post buildPostEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Post post = Post
                .builder()
                .id(resultSet.getInt(4))
                .filePathPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(5)))
                .description(resultSet.getString(6))
                .createdAt(resultSet.getTimestamp(7).toLocalDateTime())
                .build();

        Account account = Account
                .builder()
                .id(resultSet.getInt(8))
                .name(resultSet.getString(9))
                .surname(resultSet.getString(10))
                .username(resultSet.getString(11))
                .filePathPhoto(Base64.getEncoder().encodeToString(resultSet.getBytes(12)))
                .email(resultSet.getString(13))
                .password(resultSet.getString(14))
                .build();

        post.setAccount(account);

        return post;
    }
}
