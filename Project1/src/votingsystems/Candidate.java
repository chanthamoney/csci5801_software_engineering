
/**
 * File: Candidate.java
 * Date Created: 11/08/2018
 * Last Update: Nov 11, 2018 2:38:18 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

package votingsystems;

// TODO: Auto-generated Javadoc
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