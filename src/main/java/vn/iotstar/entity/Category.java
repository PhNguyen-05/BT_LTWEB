//package vn.iotstar.entity;
//
//import java.io.Serializable;
//import java.util.List;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.NamedQuery;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "categories")
//@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
//public class Category implements Serializable {
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) 
//    private int id; 
//
//    @Column(name = "name", columnDefinition = "NVARCHAR(255)") 
//    private String name;
//
//    @Column(columnDefinition = "NVARCHAR(500)")
//    private String images;
//    
//    
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int CategoryID;
//	@Column(columnDefinition = "nvarchar(200)")
//	private String CategoryName;
//	@Column(columnDefinition = "nvarchar(MAX)")
//	private String icon;
//	@JsonManagedReference
//	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
//	private List<Product> products;
//
//}

//package vn.iotstar.entity;
//
//import java.io.Serializable;
//import java.util.List;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.NamedQuery;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//
//@Entity
//@Table(name = "Category")
//@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
//public class Category implements Serializable {
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int CategoryID;
//
//    @Column(columnDefinition = "nvarchar(200)")
//    private String CategoryName;
//
//    @Column(columnDefinition = "nvarchar(MAX)")
//    private String icon;
//
//    @JsonManagedReference
//    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
//    private List<Product> products;
//
//    // Getters and Setters (thêm manual nếu không dùng Lombok)
//    public int getCategoryID() {
//        return CategoryID;
//    }
//
//    public void setCategoryID(int categoryID) {
//        CategoryID = categoryID;
//    }
//
//    public String getCategoryName() {
//        return CategoryName;
//    }
//
//    public void setCategoryName(String categoryName) {
//        CategoryName = categoryName;
//    }
//
//    public String getIcon() {
//        return icon;
//    }
//
//    public void setIcon(String icon) {
//        this.icon = icon;
//    }
//
//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }
//}


package vn.iotstar.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Category")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryID;   // camelCase

    @Column(columnDefinition = "nvarchar(200)")
    private String categoryName;

    @Column(columnDefinition = "nvarchar(MAX)")
    private String icon;

    @JsonManagedReference
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products;

    // ===== Getters & Setters =====
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
