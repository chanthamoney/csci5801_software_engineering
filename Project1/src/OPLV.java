import java.util.ArrayList;
import java.util.Collections;

public class OPLV extends VotingSystem {
	private int _numParties;
	private int _numSeats;
	public ArrayList<OPLVBallot> _ballots = new ArrayList<OPLVBallot>();
	public ArrayList<OPLVCandidate> _candidates = new ArrayList<OPLVCandidate>();
	public ArrayList<Party> _parties = new ArrayList<Party>();
	public ArrayList<OPLVCandidate> _seats = new ArrayList<OPLVCandidate>();

	OPLV(String ballotFile) {
		super(ballotFile);
		throw new UnsupportedOperationException();
	}
	
	private void calculateQuota(int numBallots, int numSeats) {
		this._quota = -Math.floorDiv(-numBallots, numSeats);
	}

	private void rankPartyCandidates() {
		for (int i = 0; i < this._numParties; i++) {
			this._auditor.rankOPLV(this._parties.get(i).rankCandidates());
		}
	}

	private String calculatePartySeats() {
		String wasRandom = "";
		int seatsLeft = this._numSeats;
		int[] remainders = new int[this._numParties];
		for (int i = 0; i < this._numParties; i++) {
			Party curParty = this._parties.get(i);
			int curPartySeats = Math.floorDiv(curParty.getNumVotes(), this._quota);
			curParty.setNumSeats(curPartySeats);
			seatsLeft -= curPartySeats;
			remainders[i] = curParty.getNumVotes() % this._quota;
		}
		while(seatsLeft > 0) {
			int maxVal = 0;
			ArrayList<Integer> largest = new ArrayList<Integer>();
			for (int i = 0; i < this._numParties; i++) {
				if (remainders[i] > maxVal) {
					maxVal = remainders[i];
					largest.clear();
					largest.add(i);
				} else if (remainders[i] == maxVal) {
					largest.add(i);
				}
			}
			
			// If there are enough seats for all largest: add all.
			if (seatsLeft > largest.size()) {
				for (int i = 0; i < largest.size(); i++) {
					Party curParty = this._parties.get(largest.get(i));
					curParty.setNumSeats(curParty.getNumSeats() + 1);
					remainders[largest.get(i)] = -1;
					seatsLeft--;
				}
			} else {
				// We must randomly decide, return decision was random.
				wasRandom = "There was a random decision in allocating seats based using the largest remainder theorem between the following parties:\n";
				for (int i = 0; i < largest.size(); i++) {
					Party curParty = this._parties.get(largest.get(i));
					wasRandom += curParty.getName() + "\n";
				}
				Collections.shuffle(largest);
				for (int i = 0; i < seatsLeft; i++) {
					Party curParty = this._parties.get(largest.get(i));
					curParty.setNumSeats(curParty.getNumSeats() + 1);
					remainders[largest.get(i)] = -1;
					seatsLeft--;
				}
			}
		}
		return wasRandom;
	}

	private void assignPartySeats() {
		for (int i = 0; i < this._numParties; i++) {
			this._seats.addAll(this._parties.get(i).getWinningCandidates());
		}
	}

	private OPLVCandidate findCandidate(int index) {
		return this._candidates.get(index);
	}

	public String runElection() {
		throw new UnsupportedOperationException();
	}

	protected void auditResults(String results) {
		throw new UnsupportedOperationException();
	}
}