import java.util.ArrayList;
import java.util.Collections;

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

	public int getNumVotes() {
		return this._numVotes;
	}

	public void setNumSeats(int num) {
		this._numSeats = num;
	}

	public int getNumSeats() {
		return this._numSeats;
	}

	public String rankCandidates() {
		String ret = String.format("Party %s Sort\n", this._name);
		this._candidates.sort((o1, o2) -> Integer.compare(o2.getNumVotes(), o1.getNumVotes()));
		
		// Randomize candidates with the minimum number of votes to be elected
		if (this._numSeats > 0) {
			int minVotes = this._candidates.get(this._numSeats - 1).getNumVotes();
			int firstIndx = -1, lastIndx = -1;
			for (int i = 0; i < this._numCandidates; i++) {
				OPLVCandidate can = this._candidates.get(i);
				int numVotes = can.getNumVotes();
				ret += String.format("\t%d) %s\n", i + 1, can.getName());
				if (firstIndx == -1 && numVotes == minVotes) {
					firstIndx = i;
				}
				if (numVotes == minVotes) {
					lastIndx = i;
				}
			}
			// If more than one, shuffle all!
			if (firstIndx != lastIndx) {
				Collections.shuffle(this._candidates.subList(firstIndx, lastIndx + 1));
				ret += String.format("Randomly sorted candidates ranked %d to %d.]n", firstIndx + 1, lastIndx + 1);
			}
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