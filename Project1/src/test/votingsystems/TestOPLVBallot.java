
/**
 * File: TestOPLVBallot.java
 * Date Created: 11/08/2018
 * Last Update: Nov 11, 2018 2:40:11 PM
 * Author: <A HREF="mailto:chant077@umn.edu">Cassandra Chanthamontry</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

package test.votingsystems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import votingsystems.OPLVBallot;

/**
 * The Class TestOPLV.
 */
public class TestOPLVBallot {

    /**
     * Test OPLV ballot.
     */
    // Testing OPLVBallot() constructor
    @Test(expected = IllegalArgumentException.class)
    public void testOPLVBallot() {
	try {
	    new OPLVBallot();
	} catch (IllegalArgumentException iae) {
	    assertEquals("Default constructor is not allowed.", iae.getMessage());
	    throw iae;
	}
	fail("Default constructor did not throw exception as expected.");
    }

    /**
     * Test OPLV test votes.
     */
    // Testing OPLVBallot() constructor with vote zero
    @Test
    public void testOPLVTestVotes() {
	OPLVBallot[] ballots = new OPLVBallot[5];
	for (int i = 0; i < 5; i++) {
	    ballots[i] = new OPLVBallot(i, i);
	    assertEquals(i, ballots[i].getVote());
	}
    }

    /**
     * Test OPLV test I ds.
     */
    // Testing OPLVBallot() constructor with vote zero
    @Test
    public void testOPLVTestIDs() {
	OPLVBallot[] ballots = new OPLVBallot[5];
	for (int i = 0; i < 5; i++) {
	    ballots[i] = new OPLVBallot(i, i);
	    assertEquals(i, ballots[i].getID());
	}
    }
}
