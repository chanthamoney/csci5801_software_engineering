public class OPLVCandidate extends Candidate {
	private Party _party;

	OPLVCandidate(String name, Party party) {
		super(name);
		this._party = party;
	}
	
	public Party getParty() {
		return this._party;
	}

	public void castVote() {
		this._numVotes += 1;
	}
}