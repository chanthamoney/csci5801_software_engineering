
// File:         TestOPLV.java
// Created:      2018/11/08
// Last Changed: $Date: 2018/11/08 11:37:56 $
// Author:       <A HREF="mailto:chant077@umn.edu">Cassandra Chanthamontry</A>
//
// This code is copyright (c) 2018 University of Minnesota - Twin Cities
//

package votingsystems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

//import junitx.framework.FileAssert
import org.junit.Test;

/**
 * The Class TestOPLV.
 */
public class TestOPLV {

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

	final String in_VotingSystem = scanner.nextLine();

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
	return new OPLV(in_NumBallots, in_NumCandidates, in_NumSeats, candidates, parties, in_Ballots);
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

    /** The candidates. */
    String[] candidates = { "Naruto", "Sasuke", "Sakura", "Kakashi", "Sai", "Tsunade", "Itachi" };

    /** The parties. */
    String[] parties = { "Senju", "Senju", "Akatsuki", "Akatsuki", "Anbu", "Anbu", "Uchiha" };

    /** The test ballots. */
    LinkedList<Integer> testBallots = new LinkedList<>();

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
	final VotingSystem vs = new OPLV(this.testBallots.size(), 4, 1, candidates, parties, this.testBallots);
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
		parties_100_80_60_40_20, this.testBallots);

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
     * Test run election.
     * 
     * @throws ParseException
     * @throws IOException
     */
    @Test
    public void testRunElection() throws ParseException, IOException {
	VotingSystem vs = votingSystemFromFile("../../testing/oneSeatOneWinner.txt");
	String auditFile = vs.runElection();
	// Compare her output to what we expect

    }
}
