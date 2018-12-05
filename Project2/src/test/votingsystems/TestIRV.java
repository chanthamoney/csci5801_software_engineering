/**
 * File: TestIRV.java
 * Date Created: 11/08/2018
 * Last Update: Dec 5, 2018 3:09:38 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

package test.votingsystems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;

import main.InvalidFileException;
import votingsystems.IRV;
import votingsystems.VotingSystem;

// TODO: Auto-generated Javadoc
/**
 * The Class TestIRV.
 */
public class TestIRV {

    /** The candidates. */
    String[] candidates = { "Naruto", "Sasuke", "Sakura", "Kakashi" };

    /** The test ballots. */
    LinkedList<ArrayList<Integer>> testBallots = new LinkedList<>();

    /**
     * Initialize test IRV.
     *
     * @return the irv
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InvalidFileException the invalid file exception
     */
    private IRV initializeTestIRV() throws IOException, InvalidFileException {
	final ArrayList<Integer> firstBallot = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
	final ArrayList<Integer> secondBallot = new ArrayList<>(Arrays.asList(1, 0, 2, 3));
	final ArrayList<Integer> thirdBallot = new ArrayList<>(Arrays.asList(3, 2, 1, 0));
	final ArrayList<Integer> fourthBallot = new ArrayList<>(Arrays.asList(1, 0, 2, 3));
	final ArrayList<Integer> fifthBallot = new ArrayList<>(Arrays.asList(2, 1, 0, 3));
	final ArrayList<Integer> sixthBallot = new ArrayList<>(Arrays.asList(0, 1, 2, 3));

	this.testBallots.add(firstBallot);
	this.testBallots.add(secondBallot);
	this.testBallots.add(thirdBallot);
	this.testBallots.add(fourthBallot);
	this.testBallots.add(fifthBallot);
	this.testBallots.add(sixthBallot);

	return new IRV(this.testBallots.size(), this.candidates.length, this.candidates, this.testBallots, false, 0);
    }

    /**
     * ************************************** Test Ballot Invalidation Process
     * **************************************.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    /**
     * Test an election where there are an even number of candidates and no invalid
     * ballots.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    @Test
    public void testIRVEvenCandidatesNoInvalidBallots()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException, InvalidFileException {
	testInvalidBallotsFile("evenCandidatesNoInvalidBallots", 50);
    }

    /**
     * Test an election where there are an even number of candidates and some
     * invalid ballots.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    @Test
    public void testIRVEvenCandidatesSomeInvalidBallots()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException, InvalidFileException {
	testInvalidBallotsFile("evenCandidatesSomeInvalidBallots", 50);
    }

    /**
     * Test an election where there are an even number of candidates and all but one
     * of the ballots is invalid.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    @Test
    public void testIRVEvenCandidatesAllButOneInvalidBallots()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException, InvalidFileException {
	testInvalidBallotsFile("evenCandidatesAllButOneInvalidBallots", 50);
    }

    /**
     * Test an election where there are an even number of candidates and all ballots
     * are invalid.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    @Test
    public void testIRVEvenCandidatesAllInvalidBallots()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException, InvalidFileException {
	testInvalidBallotsFile("evenCandidatesAllInvalidBallots", 50);
    }

    private void testInvalidBallotsFile(String electionFile, int percent) {
	return;
    }

    /**
     * Test an election where there are an odd number of candidates and no invalid
     * ballots.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    @Test
    public void testIRVOddCandidatesNoInvalidBallots()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException, InvalidFileException {
	testInvalidBallotsFile("oddCandidatesNoInvalidBallots", 50);
    }

    /**
     * Test an election where there are an odd number of candidates and some invalid
     * ballots.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    @Test
    public void testIRVOddCandidatesSomeInvalidBallots()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException, InvalidFileException {
	testInvalidBallotsFile("oddCandidatesSomeInvalidBallots", 50);
    }

    /**
     * Test an election where there are an odd number of candidates and all but one
     * of the ballots is invalid.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    @Test
    public void testIRVOddCandidatesAllButOneInvalidBallots()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException, InvalidFileException {
	testInvalidBallotsFile("oddCandidatesAllButOneInvalidBallots", 50);
    }

    /**
     * Test an election where there are an odd number of candidates and all ballots
     * are invalid.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    @Test
    public void testIRVOddCandidatesAllInvalidBallots()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException, InvalidFileException {
	testInvalidBallotsFile("oddCandidatesAllInvalidBallots", 50);
    }

    /**
     * *****************************************.
     *
     * @param electionFile the election file
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */

