package test.user.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.user.dao.UnitOfWork;
import test.user.dao.UserDao;
import test.user.dao.util.UnitActions;
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

		register(user, UnitActions.INSERT.getAction());
	}

	@Override
	public void registerDeleted(Integer id) {
		LOGGER.info("Registering {} for delete in context.", id);

	}

	@Override
	public void registerModified(User user) {
		LOGGER.info("Registering {} for update in context.", user.getName());
		register(user, UnitActions.UPDATE.getAction());

	}

	@Override
	public void commit() {
		if (context == null || context.isEmpty()) {
			return;
		}
		LOGGER.info("Commit started");
		if (context.containsKey(UnitActions.INSERT.getAction())) {
			commitInsert();
		}
		if (context.containsKey(UnitActions.UPDATE.getAction())) {
			commitModify();
		}
		if (context.containsKey(UnitActions.DELETE.getAction())) {
			commitDelete();
		}
		LOGGER.info("Commit finished");
	}

	private void register(User user, String operation) {
		List<User> userToOperate = context.get(operation);
		if (userToOperate == null) {
			userToOperate = new ArrayList<>();
		}
		userToOperate.add(user);
		context.put(operation, userToOperate);
	}

	private void commitInsert() {
		List<User> usersToBeStarted = context.get(UnitActions.INSERT.getAction());
		for (User user : usersToBeStarted) {
			LOGGER.info("Inserting a new user {}", user.getName());
			userDao.create(user);
		}
	}

	private void commitModify() {
		List<User> modifiedUser = context.get(UnitActions.UPDATE.getAction());
		for (User user : modifiedUser) {
			LOGGER.info("Scheduling {} for modification work.", user.getId());
			userDao.update(user);
		}
	}

	private void commitDelete() {
		List<User> deletedUser = context.get(UnitActions.DELETE.getAction());
		for (User user : deletedUser) {
			LOGGER.info("Deleting {}", user.getId());
			userDao.delete(user.getId());
		}
	}
}
