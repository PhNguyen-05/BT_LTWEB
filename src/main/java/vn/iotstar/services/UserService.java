package vn.iotstar.services;

import java.util.List;
import vn.iotstar.models.UserModel;

public interface UserService {
	UserModel login(String username, String password);

	UserModel findByUsername(String username);

	UserModel getByEmail(String email);

	void updatePassword(UserModel user);

	List<UserModel> getAll();

	// Register methods
	boolean register(UserModel user);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean checkExistSdt(String sdt);
}