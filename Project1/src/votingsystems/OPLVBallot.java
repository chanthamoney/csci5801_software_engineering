
// File:         OPLVBallot.java
// Created:      2018/11/08
// Last Changed: $Date: 2018/11/08 11:37:56 $
// Author:       <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
//
// This code is copyright (c) 2018 University of Minnesota - Twin Cities
//

package votingsystems;

/**
 * Represents a ballot in an open party list voting election.
 */
public class OPLVBallot extends Ballot {

    /**
     * The vote (generally as an index of the candidate in an array for efficiency,
     * or potentially any other unique identifier).
     */
    private final int vote;

    /** Throws an error for default constructor. */
    public OPLVBallot() {
	super();
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * Instantiates a new OPLV ballot.
     *
     * @param vote the vote
     * @param id   the id
     */
    public OPLVBallot(int vote, int id) {
	super(id);
	this.vote = vote;
    }

    /**
     * Gets the vote.
     *
     * @return the vote
     */
    public int getVote() {
	return this.vote;
    }
}