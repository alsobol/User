package test.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.user.dao.impl.UserDaoImpl;
import test.user.entity.User;

public class UserDaoImplTest {

	private static UserDao userDao;
	private User user;

	@BeforeClass
	public static void setUpClass() {
		userDao = new UserDaoImpl();

	}

	@Before
	public void setUp() {
		user = new User();
		user.setName("TestName");
		user.setEmail("user.email@test.com");
		user.setAge(35);
		userDao.create(user);

	}

	@After
	public void deleteUserAfterTest() {
		if (user.getId() != null) {
			userDao.delete(user.getId());
		}
	}

	@Test
	public void testCreate() {
		assertNotNull(user);

		User createdUser = userDao.getById(user.getId());
		assertNotNull(createdUser);
		assertEquals("TestName", createdUser.getName());
	}

	@Test
	public void testGetById() {
		Integer id = user.getId();
		User getUser = userDao.getById(id);

		assertNotNull(getUser);
		assertEquals("TestName", getUser.getName());
	}

	@Test
	public void testUpdate() {
		Integer id = user.getId();

		user.setName("UpdatedName");
		userDao.update(user);

		User updatedUser = userDao.getById(id);

		assertNotNull(updatedUser);
		assertEquals("UpdatedName", updatedUser.getName());
	}

	@Test
	public void testgetAll() {
		List<User> users = userDao.getAll();

		assertNotNull(users);
	}
}
