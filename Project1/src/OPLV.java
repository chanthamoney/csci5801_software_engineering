import java.util.ArrayList;

public class OPLV extends VotingSystem {
	private int _numParties;
	private int _numSeats;
	public ArrayList<OPLVBallot> _ballots = new ArrayList<OPLVBallot>();
	public ArrayList<OPLVCandidate> _candidates = new ArrayList<OPLVCandidate>();
	public ArrayList<Party> _parties = new ArrayList<Party>();

	private int calculateQuota(int numBallots, int numSeats) {
		throw new UnsupportedOperationException();
	}

	private void rankPartyCandidates() {
		throw new UnsupportedOperationException();
	}

	private void calculatePartySeats() {
		throw new UnsupportedOperationException();
	}

	private void assignPartySeats() {
		throw new UnsupportedOperationException();
	}

	private OPLVCandidate findCandidate(int index) {
		throw new UnsupportedOperationException();
	}

	public String runElection() {
		throw new UnsupportedOperationException();
	}

	public void auditResults(String results) {
		throw new UnsupportedOperationException();
	}
}