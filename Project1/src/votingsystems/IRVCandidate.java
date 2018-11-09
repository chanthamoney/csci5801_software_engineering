
// File:         IRVCandidate.java
// Created:      2018/11/08
// Last Changed: $Date: 2018/11/08 11:37:56 $
// Author:       <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
//
// This code is copyright (c) 2018 University of Minnesota - Twin Cities
//

package votingsystems;

import java.util.LinkedList;

/**
 * Represents a candidate in an instant runoff election.
 */
public class IRVCandidate extends Candidate {

    /** The ballots cast for the candidate. */
    private LinkedList<IRVBallot> ballots = new LinkedList<IRVBallot>();

    /** Maintains whether the candidate has been eliminated. */
    private boolean eliminated = false;

    /** Throws an error for default constructor. */
    IRVCandidate() {
	super();
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * Instantiates a new IRV candidate.
     *
     * @param name the name of the candidate
     */
    IRVCandidate(String name) {
	super(name);
    }

    /**
     * Adds the ballot to the candidate indicating the ballot has been cast for this
     * candidate.
     *
     * @param ballot the ballot
     */
    public void addBallot(IRVBallot ballot) {
	this.ballots.add(ballot);
	this.numVotes += 1;
    }

    /**
     * Eliminates the candidate and provides all of the ballots that were cast for
     * this candidate.
     *
     * @return the IRV list of ballots cast for the candidate
     */
    public IRVBallot[] eliminate() {
	this.eliminated = true;
	final IRVBallot[] elimBallots = new IRVBallot[this.ballots.size()];
	return this.ballots.toArray(elimBallots);
    }

    /**
     * Checks if the candidate has been eliminated for an instant runoff.
     *
     * @return true, if the candidate has been eliminated
     */
    public boolean isEliminated() {
	return this.eliminated;
    }
}
