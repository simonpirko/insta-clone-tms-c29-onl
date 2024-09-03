package servlet.PostServlet;

import core.model.Account;
import core.model.Like;
import core.model.Post;
import core.service.LikeService;
import core.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {
    private final LikeService likeService = LikeService.getInstance();
    private final PostService postService = PostService.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Account account = (Account) req.getSession().getAttribute("account");
        int postId = Integer.parseInt(req.getParameter("post_id"));

        Optional<Post> postById = postService.findById(postId);
        if (postById.isPresent()) {
            Post post = postById.get();

            Like like = Like
                    .builder()
                    .post(post)
                    .account(account)
                    .createdAt(LocalDateTime.now())
                    .build();

            likeService.saveForPost(like);

            resp.sendRedirect("/account/viewpost?id=" + postId);
        }
    }
}
