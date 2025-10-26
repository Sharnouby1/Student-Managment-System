package model;

public class User {

    //Attributes
    private String username;
    private String password;

    //Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
