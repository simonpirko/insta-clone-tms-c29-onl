package core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.ByteArrayInputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String website;
    private String about;
    private String avatar;
    private String gender;

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Account(String name, String website, String about, String avatar, String gender) {
        this.name = name;
        this.website = website;
        this.about = about;
        this.avatar = avatar;
        this.gender = gender;
    }
}
