import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents an abstract voting system.
 */
abstract public class VotingSystem {

    /**
     * The auditor for the voting system to maintain information about the election
     * processing.
     */
    protected Auditor _auditor;

    /**
     * The number of ballots cast in the election.
     */
    protected int _numBallots;

    /**
     * The number of candidates participating in the election.
     */
    protected int _numCandidates;

    /**
     * The quota for votes associated with being guaranteed a win in the election.
     */
    protected int _quota;

    /**
     * Maintains whether the election has been run before.
     */
    protected AtomicBoolean wasRun = new AtomicBoolean(false);

    /**
     * Throws an error for default constructor.
     */
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
	this._numBallots = numBallots;
	this._numCandidates = numCandidates;
	this._auditor = new Auditor();
    }

    /**
     * Run election.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    abstract protected void runElection() throws IOException;
}