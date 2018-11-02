public class IRVBallot implements Ballot {
	private int[] _votes;
	private Object _curVoteIndex = 0;
	private int _numVotes;
	public IRVCandidate _elimBallots;
	public IRV _ballots;

	public int getNextVote() {
		throw new UnsupportedOperationException();
	}

	public boolean isExhausted() {
		throw new UnsupportedOperationException();
	}

	public int getID() {
		throw new UnsupportedOperationException();
	}
}