/**
 * File: TestMariahEP.java
 * Date Created: 11/08/2018
 * Last Update: Nov 12, 2018 12:27:22 AM
 * Author: <A HREF="mailto:silag001@umn.edu">Meghann Silagan</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */
package test.main;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

import org.junit.Test;

/**
 * File: TestMariahEP.java
 * Date Created: 11/08/2018
 * Last Update: Nov 11, 2018 2:41:18 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

import main.MariahEP;

/**
 * The Class TestOPLV.
 */
public class TestMariahEP {

    /**
     * Test file audit pair.
     *
     * @param electionFile the election file
     * @throws ParseException       the parse exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    private static void testFileAuditPair(String electionFile)
	    throws ParseException, IOException, InterruptedException {
	// Keep current System.out
	final PrintStream oldOut = System.out;
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	// Change so System.out saved in baos
	System.setOut(new PrintStream(baos));

	MariahEP.main(new String[] { "../testing/" + electionFile + ".txt", "--no-gui" });

	// Reset the System.out to console
	System.setOut(oldOut);

	// baos contains winner printed from the runElection function
	final String output = new String(baos.toByteArray());
	Path auditFile = Paths.get(".", output.substring(output.lastIndexOf(" ") + 1).trim());

	// Retrieve audit output and expected output.
	List<String> testOutput = Files.readAllLines(auditFile);
	List<String> expectedOutput = Files.readAllLines(Paths.get("../testing/", electionFile + "Audit.txt"));
	assertTrue(expectedOutput.containsAll(testOutput) && expectedOutput.size() == testOutput.size());
    }

    /**
     * Test file audit pair random msg.
     *
     * @param electionFile the election file
     * @param randomMsg    the random msg
     * @throws ParseException       the parse exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    private static String testFileAuditPairRandomMsg(String electionFile, String randomMsg)
	    throws ParseException, IOException, InterruptedException {
	// Keep current System.out
	final PrintStream oldOut = System.out;
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	// Change so System.out saved in baos
	System.setOut(new PrintStream(baos));

	MariahEP.main(new String[] { "../testing/" + electionFile + ".txt", "--no-gui" });

	// Reset the System.out to console
	System.setOut(oldOut);

	// baos contains winner printed from the runElection function
	final String output = new String(baos.toByteArray());

	System.out.print(output);

	Path auditFile = Paths.get(".", output.substring(output.lastIndexOf(" ") + 1).trim());

	// Retrieve audit output and expected output.
	List<String> testOutput = Files.readAllLines(auditFile);
	testOutput.replaceAll(String::trim);
	assertTrue(testOutput.contains(randomMsg));

	int auditFileIndex = output.indexOf("Audit File:");
	return output.substring(0, auditFileIndex);
    }

    /**
     * Test an election where there is one seat and one winner with six candidates.
     *
     * @throws ParseException       the parse exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @Test
    public void testMainOPLVOneSeatOneWinner() throws ParseException, IOException, InterruptedException {
	testFileAuditPair("OPLV/oneSeatOneWinner");
    }

    /**
     * Test an election where there is one seat and one winner with six candidates
     * with only a single vote cast.
     *
     * @throws ParseException       the parse exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @Test
    public void testMainOPLVOneSeatOneWinnerOneVote() throws ParseException, IOException, InterruptedException {
	testFileAuditPair("OPLV/oneSeatOneWinnerOneVote");
    }

    /**
     * Test an election where there is six seats and all candidates receive one
     * vote.
     *
     * @throws ParseException       the parse exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @Test
    public void testMainOPLVSixSeatsSixCandidatesEqual() throws ParseException, IOException, InterruptedException {
	testFileAuditPair("OPLV/sixSeatsSixCandidatesEqual");
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
    public void testMainOPLVConsequentialPartyTieTwoCandidates()
	    throws ParseException, IOException, InterruptedException {
	String electionWinner = testFileAuditPairRandomMsg("OPLV/consequentialPartyTieTwoCandidates",
		"NOTE: Randomly ranked candidates 1 to 2 due to a consequential tie in Party seat allocations.");

	// check if winner is as expected
	assertTrue("Election Winners:\n\tNaruto (Senju)\n".equals(electionWinner)
		|| "Election Winners:\n\tSasuke (Senju)\n".equals(electionWinner));

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
    public void testMainOPLVConsequentialPartyTieThreeCandidates()
	    throws ParseException, IOException, InterruptedException {

	String electionWinner = testFileAuditPairRandomMsg("OPLV/consequentialPartyTieThreeCandidates",
		"NOTE: Randomly ranked candidates 1 to 3 due to a consequential tie in Party seat allocations.");

	// check if winner is as expected
	assertTrue("Election Winners:\n\tNaruto (Senju)\n".equals(electionWinner)
		|| "Election Winners:\n\tSasuke (Senju)\n".equals(electionWinner)
		|| "Election Winners:\n\tJake (Senju)\n".equals(electionWinner));

    }

    /**
     * Test an election where there are two seats, the sole candidate from one party
     * receives 5 votes and another candidate from another party receives 1 vote.
     *
     * @throws ParseException       the parse exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @Test
    public void testMainOPLVTwoSeatsFiveCandidatesUnequal() throws ParseException, IOException, InterruptedException {
	testFileAuditPair("OPLV/twoSeatsFiveCandidatesUnequal");
    }

    /**
     * Test election where winner is random
     * 
     * @throws IOException
     * @throws ParseException
     * @throws InterruptedException
     */
    @Test
    public void testMainIRVRandomWinner() throws ParseException, IOException, InterruptedException {
	String electionWinner = testFileAuditPairRandomMsg("IRV/randomWinner",
		"NOTE: This elimination was the result of a random toss due to a consequential tie in least amount of votes.");

	// check if winner is as expected
	assertTrue(
		"Election Winner: Sasuke\n".equals(electionWinner) || "Election Winner: Naruto\n".equals(electionWinner)
			|| "Election Winner: Sakura\n".equals(electionWinner));
    }

