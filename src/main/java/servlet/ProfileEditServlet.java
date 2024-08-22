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
import javax.servlet.http.Part;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@WebServlet(value = "/profileEdit", name = "ProfileEditServlet")
@MultipartConfig()
public class ProfileEditServlet extends HttpServlet {
    private final AccountService accountService = AccountService.getInstance();
    private final InDBAccountDAO inDBAccountDAO = InDBAccountDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ProfileEditServlet");
        getCurrentAccount(req, inDBAccountDAO);
        getServletContext().getRequestDispatcher("/pages/profileEdit.jsp").forward(req, resp);
    }

    static void getCurrentAccount(HttpServletRequest req, InDBAccountDAO inDBAccountDAO) {
        Account account = (Account) req.getSession().getAttribute("account");

        Optional<Account> accountUpdated = inDBAccountDAO.getById(account.getId());
        req.setAttribute("userName", accountUpdated.get().getUsername());
        req.setAttribute("name", accountUpdated.get().getName());
        req.setAttribute("avatar", accountUpdated.get().getAvatar());
        req.setAttribute("website", accountUpdated.get().getWebsite());
        req.setAttribute("about", accountUpdated.get().getAbout());
        req.setAttribute("gender", accountUpdated.get().getGender());
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account");
        String name = req.getParameter("name");
        String website = req.getParameter("website");
        String about = req.getParameter("about");
        String gender = req.getParameter("gender");

        account.setName(name);
        account.setWebsite(website);
        account.setAbout(about);
        account.setGender(gender);

        Part fileAvatar = req.getPart("fileAvatar");
        System.out.println(fileAvatar.getContentType());
        InputStream inputStream = fileAvatar.getInputStream();
        byte[] bytes = inputStream.readAllBytes();
        try {
            accountService.saveAvatar(account.getId(), bytes);
            //resp.sendRedirect("/profileEdit?username=" + account.getUsername());
            //resp.sendRedirect("/profileEdit?username=" + account.getUsername());
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Ошибка при сохранении файла");
            //getServletContext().getRequestDispatcher("/pages/profileEdit.jsp").forward(req, resp);
        }

        try {
            accountService.saveProfile(account);
            resp.sendRedirect("/profile?username=" + account.getUsername());
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Ошибка при сохранении данных профиля пользователя");
            getServletContext().getRequestDispatcher("/pages/profileEdit.jsp").forward(req, resp);
        }
    }
}
