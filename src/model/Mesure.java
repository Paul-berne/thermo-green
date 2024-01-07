/**
 * The Mesure class represents temperature measurements of the lawn.
 * Each sensor regularly measures the temperature, providing data in Fahrenheit,
 * localized by zones, and timestamped with date and time.
 * 
 * @author Paul Berne
 * @version 2.0.0
 */
package model;

import java.util.Date;

public class Mesure {
    
    /**
     * The zone number of the measured area.
     */
    private String numZone;
    
    /**
     * The date and time of the measurement in the format yy-mm-dd hh:mm.
     */
    private Date horoDate;  
    
    /**
     * The temperature value measured in Fahrenheit.
     */
    private float fahrenheit; 

    /**
     * Default constructor for the Mesure class.
     */
    public Mesure() {
        this.numZone = new String();
        this.horoDate = new Date();
        this.fahrenheit = 0.0f;    
    }
    
    /**
     * Parameterized constructor for the Mesure class.
     * 
     * @param pZone The zone number of the measured area.
     * @param pDate The date and time of the measurement.
     * @param pFahrenheit The temperature value measured in Fahrenheit.
     */
    public Mesure(String pZone, Date pDate, float pFahrenheit) {
        this.numZone = pZone;
        this.horoDate = pDate;
        this.fahrenheit = pFahrenheit;
    }

    /**
     * Getter for the zone number.
     * 
     * @return String The zone number.
     */
    public String getNumZone() {
        return numZone;
    }

    /**
     * Setter for the zone number.
     * 
     * @param numZone The zone number to set.
     */
    public void setNumZone(String numZone) {
        this.numZone = numZone;
    }

    /**
     * Getter for the date and time of the measurement.
     * 
     * @return Date The date and time of the measurement.
     */
    public Date getHoroDate() {
        return horoDate;
    }

    /**
     * Setter for the date and time of the measurement.
     * 
     * @param horoDate The date and time to set.
     */
    public void setHoroDate(Date horoDate) {
        this.horoDate = horoDate;
    }

    /**
     * Getter for the temperature value in Fahrenheit.
     * 
     * @return float The temperature value in Fahrenheit.
     */
    public float getFahrenheit() {
        return fahrenheit;
    }

    /**
     * Setter for the temperature value in Fahrenheit.
     * 
     * @param valFahrenheit The temperature value in Fahrenheit to set.
     */
    public void setFahrenheit(float valFahrenheit) {
        this.fahrenheit = valFahrenheit;
    }

    /**
     * Converts Fahrenheit to Celsius.
     * 
     * @since 2.0.0
     * @return float The temperature value in Celsius.
     */
    public float getCelsius() {
        return (fahrenheit - 32.0f) / 1.8f;
    }
}
