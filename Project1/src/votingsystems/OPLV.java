
/**
 * File: OPLV.java
 * Date Created: 11/08/2018
 * Last Update: Nov 12, 2018 12:24:29 AM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

package votingsystems;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import mariahgui.MariahElectionResults;

/**
 * Represents an open party list voting system.
 */
public class OPLV extends VotingSystem {

    /** The ballots in the election. */
    private OPLVBallot[] ballots;

    /** The candidates running in the election for the given parties. */
    private OPLVCandidate[] candidates;

    /** The number of seats available in the election. */
    private final int numSeats;

    /** The parties participating in the election. */
    private LinkedList<Party> parties = new LinkedList<>();

    /**
     * Maintains a list of candidates who have been awarded seats in the election.
     */
    private LinkedList<OPLVCandidate> seats = new LinkedList<>();

    /**
     * Maintains whether a specific ranking of parties required a random toss
     * decision for a tie.
     */
    private boolean wasRandomRanking = false;

    /** Indicates if the results should be output to the GUI. */
    private boolean resultsGUI;

    /**
     * Instantiates a new open party list voting system.
     *
     * @param numBallots    the number of ballots cast
     * @param numCandidates the number of candidates participating
     * @param numSeats      the number of seats available
     * @param candidates    the candidates participating in the election
     * @param parties       the parties participating in the election
     * @param ballots       the ballots cast in the election
     * @param resultsGUI    indicates if the results should be output to the GUI
     */
    public OPLV(int numBallots, int numCandidates, int numSeats, String[] candidates, String[] parties,
	    LinkedList<Integer> ballots, boolean resultsGUI) {
	super(numBallots, numCandidates);
	this.resultsGUI = resultsGUI;
	this.numSeats = numSeats;
	this.candidates = new OPLVCandidate[numCandidates];
	for (int i = 0; i < numCandidates; i++) {
	    Party canParty = findParty(parties[i]);
	    if (canParty == null) {
		canParty = new Party(parties[i]);
		this.parties.add(canParty);
	    }
	    final OPLVCandidate newCan = new OPLVCandidate(candidates[i], canParty);
	    this.candidates[i] = newCan;
	    canParty.addCandidate(newCan);
	}
	this.ballots = new OPLVBallot[numBallots];
	int i = 0;
	for (Integer bal : ballots) {
	    this.ballots[i] = new OPLVBallot(bal, i + 1);
	    i++;
	}
	calculateQuota(numBallots, numSeats);

	final StringBuilder setup = new StringBuilder("Voting System:\tOpen Party List Voting\n\nParties:\n");
	this.parties.forEach(curParty -> {
	    setup.append(String.format("%n%s%n\tCandidates:%n", curParty.getName()));
	    curParty.getCandidates().forEach(curCan -> setup.append(String.format("\t\t- %s%n", curCan.getName())));
	});
	setup.append(String.format(
		"%nTotal Number of Candidates: %s%n%nNumber of Ballots: %s%n%nBallots: %s%n%nBallot Candidates Key:%n",
		this.numCandidates, this.numBallots, ballots));
	for (i = 0; i < numCandidates; i++) {
	    setup.append(String.format("\t%d - %s%n", i, this.candidates[i].getName()));
	}
	setup.append(String.format("%nNumber of Seats: %s%n", this.numSeats));
	this.auditor.auditSetup(setup.toString());
    }

