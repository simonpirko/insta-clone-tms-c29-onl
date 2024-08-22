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
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/upload")
@MultipartConfig()
public class UploadServlet extends HttpServlet {
    private final AccountService accountService = AccountService.getInstance();

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Part fileAvatar = req.getPart("avatar");
//        System.out.println(fileAvatar.getContentType());
//        InputStream inputStream = fileAvatar.getInputStream();
//
//        Account account = (Account) req.getSession().getAttribute("account");
//        byte[] bytes = inputStream.readAllBytes();
//
//        try {
//            accountService.saveAvatar(account.getId(), bytes);
//            resp.sendRedirect("/profileEdit?username=" + account.getUsername());
//            //resp.sendRedirect("/profileEdit?username=" + account.getUsername());
//        } catch (Exception e) {
//            req.setAttribute("errorMessage", "Ошибка при сохранении файла");
//            //getServletContext().getRequestDispatcher("/pages/profileEdit.jsp").forward(req, resp);
//        }
    }
}
