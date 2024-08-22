package servlet;

import core.model.Account;
import core.service.AccountService;
import exceptions.account.AccountNotFoundException;
import exceptions.account.InvalidAccountPasswordException;
import storage.account.InDBAccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/login", name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    private final AccountService service = AccountService.getInstance();
    private final InDBAccountDAO inDBAccountDAO = InDBAccountDAO.getInstance();

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
        try {
            Optional<Account> login = service.login(usernameOrEmail, password);
            if (login.isPresent()) {
                Account account = login.get();
                req.getSession().setAttribute("account", account);

                ProfileEditServlet.getCurrentAccount(req, inDBAccountDAO);

                resp.sendRedirect("/home");
            } else {
                throw new AccountNotFoundException();
            }
        } catch (InvalidAccountPasswordException | AccountNotFoundException e) {
            req.setAttribute("errorMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        }
    }
}