
// File:         TestIRV.java
// Created:      2018/11/08
// Last Changed: $Date: 2018/11/08 11:37:56 $
// Author:       <A HREF="mailto:silag001@umn.edu">Meghann Silagan</A>
//
// This code is copyright (c) 2018 University of Minnesota - Twin Cities
//

package votingsystems;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

public class TestIRVCandidate {
    IRVBallot testBallot;
    IRVCandidate testCandidate = new IRVCandidate("Jenny");
    int testId = 4;
    ArrayList<Integer> testVotes = new ArrayList<Integer>(); // to initialize the IRVBallot needed for testing

    private void initializeTestBallot() {
	// add votes to testVotes
	this.testVotes.add(1);
	this.testVotes.add(2);
	this.testVotes.add(3);

	this.testBallot = new IRVBallot(this.testVotes, this.testId);
    }

    // Testing IRVCandidate() constructor
    @Test(expected = IllegalArgumentException.class)
    public void testIRVCandidate() {
	try {
	    new IRVCandidate();
	} catch (IllegalArgumentException iae) {
	    assertEquals("Default constructor is not allowed.", iae.getMessage());
	    throw iae;
	}
	fail("Employee Id Null exception did not throw!");
    }

    // Testing IRVCandidate() constructor with parameters
    @Test
    public void testIRVCandidateWithParams() {

    }

    // Testing AddBallot
    @Test
    public void testAddBallot() {
	// initializeTestBallot
	initializeTestBallot();

	// test AddBallot() with the testBallot
	this.testCandidate.addBallot(this.testBallot);

	// Check if number of votes is one
	assertEquals(this.testCandidate.getNumVotes(), 1);

	// Check if elim ballots(0)/elim ballots(numBallots-1) == testBallot
	IRVBallot[] ballots = testCandidate.eliminate();
	assertEquals(this.testBallot, ballots[0]);
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
