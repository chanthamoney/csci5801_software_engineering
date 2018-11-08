import java.util.LinkedList;

/**
 * Represents a candidate in an instant runoff election.
 */
public class IRVCandidate extends Candidate {

    /**
     * The ballots cast for the candidate.
     */
    public LinkedList<IRVBallot> _ballots = new LinkedList<IRVBallot>();

    /**
     * Maintains whether the candidate has been eliminated.
     */
    private boolean _eliminated = false;

    /**
     * Throws an error for default constructor.
     */
    IRVCandidate() {
	super();
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * Instantiates a new IRV candidate.
     *
     * @param name the name of the candidate
     */
    IRVCandidate(String name) {
	super(name);
    }

    /**
     * Adds the ballot to the candidate indicating the ballot has been cast for this
     * candidate.
     *
     * @param ballot the ballot
     */
    public void addBallot(IRVBallot ballot) {
	this._ballots.add(ballot);
	this._numVotes += 1;
    }

    /**
     * Eliminates the candidate and provides all of the ballots that were cast for
     * this candidate.
     *
     * @return the IRV list of ballots cast for the candidate
     */
    public IRVBallot[] eliminate() {
	this._eliminated = true;
	final IRVBallot[] elimBallots = new IRVBallot[this._ballots.size()];
	return this._ballots.toArray(elimBallots);
    }

    /**
     * Checks if the candidate has been eliminated for an instant runoff.
     *
     * @return true, if the candidate has been eliminated
     */
    public boolean isEliminated() {
	return this._eliminated;
    }
}
