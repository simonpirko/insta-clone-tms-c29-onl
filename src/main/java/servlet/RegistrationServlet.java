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
import java.io.InputStream;

@WebServlet(value = "/registration", name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private final AccountService accountService = AccountService.getInstance();
    private final static String NAME = "name";
    private final static String SURNAME = "surname";
    private final static String USERNAME = "username";
    private final static String EMAIL = "email";
    private final static String PASSWORD = "password";
    private final static String PHOTO = "filePathPhoto";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        InputStream photoInputStream = req.getPart(PHOTO).getInputStream();
        String name = req.getParameter(NAME);
        String surname = req.getParameter(SURNAME);
        String username = req.getParameter(USERNAME);
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());


        try {
            accountService.save(new Account(username, hashPassword, email));
            resp.sendRedirect("/login?username=" + username);
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Ошибка при создании аккаунта");
            getServletContext().getRequestDispatcher("/pages/registration.jsp").forward(req, resp);
        }
    }
}