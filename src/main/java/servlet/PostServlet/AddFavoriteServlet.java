package servlet.PostServlet;

import core.model.Account;
import core.model.Post;
import core.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/addfavorite")
public class AddFavoriteServlet extends HttpServlet {
    private final PostService postService = PostService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account");
        int postId = Integer.parseInt(req.getParameter("post_id"));
        Optional<Post> favoritePost = postService.findById(postId);

        favoritePost.ifPresent(post -> postService.saveFavorite(account, post));

        resp.sendRedirect("/account/viewpost?id=" + postId);
    }
}
