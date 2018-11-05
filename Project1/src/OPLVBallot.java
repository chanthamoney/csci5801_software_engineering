public class OPLVBallot extends Ballot {
	private final int _vote;

	OPLVBallot(int vote, int id) {
		super(id);
		this._vote = vote;
	}

	public int getVote() {
		return this._vote;
	}
}