package depaul.edu;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Scanner;

public class Authenticate {
	
	private UserAccess userBase;
	
	public Authenticate(UserAccess userBase) {
		this.userBase = userBase;
	}
	
    public boolean authenticateUser(Scanner scanner, AccountMenu menu) {
        System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		
		Map<String, User> users = userBase.getUsers();

    	if (users.containsKey(username)) {
    		User user = users.get(username);
    		if (user.getPassword().equals(password)) {
    			System.out.println("");
	    		System.out.println("**************************");
	    		System.out.println("Authentication successful!");
	    		System.out.println("**************************");
	    		System.out.println("");
	    		
	    		menu.setLoggedInUser(user);
	    		menu.accountMenu(scanner);
	            return true;
    		} else {
    			System.out.println("");
            	System.out.println("*****************************");
                System.out.println("Invalid username or password ");
                System.out.println("   Authentication failed!");
                System.out.println("*****************************");
                System.out.println("");
                return false;
    		}
    	} else {
    		System.out.println("");
        	System.out.println("*****************************");
            System.out.println("      User not found ");
            System.out.println("   Authentication failed!");
            System.out.println("*****************************");
            System.out.println("");
            return false;
    	}
    }        
    
    


}
