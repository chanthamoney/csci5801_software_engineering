abstract public class VotingSystem {
	private Auditor _auditor;
	private int _numBallots;
	private int numCandidates;
	private int _quota;
	
	VotingSystem(String ballotFile) {
		throw new UnsupportedOperationException();
	}

	abstract protected String runElection();

	abstract protected void auditResults(String results);
}