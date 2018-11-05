import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class OPLV extends VotingSystem {
	public OPLVBallot[] _ballots;
	public OPLVCandidate[] _candidates;
	private final int _numSeats;
	public ArrayList<Party> _parties = new ArrayList<Party>();
	public ArrayList<OPLVCandidate> _seats = new ArrayList<OPLVCandidate>();

	OPLV(int numBallots, int numCandidates, int numSeats, String[] candidates, String[] parties,
			ArrayList<Integer> ballots) {
		super(numBallots, numCandidates);
		this._numSeats = numSeats;
		this._candidates = new OPLVCandidate[numCandidates];
		for (int i = 0; i < numCandidates; i++) {
			Party canParty = findParty(parties[i]);
			if (canParty == null) {
				canParty = new Party(parties[i]);
				this._parties.add(canParty);
			}
			final OPLVCandidate newCan = new OPLVCandidate(candidates[i], canParty);
			this._candidates[i] = newCan;
			canParty.addCandidate(newCan);
		}
		this._ballots = new OPLVBallot[numBallots];
		for (int i = 0; i < numBallots; i++)
			this._ballots[i] = new OPLVBallot(ballots.get(i), i + 1);
		calculateQuota(numBallots, numSeats);

		String setup = "";
		setup += "Voting System:\t" + "Open Party List Voting\n";
		setup += "\nParties:\n";
		for (final Party curParty : this._parties) {
			setup += String.format("\n%s\n\tCandidates:\n", curParty.getName());
			for (final OPLVCandidate curCan : curParty.getCandidates())
				setup += String.format("\t\t- %s\n", curCan.getName());
		}
		setup += String.format("\nTotal Number of Candidates: %s\n", this._numCandidates);
		setup += String.format("\nNumber of Ballots: %s\n", this._numBallots);
		setup += String.format("\nBallots: %s\n", ballots);
		setup += String.format("\nBallot Candidates Key:\n", ballots);
		for (int i = 0; i < numCandidates; i++)
			setup += String.format("\t%d - %s\n", i, this._candidates[i].getName());
		setup += String.format("\nNumber of Seats: %s\n", this._numSeats);
		this._auditor.auditSetup(setup);
	}

	private void assignSeats() {
		for (final Party curParty : this._parties)
			this._seats.addAll(curParty.getWinningCandidates());
	}

	private void calculatePartySeats() {
		String seatAllocations = "\nSeat Allocation Calculation:\n";
		int seatsLeft = this._numSeats;
		seatAllocations += String.format("%d Seats Remaining\n", seatsLeft);
		seatAllocations += String.format(
				"Allocating Initial Seats [Floor(Number of Votes / Quota {%d}) with Max as Number of Candidates]:\n",
				this._quota);

		for (final Party curParty : this._parties) {
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
			for (final Party curParty : this._parties)
				// If party has not exhausted all candidates in filling seats
				if (curParty.getNumCandidates() > curParty.getNumSeats())
					rankedRemainders.add(curParty);

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
		for (final Party curParty : this._parties)
			seatAllocations += String.format("\t%s - %d\n", curParty.getName(), curParty.getNumSeats());

		this._auditor.auditProcess(seatAllocations + "\n");
	}

	private void calculateQuota(int numBallots, int numSeats) {
		this._quota = -Math.floorDiv(-numBallots, numSeats);
	}

	private Party findParty(String name) {
		for (final Party curParty : this._parties)
			if (curParty.getName().equals(name))
				return curParty;
		return null;
	}

	private void rankPartyCandidates() {
		String rankings = "Party Rankings [* - Allocated Party Seat]\n";
		for (final Party curParty : this._parties)
			rankings += curParty.rankCandidates();
		this._auditor.auditProcess(rankings);
	}

	public void runElection() throws IOException {
		if (!this.wasRun.getAndSet(true)) {
			for (final OPLVBallot curBal : this._ballots) {
				final OPLVCandidate can = this._candidates[curBal.getVote()];
				can.castVote();
				this._auditor.auditProcess(String.format("Ballot %d cast a vote for %s in party %s\n", curBal.getID(),
						can.getName(), can.getParty().getName()));
			}
			calculatePartySeats();
			rankPartyCandidates();
			assignSeats();

			String res = "Election Winners:\n";
			for (final OPLVCandidate curCan : this._seats)
				res += "\t" + curCan.getName() + " (" + curCan.getParty().getName() + ")\n";
			this._auditor.auditResult(res);
			this._auditor.createAuditFile("auditFile");
			System.out.print(res);
		} else
			System.out.print("ERROR: An election can only be run once for a given voting system.\n");
	}
}