package core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class Like {
    private int id;
    private Account account;
    private Post post;
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", account=" + account +
                ", post=" + post +
                ", createdAt=" + createdAt +
                '}';
    }
}
