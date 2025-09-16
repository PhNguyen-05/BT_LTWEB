package vn.iotstar.services.impl;

import java.util.List;
import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public UserModel login(String username, String password) {
        UserModel user = this.findByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public UserModel findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public UserModel getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public void updatePassword(UserModel user) {
        userDao.updatePassword(user);
    }

    @Override
    public List<UserModel> getAll() {
        return userDao.getAll();
    }

    @Override
    public boolean register(UserModel user) {
        // TODO: Hash password nếu cần (e.g., BCrypt)
        return userDao.register(user);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistSdt(String sdt) {
        return userDao.checkExistSdt(sdt);  // Thay checkExistPhone bằng checkExistSdt
    }
}