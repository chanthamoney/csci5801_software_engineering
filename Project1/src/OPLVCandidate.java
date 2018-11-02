public class OPLVCandidate implements Candidate {
	public OPLV _candidates;
	public OPLV _ballots;

	public Party getParty() {
		throw new UnsupportedOperationException();
	}

	public void castVote() {
		throw new UnsupportedOperationException();
	}

	public String getName() {
		return this._name;
	}

	public int getNumVotes() {
		return this._numVotes;
	}
}