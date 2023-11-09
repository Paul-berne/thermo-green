/**
 * @author Paul Berne
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
 * <p>
 * Le cont&ocirc;lleur :
 * </p>
 * <ol>
 * <li>lit les mesures de temp�rature dans un fichier texte</li>
 * <li>retourne la collection des mesures<br />
 * </li>
 * </ol>
 * 
 * @author Paul Berne
 * @version 2.0.0
 *
 */
public class Controller {

	//specification
	private ArrayList<Mesure> lesMesures;
	private User leUser;
	private DataFileMesure leDAObMesure;
	private DataFileUser leDAOUser;
	
	private Login myLogin;
	
	private Configuration myConfiguration;
	
	
	



	public Controller() throws ParseException, SQLException {
		this.myConfiguration = new Configuration();
		
	    this.myLogin = new Login(this);
	    this.myLogin.setLocation(100, 100);
	    myLogin.setVisible(true);

	    this.leDAObMesure = new DataFileMesure(this);
	    this.leDAObMesure.lireMesureSQL();

	    this.lesMesures = leDAObMesure.getLesMesures();
	    
	    
	}



	public boolean verifyUserLogin(String login, String password) {
	    DataFileUser dataFileUser = new DataFileUser(this);
	    try {
	        return dataFileUser.lireUserSQL(login, password);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public void CreateFrameChangePassword(String thelogin) {
		SwingUtilities.invokeLater(() -> {
        ChangePassword monIHM = null;
        monIHM = new ChangePassword(this, thelogin);
        monIHM.setVisible(true);
		});

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
		return leDAObMesure;
	}


	public DataFileUser getLeStubUser() {
		return leDAOUser;
	}
	
	public Configuration getMyConfiguration() {
		return myConfiguration;
	}

}
