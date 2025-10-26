package database;

import model.User;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDatabase {

    //Attributes
    protected ArrayList<User> users = new ArrayList<>();
    protected String filename;

    public UserDatabase(String filename) {
        this.filename = filename;
    }

    //Methods
    public void readFromFile() {
        try (Scanner sc = new Scanner(new File(this.filename))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                this.users.add(createRecordFrom(line));
            }
        } catch (Exception e) {
            System.out.println("Failed reading file " + this.filename + "!!");
        }
    }

    public User createRecordFrom(String line) {
        String[] parts = line.split(",");

        if (parts.length != 2) {
            System.out.println("Incorrect line format!");
            return null;
        }
        return new User(parts[0], parts[1]);
    }

    public boolean contains(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
            else{
                System.out.println("Incorrect username or password!");
            }
        }
        return false;
    }
}
