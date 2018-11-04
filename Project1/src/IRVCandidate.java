import java.util.ArrayList;

public class IRVCandidate extends Candidate {
	public ArrayList<IRVBallot> _elimBallots = new ArrayList<IRVBallot>();
	private boolean _eliminated = false;

	IRVCandidate(String name) {
		super(name);
	}

	public void addBallot(IRVBallot ballot) {
		this._elimBallots.add(ballot);
		this._numVotes += 1;
	}

	public ArrayList<IRVBallot> eliminate() {
		this._eliminated = true;
		return this._elimBallots;
	}

	public boolean isEliminated() {
		return this._eliminated;
	}
}
