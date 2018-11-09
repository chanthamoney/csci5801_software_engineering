import java.util.ArrayList;

/**
 * Represents a ballot in an instant runoff election.
 * 
 * @author Team14 [Cassandra Chanthamontry (chant077), Jake Nippert (nippe014),
 *         Meghann Silagan (silag001), Christine Tsai (tsaix223)]
 */
public class IRVBallot extends Ballot {

    /** Maintains the index on the ballot array to retrieve the next vote. */
    private int _curVoteIndex = 0;

    /**
     * Keeps the list of votes (generally the index of candidates in the candidate
     * array for efficiency, or potentially any other unique identifier) on the
     * ballot.
     */
    private final ArrayList<Integer> _votes;

    /** Throws an error for default constructor. */
    IRVBallot() {
	super();
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * Instantiates a new instant runoff ballot.
     *
     * @param votes the votes
     * @param id    the id
     */
    IRVBallot(ArrayList<Integer> votes, int id) {
	super(id);
	this._votes = votes;
    }

    /**
     * Gets the next vote.
     *
     * @return the next vote
     */
    public int getNextVote() {
	this._curVoteIndex += 1;
	return this._votes.get(this._curVoteIndex - 1);
    }

    /**
     * Checks if the ballot has exhausted all of its votes.
     *
     * @return true, if the ballot has exhausted all of its votes
     */
    public boolean isExhausted() {
	return this._votes.size() <= this._curVoteIndex;
    }
}