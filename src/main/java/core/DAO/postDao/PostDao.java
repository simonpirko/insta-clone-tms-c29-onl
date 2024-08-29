package core.DAO.postDao;

import core.model.Account;
import core.model.Page;
import core.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostDao<ID> {
    Optional<ID> save(Post post);
    Optional<Post> findById(ID id);
    List<Post> findAllByAccount(Account account);
    Iterable<Post> findAll();
    Iterable<Post> findAllWithPageable(Page page);

    int countAll();

    void removeById(ID id);

    void removeByAccount(Account account);

    void updatePost(ID id, Post newPost);

    Optional<ID> saveFavorite(Account account, Post post);
    List<Post> findFavorite(Account account);
    void removeFavoriteByAccount(Account account);
}
