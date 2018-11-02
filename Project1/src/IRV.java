import java.util.ArrayList;

public class IRV extends VotingSystem {
	private IRVBallot[] _voterPool;
	public ArrayList<IRVBallot> _ballots = new ArrayList<IRVBallot>();
	public ArrayList<IRVCandidate> _unnamed_IRVCandidate_ = new ArrayList<IRVCandidate>();

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

	public void auditResults(String results) {
		throw new UnsupportedOperationException();
	}
}