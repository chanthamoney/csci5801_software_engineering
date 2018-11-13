
/**
 * File: TestOPLV.java
 * Date Created: 11/08/2018
 * Last Update: Nov 12, 2018 12:30:43 AM
 * Author: <A HREF="mailto:chant077@umn.edu">Cassandra Chanthamontry</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

package test.votingsystems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;

import votingsystems.OPLV;
import votingsystems.VotingSystem;

/**
 * The Class TestOPLV.
 */
public class TestOPLV {

    /** The candidates. */
    String[] candidates = { "Naruto", "Sasuke", "Sakura", "Kakashi", "Sai", "Tsunade", "Itachi" };

    /** The parties. */
    String[] parties = { "Senju", "Senju", "Akatsuki", "Akatsuki", "Anbu", "Anbu", "Uchiha" };

    /** The test ballots. */
    LinkedList<Integer> testBallots = new LinkedList<>();

    /**
     * Test file audit pair.
     *
     * @param electionFile the election file
     * @throws ParseException the parse exception
     * @throws IOException    Signals that an I/O exception has occurred.
     */
    private static void testFileAuditPair(String electionFile) throws ParseException, IOException {
	VotingSystem vs = votingSystemFromFile("../testing/OPLV/" + electionFile + ".txt");
	Path auditFile = Paths.get(".", vs.runElection());

	// Retrieve audit output and expected output.
	List<String> testOutput = Files.readAllLines(auditFile);
	List<String> expectedOutput = Files.readAllLines(Paths.get("../testing/OPLV", electionFile + "Audit.txt"));
	assertTrue(expectedOutput.containsAll(testOutput) && expectedOutput.size() == testOutput.size());
    }

    /**
     * Test file audit pair random msg.
     *
     * @param electionFile the election file
     * @param randomMsg    the random msg
     * @throws ParseException the parse exception
     * @throws IOException    Signals that an I/O exception has occurred.
     */
    private static void testFileAuditPairRandomMsg(String electionFile, String randomMsg)
	    throws ParseException, IOException {
	VotingSystem vs = votingSystemFromFile("../testing/OPLV/" + electionFile + ".txt");
	Path auditFile = Paths.get(".", vs.runElection());

	// Retrieve audit output and expected output.
	List<String> testOutput = Files.readAllLines(auditFile);
	testOutput.replaceAll(String::trim);
	assertTrue(testOutput.contains(randomMsg));
    }

    /**
     * Generates a voting system from a standardized voting system file.
     *
     * @param fileName the file name
     * @return the voting system
     * @throws FileNotFoundException the file not found exception
     * @throws ParseException        the parse exception
     */
    private static VotingSystem votingSystemFromFile(String fileName) throws FileNotFoundException, ParseException {
	File file = new File(fileName);
	final Scanner scanner = new Scanner(file);

	// Reads in first line which is oplv voting system
	scanner.nextLine();

	// Open Party Listing
	final int in_NumCandidates = Integer.valueOf(scanner.nextLine());
	final String in_Candidates = scanner.nextLine();
	final String[] cpPairs = in_Candidates.split(",(?![^\\(\\[]*[\\]\\)]) *");

	final String[] candidates = new String[in_NumCandidates];
	final String[] parties = new String[in_NumCandidates];
	CandidateParyPairsSeparator(in_NumCandidates, cpPairs, candidates, parties);

	final int in_NumSeats = Integer.valueOf(scanner.nextLine());
	final int in_NumBallots = Integer.valueOf(scanner.nextLine());
	final LinkedList<Integer> in_Ballots = OPLVBallotsFromFile(in_NumBallots, scanner);
	scanner.close();
	return new OPLV(in_NumBallots, in_NumCandidates, in_NumSeats, candidates, parties, in_Ballots, false);
    }

    /**
     * Retrieves the open party list voting ballots from a file.
     *
     * @param numBallots the number of ballots cast in the election
     * @param scanner    the scanner reading the specified file
     * @return the list of ballots
     */
    private static LinkedList<Integer> OPLVBallotsFromFile(int numBallots, Scanner scanner) {
	LinkedList<Integer> in_Ballots = new LinkedList<>();
	for (int i = 0; i < numBallots; i++) {
	    final String[] ballotInfo = scanner.nextLine().split(", *");
	    for (int j = 0; j < ballotInfo.length; j++) {
		if (!"".equals(ballotInfo[j])) {
		    in_Ballots.add(j);
		    break;
		}
	    }
	}
	return in_Ballots;
    }

