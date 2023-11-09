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
	
	//specification
	
	/**
	 * <p>
	 * Les mesures lues dans le fichier des relev�s de temp�ratures
	 * </p>
	 */
	private ArrayList<Mesure> lesMesures = new ArrayList<Mesure>();
	
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	private Controller myController;

	//implementation
	
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
	 * <p>Convertion d'une String en Date</p>
	 * 
	 * @param timestamp
	 * @return Date
	 * @throws ParseException
	 */
	private Date strToDate(Timestamp timestamp) throws ParseException {
	    SimpleDateFormat leFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH);
	    Date laDate = leFormat.parse(timestamp.toString());
	    return laDate;
	}



	
	/**
	 * <p>
	 * Filtre la collection des mesures en fonction des param&egrave;tres :
	 * </p>
	 * <ol>
	 * <li>la zone (null = toutes les zones)</li>
	 * <li>la date de d&eacute;but (null = &agrave; partir de l'origine)</li>
	 * <li>la date de fin (null = jusqu'&agrave; la fin)<br />
	 * </li>
	 * </ol>
	 */
	// public void filtrerLesMesure(String laZone, Date leDebut, Date lafin) {
	public ArrayList<Mesure> filtrerLesMesure(String laZone) {
		// Parcours de la collection
		// Ajout � laSelection des objets qui correspondent aux param�tres
		// Envoi de la collection
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
	 * <p>
	 * Retourne la collection des mesures
	 * </p>
	 * 
	 * @return ArrayList<Mesure>
	 */
	public ArrayList<Mesure> getLesMesures() {

		return lesMesures;
	}
	
	
	/**
	 * <p>Lit un fichier de type CSV (Comma Separated Values)</p>
	 * <p>Le fichier contient les mesures de temp&eacute;rature de la pelouse.</p>
	 * 
	 * @author J�r�me Valenti
	 * @return
	 * @throws ParseException
	 * @since 2.0.0
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

}




