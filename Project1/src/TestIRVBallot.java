import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class TestIRVBallot {
    int testId = 4;
    // initialize a LinkedList of votes and an id
    ArrayList<Integer> testVotes = new ArrayList<Integer>();

    private IRVBallot testBallotInitialize() {
	// initialize testVotes
	this.testVotes.addAll(Arrays.asList(1, 2, 3, 4));

	return new IRVBallot(this.testVotes, this.testId);
    }

    // Testing IRVBallot() constructor
    @Test(expected = IllegalArgumentException.class)
    public void testIRVBallot() {
	try {
	    new IRVBallot();
	} catch (IllegalArgumentException iae) {
	    assertEquals("Default constructor is not allowed.", iae.getMessage());
	    throw iae;
	}
	fail("Employee Id Null exception did not throw!");
    }

    // Testing IRVBallot() constructor with parameters
    @Test
    public void testIRVBallotWithParams() {

    }

    // Testing getID() method of ballot class that irv ballot extends
    @Test
    public void testGetID() {
	// initialize Test Ballot
	final IRVBallot testBallot = testBallotInitialize();

	assertEquals(4, testBallot.getID());
    }

    // Testing getNextVote() with one vote
    @Test
    public void testGetNextVoteOneVote() {
	// initialize test IRVBallot
	final IRVBallot testBallot = testBallotInitialize();

	// test get next vote is as expected
	final int nextVote = testBallot.getNextVote();
	assertEquals(this.testVotes.get(0), nextVote, 0.0);
    }

    // Testing getNextVote() with two three
    @Test
    public void testGetNextVoteThreeVotes() {
	// initialize test IRVBallot
	final IRVBallot testBallot = testBallotInitialize();

	// test get next vote is as expected
	int nextVote = testBallot.getNextVote();
	assertEquals(this.testVotes.get(0), nextVote, 0.0);
	nextVote = testBallot.getNextVote();
	assertEquals(this.testVotes.get(1), nextVote, 0.0);
	nextVote = testBallot.getNextVote();
	assertEquals(this.testVotes.get(2), nextVote, 0.0);
    }

    // Testing is exhausted method
    @Test
    public void testIsExhausted() {
	// initalize testBallot
	final IRVBallot testBallot = testBallotInitialize();

	assertFalse(testBallot.isExhausted());
    }
}
