
// File:         TestMariahEP.java
// Created:      2018/11/08
// Last Changed: $Date: 2018/11/08 11:37:56 $
// Author:       <A HREF="nippe014@umn.edu">Jake Nippert</A>
//
// This code is copyright (c) 2018 University of Minnesota - Twin Cities
//

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

//import junitx.framework.FileAssert
import org.junit.Test;

/**
 * The Class TestOPLV.
 */
public class TestMariahEP {
    private static void testFileAuditPair(String electionFile)
	    throws ParseException, IOException, InterruptedException {
	// Keep current System.out
	final PrintStream oldOut = System.out;
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	// Change so System.out saved in baos
	System.setOut(new PrintStream(baos));

	MariahEP.main(new String[] { "../testing/" + electionFile + ".txt", "NoGUI" });

	// Reset the System.out to console
	System.setOut(oldOut);

	// baos contains winner printed from the runElection function
	final String output = new String(baos.toByteArray());
	Path auditFile = Paths.get(".", output.substring(output.lastIndexOf(" ") + 1));

	// Retrieve audit output and expected output.
	List<String> testOutput = Files.readAllLines(auditFile);
	List<String> expectedOutput = Files.readAllLines(Paths.get("../testing", electionFile + "Audit.txt"));
	assertTrue(expectedOutput.containsAll(testOutput) && expectedOutput.size() == testOutput.size());
    }

    private static void testFileAuditPairRandomMsg(String electionFile, String randomMsg)
	    throws ParseException, IOException, InterruptedException {
	// Keep current System.out
	final PrintStream oldOut = System.out;
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	// Change so System.out saved in baos
	System.setOut(new PrintStream(baos));

	MariahEP.main(new String[] { "../testing/" + electionFile + ".txt", "NoGUI" });

	// Reset the System.out to console
	System.setOut(oldOut);

	// baos contains winner printed from the runElection function
	final String output = new String(baos.toByteArray());
	Path auditFile = Paths.get(".", output.substring(output.lastIndexOf(" ") + 1));

	// Retrieve audit output and expected output.
	List<String> testOutput = Files.readAllLines(auditFile);
	testOutput.replaceAll(String::trim);
	assertTrue(testOutput.contains(randomMsg));
    }

    /**
     * Test an election where there is one seat and one winner with six candidates.
     * 
     * @throws ParseException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testMainOPLVOneSeatOneWinner() throws ParseException, IOException, InterruptedException {
	testFileAuditPair("OPLV/oneSeatOneWinner");
    }

    /**
     * Test an election where there is one seat and one winner with six candidates
     * with only a single vote cast.
     * 
     * @throws ParseException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testMainOPLVOneSeatOneWinnerOneVote() throws ParseException, IOException, InterruptedException {
	testFileAuditPair("OPLV/oneSeatOneWinnerOneVote");
    }

    /**
     * Test an election where there is six seats and all candidates receive one vote
     * 
     * @throws ParseException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testMainOPLVSixSeatsSixCandidatesEqual() throws ParseException, IOException, InterruptedException {
	testFileAuditPair("OPLV/sixSeatsSixCandidatesEqual");
    }

    /**
     * Test an election where there is a consequential tie between candidates on the
     * same party
     * 
     * @throws ParseException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testMainOPLVConsequentialPartyTieTwoCandidates()
	    throws ParseException, IOException, InterruptedException {
	testFileAuditPairRandomMsg("OPLV/consequentialPartyTieTwoCandidates",
		"NOTE: Randomly ranked candidates 1 to 2 due to a consequential tie in Party seat allocations.");
    }

    /**
     * Test an election where there is a consequential tie between candidates on the
     * same party
     * 
     * @throws ParseException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testMainOPLVConsequentialPartyTieThreeCandidates()
	    throws ParseException, IOException, InterruptedException {
	testFileAuditPairRandomMsg("OPLV/consequentialPartyTieThreeCandidates",
		"NOTE: Randomly ranked candidates 1 to 3 due to a consequential tie in Party seat allocations.");
    }
}
