package core.model;

import lombok.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int id;
    private String name;
    private String surname;
    private String username;
    private String filePathPhoto;
    private String email;
    private String password;


    private Iterable<Post> posts;
    private Iterable<Comment> comments;
    private Iterable<Like> likes;


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", filePathPhoto='" + filePathPhoto + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", posts=" + posts +
                ", comments=" + comments +
                ", likes=" + likes +
                '}';
    }
}
