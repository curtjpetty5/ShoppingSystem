package depaul.edu;

import java.io.File;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean continueLoop = true;
		
		UserAccess userBase = new UserAccess();
		Authenticate authentication = new Authenticate(userBase);
		Registration registration = new Registration(userBase);
		
		String currentDirectory = System.getProperty("user.dir");
        String catalogFilePath = currentDirectory + File.separator + "catalog.txt";
		
        Catalog catalog = new Catalog(catalogFilePath, Cart.getInstance());
		catalog.loadCatalog();
		
		Checkout checkout = new Checkout(Cart.getInstance(), new OrderLogger());
		
		AccountMenu accountMenu = new AccountMenu(catalog, Cart.getInstance(), checkout);
		
		while (continueLoop) {
			System.out.println("+-------------------+");
			System.out.println("|     MAIN MENU     |");
			System.out.println("+-------------------+");
			System.out.println("1 - Login");
			System.out.println("2 - Create account");
			System.out.println("3 - Exit");
			System.out.println("");
			System.out.print("Enter an option to proceed: ");
			int begin = scanner.nextInt();
			scanner.nextLine();
			
			switch (begin) {
		        case 1:
		        	System.out.println("");
		    		authentication.authenticateUser(scanner, accountMenu);
		            break;
		        case 2:
		        	System.out.println("");
		            registration.registerUser(scanner);
		        	break;
		        case 3:
		        	System.out.println("");
		        	System.out.println("********");
		        	System.out.println("EXITING!");
		        	System.out.println("********");
		        	continueLoop = false;
		        	break;
			}
		}
		scanner.close();
	}
}
