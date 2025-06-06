package test.user.controller;

import java.util.HashMap;

import test.user.dao.UnitOfWork;
import test.user.dao.impl.UnitOfWorkImpl;
import test.user.dao.impl.UserDaoImpl;
import test.user.entity.User;
import test.user.service.UserService;
import test.user.service.impl.UserServiceImpl;

public class TestUserControllerUoW {
	private static UserService userService = new UserServiceImpl();

	public static void main(String[] args) {

		User userCreate = new User();
		userCreate.setAge(15);
		userCreate.setName("UserUoW");
		userCreate.setEmail("user.create@unit.work");

		User userModify = userService.getUserById(42);
		userModify.setAge(45);
		userModify.setName("UserUoW");
		userModify.setEmail("user.modify@unit.work");

		Integer id = 47;
		userService.removeUser(id);

		UnitOfWork<User> userRepo = new UnitOfWorkImpl(new HashMap<>(), new UserDaoImpl());

		userRepo.registerNew(userCreate);
		userRepo.registerModified(userModify);
		userRepo.registerDeleted(id);
		userRepo.commit();
	}
}
