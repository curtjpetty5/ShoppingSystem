package depaul.edu;

import java.util.Map;
import java.util.Scanner;

public class Checkout {
	private Cart cart;
	private OrderLogger logger;
	
	public Checkout(Cart cart, OrderLogger logger) {
		this.cart = cart;
		this.logger = logger;
	}
	
	public void processCheckout(Scanner scanner, User user) {
		if (user != null) {
			Map<Product, Integer> items = cart.getItems();
			if (!items.isEmpty()) {
				Order order = new Order(user.getUsername());
				for (Map.Entry<Product, Integer> entry : items.entrySet()) {
					Product product = entry.getKey();
					int quantity = entry.getValue();
					order.addProduct(product, quantity);
				}
				double totalPrice = order.getTotalPrice();
				System.out.println("");
				System.out.println("--------------------------------");
				System.out.println("Processing order for user: " + user.getUsername());
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
	
					OrderLogger.logOrder(user.getUsername(), totalPrice);
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
