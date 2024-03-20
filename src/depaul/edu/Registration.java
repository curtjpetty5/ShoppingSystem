package depaul.edu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Registration {
	private UserAccess userBase;
	
	public Registration(UserAccess userBase) {
		this.userBase = userBase;
	}
	
	public void registerUser(Scanner scanner) {
		System.out.println("--------------");
    	System.out.println("Create Account");
        System.out.println("--------------");
		System.out.print("Set username: ");
		String username = scanner.nextLine();
		
		System.out.print("Set password: ");
		String password = scanner.nextLine();
		
		Map<String, User> users = userBase.getUsers();
    	
    	if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
        } else {
            User user = new User(username, password);
        	userBase.addUser(user);
        	System.out.println("");
            System.out.println("User registered successfully!");
            System.out.println("");
        }
    }
}
