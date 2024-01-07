/**
 * The Start class contains the main method to launch the application.
 * It initializes the Controller to start the application.
 *
 * @author Paul Berne
 */
package control;

import java.sql.SQLException;
import java.text.ParseException;

public class Start {

    /**
     * The main method to start the application.
     *
     * @param args Command-line arguments (not used in this application).
     * @throws ParseException Thrown if there is an error parsing data.
     * @throws SQLException    Thrown if there is an SQL-related error.
     */
    public static void main(String[] args) throws ParseException, SQLException {
        // Initialize the Controller to start the application
        Controller leController = new Controller();
    }
}
