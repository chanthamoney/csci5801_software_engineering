import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class OPLV extends VotingSystem {
	private int _numParties;
	private int _numSeats;
	public ArrayList<OPLVBallot> _ballots = new ArrayList<OPLVBallot>();
	public ArrayList<OPLVCandidate> _candidates = new ArrayList<OPLVCandidate>();
	public ArrayList<Party> _parties = new ArrayList<Party>();
	public ArrayList<OPLVCandidate> _seats = new ArrayList<OPLVCandidate>();

	OPLV(int numBallots, int numCandidates, int numSeats, ArrayList<String> candidates, ArrayList<String> parties, ArrayList<Integer> ballots) {
		super(numBallots, numCandidates);
		this._numParties = 0;
		this._numSeats = numSeats;
		for (int i = 0; i < numCandidates; i++) {
			Party canParty = findParty(parties.get(i));
			if (canParty == null) {
				canParty = new Party(parties.get(i));
				this._parties.add(canParty);
				this._numParties++;	
			}
			OPLVCandidate newCan = new OPLVCandidate(candidates.get(i), canParty);
			this._candidates.add(newCan);
			canParty.addCandidate(newCan);
		}
		for (int i = 0; i < numBallots; i++) {
			this._ballots.add(new OPLVBallot(ballots.get(i), i + 1));
		}
		calculateQuota(numBallots, numSeats);
	}

	private void calculateQuota(int numBallots, int numSeats) {
		this._quota = -Math.floorDiv(-numBallots, numSeats);
	}

	private void rankPartyCandidates() {
		String rankings = "Party Rankings [* - Allocated Party Seat]\n";
		for (int i = 0; i < this._numParties; i++) {
			rankings += this._parties.get(i).rankCandidates();
		}
		this._auditor.rankOPLV(rankings);
	}

	private void calculatePartySeats() {
		int seatsLeft = this._numSeats;
		int[] remainders = new int[this._numParties];
		for (int i = 0; i < this._numParties; i++) {
			Party curParty = this._parties.get(i);
			int curPartySeats = Math.floorDiv(curParty.getNumVotes(), this._quota);
			curPartySeats = curPartySeats > curParty.getNumCandidates() ? curParty.getNumCandidates() : curPartySeats;
			curParty.setNumSeats(curPartySeats);
			seatsLeft -= curPartySeats;
			remainders[i] = curParty.getNumVotes() % this._quota;
		}
		while (seatsLeft > 0) {
			int maxVal = -1;
			ArrayList<Integer> largest = new ArrayList<Integer>();
			for (int i = 0; i < this._numParties; i++) {
				// If party has not exhausted all candidates in filling seats
				if (this._parties.get(i).getNumCandidates() > this._parties.get(i).getNumSeats()) {
					if (remainders[i] > maxVal) {
						maxVal = remainders[i];
						largest.clear();
						largest.add(i);
					} else if (remainders[i] == maxVal) {
						largest.add(i);
					}
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
				ArrayList<String> randomParties = new ArrayList<String>();
				for (int i = 0; i < largest.size(); i++) {
					Party curParty = this._parties.get(largest.get(i));
					randomParties.add(curParty.getName());
				}
				Collections.shuffle(largest, new Random(System.currentTimeMillis()));
				for (int i = 0; i < seatsLeft; i++) {
					Party curParty = this._parties.get(largest.get(i));
					curParty.setNumSeats(curParty.getNumSeats() + 1);
					remainders[largest.get(i)] = -1;
					seatsLeft--;
				}
				this._auditor.randomLargestRemainderTie(randomParties);
			}
		}
	}

	private void assignSeats() {
		for (int i = 0; i < this._numParties; i++) {
			this._seats.addAll(this._parties.get(i).getWinningCandidates());
		}
	}

	private OPLVCandidate findCandidate(int index) {
		return this._candidates.get(index);
	}
	
	private Party findParty(String name) {
		for (int i = 0; i < this._numParties; i++) {
			if (this._parties.get(i).getName().equals(name)) {
				return this._parties.get(i);
			}
		}
		return null;
	}

	public String runElection() throws IOException {
		for (int i = 0; i < this._numBallots; i++) {
			OPLVBallot bal = this._ballots.get(i);
			OPLVCandidate can = findCandidate(bal.getVote());
			can.castVote();
			this._auditor.ballot(bal.getID(), can.getName(), can.getParty().getName());
		}
		calculatePartySeats();
		rankPartyCandidates();
		assignSeats();
		
		String res = "Election Winners:\n";
		for (int i = 0; i < this._numSeats; i++) {
			OPLVCandidate curCan = this._seats.get(i);
			res += "\t"+ curCan.getName() + " (" + curCan.getParty().getName() + ")\n";
		}
		this._auditor.result(res);
		this._auditor.createAuditFile("auditFile");
		return "TODO";
	}
}