package servlet.PostServlet;

import core.model.Page;
import core.model.Post;
import core.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/page")
public class PagingServlet extends HttpServlet {
    private final PostService postService = PostService.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNumber = 1;
        int postsPerPage;
        if (req.getSession().getAttribute("postsPerPage") != null) {
            postsPerPage = (int) req.getSession().getAttribute("postsPerPage");
        } else {
            postsPerPage = 2;
        }

        if (req.getParameter("page") != null) {
            pageNumber = Integer.parseInt(req.getParameter("page"));
        }
        Page<Post> page = Page.<Post>builder()
                .pageMin(5)
                .pageMax(5)
                .limit(postsPerPage)
                .build();

        page.setPageNumber(pageNumber);

        PostService postService = PostService.getInstance();

        Iterable<Post> postsForPageList = postService.getAllWithPageable(page);

        page.setItemsForPageList(postsForPageList);

        int numberOfPosts = postService.countAll();
        page.setTotalItems(numberOfPosts);
        int numberOfPages = page.getTotalPages();

        req.setAttribute("page", page);


        getServletContext().getRequestDispatcher("/pages/index.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNumber = Integer.parseInt(req.getParameter("page"));
        int postsPerPage = Integer.parseInt(req.getParameter("postsPerPage"));
        req.getSession().setAttribute("postsPerPage", postsPerPage);

        resp.sendRedirect("/pages?page=" + pageNumber);
    }

}


