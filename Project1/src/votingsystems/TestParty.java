
// File:         TestParty.java
// Created:      2018/11/08
// Last Changed: $Date: 2018/11/08 11:37:56 $
// Author:       <A HREF="mailto:chant077@umn.edu">Cassandra Chanthamontry</A>
//
// This code is copyright (c) 2018 University of Minnesota - Twin Cities
//

package votingsystems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

/**
 * The Class TestOPLV.
 */
public class TestParty {
    private ArrayList<OPLVCandidate> candidates = new ArrayList<>();

    /**
     * Initialize test Candidate
     *
     * @return the candidate
     */
    private Party initializeTestParty() {
	Party party = new Party("TestParty");
	for (int i = 0; i < 5; i++) {
	    candidates.add(new OPLVCandidate(String.format("TestCandidate%d", i), party));
	    party.addCandidate(candidates.get(i));
	}
	return party;
    }

    // Testing Party() constructor
    @Test(expected = IllegalArgumentException.class)
    public void testParty() {
	try {
	    new Party();
	} catch (IllegalArgumentException iae) {
	    assertEquals("Default constructor is not allowed.", iae.getMessage());
	    throw iae;
	}
	fail("Default constructor did not throw exception as expected.");
    }

    // Testing if party name is correct
    @Test
    public void testPartyName() {
	Party party = initializeTestParty();
	assertEquals("TestParty", party.getName());
    }

    // Testing casting zero vote
    @Test
    public void testPartyCastZeroVote() {
	Party party = initializeTestParty();
	assertEquals(0, party.getNumVotes());
    }

    // Testing party one vote
    @Test
    public void testPartyCastOneVote() {
	Party party = initializeTestParty();
	party.addVote();
	assertEquals(1, party.getNumVotes());
    }

    // Testing casting five votes
    @Test
    public void testPartyCastFiveVotes() {
	Party party = initializeTestParty();
	for (int i = 0; i < 5; i++) {
	    party.addVote();
	}
	assertEquals(5, party.getNumVotes());
    }

    // Testing default zero seats
    @Test
    public void testPartyZeroSeats() {
	Party party = initializeTestParty();
	assertEquals(0, party.getNumSeats());
    }

    // Testing party seats are correct
    @Test
    public void testPartyOneSeat() {
	Party party = initializeTestParty();
	for (int i = 0; i < 5; i++) {
	    party.setNumSeats(i);
	    assertEquals(i, party.getNumSeats());
	}
    }

    // Testing if number of seats is equal to number of candidates, all candidates
    // are returned
    @Test
    public void testGetWinningCandidatesNumSeatsEqualsNumCandidates() {
	Party party = initializeTestParty();
	party.setNumSeats(5);
	assertEquals(party.getWinningCandidates(), candidates);
    }

    // Testing if number of seats is smaller than the number of candidates, only
    // winning candidates are returned
    @Test
    public void testGetWinningCandidatesLessNumSeatsThanNumCandidates() {
	Party party = initializeTestParty();
	// Initialize candidates to have sequential ranked number of votes
	for (int i = 0; i < 5; i++) {
	    OPLVCandidate candidate = party.getCandidates().get(i);
	    for (int j = 5; j > i; j--) {
		candidate.castVote();
	    }
	}
	// Test that sublist of winning candidates is returned for seats 0 - 5
	for (int i = 0; i < 5; i++) {
	    party.setNumSeats(i);
	    assertEquals(party.getWinningCandidates(), candidates.subList(0, i));
	}
    }

    // Testing ranking zero candidates gives back zero candidates
    @Test
    public void testRankCandidatesZeroCandidate() {
	Party party = new Party("ZeroCandidateParty");
	ArrayList<OPLVCandidate> zeroCandidates = new ArrayList<>();
	party.rankCandidates();
	assertEquals(zeroCandidates, party.getCandidates());
    }

    // Testing ranking one candidate only gives back ranked candidate and has 1 vote
    @Test
    public void testRankCandidatesOneCandidate() {
	Party party = new Party("OneCandidateParty");
	party.addCandidate(new OPLVCandidate("OneCandidate", party));
	OPLVCandidate candidate = party.getCandidates().get(0);
	ArrayList<OPLVCandidate> oneCandidates = new ArrayList<>();
	oneCandidates.add(candidate);
	party.rankCandidates();
	assertEquals(oneCandidates, party.getCandidates());
    }

    // Testing ranking two candidate only gives back two ranked candidates
    @Test
    public void testRankCandidatesTwoCandidates() {
	Party party = new Party("TwoCandidateParty");
	OPLVCandidate candidate1 = new OPLVCandidate("TwoCandidate1", party);
	OPLVCandidate candidate2 = new OPLVCandidate("TwoCandidate2", party);

	party.addCandidate(candidate1);
	party.addCandidate(candidate2);

	// Distribute votes
	party.getCandidates().get(0).castVote();
	party.getCandidates().get(1).castVote();
	party.getCandidates().get(1).castVote();

	ArrayList<OPLVCandidate> twoCandidates = new ArrayList<>();
	twoCandidates.add(candidate2);
	twoCandidates.add(candidate1);
	party.rankCandidates();
	assertEquals(twoCandidates, party.getCandidates());
    }

    // Testing ranking five candidate only gives back five ranked candidates
    @Test
    public void testRankCandidatesFiveCandidates() {
	Party party = initializeTestParty();

	// Initialize candidates to have sequential ranked number of votes
	for (int i = 0; i < 5; i++) {
	    OPLVCandidate candidate = party.getCandidates().get(i);
	    for (int j = 5; j > i; j--) {
		candidate.castVote();
	    }
	}

	for (int i = 0; i < 1; i++) {
	    Collections.shuffle(party.getCandidates());
	    party.rankCandidates();
	    assertEquals(candidates, party.getCandidates());
	}
    }
}
