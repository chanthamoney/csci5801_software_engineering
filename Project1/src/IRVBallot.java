public class IRVBallot extends Ballot {
	private int[] _votes;
	private Object _curVoteIndex = 0;
	private int _numVotes;

	public int getNextVote() {
		throw new UnsupportedOperationException();
	}

	public boolean isExhausted() {
		throw new UnsupportedOperationException();
	}
}