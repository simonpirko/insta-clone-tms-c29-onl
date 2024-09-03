package filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Properties;

@WebFilter(urlPatterns = {"/login", "/registration", "/localization", "/create-post", "/", "/account/profile", "/account/viewpost", "/pages/locale.jsp", "/pages/_header.jsp", "/pages/login.jsp", "/pages/register.jsp", "/pages/createPost.jsp", "/pages/index.jsp", "/pages/profile.jsp",
        "/pages/viewpost.jsp", "/page"})
public class LocalizationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        Locale locale = null;
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("locale")) {
                    String language = cookie.getValue();
                    locale = new Locale(language);
                    break;
                }
            }
        }

        if (locale == null) {
            locale = new Locale("en");
        }

        String language = locale.getLanguage();

        Properties properties = new Properties();
        InputStream filePath;

        if ("ru".equals(language)) {
            filePath = getClass().getClassLoader().getResourceAsStream("massage_ru.properties");
        } else {
            filePath = getClass().getClassLoader().getResourceAsStream("massage_en.properties");
        }

        try (Reader reader = new InputStreamReader(filePath, StandardCharsets.UTF_8)) {
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("properties", properties);

        chain.doFilter(req, res);
    }
}
