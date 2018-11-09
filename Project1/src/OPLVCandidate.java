/**
 * Represents a candidate in an open party list voting election.
 * 
 * @author Team14 [Cassandra Chanthamontry (chant077), Jake Nippert (nippe014),
 *         Meghann Silagan (silag001), Christine Tsai (tsaix223)]
 */
public class OPLVCandidate extends Candidate {

    /** The party a candidate belongs to. */
    private final Party _party;

    /** Throws an error for default constructor. */
    OPLVCandidate() {
	super();
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * Instantiates a new OPLV candidate.
     *
     * @param name  the name of the candidate
     * @param party the party the candidate belongs to
     */
    OPLVCandidate(final String name, final Party party) {
	super(name);
	this._party = party;
    }

    /**
     * Records a cast vote to the candidate.
     */
    public void castVote() {
	this._party.addVote();
	this._numVotes += 1;
    }

    /**
     * Gets the party the candidate belongs to.
     *
     * @return the party
     */
    public Party getParty() {
	return this._party;
    }
}