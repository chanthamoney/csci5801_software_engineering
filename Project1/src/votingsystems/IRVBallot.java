
// File:         IRVBallot.java
// Created:      2018/11/08
// Last Changed: $Date: 2018/11/08 11:37:56 $
// Author:       <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
//
// This code is copyright (c) 2018 University of Minnesota - Twin Cities
//

package votingsystems;

import java.util.ArrayList;

/**
 * Represents a ballot in an instant runoff election.
 */
public class IRVBallot extends Ballot {

    /** Maintains the index on the ballot array to retrieve the next vote. */
    private int curVoteIndex = 0;

    /**
     * Keeps the list of votes (generally the index of candidates in the candidate
     * array for efficiency, or potentially any other unique identifier) on the
     * ballot.
     */
    private final ArrayList<Integer> votes;

    /** Throws an error for default constructor. */
    IRVBallot() {
	super();
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * Instantiates a new instant runoff ballot.
     *
     * @param votes the votes
     * @param id    the id
     */
    IRVBallot(ArrayList<Integer> votes, int id) {
	super(id);
	this.votes = votes;
    }

    /**
     * Gets the next vote.
     *
     * @return the next vote
     */
    public int getNextVote() {
	this.curVoteIndex += 1;
	return this.votes.get(this.curVoteIndex - 1);
    }

    /**
     * Checks if the ballot has exhausted all of its votes.
     *
     * @return true, if the ballot has exhausted all of its votes
     */
    public boolean isExhausted() {
	return this.votes.size() <= this.curVoteIndex;
    }
}