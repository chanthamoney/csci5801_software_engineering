
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class TestAuditor {
    ArrayList<String> testStrings = new ArrayList<String>();

    private Auditor initializeTestAuditor() {
	testStrings.add("Audit line 1\n");
	testStrings.add("Audit line 2\n");
	testStrings.add("Audit line 3\n");
	testStrings.add("Audit line 4\n");
	testStrings.add("Audit line 5\n");
	return new Auditor();
    }

    // Testing auditProcess()
    @Test
    public void testAuditProcess() throws IOException {
	final Auditor aud = initializeTestAuditor();

	for (int i = 0; i < testStrings.size(); i++) {
	    aud.auditProcess(testStrings.get(i));
	}

	String fileName = String.format("TEST_AUDIT_%d", System.currentTimeMillis());
	final File fileDNE = new File(fileName);
	assertFalse(fileDNE.isFile());

	aud.createAuditFile(fileName);

	final File file = new File(fileName);
	final Scanner fileReader = new Scanner(file);

	final String l1 = fileReader.nextLine();
	final String l2 = fileReader.nextLine();
	final String l3 = fileReader.nextLine();
	final String l4 = fileReader.nextLine();
	final String l5 = fileReader.nextLine();
	fileReader.close();
	file.delete();
	assertTrue((l1 + l2 + l3 + l4 + l5).equals("Audit line 1Audit line 2Audit line 3Audit line 4Audit line 5"));
    }

    // Testing auditResult()
    @Test
    public void testAuditResult() throws IOException {
	final Auditor aud = initializeTestAuditor();

	for (int i = 0; i < testStrings.size(); i++) {
	    aud.auditResult(testStrings.get(i));
	}

	String fileName = String.format("TEST_AUDIT_%d", System.currentTimeMillis());
	final File fileDNE = new File(fileName);
	assertFalse(fileDNE.isFile());

	aud.createAuditFile(fileName);

	final File file = new File(fileName);
	final Scanner fileReader = new Scanner(file);

	final String l1 = fileReader.nextLine();
	final String l2 = fileReader.nextLine();
	final String l3 = fileReader.nextLine();
	final String l4 = fileReader.nextLine();
	final String l5 = fileReader.nextLine();
	fileReader.close();
	file.delete();
	assertTrue((l1 + l2 + l3 + l4 + l5).equals("Audit line 1Audit line 2Audit line 3Audit line 4Audit line 5"));
    }

    // Testing auditSetup()
    @Test
    public void testAuditSetup() throws IOException {
	final Auditor aud = initializeTestAuditor();

	for (int i = 0; i < testStrings.size(); i++) {
	    aud.auditSetup(testStrings.get(i));
	}

	String fileName = String.format("TEST_AUDIT_%d", System.currentTimeMillis());
	final File fileDNE = new File(fileName);
	assertFalse(fileDNE.isFile());

	aud.createAuditFile(fileName);

	final File file = new File(fileName);
	final Scanner fileReader = new Scanner(file);

	final String l1 = fileReader.nextLine();
	final String l2 = fileReader.nextLine();
	final String l3 = fileReader.nextLine();
	final String l4 = fileReader.nextLine();
	final String l5 = fileReader.nextLine();
	fileReader.close();
	file.delete();
	assertTrue((l1 + l2 + l3 + l4 + l5).equals("Audit line 1Audit line 2Audit line 3Audit line 4Audit line 5"));
    }

    // Testing audit file output structure
    @Test
    public void testAuditStructure() throws IOException {

    }

    // Testing createAuditFile()
    @Test
    public void testCreateAuditFile() throws IOException {

	final Auditor aud = initializeTestAuditor();
	String fileName = String.format("TEST_AUDIT_%d", System.currentTimeMillis());
	final File fileDNE = new File(fileName);
	assertFalse(fileDNE.isFile());

	aud.createAuditFile(fileName);

	final File file = new File(fileName);
	assertTrue(file.isFile());
	file.delete();
    }

}