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
	// specification
	private Controller myController;
	
	//Implementation
	public DataFileUser() {
		
	}
	
	public boolean lireUserSQL(String theLogin, String thePassword) throws ParseException {
		boolean verif = false;
	    Connection connection = null;
	    Statement statement = null;
	    ResultSet resultSet = null;

	    try {
	        // Établir une connexion à la base de données (assurez-vous d'avoir les détails de connexion appropriés)
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thermogreen", "root", "Paulberne13?");

	        // Créer une instruction SQL pour exécuter la requête SELECT
	        statement = connection.createStatement();
	        String sqlQuery = "SELECT login, password FROM user";
	        resultSet = statement.executeQuery(sqlQuery);

	        while (resultSet.next()) {
	            String login = resultSet.getString("login");
	            String password = resultSet.getString("password");
	            if (login.equals(theLogin) && BCrypt.checkpw(thePassword,password)) {
	                verif = true;
	                break; // Sortir de la boucle dès que la correspondance est trouvée
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

	
}
