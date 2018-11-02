public class Party {
	private String _name;
	private int _numCandidates = 0;
	private int _numSeats = 0;
	private int _numVotes = 0;
	public OPLV _parties;

	public void addCandidate(OPLVCandidate aCandidate) {
		throw new UnsupportedOperationException();
	}

	public void addVote() {
		throw new UnsupportedOperationException();
	}

	public String getName() {
		return this._name;
	}

	public int getNumVotes() {
		return this._numVotes;
	}

	public void setNumSeats(int aNum) {
		this._numSeats = aNum;
	}

	public int getNumSeats() {
		return this._numSeats;
	}

	public String rankCandidates() {
		throw new UnsupportedOperationException();
	}

	public OPLVCandidate[] getWinningCandidates() {
		throw new UnsupportedOperationException();
	}
}