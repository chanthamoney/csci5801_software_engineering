import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class OPLV extends VotingSystem {
	/**
	 *
	 */
	public OPLVBallot[] _ballots;
	/**
	 *
	 */
	public OPLVCandidate[] _candidates;
	/**
	 *
	 */
	private final int _numSeats;
	/**
	 *
	 */
	public LinkedList<Party> _parties = new LinkedList<Party>();
	/**
	 *
	 */
	public LinkedList<OPLVCandidate> _seats = new LinkedList<OPLVCandidate>();

	/**
	 * @param numBallots
	 * @param numCandidates
	 * @param numSeats
	 * @param candidates
	 * @param parties
	 * @param ballots
	 */
	OPLV(int numBallots, int numCandidates, int numSeats, String[] candidates, String[] parties,
			LinkedList<Integer> ballots) {
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
		int i = 0;
		for (Integer bal : ballots) {
			this._ballots[i] = new OPLVBallot(bal, i + 1);
			i++;
		}
		calculateQuota(numBallots, numSeats);

		final StringBuilder setup = new StringBuilder("Voting System:\tOpen Party List Voting\n\nParties:\n");
		for (final Party curParty : this._parties) {
			setup.append(String.format("\n%s\n\tCandidates:\n", curParty.getName()));
			for (final OPLVCandidate curCan : curParty.getCandidates())
				setup.append(String.format("\t\t- %s\n", curCan.getName()));
		}
		setup.append(String.format(
				"\nTotal Number of Candidates: %s\n\nNumber of Ballots: %s\n\nBallots: %s\n\nBallot Candidates Key:\n",
				this._numCandidates, this._numBallots, ballots));
		for (i = 0; i < numCandidates; i++)
			setup.append(String.format("\t%d - %s\n", i, this._candidates[i].getName()));
		setup.append(String.format("\nNumber of Seats: %s\n", this._numSeats));
		this._auditor.auditSetup(setup.toString());
	}

	/**
	 *
	 */
	private void assignSeats() {
		for (final Party curParty : this._parties)
			this._seats.addAll(curParty.getWinningCandidates());
	}

	/**
	 *
	 */
	private void calculatePartySeats() {
		int seatsLeft = this._numSeats;

		final StringBuilder seatAllocations = new StringBuilder(String.format(
				"\nSeat Allocation Calculation:\n%d Seats Remaining\nAllocating Initial Seats [Floor(Number of Votes / Quota {%d}) with Max as Number of Candidates]:\n",
				seatsLeft, this._quota));

		for (final Party curParty : this._parties) {
			int curPartySeats = Math.floorDiv(curParty.getNumVotes(), this._quota);
			curPartySeats = curPartySeats > curParty.getNumCandidates() ? curParty.getNumCandidates() : curPartySeats;
			curParty.setNumSeats(curPartySeats);
			seatsLeft -= curPartySeats;
			seatAllocations.append(String.format(
					"\t[Party: %s, Votes: %d, Number of Candidates: %d, Initial Seats: %d, Remainder: %d]\n",
					curParty.getName(), curParty.getNumVotes(), curParty.getNumCandidates(), curPartySeats,
					curParty.getNumVotes() % this._quota));
		}
		while (seatsLeft > 0) {
			seatAllocations.append(String.format("%d Seats Remaining\n", seatsLeft));
			final LinkedList<Party> rankedRemainders = new LinkedList<Party>();
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

			seatAllocations.append(String.format("Allocating Additional Seats to Largest Remainders:\n", seatsLeft));
			// If there are enough seats for all largest: add all.
			int newSeats;
			for (newSeats = 0; (newSeats < rankedRemainders.size()) && (newSeats < seatsLeft); newSeats++) {
				final Party curParty = rankedRemainders.get(newSeats);
				curParty.setNumSeats(curParty.getNumSeats() + 1);
				seatAllocations.append(String.format("\tAllocating Additional Seat to %s [%d Seats]\n",
						curParty.getName(), curParty.getNumSeats()));
			}
			seatsLeft -= newSeats;
		}
		seatAllocations.append("\nFinal Seat Allocations:\n");
		for (final Party curParty : this._parties)
			seatAllocations.append(String.format("\t%s - %d\n", curParty.getName(), curParty.getNumSeats()));

		this._auditor.auditProcess(seatAllocations.toString() + "\n");
	}

	/**
	 * @param numBallots
	 * @param numSeats
	 */
	private void calculateQuota(int numBallots, int numSeats) {
		this._quota = -Math.floorDiv(-numBallots, numSeats);
	}

	/**
	 * @param name
	 * @return
	 */
	private Party findParty(String name) {
		for (final Party curParty : this._parties)
			if (curParty.getName().equals(name))
				return curParty;
		return null;
	}

	/**
	 *
	 */
	private void rankPartyCandidates() {
		final StringBuilder rankings = new StringBuilder("Party Rankings [* - Allocated Party Seat]\n");
		for (final Party curParty : this._parties)
			rankings.append(curParty.rankCandidates());
		this._auditor.auditProcess(rankings.toString());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see VotingSystem#runElection()
	 */
	public void runElection() throws IOException {
		if (!this.wasRun.getAndSet(true)) {
			final StringBuilder processedBallots = new StringBuilder();
			for (final OPLVBallot curBal : this._ballots) {
				final OPLVCandidate can = this._candidates[curBal.getVote()];
				can.castVote();
				processedBallots.append(String.format("Ballot %d cast a vote for %s in party %s\n", curBal.getID(),
						can.getName(), can.getParty().getName()));
			}
			this._auditor.auditProcess(processedBallots.toString());
			calculatePartySeats();
			rankPartyCandidates();
			assignSeats();

			final StringBuilder res = new StringBuilder("Election Winners:\n");
			for (final OPLVCandidate curCan : this._seats)
				res.append(String.format("\t%s (%s)\n", curCan.getName(), curCan.getParty().getName()));
			this._auditor.auditResult(res.toString());
			this._auditor.createAuditFile();
			System.out.print(res.toString());
		} else
			System.out.print("ERROR: An election can only be run once for a given voting system.\n");
	}
}