
/**
 * File: TestOPLVCandidate.java
 * Date Created: 11/08/2018
 * Last Update: Nov 12, 2018 12:28:37 AM
 * Author: <A HREF="mailto:chant077@umn.edu">Cassandra Chanthamontry</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

package test.votingsystems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import votingsystems.OPLVCandidate;
import votingsystems.Party;

/**
 * The Class TestOPLV.
 */
public class TestOPLVCandidate {

    /** The party. */
    private Party party = new Party("TestParty");

    /**
     * Initialize test Candidate.
     *
     * @return the candidate
     */
    private OPLVCandidate initializeTestCandidate() {
	return new OPLVCandidate("TestCandidate", party);
    }

    /**
     * Test OPLV candidate.
     */
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

    /**
     * Test OPLV candidate name.
     */
    // Testing if candidate name is correct
    @Test
    public void testOPLVCandidateName() {
	OPLVCandidate candidate = initializeTestCandidate();
	assertEquals("TestCandidate", candidate.getName());
    }

    /**
     * Test OPLV candidate party.
     */
    // Testing if candidate party is correct
    @Test
    public void testOPLVCandidateParty() {
	OPLVCandidate candidate = initializeTestCandidate();
	assertEquals(party, candidate.getParty());
    }

    /**
     * Test OPLV candidate cast zero vote.
     */
    // Testing casting zero vote
    @Test
    public void testOPLVCandidateCastZeroVote() {
	OPLVCandidate candidate = initializeTestCandidate();
	assertEquals(0, candidate.getNumVotes());
	assertEquals(0, candidate.getParty().getNumVotes());
    }

    /**
     * Test OPLV candidate cast one vote.
     */
    // Testing casting one vote
    @Test
    public void testOPLVCandidateCastOneVote() {
	OPLVCandidate candidate = initializeTestCandidate();
	candidate.castVote();
	assertEquals(1, candidate.getNumVotes());
	assertEquals(1, candidate.getParty().getNumVotes());
    }

    /**
     * Test OPLV candidate cast five votes.
     */
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
