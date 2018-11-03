abstract public class VotingSystem {
	protected Auditor _auditor;
	protected int _numBallots;
	protected int _numCandidates;
	protected int _quota;

	VotingSystem(int numBallots, int numCandidates) {
		this._numBallots = numBallots;
		this._numCandidates = numCandidates;
	}

	abstract protected String runElection();

	abstract protected String auditResults();
}