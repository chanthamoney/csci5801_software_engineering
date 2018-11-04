import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Auditor {
	private String _auditProcess = "";
	private String _auditResult = "";
	private String _auditSetup = "";

	public void ballot(int id, String candidate) {
		this._auditProcess += String.format("Ballot %d cast a vote for %s\n", id, candidate);
	}

	public void ballot(int id, String candidate, String party) {
		this._auditProcess += String.format("Ballot %d cast a vote for %s in party %s\n", id, candidate, party);
	}

	public void ballotExhausted(int id) {
		this._auditProcess += String.format("Ballot %d has exhausted all of its votes.\n", id);
	}

	public void createAuditFile(String name) throws IOException {
		final File file = new File(name);
		final FileWriter writer = new FileWriter(file);
		writer.write(this._auditSetup + "\n\n- - - - - - - - - - - - - - - - - - - -\n\n" + this._auditProcess
				+ "\n\n- - - - - - - - - - - - - - - - - - - -\n\n" + this._auditResult);
		writer.close();
	}

	public void curPartyVotes(String curPartyVotes) {
		this._auditProcess += curPartyVotes;
	}

	public void eliminateCandidateIRV(String candidate, int numVotes, boolean wasRandom) {
		this._auditProcess += String.format("Candidate %s is eliminated with only %d votes.\n", candidate, numVotes);

		if (wasRandom)
			this._auditProcess += "NOTE: This elimination was the result of a random toss due to a consequential tie in least amount of votes.\n";
	}

	public void majorityIRV(String name, int quota) {
		this._auditProcess += String.format("\nProcessing Complete!\nCandidate %s has a majority of votes (>= %d).\n",
				name, quota);
	}

	public void noVoteCandidateElimination(ArrayList<String> candidates) {
		this._auditProcess += "Eliminated the following candidates who received no votes:\n";
		for (int i = 0; i < candidates.size(); i++)
			this._auditProcess += String.format("\t%s\n", candidates.get(i));
	}

	public void oneCandidateRemaining() {
		this._auditProcess += String.format("\nProcessing Complete!\nOnly one candidate has not been eliminated.\n");
	}

	public void processVoterPool(ArrayList<Integer> ballotIDs) {
		if (ballotIDs.size() > 0) {
			this._auditProcess += "Processing the following ballots:\n";
			final int numBallots = ballotIDs.size();
			this._auditProcess += "\t";
			for (int i = 0; i < (numBallots - 1); i++)
				this._auditProcess += String.format("%d, ", ballotIDs.get(i));
			this._auditProcess += String.format("%d\n", ballotIDs.get(numBallots - 1));
		}
	}

	public void rankOPLV(String rankMsg) {
		this._auditProcess += rankMsg;
	}

	public void result(String results) {
		this._auditResult += results;
	}

	public void seatAllocations(String seatAllocations) {
		this._auditProcess += seatAllocations;
	}

	public void setup(String setup) {
		this._auditSetup += setup;
	}
}