
/**
 * File: VotingSystem.java
 * Date Created: 11/08/2018
 * Last Update: Nov 26, 2018 5:31:53 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

package votingsystems;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
    public VotingSystem() {
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * Constructed the data in the abstract voting system instantiation.
     *
     * @param numBallots    the number of ballots
     * @param numCandidates the number of candidates
     */
    public VotingSystem(int numBallots, int numCandidates) {
	this.numBallots = numBallots;
	this.numCandidates = numCandidates;
	this.auditor = new Auditor();
    }

    /**
     * Run election.
     *
     * @return the name of the Audit file produced
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    public abstract String runElection() throws IOException, InterruptedException, InvocationTargetException;
}