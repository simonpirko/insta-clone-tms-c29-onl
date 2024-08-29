package core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private int id;
    private Account account;
    private String filePathPhoto;
    private String description;
    private LocalDateTime createdAt;
    private Iterable<Comment> comments;
    private Iterable<Like> likes;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", account=" + account +
                ", filePathPhoto='" + filePathPhoto + '\'' +
                ", description='" + description + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
