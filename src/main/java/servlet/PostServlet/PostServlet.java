package servlet.PostServlet;

import core.model.Post;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

import static connection.PostgresConnection.getConnection;

@WebServlet("/createPost")
@MultipartConfig
public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/createPost.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Post post = new Post();

        String description = req.getParameter("description");
        post.setDescription(description);

        Part filePath = req.getPart("file");
        InputStream inputStream = filePath.getInputStream();

        Connection connection = getConnection();

        String sql = "INSERT INTO posts (id, description, filepath, created_at) VALUES (default, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, post.getDescription());
        preparedStatement.setBytes(2, inputStream.readAllBytes());
        preparedStatement.setTimestamp(3, new Timestamp(new Date().getTime()));
        preparedStatement.executeUpdate();

        resp.sendRedirect("/pages/viewPosts");
    }
}