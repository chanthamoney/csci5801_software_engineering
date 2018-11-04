import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Party {
	private String _name;
	private int _numCandidates = 0;
	private int _numSeats = 0;
	private int _numVotes = 0;
	public ArrayList<OPLVCandidate> _candidates = new ArrayList<OPLVCandidate>();

	Party(String name) {
		this._name = name;
	}

	public void addCandidate(OPLVCandidate candidate) {
		this._candidates.add(candidate);
		this._numCandidates += 1;
	}

	public void addVote() {
		this._numVotes += 1;
	}

	public String getName() {
		return this._name;
	}
	
	public ArrayList<OPLVCandidate> getCandidates() {
		return this._candidates;
	}

	public int getNumVotes() {
		return this._numVotes;
	}
	
	public int getNumCandidates() {
		return this._numCandidates;
	}

	public void setNumSeats(int num) {
		this._numSeats = num;
	}

	public int getNumSeats() {
		return this._numSeats;
	}

	public String rankCandidates() {
		String ret = String.format("\tParty %s:\n", this._name);
		
		// Shuffle to dispute ties.
		Collections.shuffle(this._candidates, new Random(System.currentTimeMillis()));
		
		this._candidates.sort((o1, o2) -> Integer.compare(o2.getNumVotes(), o1.getNumVotes()));
		
		int minVotes;
		if (this._numSeats <= this._numCandidates && this._numSeats > 0) {
			minVotes = this._candidates.get(this._numSeats - 1).getNumVotes();
		} else {
			minVotes = Integer.MAX_VALUE;
		}
		int firstIndx = -1, lastIndx = -1;
		for (int i = 0; i < this._numCandidates; i++) {
			OPLVCandidate can = this._candidates.get(i);
			if (i < this._numSeats) {
				ret += "\t\t* ";
			} else {
				ret += "\t\t- ";
			}
			OPLVCandidate curCan = this._candidates.get(i);
			ret += String.format("%s [%d vote", curCan.getName(), curCan.getNumVotes());
			if (curCan.getNumVotes() == 1) {
				ret += "]\n";
			} else {
				ret += "s]\n";
			}
			int numVotes = can.getNumVotes();
			if (firstIndx == -1 && numVotes == minVotes) {
				firstIndx = i;
			}
			if (numVotes == minVotes) {
				lastIndx = i;
			}
		}
			
		// If more than one minimum relay that there was a shuffle decision.
		if (firstIndx != lastIndx && lastIndx >= this._numSeats) {
			ret += String.format("\t\tNOTE: Randomly ranked candidates %d to %d due to a consequential tie in Party seat allocations.\n", firstIndx + 1, lastIndx + 1);
		}
		return ret;
	}

	public ArrayList<OPLVCandidate> getWinningCandidates() {
		ArrayList<OPLVCandidate> winners = new ArrayList<OPLVCandidate>();
		for (int i = 0; i < this._numSeats && i < this._numCandidates; i++) {
			winners.add(this._candidates.get(i));
		}
		return winners;
	}
}