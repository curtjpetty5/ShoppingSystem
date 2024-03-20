package depaul.edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserAccess {
	private Map<String, User> users;
	String currentDirectory = System.getProperty("user.dir");
    String credentialsPath = currentDirectory + "/src/depaul/edu" + File.separator + "credentials.txt";
	
	public UserAccess() {
		users = new HashMap<>();
		loadUsers();
	}
	
	private void loadUsers() {
		try (BufferedReader reader = new BufferedReader(new FileReader(credentialsPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    User user = new User(username, password);
                    users.put(username, user);
                }
            }
        } catch (FileNotFoundException e) {
        	try {
        		File file = new File(credentialsPath);
        		file.createNewFile();
        	} catch (IOException ex) {
                System.err.println("Error creating credentials file: " + ex.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error loading users from file: " + e.getMessage());
        }
	}
	
	private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(credentialsPath))) {
            for (User user : users.values()) {
            	writer.write(user.getUsername() + ":" + user.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving user to file: " + e.getMessage());
        }
    }
	
	public Map<String, User> getUsers() {
		return users;
	}
	
	public void addUser(User user) {
		users.put(user.getUsername(), user);
		saveUsers();
	}
}
