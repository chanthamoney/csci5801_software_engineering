import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class IRV extends VotingSystem {
	private ArrayList<IRVBallot> _voterPool;
	private ArrayList<IRVBallot> _ballots = new ArrayList<IRVBallot>();
	private ArrayList<IRVCandidate> _candidates = new ArrayList<IRVCandidate>();

	IRV(int numBallots, int numCandidates, String[] candidates, ArrayList<ArrayList<Integer>> ballots) {
		super(numBallots, numCandidates);
		for (int i = 0; i < numCandidates; i++) {
			this._candidates.add(new IRVCandidate(candidates[i]));
		}
		for (int i = 0; i < numBallots; i++) {
			for (int j = 0; j < ballots.get(i).size(); j++) {
			}
			this._ballots.add(new IRVBallot(ballots.get(i), i + 1));
		}
		this._voterPool = this._ballots;
		calculateQuota(numBallots);
		
		String setup = "";
		setup += "Voting System:\t" + "Instant Runoff Voting\n";
		setup += String.format("Number of Candidates: %s\n", this._numCandidates);
		setup += "Candidates:\n";
		for (int i = 0; i < this._numCandidates; i++) {
			setup += String.format("\t%d - %s\n", i, this._candidates.get(i).getName());
		}
		setup += String.format("Number of Ballots: %s\n", this._numBallots);
		setup += String.format("Ballots: %s\n", ballots);
		this._auditor.setup(setup);
	}

	private void calculateQuota(int numBallots) {
		if ((numBallots % 2) == 0)
			this._quota = (numBallots / 2) + 1;
		else
			this._quota = (int) Math.ceil(numBallots * 0.5);
	}

	private IRVCandidate findCandidate(int index) {
		return this._candidates.get(index);
	}

	private IRVCandidate findMinimumCandidate() {
		ArrayList<IRVCandidate> minCandidates = new ArrayList<IRVCandidate>();
		int minimum = Integer.MAX_VALUE;
		for (int i = 0; i < this._numCandidates; i++) {
			IRVCandidate can = this._candidates.get(i);
			if (!can.isEliminated()) {
				int numVotes = can.getNumVotes();
				if (can.getNumVotes() == minimum) {
					minCandidates.add(can);
				} else if (numVotes < minimum) {
					minCandidates.clear();
					minCandidates.add(can);
					minimum = numVotes;
				}
			}
		}

		// Determines if random decision is needed.
		if (minCandidates.size() == 1) {
			IRVCandidate mcan = minCandidates.get(0);
			this._auditor.eliminateCandidateIRV(mcan.getName(), mcan.getNumVotes(), false);
			return mcan;
		} else {
			Random randomizer = new Random();
			IRVCandidate rcan = minCandidates.get(randomizer.nextInt(minCandidates.size()));
			this._auditor.eliminateCandidateIRV(rcan.getName(), rcan.getNumVotes(), true);
			return rcan;
		}
	}

	private String processVoterPool() {
		for (int i = 0; i < this._voterPool.size(); i++) {
			IRVBallot bal = this._voterPool.get(i);
			boolean wasExhausted = true;
			while (!bal.isExhausted()) {
				IRVCandidate can = findCandidate(bal.getNextVote());
				if (!can.isEliminated()) {
					can.addBallot(bal);
					this._auditor.ballot(bal.getID(), can.getName());
					if (isMajority(can.getNumVotes())) {
						this._auditor.majorityIRV(can.getName(), this._quota);
						return can.getName();
					}
					wasExhausted = false;
					break;
				}
			}
			if (wasExhausted) {
				this._auditor.ballotExhausted(bal.getID());
			}
		}
		return "";
	}

	private boolean isMajority(int numVotes) {
		return numVotes >= this._quota;
	}

	public String runElection() throws IOException {
		while (true) {
			int numCandidatesRemaining = 0;
			String lastCan = "";
			for (int i = 0; i < this._numCandidates; i++) {
				IRVCandidate curCan = this._candidates.get(i);
				if (!curCan.isEliminated()) {
					numCandidatesRemaining++;
					lastCan = curCan.getName();
				}
			}
			if (numCandidatesRemaining < 2) {
				this._auditor.oneCandidateRemaining();
				this._auditor.result("Election Winner: " + lastCan);
				this._auditor.createAuditFile("auditFile");
				return "Election Winner: " + lastCan;
			}

			ArrayList<Integer> ids = new ArrayList<Integer>();
			for (int i = 0; i < this._voterPool.size(); i++) {
				ids.add(_voterPool.get(i).getID());
			}
			this._auditor.processVoterPool(ids);
			
			String winner = processVoterPool();

			if (winner != "") {
				this._auditor.result("Election Winner: " + winner);
				this._auditor.createAuditFile("auditFile");
				return winner;
			} else {
				String curPartyVotes = "Remaining Candidate - Votes:\n";
				for (int i = 0; i < this._numCandidates; i++) {
					IRVCandidate curCan = this._candidates.get(i);
					if (!curCan.isEliminated()) {
						curPartyVotes += String.format("\t%s - %d\n", curCan.getName(), curCan.getNumVotes());
					}
				}
				this._auditor.curPartyVotes(curPartyVotes);
				IRVCandidate can = findMinimumCandidate();
				this._voterPool = can.eliminate();
			}
		}
	}
}