package test.user.service.impl;

import java.util.List;
import java.util.regex.Pattern;

import test.user.dao.UserDao;
import test.user.dao.impl.UserDaoImpl;
import test.user.entity.User;
import test.user.service.UserService;

public class UserServiceImpl implements UserService {
	private static final String EMAIL = "([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+)";
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL);
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
		isValidAge(user.getAge());
		isValidEmail(user.getEmail());
		userDao.create(user);
	}

	@Override
	public void updateUser(User user) {
		isValidAge(user.getAge());
		isValidEmail(user.getEmail());
		userDao.update(user);
	}

	private void isValidEmail(String email) {
		if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
			throw new IllegalArgumentException("Incorrect email");
		}
	}

	private void isValidAge(int age) {
		if (age < 0 || age > 120) {
			throw new IllegalArgumentException("Incorrect age");
		}
	}
}
