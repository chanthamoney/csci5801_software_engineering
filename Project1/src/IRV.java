import java.util.ArrayList;

public class IRV extends VotingSystem {
	private IRVBallot[] _voterPool;
	private ArrayList<IRVBallot> _ballots = new ArrayList<IRVBallot>();
	private ArrayList<IRVCandidate> _candidates = new ArrayList<IRVCandidate>();
	
	IRV(String ballotFile) {
		super(ballotFile);
		throw new UnsupportedOperationException();

	}

	private void calculateQuota(int numBallots) {
		throw new UnsupportedOperationException();
	}

	private IRVCandidate findCandidate(int index) {
		throw new UnsupportedOperationException();
	}

	private IRVCandidate findMinimumCandidate() {
		throw new UnsupportedOperationException();
	}

	private void setVoterPool(IRVBallot[] voterPool) {
		this._voterPool = voterPool;
	}

	private String processVoterPool() {
		throw new UnsupportedOperationException();
	}

	private String popularVote__() {
		throw new UnsupportedOperationException();
	}

	private boolean isMajority(int numVotes) {
		throw new UnsupportedOperationException();
	}

	public String runElection() {
		throw new UnsupportedOperationException();
	}

	protected void auditResults(String results) {
		throw new UnsupportedOperationException();
	}
}