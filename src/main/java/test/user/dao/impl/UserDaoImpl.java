package test.user.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.user.dao.UserDao;
import test.user.entity.User;


public class UserDaoImpl implements UserDao {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	private SessionFactory sessionFactory;

	public UserDaoImpl() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<>();

		try (Session session = sessionFactory.openSession()) {
			users = (List<User>) session.createQuery("from User").list();
			if (users != null) {
				LOGGER.info("The list of users has been got: {}", users);
			}
		} catch (Exception ex) {
			LOGGER.warn("Error getting list users", ex);
			ex.printStackTrace();
		}
		return users;
	}

	@Override
	public User getById(Integer id) {
		User user = null;

		try (Session session = sessionFactory.openSession()) {
			user = (User) session.get(User.class, id);
			if (user != null) {
				LOGGER.info("The user has been got: {}", user);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.warn("Error getting user", ex);
		}
		return user;
	}

	@Override
	public void delete(Integer id) {
		Transaction transaction = null;

		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			User user = session.get(User.class, id);
			if (user != null) {
				session.delete(user);
				LOGGER.info("User deleted: {}", id);
			}
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null)
				transaction.rollback();
			LOGGER.warn("Error deleting user", ex);
		}
	}

	@Override
	public void create(User user) {
		Transaction transaction = null;

		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			user.setCreatedAt(LocalDateTime.now());
			session.save(user);
			transaction.commit();
			LOGGER.info("User has been created: {}", user);
		} catch (Exception ex) {
			if (transaction != null)
				transaction.rollback();
			LOGGER.warn("Error creating user", ex);
		}

	}

	@Override
	public void update(User user) {
		Transaction transaction = null;

		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
			LOGGER.info("User has been updated: {}", user);
		} catch (Exception ex) {
			if (transaction != null)
				transaction.rollback();
			LOGGER.warn("Error updating user", ex);

		}
	}

	
}
