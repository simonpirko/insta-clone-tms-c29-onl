package servlet.PostServlet;

import core.model.Account;
import core.model.Comment;
import core.model.Post;
import core.service.CommentService;
import core.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/account/create-comment")
public class AddCommentServlet extends HttpServlet {
    private final PostService postService = PostService.getInstance();
    private final CommentService commentService = CommentService.getInstance();
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = ((Account) req.getSession().getAttribute("user"));

        int postId = Integer.parseInt(req.getParameter("post_id"));
        Optional<Post> post = postService.findById(postId);

        if (post.isPresent()) {
            String commentText = req.getParameter("commentMessage");
            Comment comment = Comment.builder()
                    .account(account)
                    .post(post.get())
                    .text(commentText)
                    .build();

            commentService.saveForPost(comment);
            resp.sendRedirect("/account/viewpost?id=" + postId);
        } else {
            req.setAttribute("errormessage", "Post don't found.");
        }
    }
}
