package servlet;

import core.service.AccountService;
import storage.account.InDBAccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/home", name = "HomeServlet")
public class HomeServlet extends HttpServlet {
    private final AccountService service = AccountService.getInstance();
    private final InDBAccountDAO inDBAccountDAO = InDBAccountDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProfileEditServlet.getCurrentAccount(req, inDBAccountDAO);
        getServletContext().getRequestDispatcher("/pages/home.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
