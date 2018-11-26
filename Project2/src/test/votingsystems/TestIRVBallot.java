
/**
 * File: TestIRVBallot.java
 * Date Created: 11/08/2018
 * Last Update: Nov 26, 2018 5:30:18 PM
 * Author: <A HREF="mailto:silag001@umn.edu">Meghann Silagan</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

package test.votingsystems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import votingsystems.IRVBallot;

/**
 * The Class TestIRVBallot.
 */
public class TestIRVBallot {

    /** The test id. */
    int testId = 4;

    /** The test votes. These must be initialized. */
    ArrayList<Integer> testVotes = new ArrayList<>();

    /**
     * Test ballot initialize.
     *
     * @return the IRV ballot
     */
    private IRVBallot testBallotInitialize() {
	// initialize testVotes
	this.testVotes.addAll(Arrays.asList(1, 2, 3, 4));

	return new IRVBallot(this.testVotes, this.testId);
    }

    /**
     * Test IRV ballot constructor which must fail.
     */
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

    /**
     * Testing getID() method of ballot class that irv ballot extends.
     */
    @Test
    public void testGetID() {
	// initialize Test Ballot
	final IRVBallot testBallot = testBallotInitialize();

	assertEquals(4, testBallot.getID());
    }

    /**
     * Test get next vote one vote.
     */
    @Test
    public void testGetNextVoteOneVote() {
	// initialize test IRVBallot
	final IRVBallot testBallot = testBallotInitialize();

	// test get next vote is as expected
	final int nextVote = testBallot.getNextVote();
	assertEquals(this.testVotes.get(0), nextVote, 0.0);
    }

    /**
     * Test get next vote when no votes.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetNextVoteWhenNoVotes() {
	// initialize test IRVBallot
	final IRVBallot emptyBallot = new IRVBallot(new ArrayList<Integer>(), 0);

	// test get next vote is as expected
	try {
	    emptyBallot.getNextVote();
	} catch (IndexOutOfBoundsException ioob) {
	    assertEquals("Index: 0, Size: 0", ioob.getMessage());
	    throw ioob;
	}
	fail("Expected an IndexOutOfBoundsException to be thrown");
    }

    /**
     * Test get next vote five times with only four votes.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetNextVoteFiveTimesWithOnlyFourVotes() {
	// initialize test IRVBallot
	final IRVBallot emptyBallot = testBallotInitialize();

	// test get next vote is as expected
	try {
	    for (int i = 0; i < 5; i++) {
		emptyBallot.getNextVote();
	    }
	} catch (IndexOutOfBoundsException ioob) {
	    assertEquals("Index: 4, Size: 4", ioob.getMessage());
	    throw ioob;
	}
	fail("Expected an IndexOutOfBoundsException to be thrown");
    }

    /**
     * Test get next vote three votes.
     */
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

    /**
     * Test is exhausted when not exhausted.
     */
    @Test
    public void testIsExhaustedWhenNotExhausted() {
	// initalize testBallot
	final IRVBallot testBallot = testBallotInitialize();

	assertFalse(testBallot.isExhausted());
    }

    /**
     * Test is exhausted when exhausted.
     */
    @Test
    public void testIsExhaustedWhenExhausted() {
	// initalize testBallot
	final IRVBallot testBallot = testBallotInitialize();
	for (int i = 0; i < 4; i++) {
	    testBallot.getNextVote();
	}

	assertTrue(testBallot.isExhausted());
    }
}
