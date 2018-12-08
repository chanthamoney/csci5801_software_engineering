/**
 * File: TestInvalidFileException.java
 * Date Created: 11/08/2018
 * Last Update: Dec 4, 2018 5:47:45 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */
package test.main;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.InvalidFileException;

/**
 * The Class TestInvalidFileException.
 */
public class TestInvalidFileException {

    /**
     * Test that throwing the exception with a string throws that exception with the
     * given message.
     *
     * @throws InvalidFileException the invalid file exception
     */
    @Test
    public void testInvalidFileException() throws InvalidFileException {
	try {
	    throw new InvalidFileException("Test message.");
	} catch (InvalidFileException e) {
	    assertTrue("Test message.".equals(e.getMessage()));
	}
    }

}
