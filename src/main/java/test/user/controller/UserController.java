package test.user.controller;

import java.util.List;
import java.util.Scanner;

import test.user.entity.Method;
import test.user.entity.User;
import test.user.service.UserService;
import test.user.service.impl.UserServiceImpl;

public class UserController {

	public static final UserService userService = new UserServiceImpl();

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
		System.out.print("Enter Age: ");
		int age = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Enter Name: ");
		String name = scanner.nextLine();

		System.out.print("Enter Email: ");
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
			System.out.println("User with id: " + id + " not found");
		}

	}

	private static void updateUser(Scanner scanner) {
		System.out.println("User id: ");
		int id = scanner.nextInt();

		User user = userService.getUserById(id);

		if (user != null) {
			System.out.print("New Age: ");
			int age = scanner.nextInt();
			scanner.nextLine();

			System.out.print("New Name: ");
			String name = scanner.nextLine();

			System.out.print("New Email: ");
			String email = scanner.nextLine();

			user.setAge(age);
			user.setName(name);
			user.setEmail(email);
			userService.updateUser(user);
		} else {
			System.out.println("User with id: " + id + " not found");
		}
	}

	private static void deleteUser(Scanner scanner) {
		System.out.print("User id: ");
		Integer id = scanner.nextInt();
		userService.removeUser(id);

	}

	private static void listUsers() {
		List<User> users = userService.getAllUsers();
		if (!users.isEmpty())
			for (User user : users) {
				System.out.println(user.getName());
			}
		else {
			System.out.println("User list is empty");
		}
	}
}
