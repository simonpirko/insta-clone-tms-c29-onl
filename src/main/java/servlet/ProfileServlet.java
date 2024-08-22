package servlet;

import core.model.Account;
import core.service.AccountService;
import lombok.SneakyThrows;
import storage.account.InDBAccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/profile", name = "ProfileServlet")
public class ProfileServlet extends HttpServlet {
    private final AccountService accountService = AccountService.getInstance();
    private final InDBAccountDAO inDBAccountDAO = InDBAccountDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("ProfileServlet");

        ProfileEditServlet.getCurrentAccount(req, inDBAccountDAO);

        getServletContext().getRequestDispatcher("/pages/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ProfileServlet doPost");
        ProfileEditServlet.getCurrentAccount(req, inDBAccountDAO);
        getServletContext().getRequestDispatcher("/pages/profileEdit.jsp").forward(req, resp);
        //super.doPost(req, resp);
    }
}
