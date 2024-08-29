package core.DAO.likeDao;

import core.model.Account;
import core.model.Like;
import core.model.Post;

import java.util.Optional;

public interface LikeDAO<ID> {
    Optional<ID> saveForPost(Like like);
    Iterable<Like> findAllForPost();
    Optional<Like> findByAccountAndPost(Account account, Post post);
    void removeByAccountAndPost(Account account, Post post);
    int findAllByPost(Post post);
}
