package test.user.controller;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.user.entity.Method;
import test.user.entity.User;
import test.user.service.UserService;
import test.user.service.impl.UserServiceImpl;

public class UserController {

	public static final UserService userService = new UserServiceImpl();
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	public static void main(String[] args) {

		startApp();
	}

	public static void startApp() {

		while (true) {
			System.out.println("Enter the desired method:");
			System.out.println("create");
			System.out.println("read");
			System.out.println("update");
			System.out.println("delete");
			System.out.println("list");
			System.out.println("exit");
			Scanner scanner = new Scanner(System.in);
			Method method = Method.valueOf(scanner.next().toUpperCase());
			switch (method) {
			case CREATE:
				createUser(scanner);
				break;
			case READ:
				getUserById(scanner);
				break;
			case UPDATE:
				updateUser(scanner);
				break;
			case DELETE:
				deleteUser(scanner);
				break;
			case LIST:
				listUsers();
				break;
			case EXIT:
				scanner.close();
				System.exit(0);
				break;
			default:
				System.out.println("Method don't exist");
				break;
			}
		}
	}

	private static void createUser(Scanner scanner) {
		LOGGER.info("Create user method");

		System.out.print("User age: ");
		int age = scanner.nextInt();
		scanner.nextLine();

		System.out.print("User name: ");
		String name = scanner.nextLine();

		System.out.print("User email: ");
		String email = scanner.nextLine();

		User user = new User();
		user.setAge(age);
		user.setName(name);
		user.setEmail(email);

		userService.addUser(user);
	}

	private static void getUserById(Scanner scanner) {

		System.out.println("User id: ");
		Integer id = scanner.nextInt();

		User user = userService.getUserById(id);

		if (user == null) {
			LOGGER.warn("Not found user with id {}", id);
		}

	}

	private static void updateUser(Scanner scanner) {
		LOGGER.info("Update user method");

		LOGGER.info("User id: ");
		int id = scanner.nextInt();

		User user = userService.getUserById(id);

		if (user != null) {
			System.out.print("User age: ");
			int age = scanner.nextInt();
			scanner.nextLine();

			System.out.print("User name: ");
			String name = scanner.nextLine();

			System.out.print("User email: ");
			String email = scanner.nextLine();

			user.setAge(age);
			user.setName(name);
			user.setEmail(email);
			userService.updateUser(user);
		} else {
			LOGGER.warn("Not found user with id {}", id);
		}
	}

	private static void deleteUser(Scanner scanner) {
		LOGGER.info("Delete user method");

		System.out.print("User id: ");
		Integer id = scanner.nextInt();
		userService.removeUser(id);

	}

	private static void listUsers() {
		LOGGER.info("Get list users method");

		List<User> users = userService.getAllUsers();
		if (!users.isEmpty())
			for (User user : users) {
				System.out.println(user.getName());
			}
		else {
			LOGGER.warn("User list is empty");
		}
	}
}
