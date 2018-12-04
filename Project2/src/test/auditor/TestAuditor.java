/**
 * File: TestAuditor.java
 * Date Created: 11/08/2018
 * Last Update: Dec 4, 2018 5:47:22 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

package test.auditor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import auditor.Auditor;

/**
 * The Class TestAuditor.
 */
public class TestAuditor {

    /** The test strings. */
    ArrayList<String> testStrings = new ArrayList<>();

    /**
     * Initialize test auditor.
     *
     * @return the auditor
     */
    private Auditor initializeTestAuditor() {
	testStrings.add(String.format("Audit line 1%n"));
	testStrings.add(String.format("Audit line 2%n"));
	testStrings.add(String.format("Audit line 3%n"));
	testStrings.add(String.format("Audit line 4%n"));
	testStrings.add(String.format("Audit line 5%n"));
	return new Auditor();
    }

    /**
     * Test audit process.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testAuditProcess() throws IOException {
	final Auditor aud = initializeTestAuditor();

	testStrings.forEach(aud::auditProcess);

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
	assertTrue("Audit line 1Audit line 2Audit line 3Audit line 4Audit line 5".equals((l1 + l2 + l3 + l4 + l5)));
    }

    /**
     * Test audit result.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testAuditResult() throws IOException {
	final Auditor aud = initializeTestAuditor();

	testStrings.forEach(aud::auditResult);

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
	assertTrue("Audit line 1Audit line 2Audit line 3Audit line 4Audit line 5".equals((l1 + l2 + l3 + l4 + l5)));
    }

    /**
     * Test audit setup.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testAuditSetup() throws IOException {
	final Auditor aud = initializeTestAuditor();

	testStrings.forEach(aud::auditSetup);

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
	assertTrue("Audit line 1Audit line 2Audit line 3Audit line 4Audit line 5".equals((l1 + l2 + l3 + l4 + l5)));
    }

    /**
     * Test audit structure with setup, process, results.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testAuditStructureSPR() throws IOException {
	final Auditor aud = initializeTestAuditor();

	// add strings to auditProcess, auditResult, and auditSetup
	testStrings.forEach(el -> {
	    aud.auditProcess("p_" + el);
	    aud.auditResult("r_" + el);
	    aud.auditSetup("s_" + el);
	});

	String fileName = String.format("TEST_AUDIT_%d", System.currentTimeMillis());
	final File fileDNE = new File(fileName);
	assertFalse(fileDNE.isFile());

	aud.createAuditFile(fileName);

	final File file = new File(fileName);
	final Scanner fileReader = new Scanner(file);

	StringBuilder sb = new StringBuilder();
	while (fileReader.hasNextLine()) {
	    sb.append(fileReader.nextLine());
	}

	fileReader.close();
	file.delete();

	assertTrue(("s_Audit line 1s_Audit line 2s_Audit line 3s_Audit line 4s_Audit line 5"
		+ "- - - - - - - - - - - - - - - - - - - -"
		+ "p_Audit line 1p_Audit line 2p_Audit line 3p_Audit line 4p_Audit line 5"
		+ "- - - - - - - - - - - - - - - - - - - -"
		+ "r_Audit line 1r_Audit line 2r_Audit line 3r_Audit line 4r_Audit line 5").equals((sb.toString())));
    }

    /**
     * Test audit structure with setup and process.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testAuditStructureSP() throws IOException {
	final Auditor aud = initializeTestAuditor();

	// add strings to auditProcess, and auditSetup
	testStrings.forEach(el -> {
	    aud.auditProcess("p_" + el);
	    aud.auditSetup("s_" + el);
	});

	String fileName = String.format("TEST_AUDIT_%d", System.currentTimeMillis());
	final File fileDNE = new File(fileName);
	assertFalse(fileDNE.isFile());

	aud.createAuditFile(fileName);

	final File file = new File(fileName);
	final Scanner fileReader = new Scanner(file);

	StringBuilder sb = new StringBuilder();
	while (fileReader.hasNextLine()) {
	    sb.append(fileReader.nextLine());
	}

	fileReader.close();
	file.delete();

	assertTrue(("s_Audit line 1s_Audit line 2s_Audit line 3s_Audit line 4s_Audit line 5"
		+ "- - - - - - - - - - - - - - - - - - - -"
		+ "p_Audit line 1p_Audit line 2p_Audit line 3p_Audit line 4p_Audit line 5").equals((sb.toString())));
    }

    /**
     * Test audit structure with setup and result.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testAuditStructureSR() throws IOException {
	final Auditor aud = initializeTestAuditor();

	// add strings to auditSetup auditResult
	testStrings.forEach(el -> {
	    aud.auditResult("r_" + el);
	    aud.auditSetup("s_" + el);
	});

	String fileName = String.format("TEST_AUDIT_%d", System.currentTimeMillis());
	final File fileDNE = new File(fileName);
	assertFalse(fileDNE.isFile());

	aud.createAuditFile(fileName);

	final File file = new File(fileName);
	final Scanner fileReader = new Scanner(file);

	StringBuilder sb = new StringBuilder();
	while (fileReader.hasNextLine()) {
	    sb.append(fileReader.nextLine());
	}

	fileReader.close();
	file.delete();

	assertTrue(("s_Audit line 1s_Audit line 2s_Audit line 3s_Audit line 4s_Audit line 5"
		+ "- - - - - - - - - - - - - - - - - - - -"
		+ "r_Audit line 1r_Audit line 2r_Audit line 3r_Audit line 4r_Audit line 5").equals((sb.toString())));
    }

    /**
     * Test audit structure with process and result.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testAuditStructurePR() throws IOException {
	final Auditor aud = initializeTestAuditor();

	// add strings to auditSetup auditResult
	testStrings.forEach(el -> {
	    aud.auditProcess("p_" + el);
	    aud.auditResult("r_" + el);
	});

	String fileName = String.format("TEST_AUDIT_%d", System.currentTimeMillis());
	final File fileDNE = new File(fileName);
	assertFalse(fileDNE.isFile());

	aud.createAuditFile(fileName);

	final File file = new File(fileName);
	final Scanner fileReader = new Scanner(file);

	StringBuilder sb = new StringBuilder();
	while (fileReader.hasNextLine()) {
	    sb.append(fileReader.nextLine());
	}

	fileReader.close();
	file.delete();

	assertTrue(("p_Audit line 1p_Audit line 2p_Audit line 3p_Audit line 4p_Audit line 5"
		+ "- - - - - - - - - - - - - - - - - - - -"
		+ "r_Audit line 1r_Audit line 2r_Audit line 3r_Audit line 4r_Audit line 5").equals((sb.toString())));
    }

    /**
     * Test create audit file.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testCreateAuditFile() throws IOException {
	final Auditor aud = initializeTestAuditor();
	String fileName = String.format("TEST_AUDIT_%d", System.currentTimeMillis());
	final File fileDNE = new File(fileName);
	if (fileDNE.isFile()) {
	    fileDNE.delete();
	}

	aud.createAuditFile(fileName);

	final File file = new File(fileName);
	assertTrue(file.isFile());
	file.delete();
    }

}
