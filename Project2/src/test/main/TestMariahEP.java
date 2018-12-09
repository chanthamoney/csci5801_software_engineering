/**
 * File: TestMariahEP.java
 * Date Created: 11/08/2018
 * Last Update: Dec 4, 2018 5:47:51 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */
package test.main;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import main.MariahEP;

/**
 * The Class TestMariahEP.
 */
public class TestMariahEP {

    /**
     * Test file audit pair.
     *
     * @param electionFile the election file
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    private static void testFileAuditPair(String electionFile)
	    throws ParseException, IOException, InterruptedException, InvocationTargetException {
	// Keep current System.out
	final PrintStream oldOut = System.out;
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	// Change so System.out saved in baos
	System.setOut(new PrintStream(baos));

	MariahEP.main(new String[] { "../testing/" + electionFile + ".txt", "--no-gui", "--valid-ballot-quotient=0" });

	// Reset the System.out to console
	System.setOut(oldOut);

	// baos contains winner printed from the runElection function
	final String output = new String(baos.toByteArray());
	Map<String, String> outputMap = new HashMap<>();
	String[] splitOutput = output.split(String.format("%n%n"));
	for (String aSplitOutput : splitOutput) {
	    String[] splitPair = aSplitOutput.split(":");
	    outputMap.put(splitPair[0].trim(), splitPair[1].trim());
	}
	Path auditFile = Paths.get(".", outputMap.get("Audit File"));

	// Retrieve audit output and expected output.
	List<String> testOutput = Files.readAllLines(auditFile);
	List<String> expectedOutput = Files.readAllLines(Paths.get("../testing/", electionFile + "Audit.txt"));

	final File file = auditFile.toFile();
	file.delete();

	// Invalid audit file comparison
	String audFileName = outputMap.get("Invalid Ballots File");
	if (audFileName != null) {
	    Path invalidAuditFile = Paths.get(".", audFileName);
	    final File invalidFile = invalidAuditFile.toFile();
	    invalidFile.delete();
	}

	assertTrue(expectedOutput.containsAll(testOutput) && expectedOutput.size() == testOutput.size());
    }

    /**
     * Test file audit pair random msg.
     *
     * @param electionFile the election file
     * @param randomMsg    the random msg
     * @return the string
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    private static String testFileAuditPairRandomMsg(String electionFile, String randomMsg)
	    throws ParseException, IOException, InterruptedException, InvocationTargetException {
	// Keep current System.out
	final PrintStream oldOut = System.out;
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	// Change so System.out saved in baos
	System.setOut(new PrintStream(baos));

	MariahEP.main(new String[] { "../testing/" + electionFile + ".txt", "--no-gui", "--valid-ballot-quotient=0" });

	// Reset the System.out to console
	System.setOut(oldOut);

	// baos contains winner printed from the runElection function
	final String output = new String(baos.toByteArray());
	Map<String, String> outputMap = new HashMap<>();
	String[] splitOutput = output.split(String.format("%n%n"));
	for (String aSplitOutput : splitOutput) {
	    String[] splitPair = aSplitOutput.split(":");
	    outputMap.put(splitPair[0].trim(), splitPair[1].trim());
	}
	Path auditFile = Paths.get(".", outputMap.get("Audit File"));

	// Retrieve audit output and expected output.
	List<String> testOutput = Files.readAllLines(auditFile);
	testOutput.replaceAll(String::trim);
	assertTrue(testOutput.contains(randomMsg));

	final File file = auditFile.toFile();
	file.delete();

	if (outputMap.get("Election Winner") != null) {
	    return outputMap.get("Election Winner");
	} else {
	    return outputMap.get("Election Winners");
	}
    }

    /**
     * Test an election where there is one seat and one winner with six candidates.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    @Test
    public void testMainOPLVOneSeatOneWinner()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException {
	testFileAuditPair("OPLV/oneSeatOneWinner");

    }

    /**
     * Test an election where there is one seat and one winner with six candidates
     * with only a single vote cast.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    @Test
    public void testMainOPLVOneSeatOneWinnerOneVote()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException {
	testFileAuditPair("OPLV/oneSeatOneWinnerOneVote");
    }

    /**
     * Test an election where there is six seats and all candidates receive one
     * vote.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    @Test
    public void testMainOPLVSixSeatsSixCandidatesEqual()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException {
	testFileAuditPair("OPLV/sixSeatsSixCandidatesEqual");
    }

    /**
     * Test an election where there is a consequential tie between candidates on the
     * same party.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    @Test
    public void testMainOPLVConsequentialPartyTieTwoCandidates()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException {
	String electionWinner = testFileAuditPairRandomMsg("OPLV/consequentialPartyTieTwoCandidates",
		"NOTE: Randomly ranked candidates 1 to 2 due to a consequential tie in Party seat allocations.");

	// check if winner is as expected
	assertTrue("Naruto (Senju)".equals(electionWinner) || "Sasuke (Senju)".equals(electionWinner));

    }

    /**
     * Test an election where there is a consequential tie between candidates on the
     * same party.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    @Test
    public void testMainOPLVConsequentialPartyTieThreeCandidates()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException {

	String electionWinner = testFileAuditPairRandomMsg("OPLV/consequentialPartyTieThreeCandidates",
		"NOTE: Randomly ranked candidates 1 to 3 due to a consequential tie in Party seat allocations.");

	// check if winner is as expected
	assertTrue("Naruto (Senju)".equals(electionWinner) || "Sasuke (Senju)".equals(electionWinner)
		|| "Jake (Senju)".equals(electionWinner));

    }

    /**
     * Test an election where there are two seats, the sole candidate from one party
     * receives 5 votes and another candidate from another party receives 1 vote.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    @Test
    public void testMainOPLVTwoSeatsFiveCandidatesUnequal()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException {
	testFileAuditPair("OPLV/twoSeatsFiveCandidatesUnequal");
    }

    /**
     * Test election where winner is random.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    @Test
    public void testMainIRVRandomWinner()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException {
	String electionWinner = testFileAuditPairRandomMsg("IRV/randomWinner",
		"NOTE: This elimination was the result of a random toss due to a consequential tie in least amount of votes.");

	// check if winner is as expected
	assertTrue(
		"Sasuke".equals(electionWinner) || "Naruto".equals(electionWinner) || "Sakura".equals(electionWinner));
    }

    /**
     * Test an election where there is a clear winner by majority.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    @Test
    public void testMainIRVMajorityPopularVote()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException {
	testFileAuditPair("IRV/majorityPopularVote");
    }

    /**
     * Test an election where there are 10,000 ballots.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    @Test
    public void testMainIRVTenThousandVotes()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException {
	testFileAuditPair("IRV/tenThousandVotes");
    }

    /**
     * Test an election where there is only one ballot and multiple candidates.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    @Test
    public void testMainIRVOneVote()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException {
	testFileAuditPair("IRV/oneVote");
    }

    /**
     * Test an election where there is only one candidate.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    @Test
    public void testMainIRVOneCandidate()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException {
	testFileAuditPair("IRV/OneCandidate");
    }

    /**
     * Test an election where there is no candidate that receives majority vote.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    @Test
    public void testMainIRVNoMajorityPopularVote()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException {
	testFileAuditPair("IRV/noMajorityPopularVote");
    }

    /**
     * Test an election where no majority is ever reached in the instant runoffs and
     * the winner is decided by popular vote.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    @Test
    public void testMainIRVConsequentialTieTwoCandidates()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException {
	String electionWinners = testFileAuditPairRandomMsg("IRV/consequentialTieTwoCandidates",
		"NOTE: This elimination was the result of a random toss due to a consequential tie in least amount of votes.");

	// check if winner is as expected
	assertTrue("Naruto (Senju)".equals(electionWinners) || "Sasuke (Senju)".equals(electionWinners));
    }

    /**
     * Test an election where there is a tie between candidates on the same party.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    @Test
    public void testMainOPLVconsequentialTie()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException {
	String electionWinner = testFileAuditPairRandomMsg("OPLV/consequentialCandidateTie",
		"NOTE: Randomly ranked candidates 1 to 2 due to a consequential tie in Party seat allocations.");

	// check if winner is as expected
	assertTrue(String.format(
		"Katsuki (All Might)%n\tDeku (All Might)%n\tTodoroki (Endeavor)%n\tDabi (Endeavor)%n\tMomo (EraserHead)")
		.equals(electionWinner)
		|| String.format(
			"Katsuki (All Might)%n\tDeku (All Might)%n\tTodoroki (Endeavor)%n\tDabi (Endeavor)%n\tFroppy (EraserHead)")
			.equals(electionWinner));
    }

    /**
     * Test an election where there is two seats with five candidates and the
     * remaining seat goes to the party with the largest remainder .
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    @Test
    public void testMainOPLVtwoSeatsFiveCandidatesTenVotes()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException {
	testFileAuditPair("OPLV/twoSeatsFiveCandidatesTenVotes");
    }

    /**
     * Test an election where there is two seats with five candidates and the party
     * with the largest remainder does not have a candidate to fill seat so the seat
     * is allocated to the next party with the next highest largest remainder.
     *
     * @throws ParseException            the parse exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    @Test
    public void testMainOPLVtwoSeatsFiveCandidatesUnequal()
	    throws ParseException, IOException, InterruptedException, InvocationTargetException {
	testFileAuditPair("OPLV/twoSeatsFiveCandidatesUnequal");
    }
}
