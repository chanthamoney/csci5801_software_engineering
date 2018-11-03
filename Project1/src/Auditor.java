import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Auditor {
	private String _auditProcess = "";
	private String _auditResult = "";

	public void ballot(int id, String candidate, String party) {
		this._auditProcess += String.format("Ballot %d cast a vote for %s in party %s\n", id, candidate, party);
	}
	
	public void ballotExhausted(int id) {
		this._auditProcess += String.format("Ballot %d has exhausted all of its votes.\n", id);
	}
	
	public void ballot(int id, String candidate) {
		this._auditProcess += String.format("Ballot %d cast a vote for %s\n", id, candidate);
	}

	public void eliminateCandidateIRV(String candidate, int numVotes, boolean wasRandom) {
		if (wasRandom) {
			this._auditProcess += String.format("Candidate %s is eliminated from a random toss with only %d votes.\n", candidate, numVotes);
		} else {
			this._auditProcess += String.format("Candidate %s is eliminated with only %d votes.\n", candidate, numVotes);
		}
	}

	public void processVoterPool(ArrayList<Integer> ballotIDs) {
		if (ballotIDs.size() > 0) {
			this._auditProcess += "Processing the following ballots:\n";
			int numBallots = ballotIDs.size();
			this._auditProcess += "\t";
			for (int i = 0; i < numBallots - 1; i++) {
				this._auditProcess += String.format("%d, ", ballotIDs.get(i));
			}
			this._auditProcess += String.format("%d\n", ballotIDs.get(numBallots - 1));
		}
	}

	public void rankOPLV(String rankMsg) {
		this._auditProcess += rankMsg;
	}

	public void result(String results) {
		this._auditResult += results;
	}
	
	public void majorityIRV(String name, int quota) {
		this._auditProcess += String.format("\nProcessing Complete!\nCandidate %s has a majority of votes (>= %d).\n", name, quota);		
	}
	
	public void oneCandidateRemaining() {
		this._auditProcess += String.format("\nProcessing Complete!\nOnly one candidate has not been eliminated.\n");				
	}

	public void createAuditFile(String name) throws IOException {
		File file = new File(name);
		FileWriter writer = new FileWriter(file);
		writer.write(this._auditProcess + "\n\n- - - - - - - - - - - - - - - - - - - -\n\n" + this._auditResult);
		writer.close();
	}
}