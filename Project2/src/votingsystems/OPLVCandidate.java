/**
 * File: OPLVCandidate.java
 * Date Created: 11/08/2018
 * Last Update: Dec 4, 2018 5:50:19 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

package votingsystems;

/**
 * Represents a candidate in an open party list voting election.
 */
public class OPLVCandidate extends Candidate {

    /** The party a candidate belongs to. */
    private final Party party;

    /** Throws an error for default constructor. */
    public OPLVCandidate() {
	super();
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * Instantiates a new OPLV candidate.
     *
     * @param name  the name of the candidate
     * @param party the party the candidate belongs to
     */
    public OPLVCandidate(final String name, final Party party) {
	super(name);
	this.party = party;
    }

    /**
     * Records a cast vote to the candidate.
     */
    public void castVote() {
	this.party.addVote();
	this.numVotes += 1;
    }

    /**
     * Gets the party the candidate belongs to.
     *
     * @return the party
     */
    public Party getParty() {
	return this.party;
    }
}