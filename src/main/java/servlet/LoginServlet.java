package servlet;

import core.model.Account;
import core.service.AccountService;
import exceptions.account.AccountNotFoundException;
import exceptions.account.InvalidAccountPasswordException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final AccountService accountService = AccountService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        req.setAttribute("username", username);
        getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usernameOrEmail = req.getParameter("username_or_email");
        String password = req.getParameter("password");
        Optional<Account> accountByName = accountService.findAccountByName(usernameOrEmail);

        if (accountByName.isPresent()) {
            Account account = accountByName.get();

            if (account.getPassword().equals(password)) {
                req.getSession().setAttribute("account", account);

                resp.sendRedirect("/");
            } else {
                req.setAttribute("passwordStatus", "Invalid password");
                getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("usernameStatus", "Invalid username");
            getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        }
//        try {
//            Optional<Account> login = accountService.login(usernameOrEmail, password);
//            if (login.isPresent()) {
//                Account account = login.get();
//                req.getSession().setAttribute("account", account);
//                resp.sendRedirect("/");
//            } else {
//                throw new AccountNotFoundException();
//            }
//        } catch (InvalidAccountPasswordException | AccountNotFoundException e) {
//            req.setAttribute("errorMessage", e.getMessage());
//            getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
//        }
    }
}
