package depaul.edu;

public class ProductFactory {
	public enum ProductType{
		TYPE1,
		TYPE2,
	}
	
	public static Product createProduct(ProductType type, String name, String description, String sku, double price) {
		switch (type) {
		case TYPE1:
			return new Product(name, description, sku, price); 
		case TYPE2:
			break;
		}
		return null;
	}
}
