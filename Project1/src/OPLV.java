import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class OPLV extends VotingSystem {
	public ArrayList<OPLVBallot> _ballots = new ArrayList<OPLVBallot>();
	public ArrayList<OPLVCandidate> _candidates = new ArrayList<OPLVCandidate>();
	private int _numParties;
	private final int _numSeats;
	public ArrayList<Party> _parties = new ArrayList<Party>();
	public ArrayList<OPLVCandidate> _seats = new ArrayList<OPLVCandidate>();

	OPLV(int numBallots, int numCandidates, int numSeats, ArrayList<String> candidates, ArrayList<String> parties,
			ArrayList<Integer> ballots) {
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
			final OPLVCandidate newCan = new OPLVCandidate(candidates.get(i), canParty);
			this._candidates.add(newCan);
			canParty.addCandidate(newCan);
		}
		for (int i = 0; i < numBallots; i++)
			this._ballots.add(new OPLVBallot(ballots.get(i), i + 1));
		calculateQuota(numBallots, numSeats);

		String setup = "";
		setup += "Voting System:\t" + "Open Party List Voting\n";
		setup += "\nParties:\n";
		for (int i = 0; i < this._numParties; i++) {
			final Party curPar = this._parties.get(i);
			setup += String.format("\n%s\n\tCandidates:\n", curPar.getName());
			for (int j = 0; j < curPar.getNumCandidates(); j++) {
				final ArrayList<OPLVCandidate> cans = curPar.getCandidates();
				setup += String.format("\t\t- %s\n", cans.get(j).getName());
			}
		}
		setup += String.format("\nTotal Number of Candidates: %s\n", this._numCandidates);
		setup += String.format("\nNumber of Ballots: %s\n", this._numBallots);
		setup += String.format("\nBallots: %s\n", ballots);
		setup += String.format("\nBallot Candidates Key:\n", ballots);
		for (int i = 0; i < this._numCandidates; i++)
			setup += String.format("\t%d - %s\n", i, this._candidates.get(i).getName());
		setup += String.format("\nNumber of Seats: %s\n", this._numSeats);
		this._auditor.setup(setup);
	}

	private void assignSeats() {
		for (int i = 0; i < this._numParties; i++)
			this._seats.addAll(this._parties.get(i).getWinningCandidates());
	}

	private void calculatePartySeats() {
		String seatAllocations = "\nSeat Allocation Calculation:\n";
		int seatsLeft = this._numSeats;
		seatAllocations += String.format("%d Seats Remaining\n", seatsLeft);
		seatAllocations += String.format(
				"Allocating Initial Seats [Floor(Number of Votes / Quota {%d}) with Max as Number of Candidates]:\n",
				this._quota);

		for (int i = 0; i < this._numParties; i++) {
			final Party curParty = this._parties.get(i);
			int curPartySeats = Math.floorDiv(curParty.getNumVotes(), this._quota);
			curPartySeats = curPartySeats > curParty.getNumCandidates() ? curParty.getNumCandidates() : curPartySeats;
			curParty.setNumSeats(curPartySeats);
			seatsLeft -= curPartySeats;
			seatAllocations += String.format(
					"\t[Party: %s, Votes: %d, Number of Candidates: %d, Initial Seats: %d, Remainder: %d]\n",
					curParty.getName(), curParty.getNumVotes(), curParty.getNumCandidates(), curPartySeats,
					curParty.getNumVotes() % this._quota);
		}
		while (seatsLeft > 0) {
			seatAllocations += String.format("%d Seats Remaining\n", seatsLeft);
			final ArrayList<Party> rankedRemainders = new ArrayList<Party>();
			// Retrieve Parties that do not have all seats filled
			for (int i = 0; i < this._numParties; i++) {
				final Party curParty = this._parties.get(i);
				// If party has not exhausted all candidates in filling seats
				if (curParty.getNumCandidates() > curParty.getNumSeats())
					rankedRemainders.add(curParty);
			}

			final Random random = new Random(System.currentTimeMillis());
			rankedRemainders.sort(
					(o1, o2) -> Integer.compare(o2.getNumVotes() % this._quota, o1.getNumVotes() % this._quota) == 0
							? (random.nextBoolean() ? -1 : 1)
							: Integer.compare(o2.getNumVotes() % this._quota, o1.getNumVotes() % this._quota));

			seatAllocations += String.format("Allocating Additional Seats to Largest Remainders:\n", seatsLeft);
			// If there are enough seats for all largest: add all.
			int newSeats;
			for (newSeats = 0; (newSeats < rankedRemainders.size()) && (newSeats < seatsLeft); newSeats++) {
				final Party curParty = rankedRemainders.get(newSeats);
				curParty.setNumSeats(curParty.getNumSeats() + 1);
				seatAllocations += String.format("\tAllocating Additional Seat to %s [%d Seats]\n", curParty.getName(),
						curParty.getNumSeats());
			}
			seatsLeft -= newSeats;
		}
		seatAllocations += "\nFinal Seat Allocations:\n";
		for (int i = 0; i < this._numParties; i++) {
			final Party curParty = this._parties.get(i);
			seatAllocations += String.format("\t%s - %d\n", curParty.getName(), curParty.getNumSeats());
		}

		this._auditor.seatAllocations(seatAllocations + "\n");
	}

	private void calculateQuota(int numBallots, int numSeats) {
		this._quota = -Math.floorDiv(-numBallots, numSeats);
	}

	private OPLVCandidate findCandidate(int index) {
		return this._candidates.get(index);
	}

	private Party findParty(String name) {
		for (int i = 0; i < this._numParties; i++)
			if (this._parties.get(i).getName().equals(name))
				return this._parties.get(i);
		return null;
	}

	private void rankPartyCandidates() {
		String rankings = "Party Rankings [* - Allocated Party Seat]\n";
		for (int i = 0; i < this._numParties; i++)
			rankings += this._parties.get(i).rankCandidates();
		this._auditor.rankOPLV(rankings);
	}

	public void runElection() throws IOException {
		if (!this.wasRun.getAndSet(true)) {
			for (int i = 0; i < this._numBallots; i++) {
				final OPLVBallot bal = this._ballots.get(i);
				final OPLVCandidate can = findCandidate(bal.getVote());
				can.castVote();
				this._auditor.ballot(bal.getID(), can.getName(), can.getParty().getName());
			}
			calculatePartySeats();
			rankPartyCandidates();
			assignSeats();

			String res = "Election Winners:\n";
			for (int i = 0; i < this._numSeats; i++) {
				final OPLVCandidate curCan = this._seats.get(i);
				res += "\t" + curCan.getName() + " (" + curCan.getParty().getName() + ")\n";
			}
			this._auditor.result(res);
			this._auditor.createAuditFile("auditFile");
			System.out.print(res);
		} else
			System.out.print("ERROR: An election can only be run once for a given voting system.\n");
	}
}