/**
 * Configuration class for handling properties and encryption.
 *
 * - Initializes properties and encryption with a default password.
 * - Loads configuration from the "vtg.cfg" file.
 * - Provides a method to read and decrypt a specific property.
 *
 * @author Paul Berne
 */
package control;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Configuration {

    private Properties properties;
    private StandardPBEStringEncryptor encryptor;

    /**
     * Constructor for the Configuration class.
     * Initializes properties and encryption with a default password.
     * Loads configuration from the "vtg.cfg" file.
     */
    public Configuration() {
        this.properties = new Properties();
        this.encryptor = new StandardPBEStringEncryptor();
        this.encryptor.setPassword("P@ssw0rdsio");

        // Load configuration from vtg.cfg file
        try (InputStream input = getClass().getResourceAsStream("./data/vtg.cfg")) {
            if (input == null) {
                throw new RuntimeException("Unable to find vtg.cfg");
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads and decrypts a property from the configuration file.
     *
     * @param theKey The key of the property to read.
     * @return The decrypted value of the specified property.
     */
    public String readProperty(String theKey) {
        return encryptor.decrypt(properties.getProperty(theKey));
    }
}
