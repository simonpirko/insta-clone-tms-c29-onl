package servlet.PostServlet;

import core.model.Account;
import core.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deletefavorite")
public class DeleteFavorite extends HttpServlet {
    private final PostService postService = PostService.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account");

        postService.removeFavoriteByAccount(account);

        resp.sendRedirect("/allfavorite");
    }
}
