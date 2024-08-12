package core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Post {
    private String id;
    private String description;
    private String filePath;
    private Date createdAt;


    public Post(String id, String description, String filePath, Date createdAt) {
        this.id = id;
        this.description = description;
        this.filePath = filePath;
        this.createdAt = createdAt;
    }

}
