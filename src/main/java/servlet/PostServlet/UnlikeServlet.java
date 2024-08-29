package servlet.PostServlet;

import core.model.Account;
import core.model.Post;
import core.service.LikeService;
import core.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/unlike")
public class UnlikeServlet extends HttpServlet {
    private final LikeService likeService = LikeService.getInstance();
    private final PostService postService = PostService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account ");
        int post_id = Integer.parseInt(req.getParameter("post_id"));

        Optional<Post> postById = postService.findById((post_id));
        if (postById.isPresent()) {
            likeService.removeByAccountAndPost(account, postById.get());
            resp.sendRedirect("/user/viewpost?id=" + post_id);
        }
    }
}
