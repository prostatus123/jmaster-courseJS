package jmaster.model;

import org.springframework.web.multipart.MultipartFile;

public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String role;
    private String img;

    public UserDTO(int id, String username, String password, String role, String img) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.img = img;
    }

    public UserDTO(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserDTO() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
