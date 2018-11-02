import java.util.ArrayList;

public class Auditor {
	private String _auditProcess = "";
	private String _auditResult = "";

	Auditor() {
		throw new UnsupportedOperationException();
	}

	public void ballot(int id, String candidate, String party) {
		throw new UnsupportedOperationException();
	}

	public void ballot(int id, String candidate) {
		throw new UnsupportedOperationException();
	}

	public void eliminateCandidateIRV(String candidate, int numVotes, boolean wasRandom) {
		throw new UnsupportedOperationException();
	}

	public void processVoterPool(ArrayList<Integer> ballotIDs) {
		throw new UnsupportedOperationException();
	}

	public void rankOPLV(String rankMsg) {
		throw new UnsupportedOperationException();
	}

	public void result(String results) {
		throw new UnsupportedOperationException();
	}

	public void createAuditFile(String name) {
		throw new UnsupportedOperationException();
	}
}