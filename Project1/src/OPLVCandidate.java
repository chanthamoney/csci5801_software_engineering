public class OPLVCandidate extends Candidate {
    /**
     *
     */
    private final Party _party;

    /**
     * Throws an error for default constructor.
     */
    OPLVCandidate() {
	super();
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * @param name
     * @param party
     */
    OPLVCandidate(final String name, final Party party) {
	super(name);
	this._party = party;
    }

    /**
     *
     */
    public void castVote() {
	this._party.addVote();
	this._numVotes += 1;
    }

    /**
     * @return
     */
    public Party getParty() {
	return this._party;
    }
}