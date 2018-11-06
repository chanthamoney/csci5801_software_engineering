import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class TestIRVCandidate {
	IRVBallot testBallot;
	IRVCandidate testCandidate = new IRVCandidate("Jenny");
	int testId = 4;
	ArrayList<Integer> testVotes = new ArrayList<Integer>(); // to intitialize the IRVBallot needed for testing

	private void initializeTestBallot() {
		// add votes to testVotes
		this.testVotes.add(1);
		this.testVotes.add(2);
		this.testVotes.add(3);

		this.testBallot = new IRVBallot(this.testVotes, this.testId);
	}

	// Testing AddBallot
	@Test
	public void testAddBallot() {
		// initializeTestBallot
		initializeTestBallot();

		// test AddBallot() with the testBallot
		this.testCandidate.addBallot(this.testBallot);

		// Check if elim ballots(0)/elim ballots(numBallots-1) == testBallot
		assertEquals(this.testBallot, this.testCandidate._elimBallots.get(1 - 1));
	}

	// Testing eliminate method
	@Test
	public void testEliminate() {
		// initializeTestBallot
		initializeTestBallot();

		// add ballot to test candidate
		this.testCandidate.addBallot(this.testBallot);

		// eliminate candidate and get their ballot
		final IRVBallot[] eliminatedBallot = this.testCandidate.eliminate();

		// expected value an array list of irv ballots
		final IRVBallot[] expectedEliminatedBallot = new IRVBallot[1];
		expectedEliminatedBallot[0] = this.testBallot;

		// check if eliminated Ballot == testBallot
		assertArrayEquals(expectedEliminatedBallot, eliminatedBallot);
	}

	// Testing getName() method of candidate class that IRVCandidate extends
	@Test
	public void testGetName() {
		assertEquals("Jenny", this.testCandidate.getName());
	}

	// Testing getNumVotes() method of candidate class that IRVCandidate extends
	@Test
	public void testGetNumVotes() {
		// initialize testBallot
		initializeTestBallot();

		// add ballot to test candidate
		this.testCandidate.addBallot(this.testBallot);

		// check num votes is as expected
		assertEquals(1, this.testCandidate.getNumVotes());
	}

	// Testing isEliminated method
	@Test
	public void testIsEliminated() {
		// initialize test ballot
		initializeTestBallot();

		// add test ballots
		this.testCandidate.addBallot(this.testBallot);

		// eliminate candidate
		this.testCandidate.eliminate();

		// Check if candidate returns true to isEliminated
		assertTrue(this.testCandidate.isEliminated());
	}
}
