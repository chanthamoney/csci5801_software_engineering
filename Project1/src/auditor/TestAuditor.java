
// File:         TestAuditor.java
// Created:      2018/11/08
// Last Changed: $Date: 2018/11/08 11:37:56 $
// Author:       <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
//
// This code is copyright (c) 2018 University of Minnesota - Twin Cities
//

package auditor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class TestAuditor {
    ArrayList<String> testStrings = new ArrayList<>();

    private Auditor initializeTestAuditor() {
	testStrings.add("Audit line 1\n");
	testStrings.add("Audit line 2\n");
	testStrings.add("Audit line 3\n");
	testStrings.add("Audit line 4\n");
	testStrings.add("Audit line 5\n");
	return new Auditor();
    }

    // Testing Auditor() constructor
    @Test
    public void testAuditor() {

    }

    // Testing Auditor() constructor with parameters
    @Test
    public void testAuditorWithParams() {
    }

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