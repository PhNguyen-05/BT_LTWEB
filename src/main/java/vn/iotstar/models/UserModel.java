package vn.iotstar.models;

import java.io.Serializable;

public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String fullname;
    private String email;
    private String sdt;
    private String password;
    private String images;  // Thêm field images
    private boolean isSeller;  // Thêm isSeller
    private boolean isAdmin;  // Thêm isAdmin

    public UserModel() {
        super();
    }

    public UserModel(int id, String username, String fullname, String email, String sdt, String password, String images, boolean isSeller, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.sdt = sdt;
        this.password = password;
        this.images = images;
        this.isSeller = isSeller;
        this.isAdmin = isAdmin;
    }

    // Getters and Setters
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public boolean isSeller() {
        return isSeller;
    }

    public void setSeller(boolean seller) {
        isSeller = seller;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "UserModel [id=" + id + ", username=" + username + ", fullname=" + fullname + ", email=" + email + ", sdt=" + sdt + ", password=" + password + ", images=" + images + ", isSeller=" + isSeller + ", isAdmin=" + isAdmin + "]";
    }
}