package core.service;

import core.DAO.likeDao.InDBLikeDao;
import core.DAO.likeDao.LikeDAO;
import core.model.Account;
import core.model.Like;
import core.model.Post;

import java.util.Optional;

public class LikeService {
    private static LikeService instance;

    private final LikeDAO<Integer> likeDao = InDBLikeDao.getInstance();

    private LikeService() {

    }

    public static LikeService getInstance() {
        if (instance == null)
            instance = new LikeService();

        return instance;
    }

    public Optional<Integer> saveForPost(Like like) {
        return likeDao.saveForPost(like);

    }

    public Optional<Like> findByAccountAndPost(Account account, Post post) {
        return likeDao.findByAccountAndPost(account, post);
    }
    public void removeByAccountAndPost(Account account, Post post) {
        likeDao.removeByAccountAndPost(account, post);
    }
    public int findAllByPost(Post post) {
        return likeDao.findAllByPost(post);
    }
}