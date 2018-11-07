import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestAuditor {
	ArrayList<String> testStrings = new ArrayList<String>();

	private Auditor initializeTestAuditor() {
		testStrings.add("Audit line 1\n");
		testStrings.add("Audit line 2\n");
		testStrings.add("Audit line 3\n");
		testStrings.add("Audit line 4\n");
		testStrings.add("Audit line 5\n");
	}
	
	// Testing auditProcess()
	@Test
	public void testAuditProcess() {
		final Auditor aud = initializeTestAuditor();

		for (int i = 0; i < testStrings.size(); i++) {
			aud.auditProcess(testStrings.get(i));
		}

		assertTrue(aud.getAuditProcess().equals("Audit line 1\nAudit line 2\nAudit line 3\nAudit line 4\nAudit line 5\n"));
	}

	// Testing auditResult()
	@Test
	public void testAuditResult() {
	
	}

	// Testing auditSetup()
	@Test
	public void testAuditSetup() {
	
	}

	// Testing createAuditFile()
	@Test
	public void testCreateAuditFile() {
	
	}

}