import java.util.ArrayList;

public class IRVCandidate extends Candidate {
	private boolean _eliminated = false;
	public ArrayList<IRVBallot> _elimBallots = new ArrayList<IRVBallot>();

	IRVCandidate(String name) {
		super(name);
	}
	
	public void addBallot(IRVBallot ballot) {
		if (!ballot.isExhausted()) {
			this._elimBallots.add(ballot);
		}
		this._numVotes += 1;
	}

	private ArrayList<IRVBallot> eliminate() {
		this._eliminated = true;
		return this._elimBallots;
	}

	private boolean isEliminated() {
		return this._eliminated;
	}
}