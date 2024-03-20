package depaul.edu;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private String username;
	private List<Product> products;
	private List<Integer> quantities;
	
	public Order(String username) {
		this.username = username;
		this.products = new ArrayList<>();
		this.quantities = new ArrayList<>();
	}
	
	public void addProduct(Product product, int quantity) {
		products.add(product);
		quantities.add(quantity);
	}

	public String getUsername() {
		return username;
	}

	public List<Product> getProducts() {
		return products;
	}

	public List<Integer> getQuantities() {
		return quantities;
	}

	public double getTotalPrice() {
		double totalPrice = 0;
		for (int i = 0; i <products.size(); i++) {
			totalPrice += products.get(i).getPrice() * quantities.get(i);
		}
		return totalPrice;
	}
}
