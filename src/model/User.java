/**
 * The User class represents a user in the system.
 * 
 * @author Paul Berne
 * @since 2.0.0
 */
package model;

public class User {

    /**
     * The login username of the user.
     */
    private String login;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * Parameterized constructor for the User class.
     * 
     * @param login The login username of the user.
     * @param password The password of the user.
     */
    public User(String login, String password) {
        super();
        this.login = login;
        this.password = password;
    }

    /**
     * Returns a string representation of the User object.
     * 
     * @return String A string representation of the User object.
     */
    @Override
    public String toString() {
        return "User [login=" + login + ", password=" + password + "]";
    }

    /**
     * Getter for the login username.
     * 
     * @return String The login username.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Getter for the password.
     * 
     * @return String The password.
     */
    public String getPassword() {
        return password;
    }
}
