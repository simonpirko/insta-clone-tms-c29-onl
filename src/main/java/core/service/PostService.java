package core.service;


import core.DAO.commentDao.CommentDao;
import core.DAO.commentDao.InDBCommentDao;
import core.DAO.postDao.InDBPostDao;
import core.DAO.postDao.PostDao;
import core.model.Account;
import core.model.Comment;
import core.model.Page;
import core.model.Post;

import java.util.List;
import java.util.Optional;

public class PostService {

    private static PostService instance;


    private final PostDao<Integer> postDao = InDBPostDao.getInstance();
    private final CommentDao<Integer> commentDao = InDBCommentDao.getInstance();


    private PostService() {
    }

    public static PostService getInstance() {
        if (instance == null) {
            instance = new PostService();
        }

        return instance;
    }

    public Optional<Integer> save(Post post) {
        return postDao.save(post);
    }

    public Optional<Post> findById(Integer id) {
        Optional<Post> optionalPost = postDao.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            Iterable<Comment> commentsByPost = commentDao.findAllByPost(post);
            post.setComments(commentsByPost);


            return Optional.of(post);
        }

        return optionalPost;
    }

    public List<Post> findAllByUser(Account account){
        return postDao.findAllByAccount(account);
    }
    public Iterable<Post> findAll(){
        return postDao.findAll();
    }

    public Iterable<Post> getAllWithPageable(Page page){
        return postDao.findAllWithPageable(page);
    }

    public int countAll(){
        return postDao.countAll();
    }

    public void removeById(Integer id){
        postDao.removeById(id);
    }

    public void removeByAccount(Account account){
        postDao.removeByAccount(account);
    }

    public void updatePost(int id, Post newPost){
        postDao.updatePost(id, newPost);
    }

    public Optional<Integer> saveFavorite(Account account, Post post) {
        return postDao.saveFavorite(account, post);
    }

    public List<Post> findAllFavorite(Account account) {
        return postDao.findFavorite(account);
    }

    public void removeFavoriteByAccount(Account account) {
        postDao.removeFavoriteByAccount(account);
    }
}


