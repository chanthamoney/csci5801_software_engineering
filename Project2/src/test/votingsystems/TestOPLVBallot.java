/**
 * File: TestOPLVBallot.java
 * Date Created: 11/08/2018
 * Last Update: Dec 4, 2018 5:48:55 PM
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
     * Testing OPLVBallot() constructor which must fail.
     */
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
    @Test
    public void testOPLVTestVotes() {
	OPLVBallot[] ballots = new OPLVBallot[5];
	for (int i = 0; i < 5; i++) {
	    ballots[i] = new OPLVBallot(i, i);
	    assertEquals(i, ballots[i].getVote());
	}
    }

    /**
     * Test OPLV test IDs.
     */
    @Test
    public void testOPLVTestIDs() {
	OPLVBallot[] ballots = new OPLVBallot[5];
	for (int i = 0; i < 5; i++) {
	    ballots[i] = new OPLVBallot(i, i);
	    assertEquals(i, ballots[i].getID());
	}
    }
}