    /**
     * Stores the separated strings of candidate party pairs into the designated
     * lists.
     *
     * @param numPairs   the number of pairs to separate
     * @param cpPairs    the candidate party pairs
     * @param candidates the list in which to store candidate names
     * @param parties    the list in which to store party names
     */
    private static void CandidateParyPairsSeparator(int numPairs, String[] cpPairs, String[] candidates,
	    String[] parties) {
	for (int i = 0; i < numPairs; i++) {
	    final String[] pair = cpPairs[i].substring(1, cpPairs[i].length() - 1).split(", *");
	    candidates[i] = pair[0];
	    parties[i] = pair[1];
	}
    }

    /**
     * Test OPLV.
     */
    // Testing OPLV() constructor
    @Test(expected = IllegalArgumentException.class)
    public void testOPLV() {
	try {
	    new OPLV();
	} catch (IllegalArgumentException iae) {
	    assertEquals("Default constructor is not allowed.", iae.getMessage());
	    throw iae;
	}
	fail("Default constructor did not throw exception as expected.");
    }

    /**
     * Test run election twice.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test(expected = RuntimeException.class)
    public void testRunElectionTwice() throws IOException {
	this.testBallots.add(1);
	final VotingSystem vs = new OPLV(this.testBallots.size(), 4, 1, candidates, parties, this.testBallots, false);
	vs.runElection();
	try {
	    vs.runElection();
	} catch (RuntimeException rte) {
	    assertEquals("An election can only be run once for a given voting system.\n", rte.getMessage());
	    throw rte;
	}
	fail("Runtime exception for running election more than once did not throw!");
    }

    /**
     * Test run election efficiency.
     */
    @Test
    public void testRunElectionEfficiency() {
	// Initialize Large OPLV Election
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
	final String[] parties_100_80_60_40_20 = { "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA",
		"PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA",
		"PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA",
		"PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA",
		"PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA",
		"PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA", "PA",
		"PA", "PA", "PA", "PA", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB",
		"PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB",
		"PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB",
		"PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB",
		"PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB", "PC",
		"PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC",
		"PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC",
		"PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC",
		"PC", "PC", "PC", "PC", "PC", "PC", "PC", "PC", "PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD",
		"PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD",
		"PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD", "PD", "PE", "PE", "PE",
		"PE", "PE", "PE", "PE", "PE", "PE", "PE", "PE", "PE", "PE", "PE", "PE", "PE", "PE", "PE", "PE", "PE", };
	final Random randomizer = new Random(System.currentTimeMillis());
	for (int i = 0; i < 100000; i++) {
	    this.testBallots.add(randomizer.nextInt(300));
	}
	final int numSeats = randomizer.nextInt(11) + 10;
	final VotingSystem vs = new OPLV(this.testBallots.size(), 300, numSeats, candidates_300,
		parties_100_80_60_40_20, this.testBallots, false);

	// Record current time in milliseconds immediately before election run
	final long timeBefore = System.currentTimeMillis();

	// Run election on vs
	try {
	    vs.runElection();
	} catch (final IOException e) {
	    e.printStackTrace();
	}

	// Record time immediately after election
	final long timeAfter = System.currentTimeMillis();

	// Must take less than 8 minutes to process a 100,000 vote election.
	assertTrue((timeAfter - timeBefore) < 480000);
    }

    /**
     * Test an election where there is one seat and one winner with six candidates.
     *
     * @throws ParseException the parse exception
     * @throws IOException    Signals that an I/O exception has occurred.
     */
    @Test
    public void testOPLVOneSeatOneWinner() throws ParseException, IOException {
	testFileAuditPair("oneSeatOneWinner");
    }

    /**
     * Test an election where there is one seat and one winner with six candidates
     * with only a single vote cast.
     *
     * @throws ParseException the parse exception
     * @throws IOException    Signals that an I/O exception has occurred.
     */
    @Test
    public void testOPLVOneSeatOneWinnerOneVote() throws ParseException, IOException {
	testFileAuditPair("oneSeatOneWinnerOneVote");
    }

