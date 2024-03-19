package depaul.edu;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean continueLoop = true;
		
		Authenticate authentication = new Authenticate();
		
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
		    		authentication.authenticateUser(scanner);
		            break;
		        case 2:
		        	System.out.println("--------------");
		        	System.out.println("Create Account");
		            System.out.println("--------------");
		            authentication.registerUser(scanner);
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
		
	}

}
