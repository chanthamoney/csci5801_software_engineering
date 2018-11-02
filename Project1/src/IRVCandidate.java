import java.util.ArrayList;

public class IRVCandidate extends Candidate {
	private boolean _eliminated = false;
	public ArrayList<IRVBallot> _elimBallots = new ArrayList<IRVBallot>();

	public void addBallot(IRVBallot ballot) {
		throw new UnsupportedOperationException();
	}

	private IRVBallot[] eliminate() {
		throw new UnsupportedOperationException();
	}

	private boolean isEliminated() {
		return this._eliminated;
	}
}