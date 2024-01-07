/**
 * The DataFileUser class handles the data access for user information.
 * It connects to a database, reads user data, and provides methods for user authentication and password change.
 * 
 * - Connects to the database using configuration properties.
 * - Authenticates a user by comparing login credentials with stored information.
 * - Changes the password of a user in the database.
 * 
 * Note: Passwords are securely hashed and verified using the BCrypt library.
 * 
 * @author Paul Berne
 * @since 2.0.0
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import control.Controller;
import tools.BCrypt;

public class DataFileUser {

    // Specification
    private Controller myController;
    
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    // Implementation

    /**
     * Constructor for the DataFileUser class.
     * Connects to the database using configuration properties.
     * 
     * @param theController The main controller for the application.
     */
    public DataFileUser(Controller theController) {
        try {
            this.myController = theController;
            String dbname = this.myController.getMyConfiguration().readProperty("database.url");
            String username = this.myController.getMyConfiguration().readProperty("database.username");
            String password = this.myController.getMyConfiguration().readProperty("database.password");

            this.connection = DriverManager.getConnection(dbname, username, password);
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Authenticates a user by comparing login credentials with stored information.
     * 
     * @param theLogin The user login.
     * @param thePassword The user password.
     * @return True if the authentication is successful, false otherwise.
     * @throws ParseException Thrown if there is an error parsing data.
     */
    public boolean lireUserSQL(String theLogin, String thePassword) throws ParseException {
        boolean verif = false;

        try {
            String sqlQuery = "SELECT login, password FROM user";
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                if (login.equals(theLogin) && BCrypt.checkpw(thePassword, password)) {
                    verif = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return verif;
    }

    /**
     * Changes the password of a user in the database.
     * 
     * @param theLogin The user login.
     * @param thePassword The new password for the user.
     */
    public void ChangePasswordUser(String theLogin, String thePassword) {
        String passwordToHash = thePassword;
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(passwordToHash, salt);

        String sqlQuery = "UPDATE user SET password = '" + hashedPassword + "' WHERE login = '" + theLogin + "'";
        try {
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
