
/**
 * File: Ballot.java
 * Date Created: 11/08/2018
 * Last Update: Nov 11, 2018 2:38:09 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

package votingsystems;

/**
 * Represents an abstract ballot.
 */
abstract public class Ballot {

    /** The unique identifier of a ballot. */
    private final int id;

    /** Throws an error for default constructor. */
    public Ballot() {
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * Instantiates a new ballot.
     *
     * @param id the unique identifier for the ballot.
     */
    public Ballot(int id) {
	this.id = id;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getID() {
	return this.id;
    }
}