package depaul.edu;

public class Product implements ProductInterface {
	private String name;
	private String description;
	private String sku;
	private double price;
	
	public Product(String name, String description, String sku, double price) {
		this.name = name;
		this.description = description;
		this.sku = sku;
		this.price = price;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getSku() {
		return sku;
	}

	@Override
	public double getPrice() {
		return price;
	}
}
