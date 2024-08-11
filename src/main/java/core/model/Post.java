package core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.Part;

@Data
@NoArgsConstructor
public class Post {
    private String id;
    private String description;
    private String filePath;
    private String atributs;

    public Post(String id, String description, String filePath, String atributs) {
        this.id = id;
        this.description = description;
        this.filePath = filePath;
        this.atributs = atributs;
    }
}
