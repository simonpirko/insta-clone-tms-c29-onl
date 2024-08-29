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
import java.util.Optional;

@WebServlet("/account/viewpost")
public class ViewPostServlet extends HttpServlet {
    private final PostService postService = PostService.getInstance();
    private final LikeService likeService = LikeService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int postId = Integer.valueOf(req.getParameter("id"));
        Optional<Post> postById = postService.findById(postId);

        if (postById.isPresent()) {
            Post post = postById.get();

            Account account = (Account) req.getSession().getAttribute("account ");

            Optional<Like> currentUserLikeStatus = likeService.findByAccountAndPost(account, post);

            if (currentUserLikeStatus.isPresent())
                req.setAttribute("like", true);
            else
                req.setAttribute("like", false);

            req.setAttribute("post", post);

            getServletContext().getRequestDispatcher("/pages/viewpost.jsp").forward(req, resp);
        }
    }
}
