import java.util.ArrayList;

public class IRVCandidate extends Candidate {
	/**
	 *
	 */
	public ArrayList<IRVBallot> _elimBallots = new ArrayList<IRVBallot>();
	/**
	 *
	 */
	private boolean _eliminated = false;

	/**
	 * @param name
	 */
	IRVCandidate(String name) {
		super(name);
	}

	/**
	 * @param ballot
	 */
	public void addBallot(IRVBallot ballot) {
		this._elimBallots.add(ballot);
		this._numVotes += 1;
	}

	/**
	 * @return
	 */
	public IRVBallot[] eliminate() {
		this._eliminated = true;
		final IRVBallot[] elimBallots = new IRVBallot[this._elimBallots.size()];
		return this._elimBallots.toArray(elimBallots);
	}

	/**
	 * @return
	 */
	public boolean isEliminated() {
		return this._eliminated;
	}
}
