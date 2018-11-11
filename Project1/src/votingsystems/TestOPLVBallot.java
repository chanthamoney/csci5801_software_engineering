
// File:         TestOPLVBallot.java
// Created:      2018/11/08
// Last Changed: $Date: 2018/11/08 11:37:56 $
// Author:       <A HREF="mailto:chant077@umn.edu">Cassandra Chanthamontry</A>
//
// This code is copyright (c) 2018 University of Minnesota - Twin Cities
//

package votingsystems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * The Class TestOPLV.
 */
public class TestOPLVBallot {
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

    // Testing OPLVBallot() constructor with vote zero
    @Test
    public void testOPLVTestVotes() {
	OPLVBallot[] ballots = new OPLVBallot[5];
	for (int i = 0; i < 5; i++) {
	    ballots[i] = new OPLVBallot(i, i);
	    assertEquals(i, ballots[i].getVote());
	}
    }

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
