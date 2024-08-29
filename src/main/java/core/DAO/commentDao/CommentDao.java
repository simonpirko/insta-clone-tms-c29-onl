package core.DAO.commentDao;

import core.model.Account;
import core.model.Comment;
import core.model.Post;

import java.util.Optional;

public interface CommentDao<ID> {
    Optional<ID> saveForPost(Comment comment);
    Optional<Comment> findAllForPostByAccount(Account account);
    Iterable<Comment> findAllByPost(Post post);
    void removeForPostById(ID id);
}
