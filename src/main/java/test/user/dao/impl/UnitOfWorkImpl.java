package test.user.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.user.dao.UnitOfWork;
import test.user.dao.UserDao;
import test.user.entity.User;

public class UnitOfWorkImpl implements UnitOfWork<User> {

	private static final Logger LOGGER = LoggerFactory.getLogger(UnitOfWorkImpl.class);

	private Map<String, List<User>> context = new HashMap<>();
	private UserDao userDao = new UserDaoImpl();

	public UnitOfWorkImpl(Map<String, List<User>> context, UserDao userDao) {
		this.context = context;
		this.userDao = userDao;

	}

	@Override
	public void registerNew(User user) {
		LOGGER.info("Registering {} for insert in context.", user.getName());

		register(user, UnitOfWork.INSERT);
	}

	@Override
	public void registerDeleted(Integer id) {
		LOGGER.info("Registering {} for delete in context.", id);

	}

	@Override
	public void registerUpdate(User user) {
		LOGGER.info("Registering {} for update in context.", user.getName());
		register(user, UPDATE);

	}

	@Override
	public void commit() {
		if (context == null || context.isEmpty()) {
			return;
		}
		LOGGER.info("Commit started");
		if (context.containsKey("INSERT")) {
			commitInsert();
		}
	}

	private void register(User user, String operation) {
		var userToOperate = context.get(operation);
		if (userToOperate == null) {
			userToOperate = new ArrayList<>();
		}
		userToOperate.add(user);
		context.put(operation, userToOperate);
	}

	private void commitInsert() {
		var usersToBeStarted = context.get("INSERT");
		for (var user : usersToBeStarted) {
			LOGGER.info("Inserting a new user {}", user.getName());
			userDao.create(user);
		}
	}
}
