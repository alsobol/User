package test.user.controller;

import java.util.HashMap;

import test.user.dao.UnitOfWork;
import test.user.dao.impl.UnitOfWorkImpl;
import test.user.dao.impl.UserDaoImpl;
import test.user.entity.User;

public class TestUserControllerUoW {

	public static void main(String[] args) {

		User userCreate = new User();
		userCreate.setAge(15);
		userCreate.setName("UserUoW");
		userCreate.setEmail("user.create@unit.work");

		User userModify = new User();
		userModify.setId(40);
		userCreate.setAge(16);
		userCreate.setName("UserModifyUoW");
		userCreate.setEmail("user.modify@unit.work");

		UnitOfWork<User> userRepo = new UnitOfWorkImpl(new HashMap<>(), new UserDaoImpl());

		userRepo.registerNew(userCreate);
		userRepo.registerModified(userModify);
		userRepo.commit();
	}
}
