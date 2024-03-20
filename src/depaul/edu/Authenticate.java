package depaul.edu;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Scanner;

public class Authenticate {
	private HashMap<String, User> users;
	private final String credentialsPath = "/Users/curt.petty/eclipse-workspace/ShoppingSystem/src/depaul/edu/credentials.txt";
	
	private List<Product> catalog;
	private final String catalogPath = "/Users/curt.petty/eclipse-workspace/ShoppingSystem/src/depaul/edu/catalog.txt";
	
	public Authenticate() {
		users = new HashMap<>();
		loadUsers();
	}
	
	private void loadUsers() {
		try (BufferedReader reader = new BufferedReader(new FileReader(credentialsPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    User user = new User(username, password);
                    users.put(username, user);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading users from file: " + e.getMessage());
        }
	}
	
    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(credentialsPath))) {
            for (User user : users.values()) {
            	writer.write(user.getUsername() + ":" + user.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving user to file: " + e.getMessage());
        }
    }
    
    public void registerUser(Scanner scanner) {
    	System.out.print("Set username: ");
		String username = scanner.nextLine();
		
		System.out.print("Set password: ");
		String password = scanner.nextLine();
    	
    	if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
        } else {
            User user = new User(username, password);
        	users.put(username, user);
            saveUsers();
            System.out.println("User registered successfully!");
        }
    }
    
    public boolean authenticateUser(Scanner scanner) {
        System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();

    	if (users.containsKey(username) && users.get(username).getPassword().equals(password)) {
            System.out.println("");
    		System.out.println("**************************");
    		System.out.println("Authentication successful!");
    		System.out.println("**************************");
    		System.out.println("");
    		
    		User user = users.get(username);
    		user.getCart().loadCart("/Users/curt.petty/eclipse-workspace/ShoppingSystem/src/depaul/edu/" + username + ".txt");
            
    		accountMenu(scanner, username);
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
    }
    
    public void accountMenu(Scanner scanner, String username) {
    	
    	boolean continueLoop = true;
    	User loggedInUser = users.get(username);
		
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
		        	loadCatalog();
		        	browseCatalog(scanner, loggedInUser);
		        	break;
		        case 3:
		        	viewCart(scanner, loggedInUser);
		        	break;
		        case 4:
		        	loggedInUser.getCart().clearCart();
		        	break;
		        case 5:
		        	checkout(scanner, username);
		        	break;
		        case 6:
		        	System.out.println("");
		        	System.out.println("***********");
		        	System.out.println("Logging out");
		        	System.out.println("***********");
		        	System.out.println("");
		        	loggedInUser.getCart().saveCart("/Users/curt.petty/eclipse-workspace/ShoppingSystem/src/depaul/edu/" + username + ".txt");
		        	loggedInUser.getCart().clearCartSilent();
		        	continueLoop = false;
		        	break;
			}
		}
    	
    	
    }

    private void loadCatalog() {
    	catalog = new ArrayList<>();
    	try (BufferedReader reader = new BufferedReader(new FileReader(catalogPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 4) {
                    String name = parts[0];
                    String description = parts[1];
                    String sku = parts[2];
                    double price = Double.parseDouble(parts[3].trim());
                    catalog.add(new Product(name, description, sku,  price));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading catalog from file: " + e.getMessage());
        }
    }
    
    private void browseCatalog(Scanner scanner, User loggedInUser) {
    	System.out.println("");
    	System.out.println("---------------------------------");
    	System.out.println("Product Catalog:");
    	System.out.println("---------------------------------");
        for (int i = 0; i < catalog.size(); i++) {
        	System.out.println("Name: " + catalog.get(i).getName());
            System.out.println("Description: " + catalog.get(i).getDescription());
            System.out.println("SKU: " + catalog.get(i).getSku());
            System.out.println("Price: $" + catalog.get(i).getPrice());
            System.out.println("");
        }
        System.out.println("---------------------------------");
        System.out.println("");
        
        System.out.print("Add a product to cart? (y/n) ");
		String addtoCart = scanner.nextLine();
		if(addtoCart.equalsIgnoreCase("y")) {
			addToCart(scanner, loggedInUser);
		}
    }
    
    private void addToCart(Scanner scanner, User loggedInUser) {
    	System.out.println("");
    	System.out.print("Enter the product SKU: ");
    	String sku = scanner.nextLine();
    	
    	System.out.println("");
    	System.out.print("Enter quantity: ");
    	int quantity = scanner.nextInt();
    	scanner.nextLine();
    	
    	Cart loggedInUserCart = loggedInUser.getCart();
    	Product productToAdd = null;
    	
    	for (Product product : catalog) {
            if (product.getSku().equals(sku)) {
            	productToAdd = product;
            }
        }
    	
    	loggedInUserCart.addProduct(productToAdd, quantity);
    	loggedInUser.getCart().saveCart("/Users/curt.petty/eclipse-workspace/ShoppingSystem/src/depaul/edu/" + loggedInUser.getUsername() + ".txt");
    }
    
    private void viewCart(Scanner scanner, User loggedInUser) {
    	Cart loggedInUserCart = loggedInUser.getCart();
    	loggedInUserCart.viewItems();
    }
    
    private void checkout(Scanner scanner, String username) {
    	User user = users.get(username);
        if (user != null) {
            Cart cart = user.getCart();
            Map<Product, Integer> items = cart.getItems();
            if (!items.isEmpty()) {
                Order order = new Order(username);
                for (Map.Entry<Product, Integer> entry : items.entrySet()) {
                    Product product = entry.getKey();
                    int quantity = entry.getValue();
                    order.addProduct(product, quantity);
                }
                double totalPrice = order.getTotalPrice();
                System.out.println("");
                System.out.println("--------------------------------");
                System.out.println("Processing order for user: " + username);
                System.out.println("--------------------------------");
                System.out.println("Ordered items:");
                for (int i = 0; i < order.getProducts().size(); i++) {
                    Product product = order.getProducts().get(i);
                    int quantity = order.getQuantities().get(i);
                    System.out.println("+ " + product.getName() + " - " + product.getDescription() + " (QTY: " + quantity + ")");
                }
                System.out.println("");
                System.out.println("Total price: $" + totalPrice);
                System.out.println("");
                System.out.print("Are you ready to pay? (y/n) ");
                String continueToPay = scanner.nextLine();
        		if(continueToPay.equalsIgnoreCase("y")) {
        			System.out.println("");
        			System.out.println("PROCEEEDING TO PAYMENT");
        			System.out.println("");
        			System.out.println("*****************");
        			System.out.println("PAYMENT PROCESSED");
        			System.out.println("*****************");
        			System.out.println("");
        			
        			OrderLogger.logOrder(username, totalPrice);
        		}
                cart.clearCartSilent(); // Clear the cart after processing the order
            } else {
                System.out.println("Your cart is empty. Please add items before processing the order.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

}
