package servlet;

import core.model.Post;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static connection.PostgresConnection.getConnection;

@WebServlet("/viewPosts")
public class ViewPostsServlet extends HttpServlet {

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM posts");

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Post> posts = new ArrayList<>();

        while (resultSet.next()) {
            Post post = new Post();
            post.setDescription(resultSet.getString("description"));
            post.setFilePath(resultSet.getBytes("filepath"));
            post.setCreatedAt(resultSet.getTimestamp("created_at"));
            posts.add(post);
        }

        req.setAttribute("posts", posts);

        req.getRequestDispatcher("/pages/viewPosts.jsp").forward(req, resp);
    }
}