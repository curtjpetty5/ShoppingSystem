package depaul.edu;

import java.io.File;
import java.util.Scanner;

public class AccountMenu {
	private Catalog catalog;
	private Cart cart;
	private Checkout checkout;
	private User loggedInUser;
	
	public AccountMenu(Catalog catalog, Cart cart, Checkout checkout) {
		this.catalog = catalog;
		this.cart = cart;
		this.checkout = checkout;
		
	}
	
	public void setLoggedInUser(User user) {
		this.loggedInUser = user;
		String currentDirectory = System.getProperty("user.dir");
        String cartFilePath = currentDirectory + "/src/depaul/edu" + File.separator + user.getUsername() + ".txt";
		cart.setCartFilePath(cartFilePath);
		cart.loadCart();
	}
	
	public void accountMenu(Scanner scanner) {
    	boolean continueLoop = true;
			
		while (continueLoop) {
	    	if (loggedInUser != null) {
	            System.out.println("Welcome, " + loggedInUser.getUsername() + "!");
            System.out.println("");
            System.out.println("1 - View Account Info");
            System.out.println("2 - Browse catalog");
            System.out.println("3 - View Cart");
            System.out.println("4 - Clear Cart");
            System.out.println("5 - Checkout");
            System.out.println("6 - Logout");
            System.out.println("");
            System.out.print("Enter an option to proceed: ");
        } else {
            System.out.println("User not found.");
            continueLoop = false;
        }
    	
		int begin = scanner.nextInt();
		scanner.nextLine();
		
		switch (begin) {
	        case 1:
	    		loggedInUser.displayAccountInfo();
	            break;
	        case 2:
	        	catalog.browseCatalog(scanner);
	        	break;
	        case 3:
	        	cart.viewItems();
	        	break;
	        case 4:
	        	cart.clearCart();
	        	break;
	        case 5:
	        	checkout.processCheckout(scanner, loggedInUser);
	        	break;
	        case 6:
	        	System.out.println("");
	        	System.out.println("***********");
	        	System.out.println("Logging out");
	        	System.out.println("***********");
	        	System.out.println("");
	        	
	        	String currentDirectory = System.getProperty("user.dir");
	            String cartFilePath = currentDirectory + "/src/depaul/edu" + File.separator + loggedInUser.getUsername() + ".txt";
	        	cart.saveCart(cartFilePath);
	        	cart.clearCartSilent();
	        	continueLoop = false;
	        	break;
			}
		}	
	}
}
