package servlet;

import core.model.Post;
import core.service.PostService;
import core.utils.FileUploadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@WebServlet("/createPost")
@MultipartConfig
public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/pages/createPost.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String description = request.getParameter("description");
        Part filePart = request.getPart("file");

        String uploadDir = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDirFile = new File(uploadDir);

        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        String filePath = FileUploadUtil.saveFile(uploadDir, filePart);
        Post post = new Post(UUID.randomUUID().toString(), description, filePath, new Date());
        PostService.addPost(post);

        response.sendRedirect("viewPosts.jsp");
    }
}