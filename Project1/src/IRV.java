import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class IRV extends VotingSystem {
	private final ArrayList<IRVBallot> _ballots = new ArrayList<IRVBallot>();
	private final ArrayList<IRVCandidate> _candidates = new ArrayList<IRVCandidate>();
	private ArrayList<IRVBallot> _voterPool;

	IRV(int numBallots, int numCandidates, String[] candidates, ArrayList<ArrayList<Integer>> ballots) {
		super(numBallots, numCandidates);
		for (int i = 0; i < numCandidates; i++)
			this._candidates.add(new IRVCandidate(candidates[i]));
		for (int i = 0; i < numBallots; i++) {
			for (int j = 0; j < ballots.get(i).size(); j++) {
			}
			this._ballots.add(new IRVBallot(ballots.get(i), i + 1));
		}
		this._voterPool = this._ballots;
		calculateQuota(numBallots);

		String setup = "";
		setup += "Voting System:\t" + "Instant Runoff Voting\n";
		setup += String.format("\nNumber of Candidates: %s\n", this._numCandidates);
		setup += "\nCandidates:\n";
		for (int i = 0; i < this._numCandidates; i++)
			setup += String.format("\t%d - %s\n", i, this._candidates.get(i).getName());
		setup += String.format("\nNumber of Ballots: %s\n", this._numBallots);
		setup += String.format("\nBallots: %s\n", ballots);
		this._auditor.auditSetup(setup);
	}

	private void calculateQuota(int numBallots) {
		if ((numBallots % 2) == 0)
			this._quota = (numBallots / 2) + 1;
		else
			this._quota = (int) Math.ceil(numBallots * 0.5);
	}

	private void eliminateAllNoVoteCandidates() {
		final ArrayList<String> eliminatedCandidates = new ArrayList<String>();
		for (int i = 0; i < this._numCandidates; i++) {
			final IRVCandidate curCan = this._candidates.get(i);
			if (curCan.getNumVotes() == 0) {
				curCan.eliminate();
				eliminatedCandidates.add(curCan.getName());
			}
		}
		if (eliminatedCandidates.size() > 0) {
			String eliminatedCandidateNames = "";
			eliminatedCandidateNames += "Eliminated the following candidates who received no votes:\n";
			for (int i = 0; i < eliminatedCandidates.size(); i++)
				eliminatedCandidateNames += String.format("\t%s\n", eliminatedCandidates.get(i));
			this._auditor.auditProcess(eliminatedCandidateNames);
		}
	}

	private IRVCandidate findMinimumCandidate() {
		final ArrayList<IRVCandidate> minCandidates = new ArrayList<IRVCandidate>();
		int minimum = Integer.MAX_VALUE;
		for (int i = 0; i < this._numCandidates; i++) {
			final IRVCandidate can = this._candidates.get(i);
			if (!can.isEliminated()) {
				final int numVotes = can.getNumVotes();
				if (can.getNumVotes() == minimum)
					minCandidates.add(can);
				else if (numVotes < minimum) {
					minCandidates.clear();
					minCandidates.add(can);
					minimum = numVotes;
				}
			}
		}

		// Determines if random decision is needed.
		if (minCandidates.size() == 1) {
			final IRVCandidate mcan = minCandidates.get(0);
			this._auditor.auditProcess(String.format("Candidate %s is eliminated with only %d votes.\n", mcan.getName(),
					mcan.getNumVotes()));
			return mcan;
		} else {
			final Random randomizer = new Random();
			final IRVCandidate rcan = minCandidates.get(randomizer.nextInt(minCandidates.size()));
			this._auditor.auditProcess(String.format(
					"Candidate %s is eliminated with only %d votes.\nNOTE: This elimination was the result of a random toss due to a consequential tie in least amount of votes.\\n",
					rcan.getName(), rcan.getNumVotes()));
			return rcan;
		}
	}

	private boolean isMajority(int numVotes) {
		return numVotes >= this._quota;
	}

	private String processVoterPool() {
		String processedBallots = "";
		for (int i = 0; i < this._voterPool.size(); i++) {
			final IRVBallot bal = this._voterPool.get(i);
			boolean wasExhausted = true;
			while (!bal.isExhausted()) {
				final IRVCandidate can = this._candidates.get(bal.getNextVote());
				if (!can.isEliminated()) {
					can.addBallot(bal);
					processedBallots += String.format("Ballot %d cast a vote for %s\n", bal.getID(), can.getName());
					if (isMajority(can.getNumVotes())) {
						processedBallots += String.format(
								"\nProcessing Complete!\nCandidate %s has a majority of votes (>= %d).\n",
								can.getName(), this._quota);
						return can.getName();
					}
					wasExhausted = false;
					break;
				}
			}
			if (wasExhausted)
				processedBallots += String.format("Ballot %d has exhausted all of its votes.\n", bal.getID());
		}
		this._auditor.auditProcess(processedBallots);
		return "";
	}

	public void runElection() throws IOException {
		if (!this.wasRun.getAndSet(true)) {
			final boolean firstRun = true;
			while (true) {
				int numCandidatesRemaining = 0;
				String lastCan = "";
				for (int i = 0; i < this._numCandidates; i++) {
					final IRVCandidate curCan = this._candidates.get(i);
					if (!curCan.isEliminated()) {
						numCandidatesRemaining++;
						lastCan = curCan.getName();
					}
				}
				if (numCandidatesRemaining < 2) {
					this._auditor.auditProcess(
							String.format("\nProcessing Complete!\nOnly one candidate has not been eliminated.\n"));
					this._auditor.auditResult("Election Winner: " + lastCan);
					this._auditor.createAuditFile("auditFile");
					System.out.print("Election Winner: " + lastCan);
					break;
				}

				String processVoterPool = "";
				processVoterPool += "Processing the following ballots:\n\t";
				final int numBallots = this._voterPool.size();
				for (int i = 0; i < numBallots; i++)
					processVoterPool += String.format("%d, ", this._voterPool.get(i).getID());
				this._auditor.auditProcess(processVoterPool + "\n");

				final String winner = processVoterPool();

				if (winner != "") {
					this._auditor.auditResult("Election Winner: " + winner);
					this._auditor.createAuditFile("auditFile");
					System.out.print(winner);
					break;
				} else {
					String curPartyVotes = "Remaining Candidate - Votes:\n";
					for (int i = 0; i < this._numCandidates; i++) {
						final IRVCandidate curCan = this._candidates.get(i);
						if (!curCan.isEliminated())
							curPartyVotes += String.format("\t%s - %d\n", curCan.getName(), curCan.getNumVotes());
					}
					this._auditor.auditProcess(curPartyVotes);
					if (firstRun)
						eliminateAllNoVoteCandidates();
					final IRVCandidate can = findMinimumCandidate();
					this._voterPool = can.eliminate();
				}
			}
		} else
			System.out.print("ERROR: An election can only be run once for a given voting system.\n");
	}
}