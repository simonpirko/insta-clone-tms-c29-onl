package servlet;

import core.model.Account;
import core.service.AccountService;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/registration", name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private final AccountService accountService = AccountService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        String email = req.getParameter("email");

        try {
            accountService.save(new Account(username, hashPassword, email));
            resp.sendRedirect("/login?username=" + username);
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Ошибка при создании аккаунта");
            getServletContext().getRequestDispatcher("/pages/registration.jsp").forward(req, resp);
        }
    }
}