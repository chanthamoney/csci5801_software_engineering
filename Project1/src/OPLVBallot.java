/**
 * Represents a ballot in an open party list voting election.
 * 
 * @author Team14 [Cassandra Chanthamontry (chant077), Jake Nippert (nippe014),
 *         Meghann Silagan (silag001), Christine Tsai (tsaix223)]
 */
public class OPLVBallot extends Ballot {

    /**
     * The vote (generally as an index of the candidate in an array for efficiency,
     * or potentially any other unique identifier).
     */
    private final int _vote;

    /** Throws an error for default constructor. */
    OPLVBallot() {
	super();
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * Instantiates a new OPLV ballot.
     *
     * @param vote the vote
     * @param id   the id
     */
    OPLVBallot(int vote, int id) {
	super(id);
	this._vote = vote;
    }

    /**
     * Gets the vote.
     *
     * @return the vote
     */
    public int getVote() {
	return this._vote;
    }
}