    /**
     * Test an election where there is six seats and all candidates receive one
     * vote.
     *
     * @throws ParseException the parse exception
     * @throws IOException    Signals that an I/O exception has occurred.
     */
    @Test
    public void testOPLVSixSeatsSixCandidatesEqual() throws ParseException, IOException {
	testFileAuditPair("sixSeatsSixCandidatesEqual");
    }

    /**
     * Test an election where there is a consequential tie between candidates on the
     * same party.
     *
     * @throws ParseException       the parse exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @Test
    public void testOPLVConsequentialPartyTieTwoCandidates() throws ParseException, IOException, InterruptedException {
	// Keep current System.out
	final PrintStream oldOut = System.out;
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	// Change so System.out saved in baos
	System.setOut(new PrintStream(baos));

	// check if it was a tie happen
	testFileAuditPairRandomMsg("consequentialPartyTieTwoCandidates",
		"NOTE: Randomly ranked candidates 1 to 2 due to a consequential tie in Party seat allocations.");

	// Reset the System.out to console
	System.setOut(oldOut);
	// baos contains winner printed from the runElection function
	final String output = new String(baos.toByteArray());

	// check if winner is as expected
	assertTrue("Election Winners:\n\tNaruto (Senju)\n".equals(output)
		|| "Election Winners:\n\tSasuke (Senju)\n".equals(output));

    }

    /**
     * Test an election where there is a consequential tie between candidates on the
     * same party.
     *
     * @throws ParseException       the parse exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @Test
    public void testOPLVConsequentialPartyTieThreeCandidates()
	    throws ParseException, IOException, InterruptedException {

	// Keep current System.out
	final PrintStream oldOut = System.out;
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	// Change so System.out saved in baos
	System.setOut(new PrintStream(baos));

	testFileAuditPairRandomMsg("consequentialPartyTieThreeCandidates",
		"NOTE: Randomly ranked candidates 1 to 3 due to a consequential tie in Party seat allocations.");

	// Reset the System.out to console
	System.setOut(oldOut);

	// baos contains winner printed from the runElection function
	final String output = new String(baos.toByteArray());
	// check if winner is as expected
	assertTrue("Election Winners:\n\tNaruto (Senju)\n".equals(output)
		|| "Election Winners:\n\tSasuke (Senju)\n".equals(output)
		|| "Election Winners:\n\tJake (Senju)\n".equals(output));

    }

    /**
     * Test an election where there is a tie between candidates on the same party.
     *
     * @throws ParseException       the parse exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @Test
    public void testOPLVconsequentialTie() throws ParseException, IOException, InterruptedException {
	// Keep current System.out
	final PrintStream oldOut = System.out;
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	// Change so System.out saved in baos
	System.setOut(new PrintStream(baos));

	testFileAuditPairRandomMsg("consequentialCandidateTie",
		"NOTE: Randomly ranked candidates 1 to 2 due to a consequential tie in Party seat allocations.");

	// Reset the System.out to console
	System.setOut(oldOut);

	// baos contains winner printed from the runElection function
	final String output = new String(baos.toByteArray());
	// check if winner is as expected
	assertTrue(
		"Election Winners:\n\tKatsuki (All Might)\n\tDeku (All Might)\n\tTodoroki (Endeavor)\n\tDabi (Endeavor)\n\tMomo (EraserHead)\n"
			.equals(output)
			|| "Election Winners:\n\tKatsuki (All Might)\n\tDeku (All Might)\n\tTodoroki (Endeavor)\n\tDabi (Endeavor)\n\tFroppy (EraserHead)\n"
				.equals(output));
    }

    /**
     * Test an election where there is two seats with five candidates .
     *
     * @throws ParseException the parse exception
     * @throws IOException    Signals that an I/O exception has occurred.
     */
    @Test
    public void testOPLVtwoSeatsFiveCandidatesTenVotes() throws ParseException, IOException {
	testFileAuditPair("twoSeatsFiveCandidatesTenVotes");
    }

    /**
     * Test an election where there is one seat and one winner with six candidates.
     *
     * @throws ParseException the parse exception
     * @throws IOException    Signals that an I/O exception has occurred.
     */
    @Test
    public void testOPLVtwoSeatsFiveCandidatesUnequal() throws ParseException, IOException {
	testFileAuditPair("twoSeatsFiveCandidatesUnequal");
    }

}
