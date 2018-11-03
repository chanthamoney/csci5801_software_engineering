public class IRVBallot extends Ballot {
	private int[] _votes;
	private int _curVoteIndex = 0;
	private int _numVotes;

	IRVBallot(int[] votes, int id) {
		super(id);
		this._votes = votes;
		this._numVotes = votes.length;
	}

	public int getNextVote() {
		this._curVoteIndex += 1;
		return this._votes[this._curVoteIndex - 1];
	}

	public boolean isExhausted() {
		return this._numVotes <= this._curVoteIndex;
	}
}