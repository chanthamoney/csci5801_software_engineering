
// File:         Party.java
// Created:      2018/11/08
// Last Changed: $Date: 2018/11/08 11:37:56 $
// Author:       <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
//
// This code is copyright (c) 2018 University of Minnesota - Twin Cities
//

package votingsystems;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a party in an open party list voting.
 */
public class Party {

    /** The candidates belonging to the party. */
    private ArrayList<OPLVCandidate> candidates = new ArrayList<>();

    /** The name of the party. */
    private final String name;

    /** The number of seats allocated to the party for candidates. */
    private int numSeats = 0;

    /** The total number of votes cast to the party. */
    private int numVotes = 0;

    /** Throws an error for default constructor. */
    Party() {
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * Instantiates a new party.
     *
     * @param name the name of the party
     */
    Party(String name) {
	this.name = name;
    }

    /**
     * Adds the candidate to the party.
     *
     * @param candidate the candidate
     */
    public void addCandidate(OPLVCandidate candidate) {
	this.candidates.add(candidate);
    }

    /**
     * Records a cast vote to the party.
     */
    public void addVote() {
	this.numVotes += 1;
    }

    /**
     * Gets the candidates in the party.
     *
     * @return the candidates in the party
     */
    public ArrayList<OPLVCandidate> getCandidates() {
	return this.candidates;
    }

    /**
     * Gets the name of the party.
     *
     * @return the name
     */
    public String getName() {
	return this.name;
    }

    /**
     * Gets the number of candidates in the party.
     *
     * @return the number of candidates
     */
    public int getNumCandidates() {
	return this.candidates.size();
    }

    /**
     * Gets the number of allocated seats.
     *
     * @return the number of seats
     */
    public int getNumSeats() {
	return this.numSeats;
    }

    /**
     * Gets the number of votes cast to the party.
     *
     * @return the number of votes
     */
    public int getNumVotes() {
	return this.numVotes;
    }

    /**
     * Gets the winning candidates of seats in the party.
     *
     * @return the winning candidates
     */
    public ArrayList<OPLVCandidate> getWinningCandidates() {
	final ArrayList<OPLVCandidate> winners = new ArrayList<>();
	for (int i = 0; (i < this.numSeats) && (i < this.candidates.size()); i++) {
	    winners.add(this.candidates.get(i));
	}
	return winners;
    }

    /**
     * Rank the candidates according to their total number of votes.
     *
     * @return a string of an audit for the ranking of candidates
     */
    public String rankCandidates() {
	String ret = String.format("\tParty %s:%n", this.name);

	// Randomly decide between ties by using random.
	final Random random = new Random(System.currentTimeMillis());
	this.candidates.sort(
		(o1, o2) -> Integer.compare(o2.getNumVotes(), o1.getNumVotes()) == 0 ? (random.nextBoolean() ? -1 : 1)
			: Integer.compare(o2.getNumVotes(), o1.getNumVotes()));

	int minVotes;
	if ((this.numSeats <= this.candidates.size()) && (this.numSeats > 0)) {
	    minVotes = this.candidates.get(this.numSeats - 1).getNumVotes();
	} else {
	    minVotes = Integer.MAX_VALUE;
	}
	int firstIndx = -1;
	int lastIndx = -1;
	for (int i = 0; i < this.candidates.size(); i++) {
	    final OPLVCandidate can = this.candidates.get(i);
	    if (i < this.numSeats) {
		ret += "\t\t* ";
	    } else {
		ret += "\t\t- ";
	    }
	    final OPLVCandidate curCan = this.candidates.get(i);
	    ret += String.format("%s [%d vote", curCan.getName(), curCan.getNumVotes());
	    if (curCan.getNumVotes() == 1) {
		ret += "]\n";
	    } else {
		ret += "s]\n";
	    }
	    final int numVotes = can.getNumVotes();
	    if ((firstIndx == -1) && (numVotes == minVotes)) {
		firstIndx = i;
	    }
	    if (numVotes == minVotes) {
		lastIndx = i;
	    }
	}

	// If more than one minimum relay that there was a shuffle decision.
	if ((firstIndx != lastIndx) && (lastIndx >= this.numSeats)) {
	    ret += String.format(
		    "\t\tNOTE: Randomly ranked candidates %d to %d due to a consequential tie in Party seat allocations.%n",
		    firstIndx + 1, lastIndx + 1);
	}
	return ret;
    }

    /**
     * Sets the number of seats allocated to the party.
     *
     * @param num the new number of seats to be allocated
     */
    public void setNumSeats(int num) {
	this.numSeats = num;
    }
}
