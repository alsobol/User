package test.user.service;

import java.util.List;

import test.user.entity.User;

public interface UserService {

	List<User> getAllUsers();

	User getUserById(Integer id);

	void removeUser(Integer id);

	void addUser(User user);

	void updateUser(User user);

}
