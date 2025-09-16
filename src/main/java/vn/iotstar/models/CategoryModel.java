package vn.iotstar.models;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int cateId;
    private String cateName;
    private String icons;
    private String userUsername;  // Sửa: string để match DB user_username (không int userId)

    public CategoryModel() {
        super();
    }

    public CategoryModel(int cateId, String cateName, String icons, String userUsername) {
        this.cateId = cateId;
        this.cateName = cateName;
        this.icons = icons;
        this.userUsername = userUsername;
    }

    // Getters and Setters
    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }

    public String getUserUsername() {  // Sửa getter/setter
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    @Override
    public String toString() {
        return "CategoryModel [cateId=" + cateId + ", cateName=" + cateName + ", icons=" + icons + ", userUsername=" + userUsername + "]";
    }
}