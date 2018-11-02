abstract public class Candidate {
	protected String _name = "";
	protected int _numVotes = 0;
	
	Candidate(String name) {
		this._name = name;
	}

	public String getName() {
		return this._name;
	}

	public int getNumVotes() {
		return this._numVotes;
	}
}