////package vn.iotstar.dao.impl;
////
////import java.util.ArrayList;
////import java.util.List;
////
////import jakarta.persistence.EntityManager;
////import jakarta.persistence.EntityTransaction;
////import jakarta.persistence.TypedQuery;
////import vn.iotstar.configs.JPAConfigs;
////import vn.iotstar.dao.UserDao;
////import vn.iotstar.entity.User;
////import vn.iotstar.models.UserModel;
////
////public class UserDaoImpl implements UserDao {
////
////    @Override
////    public UserModel findByUsername(String username) {
////        EntityManager enma = JPAConfigs.getEntityManager();
////        try {
////            TypedQuery<User> query = enma.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
////            query.setParameter("username", username);
////            User user = query.getSingleResult();
////            return convertToModel(user);
////        } catch (Exception e) {
////            return null;
////        } finally {
////            enma.close();
////        }
////    }
////
////    @Override
////    public UserModel getByEmail(String email) {
////        EntityManager enma = JPAConfigs.getEntityManager();
////        try {
////            TypedQuery<User> query = enma.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
////            query.setParameter("email", email);
////            User user = query.getSingleResult();
////            return convertToModel(user);
////        } catch (Exception e) {
////            return null;
////        } finally {
////            enma.close();
////        }
////    }
////
////    @Override
////    public void updatePassword(UserModel userModel) {
////        EntityManager enma = JPAConfigs.getEntityManager();
////        EntityTransaction trans = enma.getTransaction();
////        try {
////            trans.begin();
////            User user = enma.find(User.class, userModel.getId());
////            if (user != null) {
////                user.setPassword(userModel.getPassword());
////                enma.merge(user);
////            }
////            trans.commit();
////        } catch (Exception e) {
////            trans.rollback();
////            e.printStackTrace();
////        } finally {
////            enma.close();
////        }
////    }
////
////    @Override
////    public List<UserModel> getAll() {
////        EntityManager enma = JPAConfigs.getEntityManager();
////        try {
////            TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
////            List<User> users = query.getResultList();
////            List<UserModel> models = new ArrayList<>();
////            for (User user : users) {
////                models.add(convertToModel(user));
////            }
////            return models;
////        } finally {
////            enma.close();
////        }
////    }
////    
////    
////
////    @Override
////    public boolean register(UserModel userModel) {
////        EntityManager enma = JPAConfigs.getEntityManager();
////        EntityTransaction trans = enma.getTransaction();
////        try {
////            trans.begin();
////            User user = convertToEntity(userModel);
////            enma.persist(user);
////            trans.commit();
////            return true;
////        } catch (Exception e) {
////            trans.rollback();
////            e.printStackTrace();
////            return false;
////        } finally {
////            enma.close();
////        }
////    }
////
////    @Override
////    public boolean isUsernameExists(String username) {
////        return checkExistUsername(username);
////    }
////
////    @Override
////    public boolean isEmailExists(String email) {
////        return checkExistEmail(email);
////    }
////
////    @Override
////    public boolean checkExistEmail(String email) {
////        EntityManager enma = JPAConfigs.getEntityManager();
////        try {
////            TypedQuery<Long> query = enma.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class);
////            query.setParameter("email", email);
////            return query.getSingleResult() > 0;
////        } finally {
////            enma.close();
////        }
////    }
////
////    @Override
////    public boolean checkExistUsername(String username) {
////        EntityManager enma = JPAConfigs.getEntityManager();
////        try {
////            TypedQuery<Long> query = enma.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class);
////            query.setParameter("username", username);
////            return query.getSingleResult() > 0;
////        } finally {
////            enma.close();
////        }
////    }
////
////    @Override
////    public boolean checkExistSdt(String sdt) {
////        if (sdt == null || sdt.trim().isEmpty()) {
////            return false;
////        }
////        EntityManager enma = JPAConfigs.getEntityManager();
////        try {
////            TypedQuery<Long> query = enma.createQuery("SELECT COUNT(u) FROM User u WHERE u.sdt = :sdt", Long.class);
////            query.setParameter("sdt", sdt);
////            return query.getSingleResult() > 0;
////        } finally {
////            enma.close();
////        }
////    }
////
////    // Thêm cho profile update
////    @Override
////    public void updateProfile(UserModel userModel) {
////        EntityManager enma = JPAConfigs.getEntityManager();
////        EntityTransaction trans = enma.getTransaction();
////        try {
////            trans.begin();
////            User user = enma.find(User.class, userModel.getId());
////            if (user != null) {
////                user.setFullname(userModel.getFullname());
////                user.setSdt(userModel.getSdt());
////                user.setImages(userModel.getImages());
////                enma.merge(user);
////            }
////            trans.commit();
////        } catch (Exception e) {
////            trans.rollback();
////            e.printStackTrace();
////        } finally {
////            enma.close();
////        }
////    }
////
////    // Helper methods: Convert Entity <-> Model
////    private UserModel convertToModel(User user) {
////        if (user == null) return null;
////        UserModel model = new UserModel();
////        model.setId(user.getId());
////        model.setUsername(user.getUsername());
////        model.setFullname(user.getFullname());
////        model.setEmail(user.getEmail());
////        model.setSdt(user.getSdt());
////        model.setPassword(user.getPassword());
////        model.setImages(user.getImages());
////        model.setSeller(user.isSeller());
////        model.setAdmin(user.isAdmin());
////        return model;
////    }
////
////    private User convertToEntity(UserModel model) {
////        User user = new User();
////        user.setId(model.getId());
////        user.setUsername(model.getUsername());
////        user.setFullname(model.getFullname());
////        user.setEmail(model.getEmail());
////        user.setSdt(model.getSdt());
////        user.setPassword(model.getPassword());
////        user.setImages(model.getImages());
////        user.setSeller(model.isSeller());
////        user.setAdmin(model.isAdmin());
////        return user;
////    }
////}
//
//package vn.iotstar.dao.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.TypedQuery;
//import vn.iotstar.configs.JPAConfigs;
//import vn.iotstar.dao.UserDao;
//import vn.iotstar.entity.User;
//import vn.iotstar.models.UserModel;
//
//public class UserDaoImpl implements UserDao {
//
//    @Override
//    public UserModel findByUsername(String username) {
//        EntityManager enma = JPAConfigs.getEntityManager();
//        try {
//            TypedQuery<User> query = enma.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
//            query.setParameter("username", username);
//            User user = query.getSingleResult();
//            return convertToModel(user);
//        } catch (Exception e) {
//            System.out.println("Error in findByUsername for username: " + username + " - " + e.getMessage());
//            return null;
//        } finally {
//            if (enma != null && enma.isOpen()) {
//                enma.close();
//            }
//        }
//    }
//
//    @Override
//    public UserModel getByEmail(String email) {
//        EntityManager enma = JPAConfigs.getEntityManager();
//        try {
//            TypedQuery<User> query = enma.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
//            query.setParameter("email", email);
//            User user = query.getSingleResult();
//            return convertToModel(user);
//        } catch (Exception e) {
//            System.out.println("Error in getByEmail for email: " + email + " - " + e.getMessage());
//            return null;
//        } finally {
//            if (enma != null && enma.isOpen()) {
//                enma.close();
//            }
//        }
//    }
//
//    @Override
//    public void updatePassword(UserModel userModel) {
//        EntityManager enma = JPAConfigs.getEntityManager();
//        EntityTransaction trans = enma.getTransaction();
//        try {
//            trans.begin();
//            User user = enma.find(User.class, userModel.getId());
//            if (user != null) {
//                user.setPassword(userModel.getPassword());
//                enma.merge(user);
//                System.out.println("Password updated successfully for user ID: " + userModel.getId());
//            } else {
//                System.out.println("User not found for ID: " + userModel.getId());
//            }
//            trans.commit();
//        } catch (Exception e) {
//            if (trans.isActive()) {
//                trans.rollback();
//            }
//            System.out.println("Error in updatePassword for user ID: " + userModel.getId() + " - " + e.getMessage());
//        } finally {
//            if (enma != null && enma.isOpen()) {
//                enma.close();
//            }
//        }
//    }
//
//    @Override
//    public List<UserModel> getAll() {
//        EntityManager enma = JPAConfigs.getEntityManager();
//        try {
//            TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
//            List<User> users = query.getResultList();
//            List<UserModel> models = new ArrayList<>();
//            for (User user : users) {
//                models.add(convertToModel(user));
//            }
//            System.out.println("Retrieved " + models.size() + " users successfully");
//            return models;
//        } catch (Exception e) {
//            System.out.println("Error in getAll users - " + e.getMessage());
//            return new ArrayList<>(); // Trả về danh sách rỗng nếu lỗi
//        } finally {
//            if (enma != null && enma.isOpen()) {
//                enma.close();
//            }
//        }
//    }
//
//    @Override
//    public boolean register(UserModel userModel) {
//        EntityManager enma = JPAConfigs.getEntityManager();
//        EntityTransaction trans = enma.getTransaction();
//        try {
//            trans.begin();
//            User user = convertToEntity(userModel);
//            enma.persist(user);
//            trans.commit();
//            System.out.println("User registered successfully: " + userModel.getUsername());
//            return true;
//        } catch (Exception e) {
//            if (trans.isActive()) {
//                trans.rollback();
//            }
//            System.out.println("Error in register for username: " + userModel.getUsername() + " - " + e.getMessage());
//            return false;
//        } finally {
//            if (enma != null && enma.isOpen()) {
//                enma.close();
//            }
//        }
//    }
//
//    @Override
//    public boolean isUsernameExists(String username) {
//        return checkExistUsername(username);
//    }
//
//    @Override
//    public boolean isEmailExists(String email) {
//        return checkExistEmail(email);
//    }
//
//    @Override
//    public boolean checkExistEmail(String email) {
//        EntityManager enma = JPAConfigs.getEntityManager();
//        try {
//            TypedQuery<Long> query = enma.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class);
//            query.setParameter("email", email);
//            return query.getSingleResult() > 0;
//        } catch (Exception e) {
//            System.out.println("Error in checkExistEmail for email: " + email + " - " + e.getMessage());
//            return false;
//        } finally {
//            if (enma != null && enma.isOpen()) {
//                enma.close();
//            }
//        }
//    }
//
//    @Override
//    public boolean checkExistUsername(String username) {
//        EntityManager enma = JPAConfigs.getEntityManager();
//        try {
//            TypedQuery<Long> query = enma.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class);
//            query.setParameter("username", username);
//            return query.getSingleResult() > 0;
//        } catch (Exception e) {
//            System.out.println("Error in checkExistUsername for username: " + username + " - " + e.getMessage());
//            return false;
//        } finally {
//            if (enma != null && enma.isOpen()) {
//                enma.close();
//            }
//        }
//    }
//
//    @Override
//    public boolean checkExistSdt(String sdt) {
//        if (sdt == null || sdt.trim().isEmpty()) {
//            return false;
//        }
//        EntityManager enma = JPAConfigs.getEntityManager();
//        try {
//            TypedQuery<Long> query = enma.createQuery("SELECT COUNT(u) FROM User u WHERE u.sdt = :sdt", Long.class);
//            query.setParameter("sdt", sdt);
//            return query.getSingleResult() > 0;
//        } catch (Exception e) {
//            System.out.println("Error in checkExistSdt for sdt: " + sdt + " - " + e.getMessage());
//            return false;
//        } finally {
//            if (enma != null && enma.isOpen()) {
//                enma.close();
//            }
//        }
//    }
//
//    @Override
//    public void updateProfile(UserModel userModel) {
//        EntityManager enma = JPAConfigs.getEntityManager();
//        EntityTransaction trans = enma.getTransaction();
//        try {
//            trans.begin();
//            User user = enma.find(User.class, userModel.getId());
//            if (user != null) {
//                user.setFullname(userModel.getFullname());
//                user.setSdt(userModel.getSdt());
//                user.setImages(userModel.getImages());
//                enma.merge(user);
//                System.out.println("Profile updated successfully for user ID: " + userModel.getId());
//            } else {
//                System.out.println("User not found for ID: " + userModel.getId());
//            }
//            trans.commit();
//        } catch (Exception e) {
//            if (trans.isActive()) {
//                trans.rollback();
//            }
//            System.out.println("Error in updateProfile for user ID: " + userModel.getId() + " - " + e.getMessage());
//        } finally {
//            if (enma != null && enma.isOpen()) {
//                enma.close();
//            }
//        }
//    }
//
//    // Helper methods: Convert Entity <-> Model
//    private UserModel convertToModel(User user) {
//        if (user == null) return null;
//        UserModel model = new UserModel();
//        model.setId(user.getId());
//        model.setUsername(user.getUsername());
//        model.setFullname(user.getFullname());
//        model.setEmail(user.getEmail());
//        model.setSdt(user.getSdt());
//        model.setPassword(user.getPassword());
//        model.setImages(user.getImages());
//        model.setSeller(user.isSeller());
//        model.setAdmin(user.isAdmin());
//        return model;
//    }
//
//    private User convertToEntity(UserModel model) {
//        User user = new User();
//        user.setId(model.getId());
//        user.setUsername(model.getUsername());
//        user.setFullname(model.getFullname());
//        user.setEmail(model.getEmail());
//        user.setSdt(model.getSdt());
//        user.setPassword(model.getPassword());
//        user.setImages(model.getImages());
//        user.setSeller(model.isSeller());
//        user.setAdmin(model.isAdmin());
//        return user;
//    }
//}

