package core.DAO;

public interface DAO<T> {
    void save(T item);
    void saveProfile(T item);
    void saveAvatar(int id, byte[] item);
}
