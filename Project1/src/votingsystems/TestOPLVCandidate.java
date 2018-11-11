
// File:         TestOPLVCandidate.java
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
public class TestOPLVCandidate {

    private Party party = new Party("TestParty");

    /**
     * Initialize test Candidate
     *
     * @return the candidate
     */
    private OPLVCandidate initializeTestCandidate() {
	return new OPLVCandidate("TestCandidate", party);
    }

    // Testing OPLVCandidate() constructor
    @Test(expected = IllegalArgumentException.class)
    public void testOPLVCandidate() {
	try {
	    new OPLVCandidate();
	} catch (IllegalArgumentException iae) {
	    assertEquals("Default constructor is not allowed.", iae.getMessage());
	    throw iae;
	}
	fail("Default constructor did not throw exception as expected.");
    }

    // Testing if candidate name is correct
    @Test
    public void testOPLVCandidateName() {
	OPLVCandidate candidate = initializeTestCandidate();
	assertEquals("TestCandidate", candidate.getName());
    }

    // Testing if candidate party is correct
    @Test
    public void testOPLVCandidateParty() {
	OPLVCandidate candidate = initializeTestCandidate();
	assertEquals(party, candidate.getParty());
    }

    // Testing casting zero vote
    @Test
    public void testOPLVCandidateCastZeroVote() {
	OPLVCandidate candidate = initializeTestCandidate();
	assertEquals(0, candidate.getNumVotes());
	assertEquals(0, candidate.getParty().getNumVotes());
    }

    // Testing casting one vote
    @Test
    public void testOPLVCandidateCastOneVote() {
	OPLVCandidate candidate = initializeTestCandidate();
	candidate.castVote();
	assertEquals(1, candidate.getNumVotes());
	assertEquals(1, candidate.getParty().getNumVotes());
    }

    // Testing casting five votes
    @Test
    public void testOPLVCandidateCastFiveVotes() {
	OPLVCandidate candidate = initializeTestCandidate();
	for (int i = 0; i < 5; i++) {
	    candidate.castVote();
	}
	assertEquals(5, candidate.getNumVotes());
	assertEquals(5, candidate.getParty().getNumVotes());
    }
}
