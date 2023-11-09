package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Configuration {
	
	private Properties properties;
    private StandardPBEStringEncryptor encryptor;
    
	public Configuration() {

		this.properties = new Properties();
        this.encryptor = new StandardPBEStringEncryptor();
        this.encryptor.setPassword("P@ssw0rdsio");

        try (FileInputStream input = new FileInputStream("./data/vtg.cfg")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	
	public String readProperty(String theKey) {
		return encryptor.decrypt(properties.getProperty(theKey));
	}
}
