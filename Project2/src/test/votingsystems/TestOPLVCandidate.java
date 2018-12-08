/**
 * File: TestOPLVCandidate.java
 * Date Created: 11/08/2018
 * Last Update: Dec 4, 2018 5:49:05 PM
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
     * Test OPLVCandidate() constructor which must fail.
     */
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
     * Test OPLV candidate name is correct.
     */
    @Test
    public void testOPLVCandidateName() {
	OPLVCandidate candidate = initializeTestCandidate();
	assertEquals("TestCandidate", candidate.getName());
    }

    /**
     * Test OPLV candidate party is correct.
     */
    @Test
    public void testOPLVCandidateParty() {
	OPLVCandidate candidate = initializeTestCandidate();
	assertEquals(party, candidate.getParty());
    }

    /**
     * Test OPLV candidate cast zero vote.
     */
    @Test
    public void testOPLVCandidateCastZeroVote() {
	OPLVCandidate candidate = initializeTestCandidate();
	assertEquals(0, candidate.getNumVotes());
	assertEquals(0, candidate.getParty().getNumVotes());
    }

    /**
     * Test OPLV candidate cast one vote.
     */
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