    /**
     * Test file audit pair.
     *
     * @param electionFile the election file
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    private static void testFileAuditPair(String electionFile, int percent)
	    throws ParseException, IOException, InterruptedException, InvocationTargetException, InvalidFileException {
	VotingSystem vs = votingSystemFromFile("../testing/IRV/" + electionFile + ".txt", percent);
	Path auditFile = Paths.get(".", vs.runElection());

	// Retrieve audit output and expected output.
	List<String> testOutput = Files.readAllLines(auditFile);
	List<String> expectedOutput = Files.readAllLines(Paths.get("../testing/IRV/", electionFile + "Audit.txt"));

	final File file = auditFile.toFile();
	file.delete();
	assertTrue(expectedOutput.containsAll(testOutput) && expectedOutput.size() == testOutput.size());
    }

    /**
     * Test file audit pair random msg.
     *
     * @param electionFile the election file
     * @param randomMsg    the random msg
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    private static void testFileAuditPairRandomMsg(String electionFile, String randomMsg, int percent)
	    throws ParseException, IOException, InterruptedException, InvocationTargetException, InvalidFileException {
	VotingSystem vs = votingSystemFromFile("../testing/IRV/" + electionFile + ".txt", percent);
	Path auditFile = Paths.get(".", vs.runElection());

	// Retrieve audit output and expected output.
	List<String> testOutput = Files.readAllLines(auditFile);
	testOutput.replaceAll(String::trim);

	final File file = auditFile.toFile();
	file.delete();
	assertTrue(testOutput.contains(randomMsg));
    }

    /**
     * Generates a voting system from a standardized voting system file.
     *
     * @param fileName the file name
     * @return the voting system
     * @throws ParseException       the parse exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InvalidFileException the invalid file exception
     */
    private static VotingSystem votingSystemFromFile(String fileName, int percent)
	    throws ParseException, IOException, InvalidFileException {
	File file = new File(fileName);
	final Scanner scanner = new Scanner(file);

	// Reads in first line which is oplv voting system
	scanner.nextLine();

	// Open Party Listing
	final int in_NumCandidates = Integer.valueOf(scanner.nextLine());
	final String in_Candidates = scanner.nextLine();
	final String[] cpPairs = in_Candidates.split(",(?![^\\(\\[]*[\\]\\)]) *");
	final int in_NumBallots = Integer.valueOf(scanner.nextLine());
	final LinkedList<ArrayList<Integer>> in_Ballots = IRVBallotsFromFile(in_NumBallots, in_NumCandidates, scanner);
	scanner.close();
	return new IRV(in_NumBallots, in_NumCandidates, cpPairs, in_Ballots, false, percent);
    }

    /**
     * IRV ballots from file.
     *
     * @param numBallots    the num ballots
     * @param numCandidates the num candidates
     * @param scanner       the scanner
     * @return the linked list
     */
    private static LinkedList<ArrayList<Integer>> IRVBallotsFromFile(int numBallots, int numCandidates,
	    Scanner scanner) {
	LinkedList<ArrayList<Integer>> in_Ballots = new LinkedList<>();

	// For each ballot perform the following set of operations:
	// 1) Create an organization array in length of the number of ballots
	// 2) Split votes by commas and any amount of white space
	// 3) For each split value, if it is not empty (meaning the candidates
	// associated with the current index has been ranked), store the candidate's
	// index (the current index) in the index corresponding to the rank in the
	// organizational array
	// 4) Create an array to store the processed ballots
	// 5) Store the subset of the organizational array up to the number of votes
	// cast as the votes for that ballot
	for (int i = 0; i < numBallots; i++) {
	    final int[] balVotesOrg = new int[numCandidates];
	    final String[] ballotInfo = scanner.nextLine().split(", *");
	    int numVotes = 0;
	    for (int j = 0; j < ballotInfo.length; j++) {
		if (!"".equals(ballotInfo[j])) {
		    balVotesOrg[Integer.parseInt(ballotInfo[j]) - 1] = j;
		    numVotes++;
		}
	    }
	    final ArrayList<Integer> balVotes = new ArrayList<>();
	    for (int j = 0; j < numVotes; j++) {
		balVotes.add(balVotesOrg[j]);
	    }
	    in_Ballots.add(balVotes);
	}

	return in_Ballots;
    }