    /**
     * Test an election where there is a clear winner by majority
     *
     * @throws ParseException       the parse exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException
     */
    @Test
    public void testMainIRVMajorityPopularVote() throws ParseException, IOException, InterruptedException {
	testFileAuditPair("IRV/majorityPopularVote");
    }

    /**
     * Test an election where there are 10,000 ballots
     *
     * @throws ParseException       the parse exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException
     */
    @Test
    public void testMainIRVTenThousandVotes() throws ParseException, IOException, InterruptedException {
	testFileAuditPair("IRV/tenThousandVotes");
    }

    /**
     * Test an election where there is only one ballot and multiple candidates
     *
     * @throws ParseException       the parse exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException
     */
    @Test
    public void testMainIRVOneVote() throws ParseException, IOException, InterruptedException {
	testFileAuditPair("IRV/oneVote");
    }

    /**
     * Test an election where there is only one candidate
     *
     * @throws ParseException       the parse exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException
     */
    @Test
    public void testMainIRVOneCandidate() throws ParseException, IOException, InterruptedException {
	testFileAuditPair("IRV/OneCandidate");
    }

    /**
     * Test an election where there is no candidate that receives majority vote
     *
     * @throws ParseException       the parse exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException
     */
    @Test
    public void testMainIRVNoMajorityPopularVote() throws ParseException, IOException, InterruptedException {
	testFileAuditPair("IRV/noMajorityPopularVote");
    }

    /**
     * Test an election where no majority is ever reached in the instant runoffs and
     * the winner is decided by popular vote.
     *
     * @throws ParseException       the parse exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException
     */
    @Test
    public void testMainIRVConsequentialTieTwoCandidates() throws ParseException, IOException, InterruptedException {
	String electionWinners = testFileAuditPairRandomMsg("IRV/consequentialTieTwoCandidates",
		"NOTE: This elimination was the result of a random toss due to a consequential tie in least amount of votes.");

	// check if winner is as expected
	assertTrue("Election Winner: Naruto (Senju)\n".equals(electionWinners)
		|| "Election Winner: Sasuke (Senju)\n".equals(electionWinners));
    }

    /**
     * Test an election where there is a tie between candidates on the same party.
     *
     * @param electionFile the election file
     * @param randomMsg    the random msg
     * @throws ParseException       the parse exception
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    @Test
    public void testOPLVconsequentialTie() throws ParseException, IOException, InterruptedException {
	String electionWinner = testFileAuditPairRandomMsg("OPLV/consequentialCandidateTie",
		"NOTE: Randomly ranked candidates 1 to 2 due to a consequential tie in Party seat allocations.");

	// check if winner is as expected
	assertTrue(
		"Election Winners:\n\tKatsuki (All Might)\n\tDeku (All Might)\n\tTodoroki (Endeavor)\n\tDabi (Endeavor)\n\tMomo (EraserHead)\n"
			.equals(electionWinner)
			|| "Election Winners:\n\tKatsuki (All Might)\n\tDeku (All Might)\n\tTodoroki (Endeavor)\n\tDabi (Endeavor)\n\tFroppy (EraserHead)\n"
				.equals(electionWinner));
    }
}