    /** Throws an error for default constructor. */
    public OPLV() {
	super();
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * Sources all of the candidates from every party who was awarded a seat in the
     * election.
     */
    private void assignSeats() {
	this.parties.forEach(curParty -> this.seats.addAll(curParty.getWinningCandidates()));
    }

    /**
     * Calculate the number of seats each party will be awarded.
     */
    private void calculatePartySeats() {
	int seatsLeft = this.numSeats;

	final StringBuilder seatAllocations = new StringBuilder(String.format(
		"%nSeat Allocation Calculation:%n%n- %d Seats Remaining%nAllocating Initial Seats [Floor(Number of Votes / Quota {%d}) with Max as Number of Candidates]:%n",
		seatsLeft, this.quota));

	for (final Party curParty : this.parties) {
	    int curPartySeats = Math.floorDiv(curParty.getNumVotes(), this.quota);
	    curPartySeats = curPartySeats > curParty.getNumCandidates() ? curParty.getNumCandidates() : curPartySeats;
	    curParty.setNumSeats(curPartySeats);
	    seatsLeft -= curPartySeats;
	    seatAllocations.append(String.format(
		    "\t[Party: %s, Votes: %d, Number of Candidates: %d, Initial Seats: %d, Remainder: %d]%n",
		    curParty.getName(), curParty.getNumVotes(), curParty.getNumCandidates(), curPartySeats,
		    curParty.getNumVotes() % this.quota));
	}
	while (seatsLeft > 0) {
	    seatAllocations.append(String.format("%n- %d Seats Remaining%n", seatsLeft));
	    final LinkedList<Party> rankedRemainders = new LinkedList<>();
	    // If party has not exhausted all candidates in filling seats
	    // Retrieve Parties that do not have all seats filled
	    this.parties.stream().filter(curParty -> curParty.getNumCandidates() > curParty.getNumSeats())
		    .forEach(rankedRemainders::add);

	    final Random random = new Random(System.currentTimeMillis());
	    rankedRemainders
		    .sort((o1, o2) -> Integer.compare(o2.getNumVotes() % this.quota, o1.getNumVotes() % this.quota) == 0
			    ? getRandomSortValue(random)
			    : Integer.compare(o2.getNumVotes() % this.quota, o1.getNumVotes() % this.quota));

	    seatAllocations.append(String.format("Allocating Additional Seats to Largest Remainders:%n", seatsLeft));
	    // Add as many remaining seats as possible
	    int newSeats = Math.min(rankedRemainders.size(), seatsLeft);
	    for (int i = 0; i < newSeats; i++) {
		final Party curParty = rankedRemainders.get(i);
		curParty.setNumSeats(curParty.getNumSeats() + 1);
		seatAllocations.append(String.format("\tAllocating Additional Seat to %s [%d Seats]%n",
			curParty.getName(), curParty.getNumSeats()));
	    }
	    // If there were any random rankings, determine if consequential and audit this
	    // finding
	    if (this.wasRandomRanking && (seatsLeft < rankedRemainders.size())
		    && (rankedRemainders.get(newSeats - 1).getNumVotes()
			    % this.quota == rankedRemainders.get(newSeats).getNumVotes() % this.quota)) {
		seatAllocations
			.append("NOTE: There was a consequential tie in remainders which was resolved randomly.\n");
		this.wasRandomRanking = false;
	    }
	    seatsLeft -= newSeats;
	}
	seatAllocations.append("\nFinal Seat Allocations:\n");
	this.parties.forEach(curParty -> seatAllocations
		.append(String.format("\t%s - %d%n", curParty.getName(), curParty.getNumSeats())));

	this.auditor.auditProcess(seatAllocations.toString() + "\n");
    }

    /**
     * Generates a random value negative or positive for ranking to dispute ties and
     * documents this random toss.
     *
     * @param random the random generator
     * @return the random negative or positive comparison value
     */
    private int getRandomSortValue(Random random) {
	this.wasRandomRanking = true;
	boolean bool = random.nextBoolean();
	return bool ? -1 : 1;
    }

    /**
     * Calculate the quota necessary to be guaranteed a seat in the election. The
     * ceiling of the number of ballots divided by the number of seats.
     *
     * @param numBallots the number of ballots cast
     * @param numSeats   the number of seats available
     */
    private void calculateQuota(int numBallots, int numSeats) {
	this.quota = -Math.floorDiv(-numBallots, numSeats);
    }

    /**
     * Finds a party given the name of the party.
     *
     * @param name the name of the party
     * @return the party
     */
    private Party findParty(String name) {
	return this.parties.stream().filter(curParty -> curParty.getName().equals(name)).findFirst().orElse(null);
    }

    /**
     * Ranks the candidates in each party participating in the election.
     */
    private void rankPartyCandidates() {
	final StringBuilder rankings = new StringBuilder("Party Rankings [* - Allocated Party Seat]\n");
	this.parties.forEach(curParty -> rankings.append(curParty.rankCandidates()));
	this.auditor.auditProcess(rankings.toString());
    }

    /*
     * (non-Javadoc)
     *
     * @see VotingSystem#runElection()
     */
    @Override
    public String runElection() throws IOException {
	String auditFile = null;
	if (!this.wasRun.getAndSet(true)) {
	    final StringBuilder processedBallots = new StringBuilder();
	    for (final OPLVBallot curBal : this.ballots) {
		final OPLVCandidate can = this.candidates[curBal.getVote()];
		can.castVote();
		processedBallots.append(String.format("Ballot %d cast a vote for %s in party %s%n", curBal.getID(),
			can.getName(), can.getParty().getName()));
	    }
	    this.auditor.auditProcess(processedBallots.toString());
	    calculatePartySeats();
	    rankPartyCandidates();
	    assignSeats();

	    final StringBuilder res = new StringBuilder("Election Winners:\n");
	    this.seats.forEach(
		    curCan -> res.append(String.format("\t%s (%s)%n", curCan.getName(), curCan.getParty().getName())));
	    this.auditor.auditResult(res.toString());
	    auditFile = this.auditor.createAuditFile(String.format("AUDIT_%d", System.currentTimeMillis()));
	    System.out.print(res.toString());
	    if (this.resultsGUI) {
		MariahElectionResults frame = new MariahElectionResults("Election Results", auditFile, res.toString());
		frame.setVisible(true);
	    }
	} else {
	    throw new RuntimeException("An election can only be run once for a given voting system.\n");
	}
	return auditFile;
    }
}
