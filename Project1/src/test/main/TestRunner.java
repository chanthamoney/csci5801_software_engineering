package test.main;

/**
 * File: TestRunner.java
 * Date Created: 11/08/2018
 * Last Update: Nov 11, 2018 2:41:10 PM
 * Author: <A HREF="mailto:silag001@umn.edu">Meghann Silagan</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * The Class TestRunner.
 */
public class TestRunner {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
	final Result result = JUnitCore.runClasses(JunitTestSuite.class);

	result.getFailures().forEach(failure -> System.out.println(failure.toString()));

	System.out.println(result.wasSuccessful());
    }
}
