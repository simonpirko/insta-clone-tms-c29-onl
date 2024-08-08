package core.service;

import core.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostService {
    private static List<Post> posts = new ArrayList<>();

    public static void addPost(Post post) {
        posts.add(post);

    }

    public static List<Post> getAllPosts() {
        return new ArrayList<>(posts);

    }
}
