
// File:         VotingSystem.java
// Created:      2018/11/08
// Last Changed: $Date: 2018/11/08 11:37:56 $
// Author:       <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
//
// This code is copyright (c) 2018 University of Minnesota - Twin Cities
//

package votingsystems;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import auditor.Auditor;

/**
 * Represents an abstract voting system.
 */
abstract public class VotingSystem {

    /** The auditor which maintains information about the election processing. */
    protected Auditor auditor;

    /** The number of ballots cast in the election. */
    protected int numBallots;

    /** The number of candidates participating in the election. */
    protected int numCandidates;

    /** The quota of votes necessary to be guaranteed a win in the election. */
    protected int quota;

    /** Maintains whether the election has been run before. */
    protected AtomicBoolean wasRun = new AtomicBoolean(false);

    /** Throws an error for default constructor. */
    VotingSystem() {
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * Constructed the data in the abstract voting system instantiation.
     *
     * @param numBallots    the number of ballots
     * @param numCandidates the number of candidates
     */
    VotingSystem(int numBallots, int numCandidates) {
	this.numBallots = numBallots;
	this.numCandidates = numCandidates;
	this.auditor = new Auditor();
    }

    /**
     * Run election.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public abstract void runElection() throws IOException;
}