package depaul.edu;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<Product, Integer> items;
	private String cartFilePath;
	
	public Cart() {
		this.items = new HashMap<>();
	}
	
	public void setCartFilePath(String cartFilePath) {
		this.cartFilePath = cartFilePath;
	}
	
	public void addProduct(Product product, int quantity) {
		if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
		
		if (items.containsKey(product)) {
			items.put(product, items.get(product) + quantity);
		} else {
			items.put(product, quantity);
		}
   		
		System.out.println("");
		System.out.println("Adding QTY " + quantity + " of " + product.getDescription() + " to cart");
		
		if (items.containsKey(product)) {
			items.put(product, items.get(product) + quantity);
		} else {
			items.put(product, quantity);
		}
		
		
		System.out.println("---------------------------------");
		System.out.println("");
	}
	
	public void saveCart(String filename) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<Product, Integer> entry : items.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                writer.write(product.getName() + "," + product.getDescription() + "," + product.getSku() + ","+ product.getPrice() + "," + quantity);
                writer.newLine();
            }
            System.out.println("Cart data saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving cart data to file: " + e.getMessage());
        }
	}
	
	public void loadCart() {
		File file = new File(cartFilePath);
		if (file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(cartFilePath))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length == 5) {
	                    String name = parts[0];
	                    String description = parts[1];
	                    String sku = parts[2];
	                    double price = Double.parseDouble(parts[3]);
	                    int quantity = Integer.parseInt(parts[4]);
	                    
	                    Product product = new Product(name, description, sku, price);
	                    items.put(product, quantity);
	                } else {
	                    System.out.println("Invalid format in cart data file: " + line);
	                }
	            }
	            System.out.println("Cart data loaded from file: " + cartFilePath);
	            System.out.println("");
	        } catch (IOException | NumberFormatException e) {
	            System.err.println("Error loading cart data from file: " + e.getMessage());
	        }
		} 
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
	
	public Map<Product, Integer> getItems() {
		return items;
	}
	 
	public void clearCart() {
		items.clear();
		System.out.println("");
		System.out.println("------------");
		System.out.println("CART CLEARED");
		System.out.println("------------");
		System.out.println("");
	}
	
	public void clearCartSilent() {
		items.clear();
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
