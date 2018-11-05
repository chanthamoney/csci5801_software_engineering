abstract public class Candidate {
	/**
	 * 
	 */
	protected String _name = "";
	/**
	 * 
	 */
	protected int _numVotes = 0;

	/**
	 * @param name
	 */
	Candidate(String name) {
		this._name = name;
	}

	/**
	 * @return
	 */
	public String getName() {
		return this._name;
	}

	/**
	 * @return
	 */
	public int getNumVotes() {
		return this._numVotes;
	}
}