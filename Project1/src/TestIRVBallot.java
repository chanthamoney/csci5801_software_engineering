import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.Test;

public class TestIRVBallot {
	int testId = 4;
	// initialize an arraylist of votes and an id
	ArrayList<Integer> testVotes = new ArrayList<Integer>();

	private IRVBallot testBallotInitialize() {
		// initialize testVotes
		this.testVotes.add(1);
		this.testVotes.add(2);
		this.testVotes.add(3);
		this.testVotes.add(4);

		return new IRVBallot(this.testVotes, this.testId);
	}

	// Testing getID() method of ballot class that irv ballot extends
	@Test
	public void testGetID() {
		// initialize Test Ballot
		final IRVBallot testBallot = testBallotInitialize();

		assertEquals(4, testBallot.getID());
	}

	// Testing getNextVote()
	@Test
	public void testGetNextVote() {
		// initialize test IRVBallot
		final IRVBallot testBallot = testBallotInitialize();

		// test get next vote is as expected
		final int nextVote = testBallot.getNextVote();
		assertEquals(this.testVotes.get(0), nextVote, 0.0);
		assertEquals(1, nextVote, 0.0);
	}

	// Testing is exhausted method
	@Test
	public void testIsExhausted() {
		// initalize testBallot
		final IRVBallot testBallot = testBallotInitialize();

		assertFalse(testBallot.isExhausted());
	}
}
