package test.user.service.impl;

import java.util.List;

import test.user.dao.UserDao;
import test.user.dao.impl.UserDaoImpl;
import test.user.entity.User;
import test.user.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();

	@Override
	public List<User> getAllUsers() {
		return userDao.getAll();
	}

	@Override
	public User getUserById(Integer id) {
		return userDao.getById(id);
	}

	@Override
	public void removeUser(Integer id) {
		userDao.delete(id);
	}

	@Override
	public void addUser(User user) {
		userDao.create(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

}
