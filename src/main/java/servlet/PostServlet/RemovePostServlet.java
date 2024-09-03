package servlet.PostServlet;

import core.model.Account;
import core.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/account/remove_post")
public class RemovePostServlet extends HttpServlet {
    PostService postService = PostService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account ");
        int postId = Integer.parseInt(req.getParameter("postId"));

        if (postService.findById(postId) != null) {
            postService.removeById(postId);

        }

        resp.sendRedirect("/account/profile?username=" + account.getUsername());
    }
}
