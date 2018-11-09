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
