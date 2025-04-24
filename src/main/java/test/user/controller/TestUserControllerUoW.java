package test.user.controller;

import java.util.HashMap;

import test.user.dao.impl.UnitOfWorkImpl;
import test.user.dao.impl.UserDaoImpl;
import test.user.entity.User;

public class TestUserControllerUoW {

	public static void main(String[] args) {

		var userCreate = new User();
		userCreate.setAge(15);
		userCreate.setName("UserUoW");
		userCreate.setEmail("user.unitOfWork@unit.work");

		var userRepo = new UnitOfWorkImpl(new HashMap<>(), new UserDaoImpl());

		userRepo.registerNew(userCreate);
		userRepo.commit();
	}
}
