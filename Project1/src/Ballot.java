/**
 * Represents an abstract ballot.
 */
abstract public class Ballot {

	/**
	 * The unique identifier of a ballot.
	 */
	private final int _id;

	/**
	 * Instantiates a new ballot.
	 *
	 * @param id the unique identifier for the ballot.
	 */
	Ballot(int id) {
		this._id = id;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getID() {
		return this._id;
	}
}