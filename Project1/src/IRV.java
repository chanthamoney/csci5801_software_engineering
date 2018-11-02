import java.util.ArrayList;

public class IRV implements VotingSystem {
	private IRVBallot[] _voterPool;
	public ArrayList<IRVBallot> _ballots = new ArrayList<IRVBallot>();
	public ArrayList<IRVCandidate> _unnamed_IRVCandidate_ = new ArrayList<IRVCandidate>();

	private void calculateQuota(int aNumBallots) {
		throw new UnsupportedOperationException();
	}

	private IRVCandidate findCandidate(int aIndex) {
		throw new UnsupportedOperationException();
	}

	private IRVCandidate findMinimumCandidate() {
		throw new UnsupportedOperationException();
	}

	private void setVoterPool(IRVBallot[] aVoterPool) {
		this._voterPool = aVoterPool;
	}

	private String processVoterPool() {
		throw new UnsupportedOperationException();
	}

	private String popularVote__() {
		throw new UnsupportedOperationException();
	}

	private boolean isMajority(int aNumVotes) {
		throw new UnsupportedOperationException();
	}

	public String runElection() {
		throw new UnsupportedOperationException();
	}

	public void auditResults(String aResults) {
		throw new UnsupportedOperationException();
	}
}