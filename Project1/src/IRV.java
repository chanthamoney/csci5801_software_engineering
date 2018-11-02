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
		if ((numBallots % 2) == 0)
			this._quota = (numBallots / 2) + 1;
		else
			this._quota = (int) Math.ceil(numBallots * 0.5);
			
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

	private String popularVote() {
		throw new UnsupportedOperationException();
	}

	private boolean isMajority(int numVotes) {
		throw new UnsupportedOperationException();
	}

	public String runElection() {
		throw new UnsupportedOperationException();
	}

	protected String auditResults() {
		throw new UnsupportedOperationException();
		// Collect results and send to auditor
		// NOTE: ONLY RESULTS (WINNERS & # VOTES), NOT PROCESS!
//		String res = "";
//		return res;
	}
}