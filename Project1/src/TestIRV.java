import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import org.junit.Test;

public class TestIRV {
	String[] candidates = { "Naruto", "Sasuke", "Sakura", "Kakashi" };
	ArrayList<ArrayList<Integer>> testBallots = new ArrayList<ArrayList<Integer>>();

	private IRV intitializeTestIRV() {
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

		return new IRV(this.testBallots.size(), this.candidates.length, this.candidates, this.testBallots, "auditFile");
	}

	// _QUESTION: Do we test private functions?? - Maybe not bc they are used in the
	// public methods?

	// Testing runElection() only public method

	@Test
	public void testRunElection() {
		// Initialize IRV Election
		final IRV hokageElection = intitializeTestIRV();

		// Keep current System.out
		final PrintStream oldOut = System.out;
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// Change so System.out saved in baos
		System.setOut(new PrintStream(baos));

		// Run election on irvElection
		try {
			hokageElection.runElection();
		} catch (final IOException e) {
			e.printStackTrace();
		}

		// Reset the System.out to console
		System.setOut(oldOut);

		// baos contains winner printed from the runElection function
		final String output = new String(baos.toByteArray());

		System.out.println(output); // prints winner from function

		// check if winner is as expected
		assertTrue(output.equals("Election Winner: Sasuke\n") || output.equals("Election Winner: Naruto\n")
				|| output.equals("Election Winner: Sakura\n"));
		// assertEquals("Election Winner: Sasuke", output);

	}

	@Test
	public void testRunElectionEfficiency() {
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
			final ArrayList<Integer> newBallot = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
					12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30));
			Collections.shuffle(newBallot, randomizer);
			this.testBallots.add(newBallot);
		}
		final VotingSystem vs = new IRV(this.testBallots.size(), candidates_300.length, candidates_300,
				this.testBallots, "auditFile");

		// Record current time in milliseconds immediately before election run
		final long timeBefore = System.currentTimeMillis();

		// Run election on irvElection
		try {
			vs.runElection();
		} catch (final IOException e) {
			e.printStackTrace();
		}

		// Record time immediately after election
		final long timeAfter = System.currentTimeMillis();

		// Reset the System.out to console
		System.setOut(oldOut);

		System.out.println(timeAfter - timeBefore); // prints winner from function

		// Must take less than 8 minutes to process a 100,000 vote election.
		assertTrue((timeAfter - timeBefore) < 480000);
	}
}
