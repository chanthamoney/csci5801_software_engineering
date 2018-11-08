import java.util.LinkedList;

public class IRVCandidate extends Candidate {
    /**
     *
     */
    public LinkedList<IRVBallot> _elimBallots = new LinkedList<IRVBallot>();
    /**
     *
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
     * @param name
     */
    IRVCandidate(String name) {
	super(name);
    }

    /**
     * @param ballot
     */
    public void addBallot(IRVBallot ballot) {
	this._elimBallots.add(ballot);
	this._numVotes += 1;
    }

    /**
     * @return
     */
    public IRVBallot[] eliminate() {
	this._eliminated = true;
	final IRVBallot[] elimBallots = new IRVBallot[this._elimBallots.size()];
	return this._elimBallots.toArray(elimBallots);
    }

    /**
     * @return
     */
    public boolean isEliminated() {
	return this._eliminated;
    }
}
