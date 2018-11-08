/**
 * Represents an abstract candidate in an election.
 */
abstract public class Candidate {

    /**
     * The name of the candidate.
     */
    protected String _name = "";

    /**
     * The number of votes currently tabulated for the candidate.
     */
    protected int _numVotes = 0;

    /**
     * Throws an error for default constructor.
     */
    Candidate() {
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * Instantiates a new candidate.
     *
     * @param name the name of the candidate
     */
    Candidate(String name) {
	this._name = name;
    }

    /**
     * Gets the name of the candidate.
     *
     * @return the name of the candidate
     */
    public String getName() {
	return this._name;
    }

    /**
     * Gets the number of votes currently tabulated for the candidate.
     *
     * @return the number of votes
     */
    public int getNumVotes() {
	return this._numVotes;
    }
}