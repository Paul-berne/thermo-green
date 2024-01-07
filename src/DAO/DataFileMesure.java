/**
 * The DataFileMesure class handles the data access for temperature measurements.
 * It connects to a database, reads data, and provides methods to filter and retrieve measurements.
 * 
 * - Connects to the database using configuration properties.
 * - Converts a Timestamp to Date format.
 * - Filters the collection of measurements based on parameters (zone).
 * - Reads temperature measurements from an SQL database.
 * - Provides methods to retrieve the collection of measurements.
 * 
 * @author Paul Berne
 * @since 2.0.0
 */
package DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Locale;

import control.Controller;
import model.Mesure;

public class DataFileMesure {
	
	// Specification
	
	/**
	 * The temperature measurements read from the temperature records file.
	 */
	private ArrayList<Mesure> lesMesures = new ArrayList<Mesure>();
	
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	private Controller myController;

	// Implementation
	
	/**
	 * Constructor for the DataFileMesure class.
	 * Connects to the database using configuration properties.
	 * 
	 * @param theController The main controller for the application.
	 * @throws SQLException Thrown if there is an SQL-related error.
	 */
	public DataFileMesure(Controller theController) throws SQLException {
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
	 * Converts a Timestamp to a Date format.
	 * 
	 * @param timestamp The Timestamp to convert.
	 * @return Date The converted Date.
	 * @throws ParseException Thrown if there is an error parsing the timestamp.
	 */
	private Date strToDate(Timestamp timestamp) throws ParseException {
	    SimpleDateFormat leFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH);
	    Date laDate = leFormat.parse(timestamp.toString());
	    return laDate;
	}

	/**
	 * Filters the collection of measurements based on the specified zone.
	 * 
	 * @param laZone The zone to filter by (null = all zones).
	 * @return ArrayList<Mesure> The filtered collection of measurements.
	 */
	public ArrayList<Mesure> filtrerLesMesure(String laZone) {
		ArrayList<Mesure> laSelection = new ArrayList<Mesure>();
		for (Mesure mesure : lesMesures) {
			if (laZone.compareTo("*") == 0) {
				laSelection.add(mesure);
			} else {
				if (laZone.compareTo(mesure.getNumZone()) == 0) {
					laSelection.add(mesure);
				}
			}
		}
		return laSelection;
	}

	/**
	 * Reads temperature measurements from an SQL database.
	 * 
	 * @throws ParseException Thrown if there is an error parsing data.
	 */
	public void lireMesureSQL() throws ParseException {
		try {
	        String sqlQuery = "SELECT numero, date_mesure, fahrenheit FROM mesure";
	        resultSet = statement.executeQuery(sqlQuery);

	        while (resultSet.next()) {
	            String numZone = resultSet.getString("numero");
	            Date horoDate = strToDate(resultSet.getTimestamp("date_mesure"));
	            float fahrenheit = resultSet.getFloat("fahrenheit");

	            Mesure laMesure = new Mesure(numZone, horoDate, fahrenheit);
	            lesMesures.add(laMesure);
	        }
	    } catch (Exception e) {
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
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	/**
	 * Returns the collection of temperature measurements.
	 * 
	 * @return ArrayList<Mesure> The collection of temperature measurements.
	 */
	public ArrayList<Mesure> getLesMesures() {
		return lesMesures;
	}
}
