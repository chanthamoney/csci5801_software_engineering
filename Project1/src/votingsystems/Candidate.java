
// File:         Candidate.java
// Created:      2018/11/08
// Last Changed: $Date: 2018/11/08 11:37:56 $
// Author:       <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
//
// This code is copyright (c) 2018 University of Minnesota - Twin Cities
//

package votingsystems;

/**
 * Represents an abstract candidate in an election.
 */
abstract public class Candidate {

    /** The name of the candidate. */
    protected String name = "";

    /** The number of votes currently tabulated for the candidate. */
    protected int numVotes = 0;

    /** Throws an error for default constructor. */
    public Candidate() {
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * Instantiates a new candidate.
     *
     * @param name the name of the candidate
     */
    public Candidate(String name) {
	this.name = name;
    }

    /**
     * Gets the name of the candidate.
     *
     * @return the name of the candidate
     */
    public String getName() {
	return this.name;
    }

    /**
     * Gets the number of votes currently tabulated for the candidate.
     *
     * @return the number of votes
     */
    public int getNumVotes() {
	return this.numVotes;
    }
}