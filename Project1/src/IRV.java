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
				System.out.print(ballots.get(i).get(j) + "\t");
			}
			System.out.print("\n");
			this._ballots.add(new IRVBallot(ballots.get(i), i + 1));
		}
		this._voterPool = this._ballots;
		calculateQuota(numBallots);
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
		// NEEDS TO RETURN STRING AS WELL INDICATING IF IT WAS RANDOM
		// OR AT LEAST COMMUNICATE TO AUDITOR
		ArrayList<IRVCandidate> minCandidates = new ArrayList<IRVCandidate>();
		int minimum = Integer.MAX_VALUE;
		for (int i = 0; i < this._numCandidates; i++) {
			IRVCandidate can = this._candidates.get(i);
			if (!can.isEliminated()) {
				int numVotes = can.getNumVotes();
				if (can.getNumVotes() == minimum) {
					minCandidates.add(can);
					System.out.print(String.format("Candidate %s also has %d votes\n", can.getName(), can.getNumVotes()));
				} else if (numVotes < minimum) {
					minCandidates.clear();
					System.out.print(String.format("Candidate %s has %d votes\n", can.getName(), can.getNumVotes()));
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
		} else if (minCandidates.size() > 1) {
			Random randomizer = new Random();
			IRVCandidate rcan = minCandidates.get(randomizer.nextInt(minCandidates.size()));
			this._auditor.eliminateCandidateIRV(rcan.getName(), rcan.getNumVotes(), true);
			return rcan;
		} else {
			return null;
		}
	}

	private String processVoterPool() {
		for (int i = 0; i < this._voterPool.size(); i++) {
			IRVBallot bal = this._voterPool.get(i);
			boolean wasExhausted = true;
			while (!bal.isExhausted()) {
				IRVCandidate can = findCandidate(bal.getNextVote());
				System.out.print(String.format("Candidate %s eliminated?: %b\n", can.getName(), can.isEliminated()));
				if (!can.isEliminated()) {
					can.addBallot(bal);
					this._auditor.ballot(bal.getID(), can.getName());
					if (isMajority(can.getNumVotes())) {
						this._auditor.majorityOPLV(can.getName(), this._quota);
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

	private String popularVote() {
		int maximum = 0;
		ArrayList<String> winners = new ArrayList<String>();
		for (int i = 0; i < this._numCandidates; i++) {
			IRVCandidate can = this._candidates.get(i);
			if (!can.isEliminated()) {
				int numVotes = can.getNumVotes();
				if (numVotes > maximum) {
					maximum = numVotes;
					winners.clear();
					winners.add(can.getName());
				} else if (numVotes == maximum) {
					winners.add(can.getName());
				}
			}
		}
		if (winners.size() == 1) {
			return winners.get(0);
		} else {
			Random randomizer = new Random();
			return "Tie in popular vote: winner was chosen at random.\n"
					+ winners.get(randomizer.nextInt(winners.size()));
		}
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
				this._auditor.result("Election Winner: " + lastCan);
				this._auditor.createAuditFile("auditFile");
				return "TODO";
			}

			boolean allBallotsExhausted = true;
			for (int i = 0; i < this._numBallots; i++) {
				if (!this._ballots.get(i).isExhausted()) {
					allBallotsExhausted = false;
					break;
				}
			}
			if (allBallotsExhausted) {
				this._auditor.result(popularVote());
				this._auditor.createAuditFile("auditFile");
				return "TODO";
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
				IRVCandidate can = findMinimumCandidate();
				if (can != null) {
					this._voterPool = can.eliminate();
				} else {
					this._voterPool.clear();
				}
			}
		}
	}
}