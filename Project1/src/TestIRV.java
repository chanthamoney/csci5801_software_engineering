import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class TestIRV {
	String[] candidates = { "Naruto", "Sasuke", "Sakura", "Kakashi" };
	ArrayList<ArrayList<Integer>> testBallots = new ArrayList<ArrayList<Integer>>();

	private IRV intitializeTestIRV() {
		final ArrayList<Integer> firstBallot = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
		final ArrayList<Integer> secondBallot = new ArrayList<>(Arrays.asList(1, 0, 2, 4));
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
	// _QUESTION: what is printed to console? Election winner: name or just name?

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

		// check if winner is as expected
		assertEquals("Sasuke", output);

		System.out.println(output); // prints winner from function
	}
}
