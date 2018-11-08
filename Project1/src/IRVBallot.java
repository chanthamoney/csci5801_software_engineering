import java.util.ArrayList;

public class IRVBallot extends Ballot {
    /**
     *
     */
    private int _curVoteIndex = 0;
    /**
     *
     */
    private final int _numVotes;
    /**
     *
     */
    private final ArrayList<Integer> _votes;

    /**
     * Throws an error for default constructor.
     */
    IRVBallot() {
	super();
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * @param votes
     * @param id
     */
    IRVBallot(ArrayList<Integer> votes, int id) {
	super(id);
	this._votes = votes;
	this._numVotes = votes.size();
    }

    /**
     * @return
     */
    public int getNextVote() {
	this._curVoteIndex += 1;
	return this._votes.get(this._curVoteIndex - 1);
    }

    /**
     * @return
     */
    public boolean isExhausted() {
	return this._numVotes <= this._curVoteIndex;
    }
}