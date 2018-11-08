import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Test;

public class TestOPLV {
    String[] candidates = { "Naruto", "Sasuke", "Sakura", "Kakashi" };
    String[] parties = { "Naruto", "Sasuke", "Sakura", "Kakashi" };
    LinkedList<Integer> testBallots = new LinkedList<Integer>();

    // Testing IRVOPLV() constructor
    @Test
    public void testOPLV() {

    }

    // Testing IRVOPLV() constructor with parameters
    @Test
    public void testOPLVWithParams() {

    }

    @Test
    public void testRunElection() {

    }

    @Test
    public void testRunElectionEfficiency() {
	// Keep current System.out
	final PrintStream oldOut = System.out;
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	// Change so System.out saved in baos
	System.setOut(new PrintStream(baos));

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
	System.out.print(parties_100_80_60_40_20);
	System.out.print(candidates_300);
	final Random randomizer = new Random(System.currentTimeMillis());
	for (int i = 0; i < 100000; i++)
	    this.testBallots.add(randomizer.nextInt(300));
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

	// Reset the System.out to console
	System.setOut(oldOut);

	// Must take less than 8 minutes to process a 100,000 vote election.
	assertTrue((timeAfter - timeBefore) < 480000);
    }
}
