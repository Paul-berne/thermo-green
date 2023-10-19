/**
 * @author J�r�me Valenti 
 */
package control;


import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import model.*;
import tools.BCrypt;
import DAO.*;
import view.*;

/**
 * <p>
 * Le cont&ocirc;lleur :
 * </p>
 * <ol>
 * <li>lit les mesures de temp�rature dans un fichier texte</li>
 * <li>retourne la collection des mesures<br />
 * </li>
 * </ol>
 * 
 * @author J�r�me Valenti
 * @version 2.0.0
 *
 */
public class Controller {

	//specification
	private ArrayList<Mesure> lesMesures;
	private User leUser;
	private DataFileMesure leStubMesure;
	private DataFileUser leStubUser;
	
	private Login myLogin;
	
	
	public Controller() throws ParseException {
	    
	    this.myLogin = new Login(this);
	    this.myLogin.setLocation(100, 100);
	    myLogin.setVisible(true);

	    this.leStubMesure = new DataFileMesure();
	    this.leStubMesure.lireMesureSQL("data\\mesures.csv");

	    // Maintenant, lesMesures de votre Controller est la même que celle de leStubMesure
	    this.lesMesures = leStubMesure.getLesMesures();
	}

	public boolean verifyUserLogin(String login, String password) {
	    DataFileUser dataFileUser = new DataFileUser();
	    try {
	        return dataFileUser.lireUserSQL(login, password);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
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


	public ArrayList<Mesure> getLesMesures() {
		return lesMesures;
	}


	public void setLesMesures(ArrayList<Mesure> lesMesures) {
		this.lesMesures = lesMesures;
	}


	public User getLeUser() {
		return leUser;
	}


	public void setLeUser(User leUser) {
		this.leUser = leUser;
	}


	public DataFileMesure getLeStubMesure() {
		return leStubMesure;
	}


	public DataFileUser getLeStubUser() {
		return leStubUser;
	}
	




	
}
