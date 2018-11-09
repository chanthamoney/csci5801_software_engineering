/**
 * Represents an abstract ballot.
 * 
 * @author Team14 [Cassandra Chanthamontry (chant077), Jake Nippert (nippe014),
 *         Meghann Silagan (silag001), Christine Tsai (tsaix223)]
 */
abstract public class Ballot {

    /** The unique identifier of a ballot. */
    private final int _id;

    /** Throws an error for default constructor. */
    Ballot() {
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

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