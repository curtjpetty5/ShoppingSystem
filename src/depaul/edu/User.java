package depaul.edu;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String username;
	private String password;
	private Cart cart;
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.cart = new Cart();
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void displayAccountInfo() {
		System.out.println("");
		System.out.println("-----------------------");
		System.out.println("ACCOUNT INFO");
		System.out.println("Username: " + username);
		System.out.println("-----------------------");
		System.out.println("");
	}
}
