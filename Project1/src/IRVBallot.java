import java.util.LinkedList;

/**
 * Represents a ballot in an instant runoff election.
 */
public class IRVBallot extends Ballot {

	/** The votes. */
	private final LinkedList<Integer> _votes;

	/**
	 * Instantiates a new IRV ballot.
	 *
	 * @param votes the votes
	 * @param id    the id
	 */
	IRVBallot(LinkedList<Integer> votes, int id) {
		super(id);
		this._votes = votes;
	}

	/**
	 * Gets the next vote.
	 *
	 * @return the next vote
	 */
	public int getNextVote() {
		return this._votes.pop();
	}

	/**
	 * Checks if is exhausted.
	 *
	 * @return true, if is exhausted
	 */
	public boolean isExhausted() {
		return this._votes.isEmpty();
	}
}