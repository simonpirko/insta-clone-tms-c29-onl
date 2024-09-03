package servlet.PostServlet;

import core.model.Account;
import core.model.Post;
import core.service.AccountService;
import core.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/account/profile")
public class ProfileServlet extends HttpServlet {
    private final AccountService accountService = AccountService.getInstance();
    private final PostService postService = PostService.getInstance();


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("username");
        Optional<Account> accountByName = accountService.findAccountByName(userName);

        if (accountByName.isPresent()) {
            Account account = accountByName.get();

            List<Post> posts = postService.findAllByUser(account);
            req.setAttribute("userPosts", posts);
            req.setAttribute("viewedAccount", account);


        }

        Account sessionAccount = (Account) req.getSession().getAttribute("account");
        req.setAttribute("account", sessionAccount);
        getServletContext().getRequestDispatcher("/pages/profile.jsp").forward(req, resp);
    }
}