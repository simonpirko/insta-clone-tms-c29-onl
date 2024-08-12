package core.service;

import core.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static connection.PostgresConnection.getConnection;

public class PostService {

    public static void addPost(Post post) {
        String sql = "INSERT INTO posts (id, description, filepath, created_at) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, UUID.fromString(post.getId()));
            stmt.setString(2, post.getDescription());
            stmt.setString(3, post.getFilePath());
            stmt.setTimestamp(4, new java.sql.Timestamp(post.getCreatedAt().getTime()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Post> getAllPosts() {
        String sql = "SELECT id, description, filepath, created_at FROM posts";
        List<Post> posts = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Post post = new Post(
                        rs.getString("id"),
                        rs.getString("description"),
                        rs.getString("filepath"),
                        rs.getTimestamp("created_at")
                );
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }
}
