package core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Comment {
    private int id;
    private Account account;
    private Post post;
    private String text;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", account=" + account +
                ", post=" + post +
                ", text='" + text + '\'' +
                '}';
    }

}
