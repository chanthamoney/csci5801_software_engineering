public class OPLVBallot extends Ballot {
	/**
	 * 
	 */
	private final int _vote;

	/**
	 * @param vote
	 * @param id
	 */
	OPLVBallot(int vote, int id) {
		super(id);
		this._vote = vote;
	}

	/**
	 * @return
	 */
	public int getVote() {
		return this._vote;
	}
}