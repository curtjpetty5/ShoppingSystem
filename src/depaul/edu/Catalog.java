package depaul.edu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Catalog {
	private List<Product> catalog;
	String currentDirectory = System.getProperty("user.dir");
    private String catalogPath = currentDirectory + "/src/depaul/edu" + File.separator + "catalog.txt";
	private Cart cart;
	
	public Catalog(String catalogPath, Cart cart) {
		this.catalogPath = catalogPath;
		this.catalog = new ArrayList<>();
		this.cart = cart;
	}
	
	public void loadCatalog() {
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
	
	public void browseCatalog(Scanner scanner) {
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
			System.out.println("");
	    	System.out.print("Enter the product SKU: ");
	    	String sku = scanner.nextLine();
	    	
	    	System.out.println("");
	    	System.out.print("Enter quantity: ");
	    	int quantity = scanner.nextInt();
	    	scanner.nextLine();
	    	
	    	Product productToAdd = null;
	    	
	    	for (Product product : catalog) {
	            if (product.getSku().equals(sku)) {
	                productToAdd = product;
	            }
	        }
	        
	    	if (productToAdd != null) {
	    		cart.addProduct(productToAdd, quantity);
	    	} else {
	    		System.out.println("No product found for the given SKU");
	    	}
		}
    }
	
	public List<Product> getCatalog() {
		return catalog;
	}
}
