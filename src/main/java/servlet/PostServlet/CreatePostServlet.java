package servlet.PostServlet;

import core.model.Account;
import core.model.Post;
import core.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@WebServlet("/create-post")
public class CreatePostServlet extends HttpServlet {
    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/createPost.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part photoPart = req.getPart("photo");
        InputStream photoPartInputStream = photoPart.getInputStream();

        String description = req.getParameter("description");
        Account account = (Account) req.getSession().getAttribute("account");


        Post post = Post
                .builder()
                .account(account)
                .description(description)
                .filePathPhoto(Base64.getEncoder().encodeToString(photoPartInputStream.readAllBytes()))
                .createdAt(LocalDateTime.now())
                .build();

        Optional<Integer> addedPostId = postService.save(post);
        if (addedPostId.isPresent()) {
            Integer postId = addedPostId.get();
            post.setId(postId);

        }

        resp.sendRedirect("/");
    }
}

