package servlet;

import core.model.Account;
import core.service.AccountService;
import lombok.SneakyThrows;

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

@WebServlet(value = "/profileEdit", name = "ProfileEditServlet")
@MultipartConfig()
public class ProfileEditServlet extends HttpServlet {
    private final AccountService accountService = AccountService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/profileEdit.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part fileAvatar = req.getPart("avatar");
        InputStream inputStream = fileAvatar.getInputStream();

        Account account = (Account) req.getSession().getAttribute("account");
        Integer accountID = account.getId();
        String name = req.getParameter("name");
        String website = req.getParameter("website");
        String about = req.getParameter("about");
        byte[] bytes = inputStream.readAllBytes();
        //String avatar = new BigInteger(bytes).toString(16);
        //String avatar = Base64.getEncoder().encodeToString(bytes);
        //b//yte[] bytes1 = new BigInteger(avatar, 16).toByteArray();


        String gender = req.getParameter("gender");

        //formFile

        account.setName(name);
        account.setWebsite(website);
        account.setAbout(about);
        //account.setAvatar(avatar);
        account.setGender(gender);

        try {
            //accountService.sa
            accountService.saveProfile(account);
            resp.sendRedirect("/profile?username=" + account.getUsername());
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Ошибка при сохранении данных профиля пользователя");
            getServletContext().getRequestDispatcher("/pages/profileEdit.jsp").forward(req, resp);
        }
    }
}
