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
import java.util.List;

@WebServlet("/allfavorite")
public class AllFavoriteServlet  extends HttpServlet {

    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account");
        List<Post> allFavorite = postService.findAllFavorite(account);

        req.setAttribute("allFavorite", allFavorite);

        getServletContext().getRequestDispatcher("/pages/favorite.jsp").forward(req, resp);
    }
}


