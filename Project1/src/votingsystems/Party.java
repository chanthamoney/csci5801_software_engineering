
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
 * 
 * @author Team14 [Cassandra Chanthamontry (chant077), Jake Nippert (nippe014),
 *         Meghann Silagan (silag001), Christine Tsai (tsaix223)]
 */
public class Party {

    /** The candidates belonging to the party. */
    public ArrayList<OPLVCandidate> _candidates = new ArrayList<OPLVCandidate>();

    /** The name of the party. */
    private final String _name;

    /** The number of seats allocated to the party for candidates. */
    private int _numSeats = 0;

    /** The total number of votes cast to the party. */
    private int _numVotes = 0;

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
	this._name = name;
    }

    /**
     * Adds the candidate to the party.
     *
     * @param candidate the candidate
     */
    public void addCandidate(OPLVCandidate candidate) {
	this._candidates.add(candidate);
    }

    /**
     * Records a cast vote to the party.
     */
    public void addVote() {
	this._numVotes += 1;
    }

    /**
     * Gets the candidates in the party.
     *
     * @return the candidates in the party
     */
    public ArrayList<OPLVCandidate> getCandidates() {
	return this._candidates;
    }

    /**
     * Gets the name of the party.
     *
     * @return the name
     */
    public String getName() {
	return this._name;
    }

    /**
     * Gets the number of candidates in the party.
     *
     * @return the number of candidates
     */
    public int getNumCandidates() {
	return this._candidates.size();
    }

    /**
     * Gets the number of allocated seats.
     *
     * @return the number of seats
     */
    public int getNumSeats() {
	return this._numSeats;
    }

    /**
     * Gets the number of votes cast to the party.
     *
     * @return the number of votes
     */
    public int getNumVotes() {
	return this._numVotes;
    }

    /**
     * Gets the winning candidates of seats in the party.
     *
     * @return the winning candidates
     */
    public ArrayList<OPLVCandidate> getWinningCandidates() {
	final ArrayList<OPLVCandidate> winners = new ArrayList<OPLVCandidate>();
	for (int i = 0; (i < this._numSeats) && (i < this._candidates.size()); i++)
	    winners.add(this._candidates.get(i));
	return winners;
    }

    /**
     * Rank the candidates according to their total number of votes.
     *
     * @return a string of an audit for the ranking of candidates
     */
    public String rankCandidates() {
	String ret = String.format("\tParty %s:\n", this._name);

	// Randomly decide between ties by using random.
	final Random random = new Random(System.currentTimeMillis());
	this._candidates.sort(
		(o1, o2) -> Integer.compare(o2.getNumVotes(), o1.getNumVotes()) == 0 ? (random.nextBoolean() ? -1 : 1)
			: Integer.compare(o2.getNumVotes(), o1.getNumVotes()));

	int minVotes;
	if ((this._numSeats <= this._candidates.size()) && (this._numSeats > 0))
	    minVotes = this._candidates.get(this._numSeats - 1).getNumVotes();
	else
	    minVotes = Integer.MAX_VALUE;
	int firstIndx = -1, lastIndx = -1;
	for (int i = 0; i < this._candidates.size(); i++) {
	    final OPLVCandidate can = this._candidates.get(i);
	    if (i < this._numSeats)
		ret += "\t\t* ";
	    else
		ret += "\t\t- ";
	    final OPLVCandidate curCan = this._candidates.get(i);
	    ret += String.format("%s [%d vote", curCan.getName(), curCan.getNumVotes());
	    if (curCan.getNumVotes() == 1)
		ret += "]\n";
	    else
		ret += "s]\n";
	    final int numVotes = can.getNumVotes();
	    if ((firstIndx == -1) && (numVotes == minVotes))
		firstIndx = i;
	    if (numVotes == minVotes)
		lastIndx = i;
	}

	// If more than one minimum relay that there was a shuffle decision.
	if ((firstIndx != lastIndx) && (lastIndx >= this._numSeats))
	    ret += String.format(
		    "\t\tNOTE: Randomly ranked candidates %d to %d due to a consequential tie in Party seat allocations.\n",
		    firstIndx + 1, lastIndx + 1);
	return ret;
    }

    /**
     * Sets the number of seats allocated to the party.
     *
     * @param num the new number of seats to be allocated
     */
    public void setNumSeats(int num) {
	this._numSeats = num;
    }
}