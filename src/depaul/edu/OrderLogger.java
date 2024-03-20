package depaul.edu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderLogger {
	private static final String LOG_FILE = "order_log.txt";
	
	public static void logOrder(String username, double totalPrice) {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String message = timestamp + " - Order processed for user: " + username + ", Total price: $" + totalPrice;
		writeLog(message);
	}
	
	public static void writeLog(String message) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
			writer.write(message);
			writer.newLine();
			
		} catch (IOException e) {
			System.err.println("Error writing to log file: " + e.getMessage());
		}
	}
}
