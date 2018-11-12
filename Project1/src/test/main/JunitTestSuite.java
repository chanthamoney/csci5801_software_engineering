/**
 * File: JunitTestSuite.java
 * Date Created: 11/08/2018
 * Last Update: Nov 12, 2018 12:27:15 AM
 * Author: <A HREF="mailto:silag001@umn.edu">Meghann Silagan</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */
package test.main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.votingsystems.TestIRV;
import test.votingsystems.TestIRVBallot;
import test.votingsystems.TestIRVCandidate;
import test.votingsystems.TestOPLV;

/**
 * The Class JunitTestSuite.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ TestMariahEP.class, TestIRVBallot.class, TestIRVCandidate.class, TestIRV.class, TestOPLV.class })
public class JunitTestSuite {
}
