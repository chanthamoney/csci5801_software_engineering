
// File:         Ballot.java
// Created:      2018/11/08
// Last Changed: $Date: 2018/11/08 11:37:56 $
// Author:       <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
//
// This code is copyright (c) 2018 University of Minnesota - Twin Cities
//

package votingsystems;

/**
 * Represents an abstract ballot.
 */
abstract public class Ballot {

    /** The unique identifier of a ballot. */
    private final int id;

    /** Throws an error for default constructor. */
    Ballot() {
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * Instantiates a new ballot.
     *
     * @param id the unique identifier for the ballot.
     */
    Ballot(int id) {
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