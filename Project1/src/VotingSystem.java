abstract public class VotingSystem {
	protected Auditor _auditor;
	protected int _numBallots;
	protected int numCandidates;
	protected int _quota;
	
	VotingSystem(String ballotFile) {
		throw new UnsupportedOperationException();
	}

	abstract protected String runElection();

	abstract protected void auditResults(String results);
}