import java.util.LinkedList;

public class IRVBallot extends Ballot {
	/**
	 *
	 */
	private final LinkedList<Integer> _votes;

	/**
	 * @param votes
	 * @param id
	 */
	IRVBallot(LinkedList<Integer> votes, int id) {
		super(id);
		this._votes = votes;
	}

	/**
	 * @return
	 */
	public int getNextVote() {
		return this._votes.pop();
	}

	/**
	 * @return
	 */
	public boolean isExhausted() {
		return this._votes.isEmpty();
	}
}