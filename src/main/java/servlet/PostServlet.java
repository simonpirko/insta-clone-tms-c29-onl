package servlet;

import core.model.Post;
import core.service.PostService;
import core.utils.FileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet("/createPost")
@MultipartConfig
public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/createPost.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pattern = "dd.MM.yyyy HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String formattedDate = sdf.format(new Date());

        Part filePart = req.getPart("file");
        String uploadDir = getServletContext().getRealPath("/uploads");

        final String filePath;

        String description = req.getParameter("description");

        filePath = FileUpload.saveFile(uploadDir, filePart);
        Post post = new Post(UUID.randomUUID().toString(), description, filePath, formattedDate);
        PostService.addPost(post);

        List<Post> posts = PostService.getAllPosts();
        req.setAttribute("posts", posts);

        getServletContext().getRequestDispatcher("/pages/viewPosts.jsp").forward(req, resp);
    }
}