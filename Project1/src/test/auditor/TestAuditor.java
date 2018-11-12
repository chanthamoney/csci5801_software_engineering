
/**
 * File: TestAuditor.java
 * Date Created: 11/08/2018
 * Last Update: Nov 11, 2018 2:36:23 PM
 * Author: <A HREF="mailto:tsaix223@umn.edu">Christine Tsai</A>
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
	testStrings.add("Audit line 1\n");
	testStrings.add("Audit line 2\n");
	testStrings.add("Audit line 3\n");
	testStrings.add("Audit line 4\n");
	testStrings.add("Audit line 5\n");
	return new Auditor();
    }

    /**
     * Test auditor.
     */
    // Testing Auditor() constructor
    @Test
    public void testAuditor() {

    }

    /**
     * Test auditor with params.
     */
    // Testing Auditor() constructor with parameters
    @Test
    public void testAuditorWithParams() {
    }

    /**
     * Test audit process.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    // Testing auditProcess()
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
    // Testing auditResult()
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
    // Testing auditSetup()
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
     * Test audit structure.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    // Testing audit file output structure
    @Test
    public void testAuditStructure() throws IOException {

    }

    /**
     * Test create audit file.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
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