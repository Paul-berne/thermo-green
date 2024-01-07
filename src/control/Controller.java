/**
 * The Controller class manages the interaction between the model, view, and data access objects.
 *
 * - Reads temperature measurements from a text file.
 * - Returns the collection of measurements.
 *
 * @author Paul Berne
 * @version 2.0.0
 */
package control;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import model.*;
import DAO.*;
import view.*;

/**
 * The Controller class orchestrates the flow of data and actions in the application.
 * It reads temperature measurements from a file, manages user authentication, and creates GUIs.
 *
 * @author Paul Berne
 * @version 2.0.0
 */
public class Controller {

    // Specification
    private ArrayList<Mesure> lesMesures;
    private User leUser;
    private DataFileMesure leDAObMesure;
    private DataFileUser leDAOUser;

    private Login myLogin;
    private Configuration myConfiguration;

    /**
     * Constructor for the Controller class.
     *
     * @throws ParseException Thrown if there is an error parsing data.
     * @throws SQLException    Thrown if there is an SQL-related error.
     */
    public Controller() throws ParseException, SQLException {
        this.myConfiguration = new Configuration();

        // Initialize and display the login screen
        this.myLogin = new Login(this);
        this.myLogin.setLocation(100, 100);
        myLogin.setVisible(true);

        // Initialize data access objects and read temperature measurements from SQL
        this.leDAObMesure = new DataFileMesure(this);
        this.leDAObMesure.lireMesureSQL();
        this.lesMesures = leDAObMesure.getLesMesures();
    }

    /**
     * Verifies user login credentials.
     *
     * @param login    The user login.
     * @param password The user password.
     * @return True if the login is successful, false otherwise.
     */
    public boolean verifyUserLogin(String login, String password) {
        DataFileUser dataFileUser = new DataFileUser(this);
        try {
            return dataFileUser.lireUserSQL(login, password);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Creates a frame for changing the user's password.
     *
     * @param thelogin The user login for which the password is to be changed.
     */
    public void CreateFrameChangePassword(String thelogin) {
        SwingUtilities.invokeLater(() -> {
            ChangePassword monIHM = new ChangePassword(this, thelogin);
            monIHM.setVisible(true);
        });
    }

    /**
     * Creates a console GUI for the application.
     */
    public void CreateConsoleGUI() {
        SwingUtilities.invokeLater(() -> {
            ConsoleGUI monIHM = null;
            try {
                monIHM = new ConsoleGUI(this);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            monIHM.setVisible(true);
        });
    }

    /**
     * Getter for the collection of temperature measurements.
     *
     * @return The collection of temperature measurements.
     */
    public ArrayList<Mesure> getLesMesures() {
        return lesMesures;
    }

    /**
     * Setter for the collection of temperature measurements.
     *
     * @param lesMesures The collection of temperature measurements to set.
     */
    public void setLesMesures(ArrayList<Mesure> lesMesures) {
        this.lesMesures = lesMesures;
    }

    /**
     * Getter for the current user.
     *
     * @return The current user.
     */
    public User getLeUser() {
        return leUser;
    }

    /**
     * Setter for the current user.
     *
     * @param leUser The user to set.
     */
    public void setLeUser(User leUser) {
        this.leUser = leUser;
    }

    /**
     * Getter for the DataFileMesure object.
     *
     * @return The DataFileMesure object.
     */
    public DataFileMesure getLeStubMesure() {
        return leDAObMesure;
    }

    /**
     * Getter for the DataFileUser object.
     *
     * @return The DataFileUser object.
     */
    public DataFileUser getLeStubUser() {
        return leDAOUser;
    }

    /**
     * Getter for the Configuration object.
     *
     * @return The Configuration object.
     */
    public Configuration getMyConfiguration() {
        return myConfiguration;
    }
}
