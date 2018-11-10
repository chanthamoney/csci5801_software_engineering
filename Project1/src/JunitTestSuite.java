
// File:         TestIRV.java
// Created:      2018/11/08
// Last Changed: $Date: 2018/11/08 11:37:56 $
// Author:       <A HREF="mailto:silag001@umn.edu">Meghann Silagan</A>
//
// This code is copyright (c) 2018 University of Minnesota - Twin Cities
//

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import votingsystems.TestIRV;
import votingsystems.TestIRVBallot;
import votingsystems.TestIRVCandidate;
import votingsystems.TestOPLV;

@RunWith(Suite.class)

@Suite.SuiteClasses({ TestIRVBallot.class, TestIRVCandidate.class, TestIRV.class, TestOPLV.class })

public class JunitTestSuite {
}