package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.DBConnect;
import vn.iotstar.dao.UserDao;
import vn.iotstar.models.UserModel;

public class UserDaoImpl extends DBConnect implements UserDao {

    @Override
    public UserModel findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UserModel user = new UserModel();
                    user.setUsername(rs.getString("username"));
                    user.setFullname(rs.getString("fullname"));
                    user.setEmail(rs.getString("email"));
                    user.setSdt(rs.getString("sdt"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserModel getByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UserModel user = new UserModel();
                    user.setUsername(rs.getString("username"));
                    user.setFullname(rs.getString("fullname"));
                    user.setEmail(rs.getString("email"));
                    user.setSdt(rs.getString("sdt"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updatePassword(UserModel user) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getUsername());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserModel> getAll() {
        List<UserModel> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                UserModel user = new UserModel();
                user.setUsername(rs.getString("username"));
                user.setFullname(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setSdt(rs.getString("sdt"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean register(UserModel user) {
        String sql = "INSERT INTO users (username, fullname, email, sdt, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFullname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getSdt() != null ? user.getSdt() : "");
            ps.setString(5, user.getPassword());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isUsernameExists(String username) {
        return checkExistUsername(username);
    }

    @Override
    public boolean isEmailExists(String email) {
        return checkExistEmail(email);
    }

    @Override
    public boolean checkExistEmail(String email) {
        String sql = "SELECT 1 FROM users WHERE email = ?";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkExistUsername(String username) {
        String sql = "SELECT 1 FROM users WHERE username = ?";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkExistSdt(String sdt) {
        if (sdt == null || sdt.trim().isEmpty()) {
            return false;
        }
        String sql = "SELECT 1 FROM users WHERE sdt = ?";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sdt);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}