    /**
     * Test run election twice.
     *
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    @Test(expected = RuntimeException.class)
    public void testRunElectionTwice()
	    throws IOException, InterruptedException, InvocationTargetException, InvalidFileException {
	final VotingSystem vs = initializeTestIRV();
	Path auditFile = Paths.get(".", vs.runElection());
	try {
	    final File file = auditFile.toFile();
	    file.delete();
	    auditFile = Paths.get(".", vs.runElection());
	} catch (RuntimeException rte) {
	    assertEquals(String.format("An election can only be run once for a given voting system.%n"),
		    rte.getMessage());
	    throw rte;
	}

	final File file = auditFile.toFile();
	file.delete();
	fail("Runtime exception for running election more than once did not throw!");
    }

    /**
     * Test IRV default constructor which should fail.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIRV() {
	try {
	    new IRV();
	} catch (IllegalArgumentException iae) {
	    assertEquals("Default constructor is not allowed.", iae.getMessage());
	    throw iae;
	}
	fail("Employee Id Null exception did not throw!");
    }

    /**
     * Test election where winner is random.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    @Test
    public void testIRVRandomWinner()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException, InvalidFileException {
	// Keep current System.out
	final PrintStream oldOut = System.out;
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	// Change so System.out saved in baos
	System.setOut(new PrintStream(baos));

	testFileAuditPairRandomMsg("randomWinner",
		"NOTE: This elimination was the result of a random toss due to a consequential tie in least amount of votes.",
		0);

	// Reset the System.out to console
	System.setOut(oldOut);

	// baos contains winner printed from the runElection function
	final String output = new String(baos.toByteArray());

	System.out.println(output);
	// check if winner is as expected
	assertTrue(String.format("Election Winner: Sasuke%n%n").equals(output)
		|| String.format("Election Winner: Naruto%n%n").equals(output)
		|| String.format("Election Winner: Sakura%n%n").equals(output));
    }

    /**
     * Test run election efficiency.
     *
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InvalidFileException      the invalid file exception
     */
    @Test
    public void testRunElectionEfficiency()
	    throws InterruptedException, InvocationTargetException, IOException, InvalidFileException {
	// Keep current System.out
	final PrintStream oldOut = System.out;
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	// Change so System.out saved in baos
	System.setOut(new PrintStream(baos));

	// Initialize Large IRV Election
	final String[] candidates_300 = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
		"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "AA", "AB", "AC", "AD", "AE", "AF", "AG", "AH",
		"AI", "AJ", "AK", "AL", "AM", "AN", "AO", "AP", "AQ", "AR", "AS", "AT", "AU", "AV", "AW", "AX", "AY",
		"AZ", "BA", "BB", "BC", "BD", "BE", "BF", "BG", "BH", "BI", "BJ", "BK", "BL", "BM", "BN", "BO", "BP",
		"BQ", "BR", "BS", "BT", "BU", "BV", "BW", "BX", "BY", "BZ", "CA", "CB", "CC", "CD", "CE", "CF", "CG",
		"CH", "CI", "CJ", "CK", "CL", "CM", "CN", "CO", "CP", "CQ", "CR", "CS", "CT", "CU", "CV", "CW", "CX",
		"CY", "CZ", "DA", "DB", "DC", "DD", "DE", "DF", "DG", "DH", "DI", "DJ", "DK", "DL", "DM", "DN", "DO",
		"DP", "DQ", "DR", "DS", "DT", "DU", "DV", "DW", "DX", "DY", "DZ", "EA", "EB", "EC", "ED", "EE", "EF",
		"EG", "EH", "EI", "EJ", "EK", "EL", "EM", "EN", "EO", "EP", "EQ", "ER", "ES", "ET", "EU", "EV", "EW",
		"EX", "EY", "EZ", "FA", "FB", "FC", "FD", "FE", "FF", "FG", "FH", "FI", "FJ", "FK", "FL", "FM", "FN",
		"FO", "FP", "FQ", "FR", "FS", "FT", "FU", "FV", "FW", "FX", "FY", "FZ", "GA", "GB", "GC", "GD", "GE",
		"GF", "GG", "GH", "GI", "GJ", "GK", "GL", "GM", "GN", "GO", "GP", "GQ", "GR", "GS", "GT", "GU", "GV",
		"GW", "GX", "GY", "GZ", "HA", "HB", "HC", "HD", "HE", "HF", "HG", "HH", "HI", "HJ", "HK", "HL", "HM",
		"HN", "HO", "HP", "HQ", "HR", "HS", "HT", "HU", "HV", "HW", "HX", "HY", "HZ", "IA", "IB", "IC", "ID",
		"IE", "IF", "IG", "IH", "II", "IJ", "IK", "IL", "IM", "IN", "IO", "IP", "IQ", "IR", "IS", "IT", "IU",
		"IV", "IW", "IX", "IY", "IZ", "JA", "JB", "JC", "JD", "JE", "JF", "JG", "JH", "JI", "JJ", "JK", "JL",
		"JM", "JN", "JO", "JP", "JQ", "JR", "JS", "JT", "JU", "JV", "JW", "JX", "JY", "JZ", "KA", "KB", "KC",
		"KD", "KE", "KF", "KG", "KH", "KI", "KJ", "KK", "KL", "KM", "KN" };
	final Random randomizer = new Random(System.currentTimeMillis());
	for (int i = 0; i < 100000; i++) {
	    final ArrayList<Integer> newBallot = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
		    13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30));
	    Collections.shuffle(newBallot, randomizer);
	    this.testBallots.add(newBallot);
	}
	final VotingSystem vs = new IRV(this.testBallots.size(), 300, candidates_300, this.testBallots, false, 0);

	// Record current time in milliseconds immediately before election run
	final long timeBefore = System.currentTimeMillis();

	// Run election on vs
	try {
	    Path auditFile = Paths.get(".", vs.runElection());

	    final File file = auditFile.toFile();
	    file.delete();
	} catch (final IOException e) {
	    e.printStackTrace();
	}

	// Record time immediately after election
	final long timeAfter = System.currentTimeMillis();

	// Reset the System.out to console
	System.setOut(oldOut);

	// Must take less than 8 minutes to process a 100,000 vote election.
	assertTrue((timeAfter - timeBefore) < 480000);
    }

    // Testing Different Election Cases

    /**
     * Test an election where there is a clear winner by majority.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    @Test
    public void testIRVMajorityPopularVote()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException, InvalidFileException {
	testFileAuditPair("majorityPopularVote", 0);
    }

    /**
     * Test an election where there are 10,000 ballots.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    @Test
    public void testIRVTenThousandVotes()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException, InvalidFileException {
	testFileAuditPair("tenThousandVotes", 0);
    }

    /**
     * Test an election where there is only one ballot and multiple candidates.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    @Test
    public void testIRVOneVote()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException, InvalidFileException {
	testFileAuditPair("oneVote", 0);
    }

    /**
     * Test an election where there is only one candidate.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    @Test
    public void testIRVOneCandidate()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException, InvalidFileException {
	testFileAuditPair("OneCandidate", 0);
    }

    /**
     * Test an election where there is no candidate that receives majority vote.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    @Test
    public void testIRVNoMajorityPopularVote()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException, InvalidFileException {
	testFileAuditPair("noMajorityPopularVote", 0);
    }

    /**
     * Test an election where no majority is ever reached in the instant runoffs and
     * the winner is decided by popular vote.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InvalidFileException      the invalid file exception
     */
    @Test
    public void testIRVConsequentialTieTwoCandidates()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException, InvalidFileException {
	// Keep current System.out
	final PrintStream oldOut = System.out;
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	// Change so System.out saved in baos
	System.setOut(new PrintStream(baos));

	testFileAuditPairRandomMsg("consequentialTieTwoCandidates",
		"NOTE: This elimination was the result of a random toss due to a consequential tie in least amount of votes.",
		0);

	// Reset the System.out to console
	System.setOut(oldOut);

	// baos contains winner printed from the runElection function
	final String output = new String(baos.toByteArray());

	// check if winner is as expected
	assertTrue(String.format("Election Winner: Naruto (Senju)%n%n").equals(output)
		|| String.format("Election Winner: Sasuke (Senju)%n%n").equals(output));
    }
}
