import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class TestRunner {
    public static void main(String[] args) {
	final Result result = JUnitCore.runClasses(JunitTestSuite.class);

	result.getFailures().forEach(failure -> System.out.println(failure.toString()));

	System.out.println(result.wasSuccessful());
    }
}
