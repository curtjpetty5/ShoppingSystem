package depaul.edu;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<Product, Integer> items;
	
	public Cart() {
		items = new HashMap<>();
	}
	
	public void addProduct(Product product, int quantity) {
		System.out.println("");
		if (quantity <= 0) {
			throw new IllegalArgumentException("Quantity must be greater than zero.");
		}
		System.out.println("Adding QTY " + quantity + " of " + product.getDescription() + " to cart");
		if (items.containsKey(product)) {
			items.put(product, items.get(product) + quantity);
		} else {
			items.put(product, quantity);
		}
		System.out.println("---------------------------------");
		System.out.println("");
	}
	
	public void viewItems() {
		 System.out.println("");
		 System.out.println("CART:");
		 System.out.println("---------------------------------");
		 System.out.println("SKU - Name - Description - Price - Quantity - Line Item Cost");
		 for (Map.Entry<Product, Integer> entry: items.entrySet()) {
			 Product product = entry.getKey();
			 int quantity = entry.getValue();
			 double lineCost = product.getPrice() * quantity;
			 System.out.println(product.getSku() + " - " + product.getName()+ " - " + product.getDescription() + " - " + product.getPrice() + " - " + quantity + " - $" + lineCost);
		 }
		 System.out.println("");
		 System.out.println("Total price: $" + calculateTotalPrice());
		 System.out.println("---------------------------------");
		 System.out.println("");
	 }
	 
	public double calculateTotalPrice() {
		 double totalPrice = 0;
		 for (Map.Entry<Product, Integer> entry : items.entrySet()) {
			 Product product = entry.getKey();
			 int quantity = entry.getValue();
			 totalPrice += product.getPrice() * quantity;
		 }
		 return totalPrice;
	 }
}
