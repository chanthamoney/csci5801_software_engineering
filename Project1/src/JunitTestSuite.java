
/**
 * File: JunitTestSuite.java
 * Date Created: 11/08/2018
 * Last Update: Nov 11, 2018 2:41:26 PM
 * Author: <A HREF="mailto:silag001@umn.edu">Meghann Silagan</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import votingsystems.TestIRV;
import votingsystems.TestIRVBallot;
import votingsystems.TestIRVCandidate;
import votingsystems.TestOPLV;

/**
 * The Class JunitTestSuite.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ TestMariahEP.class, TestIRVBallot.class, TestIRVCandidate.class, TestIRV.class, TestOPLV.class })
public class JunitTestSuite {
}
