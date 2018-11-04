public class OPLVCandidate extends Candidate {
	private final Party _party;

	OPLVCandidate(String name, Party party) {
		super(name);
		this._party = party;
	}

	public void castVote() {
		this._party.addVote();
		this._numVotes += 1;
	}

	public Party getParty() {
		return this._party;
	}
}