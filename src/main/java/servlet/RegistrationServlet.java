package servlet;

import core.model.Account;
import core.service.AccountService;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Optional;

@WebServlet("/registration")
@MultipartConfig
//        fileSizeThreshold = 1024 * 1024,
//        maxFileSize = 1024 * 1024 * 5,
//        maxRequestSize = 1024 * 1024 * 5 * 5)

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


        Account account = Account.builder()
                .name(name)
                .surname(surname)
                .username(username)
                .email(email)
                .password(password)
                .filePathPhoto(Base64.getEncoder().encodeToString(photoInputStream.readAllBytes()))
                .build();
        Optional<Account> byAccountName=accountService.findAccountByName(username);
        if (byAccountName.isEmpty()) {
            AccountService.getInstance().save(account);
            resp.sendRedirect("/login");
            return;
        }else {
            req.setAttribute("message", "This user already exists!");
        }
        if (!byAccountName.isPresent()){
            req.setAttribute("errorMessage", "Registration failed. Check the entered data!");
        }
        getServletContext().getRequestDispatcher("/pages/registration.jsp").forward(req, resp);
    }
}