/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author SteveUser
 */
public interface Calculations {
    
    /**
     * A single digit from the UI.
     * 
     * @param digit an integer
     * @return The result to display.
     */
    public String digit(String digit);
    /**
     * An arithmetic operator from the UI.
     * @param op
     * @return The result to display.
     */
    public String operator( String op);
    /**
     * The ClearEntry button on the UI has been pressed
     * so remove any digits typed so far.
     * 
     * @return A message to display. 
     */
    public String clearEntry();
    /**
     * The Clear button on the UI has been pressed
     * so all memory in the calculator is cleared.
     * 
     * @return A message to display. 
     */
    public String clear();
    /**
     * The Enter button on the UI has been pressed.
     * @return A message to display.
     */
    public String enterPressed();
    /**
     * A decimal point on the UI has been pressed.
     * @return A message to display.
     */
    public String addDecimal();
        
    }