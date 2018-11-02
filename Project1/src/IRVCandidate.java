import java.util.ArrayList;

public class IRVCandidate extends Candidate {
	private boolean _eliminated = false;
	public IRV _unnamed_IRV_;
	public ArrayList<IRVBallot> _elimBallots = new ArrayList<IRVBallot>();

	public void addBallot(IRVBallot aBallot) {
		throw new UnsupportedOperationException();
	}

	private IRVBallot[] eliminate() {
		throw new UnsupportedOperationException();
	}

	private boolean isEliminated() {
		return this._eliminated;
	}
}