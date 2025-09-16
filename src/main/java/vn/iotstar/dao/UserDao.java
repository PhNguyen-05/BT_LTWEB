package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.models.UserModel;

public interface UserDao {
	UserModel findByUsername(String username);

	UserModel getByEmail(String email);

	void updatePassword(UserModel user);

	List<UserModel> getAll();

	// Register methods
	boolean register(UserModel user);

	boolean isUsernameExists(String username);

	boolean isEmailExists(String email);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean checkExistSdt(String sdt); // Thay checkExistPhone bằng checkExistSdt
}
