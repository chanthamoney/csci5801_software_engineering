import java.util.ArrayList;
import java.util.Random;

public class IRV extends VotingSystem {
	private ArrayList<IRVBallot> _voterPool;
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
		return this._candidates.get(index);
	}

	private IRVCandidate findMinimumCandidate() {
		// NEEDS TO RETURN STRING AS WELL INDICATING IF IT WAS RANDOM
		// OR AT LEAST COMMUNICATE TO AUDITOR
		ArrayList<IRVCandidate> minCandidates = new ArrayList<IRVCandidate>();
		int minimum = findCandidate(0).getNumVotes();
		for (int i = 0; i < this._numCandidates; i++) {
			IRVCandidate can = findCandidate(i);
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
		}  else {
			Random randomizer = new Random();
			IRVCandidate rcan = minCandidates.get(randomizer.nextInt(minCandidates.size()));
			this._auditor.eliminateCandidateIRV(rcan.getName(), rcan.getNumVotes(), true);
			return rcan;
		}
	}

	private void setVoterPool(ArrayList<IRVBallot> voterPool) {
		this._voterPool = voterPool;
	}

	private String processVoterPool() {
		for (int i = 0; i < this._voterPool.size(); i++) {
			IRVBallot bal = this._voterPool.get(i);
			IRVCandidate can = findCandidate(bal.getNextVote());
			can.addBallot(bal);
			this._auditor.ballot(bal.getID(), can.getName());
			if (isMajority(can.getNumVotes())) {
				return can.getName();
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
			return "Tie in popular vote: winner was chosen at random.\n" + winners.get(randomizer.nextInt(winners.size()));
		}
	}

	private boolean isMajority(int numVotes) {
		return numVotes >= this._quota;
	}

	public String runElection() {
		while (true) {
			ArrayList<Integer> ids = new ArrayList<Integer>();
			for (int i = 0; i < this._voterPool.size(); i++) {
				ids.add(_voterPool.get(i).getID());
			}
			this._auditor.processVoterPool(ids);
			
			boolean allBallotsExhausted = true;
			for (int i = 0; i < this._numBallots; i++) {
				if (!this._ballots.get(i).isExhausted()) {
					allBallotsExhausted = false;
					break;
				}
			}
			
			if (allBallotsExhausted) {
				return popularVote();
			}
			
			String winner = processVoterPool();
			
			if (winner != "") {
				return winner;
			} else {
				IRVCandidate can = findMinimumCandidate();
				this._voterPool = can.eliminate();
			}
		}
	}

	protected String auditResults() {
		throw new UnsupportedOperationException();
		// Collect results and send to auditor
		// NOTE: ONLY RESULTS (WINNERS & # VOTES), NOT PROCESS!
//		String res = "";
//		return res;
	}
}