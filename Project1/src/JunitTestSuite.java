import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({ TestIRVBallot.class, TestIRVCandidate.class, TestIRV.class, TestOPLV.class })

public class JunitTestSuite {
}
