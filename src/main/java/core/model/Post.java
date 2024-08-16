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

}
