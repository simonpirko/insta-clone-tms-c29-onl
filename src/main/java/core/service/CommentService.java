package core.service;


import core.DAO.commentDao.CommentDao;
import core.DAO.commentDao.InDBCommentDao;
import core.model.Account;
import core.model.Comment;
import core.model.Post;

import java.util.Optional;


public class CommentService {
    private static CommentService instance;

    private final CommentDao<Integer> commentDao = InDBCommentDao.getInstance();

    private CommentService() {}

    public static CommentService getInstance() {
        if (instance == null)
            instance = new CommentService();

        return instance;
    }

    public Optional<Integer> saveForPost(Comment comment) {
        return commentDao.saveForPost(comment);
    }
    public Iterable<Comment> findByPost(Post post) {
        return commentDao.findAllByPost(post);
    }
    public Optional<Comment> findAllForPostByUser(Account account) {
        return commentDao.findAllForPostByAccount(account);
    }
    public void removeForPostById(Integer id) {
        commentDao.removeForPostById(id);
    }
}

