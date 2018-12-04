/**
 * File: InvalidFileException.java
 * Date Created: 11/08/2018
 * Last Update: Dec 4, 2018 5:45:23 PM
 * Author: <A HREF="tsaix223@umn.edu">Christine Tsai</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */
package main;

/**
 * The Class InvalidFileException.
 */
public class InvalidFileException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4483520665884643068L;

    /**
     * Instantiates a new invalid file exception.
     *
     * @param message the message
     */
    public InvalidFileException(String message) {
	super(message);
    }
}