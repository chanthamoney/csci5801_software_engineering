
// File:         JunitTestSuite.java
// Created:      2018/11/08
// Last Changed: $Date: 2018/11/08 11:37:56 $
// Author:       <A HREF="mailto:silag001@umn.edu">Meghann Silagan</A>
//
// This code is copyright (c) 2018 University of Minnesota - Twin Cities
//

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
