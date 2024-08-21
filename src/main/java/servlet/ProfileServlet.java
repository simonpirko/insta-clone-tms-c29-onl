package servlet;

import core.model.Account;
import core.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@WebServlet(value = "/profile", name = "ProfileServlet")
public class ProfileServlet extends HttpServlet {
    private final AccountService accountService = AccountService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("yyyyyyyyy");

        Account account = (Account) req.getSession().getAttribute("account");
        String username = account.getUsername();
        req.setAttribute("userName", username);
        String name = account.getName();
        req.setAttribute("name", name);

        try {
            String stringAvatar = account.getAvatar();
            byte[] bytes = Base64.getUrlDecoder().decode(stringAvatar);
            req.setAttribute("avatar", bytes);
        }catch (Exception e){

        }
        getServletContext().getRequestDispatcher("/pages/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ssssss");
        Account account = (Account) req.getSession().getAttribute("account");
        String username = account.getUsername();
        req.setAttribute("userName", username);
        req.setAttribute("name", account.getName());
        getServletContext().getRequestDispatcher("/pages/profileEdit.jsp").forward(req, resp);
        //super.doPost(req, resp);
    }
}
