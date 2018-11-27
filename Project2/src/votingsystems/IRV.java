/**
 * File: IRV.java
 * Date Created: 11/08/2018
 * Last Update: Nov 26, 2018 5:31:27 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

package votingsystems;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.SwingUtilities;

import mariahgui.MariahResults;

/**
 * Represents an Instant Runoff Voting System.
 */
public class IRV extends VotingSystem {

    /** The ballots being cast in the election. */
    private final IRVBallot[] ballots;

    /** The candidates participating in the election. */
    private final IRVCandidate[] candidates;

    /**
     * Stores the ballots currently being processed in the initial allocation of
     * votes or in a redistribution of votes for a runoff.
     */
    private IRVBallot[] voterPool;

    /** Indicates if the results should be output to the GUI. */
    private boolean resultsGUI;

    /** Maintains the number of candidates who have not been eliminated. */
    private int remainingCandidates;

    /**
     * Instantiates a new instant runoff voting system.
     *
     * @param numBallots    the number of ballots in the election
     * @param numCandidates the number of candidates in the election
     * @param candidates    the candidates running in the election
     * @param ballots       the ballots being cast in the election
     * @param resultsGUI    indicator for whether the results should be displaying
     *                      using the gui
     */
    public IRV(final int numBallots, final int numCandidates, final String[] candidates,
	    final LinkedList<ArrayList<Integer>> ballots, boolean resultsGUI) {
	super(numBallots, numCandidates);
	this.resultsGUI = resultsGUI;

	// Initialize candidates
	this.candidates = new IRVCandidate[numCandidates];
	for (int i = 0; i < numCandidates; i++) {
	    this.candidates[i] = new IRVCandidate(candidates[i]);
	}
	remainingCandidates = numCandidates;

	// Initialize ballots
	this.ballots = new IRVBallot[numBallots];
	int i = 0;
	for (final ArrayList<Integer> bal : ballots) {
	    this.ballots[i] = new IRVBallot(bal, i + 1);
	    i++;
	}

	// Initialize voter pool to all ballots
	this.voterPool = this.ballots;

	// Initialize quota
	initializeQuota(numBallots);

	// Produce audit file information
	final StringBuilder setup = new StringBuilder(
		String.format("Voting System:\tInstant Runoff Voting%n%nNumber of Candidates: %s%n%nCandidates:%n",
			this.numCandidates));
	for (i = 0; i < this.numCandidates; i++) {
	    setup.append(String.format("\t%d - %s%n", i, candidates[i]));
	}
	setup.append(String.format("%nNumber of Ballots: %s%n%nBallots: %s%n", this.numBallots, ballots));
	this.auditor.auditSetup(setup.toString());
    }

    /** Throws an error for default constructor. */
    public IRV() {
	super();
	throw new IllegalArgumentException("Default constructor is not allowed.");
    }

    /**
     * Determines the quota to be guaranteed a win in the election and stores it in
     * a private variable. The quota is a majority greater than 50%.
     *
     * @param numBallots the number of ballots cast in the election
     */
    private void initializeQuota(final int numBallots) {
	if ((numBallots % 2) == 0) {
	    this.quota = (numBallots / 2) + 1;
	} else {
	    this.quota = (int) Math.ceil(numBallots * 0.5);
	}
    }

    /**
     * Eliminate all candidates who received no votes.
     *
     * @return the number of candidates with no votes eliminated
     */
    private int eliminateAllNoVoteCandidates() {
	final LinkedList<String> eliminatedCandidates = new LinkedList<>();
	int numElim = 0;
	for (final IRVCandidate curCan : this.candidates) {
	    if (curCan.getNumVotes() == 0) {
		curCan.eliminate();
		numElim++;
		eliminatedCandidates.add(curCan.getName());
	    }
	}

	// Audit elimination of candidates with no votes
	if (numElim > 0) {
	    final StringBuilder eliminatedCandidateNames = new StringBuilder(
		    "Eliminated the following candidates who received no votes:\n");
	    eliminatedCandidates
		    .forEach(curCanName -> eliminatedCandidateNames.append(String.format("\t%s%n", curCanName)));
	    this.auditor.auditProcess(eliminatedCandidateNames.toString());
	}

	return numElim;
    }

    /**
     * Find the candidate who received the least amount of votes after the initial
     * election process or during a runoff.
     *
     * @return the IRV candidate
     */
    private IRVCandidate findMinimumCandidate() {
	final LinkedList<IRVCandidate> minCandidates = new LinkedList<>();
	int minimum = Integer.MAX_VALUE;
	for (final IRVCandidate curCan : this.candidates) {
	    if (!curCan.isEliminated()) {
		final int numVotes = curCan.getNumVotes();
		if (curCan.getNumVotes() == minimum) {
		    minCandidates.add(curCan);
		} else if (numVotes < minimum) {
		    minCandidates.clear();
		    minCandidates.add(curCan);
		    minimum = numVotes;
		}
	    }
	}

	// Determines if random decision is needed.
	if (minCandidates.size() == 1) {
	    // Return and audit winner
	    final IRVCandidate mcan = minCandidates.get(0);
	    this.auditor.auditProcess(String.format("%nCandidate %s is eliminated with only %d votes.%n",
		    mcan.getName(), mcan.getNumVotes()));
	    return mcan;
	} else {
	    // Return and audit randomly selected winner
	    final Random randomizer = new Random(System.currentTimeMillis());
	    final IRVCandidate rcan = minCandidates.get(randomizer.nextInt(minCandidates.size()));
	    this.auditor.auditProcess(String.format(
		    "Candidate %s is eliminated with only %d votes.%nNOTE: This elimination was the result of a random toss due to a consequential tie in least amount of votes.%n",
		    rcan.getName(), rcan.getNumVotes()));
	    return rcan;
	}
    }

    /**
     * Used to check if a candidate has received enough votes for a majority.
     *
     * @param numVotes the number of votes the candidate has received.
     * @return true, if is majority
     */
    private boolean isMajority(final int numVotes) {
	return numVotes >= this.quota;
    }

    /**
     * Processes the current voter pool in the running of an election.
     *
     * @return the name of the candidate who has received a majority of votes
     *         otherwise an empty string if no winner has been tabulated.
     */
    private String processVoterPool() {
	// Iterate through the voter pool and perform the following actions:
	// 1) Find the next vote on the ballot
	// 2) If ballot becomes exhausted or was exhausted before a vote is found then
	// audit this information and move to the next ballot
	// 3) Else if a vote is found find the candidate the vote corresponds to and add
	// the ballot and check if the candidate now has a majority
	// 3a) If there is a majority, return the winner with an audit
	final StringBuilder processedBallots = new StringBuilder();
	for (final IRVBallot bal : this.voterPool) {
	    boolean wasExhausted = true;
	    while (!bal.isExhausted()) {
		final IRVCandidate can = this.candidates[bal.getNextVote()];
		if (!can.isEliminated()) {
		    can.addBallot(bal);
		    processedBallots
			    .append(String.format("Ballot %d cast a vote for %s%n", bal.getID(), can.getName()));
		    if (isMajority(can.getNumVotes())) {
			processedBallots.append(
				String.format("%nProcessing Complete!%nCandidate %s has a majority of votes (>= %d).%n",
					can.getName(), this.quota));
			this.auditor.auditProcess(processedBallots.toString());
			return can.getName();
		    }
		    wasExhausted = false;
		    break;
		}
	    }
	    if (wasExhausted) {
		processedBallots.append(String.format("Ballot %d has exhausted all of its votes.%n", bal.getID()));
	    }
	}
	this.auditor.auditProcess(processedBallots.toString());
	return "";
    }

    /*
     * (non-Javadoc)
     *
     * @see VotingSystem#runElection()
     */
    @Override
    public String runElection() throws IOException, InterruptedException, InvocationTargetException {
	String auditFile;
	// Atomically determine if election was run before. Throw error if run before as
	// an election can only be run once!
	if (!this.wasRun.getAndSet(true)) {
	    boolean firstRun = true;
	    while (true) {
		// If there is only one candidate remaining return and audit winner
		if (remainingCandidates < 2) {
		    String lastCan = "";
		    // Collect candidate who has not been eliminated
		    for (final IRVCandidate curCan : this.candidates) {
			if (!curCan.isEliminated()) {
			    lastCan = curCan.getName();
			    break;
			}
		    }

		    // Audit winner found and break;
		    this.auditor.auditProcess(
			    String.format("%nProcessing Complete!%nOnly one candidate has not been eliminated.%n"));
		    this.auditor.auditResult("Election Winner: " + lastCan);
		    auditFile = this.auditor.createAuditFile(String.format("AUDIT_%d", System.currentTimeMillis()));
		    System.out.print("Election Winner: " + lastCan + "\n\n");
		    if (resultsGUI) {
			MariahResults frame = new MariahResults("Election Results", auditFile,
				new String[] { "Invalid Ballots" }, new String[] { "TODO INVALID BALLOTS FILE" },
				"Election Winner: " + lastCan + "\n",
				new String[][] { { "A1", "B1", "C1" }, { "A2", "B2", "C2" } },
				new String[] { "Title 1", "Title 2", "Title 3" },
				"Official Mariah Election Processor Report", "Print Report TODO");

			// Ensures thread safety with GUI
			SwingUtilities.invokeAndWait(() -> frame.setVisible(true));
		    }
		    break;
		}

		// Audit the processing of voter pool (NOTE: Not all ballots may actually
		// process if majority is found early!)
		final StringBuilder ballotsProcessed = new StringBuilder();
		for (final IRVBallot curBallot : this.voterPool) {
		    if (ballotsProcessed.length() != 0) {
			ballotsProcessed.append(", ");
		    }
		    ballotsProcessed.append(curBallot.getID());
		}
		this.auditor.auditProcess("Processing the following ballots:\n\t" + ballotsProcessed.toString() + "\n");

		final String winner = processVoterPool();

		if (!"".equals(winner)) {
		    // Audit winner found and break;
		    this.auditor.auditResult("Election Winner: " + winner);
		    auditFile = this.auditor.createAuditFile(String.format("AUDIT_%d", System.currentTimeMillis()));
		    System.out.print("Election Winner: " + winner + "\n\n");
		    if (resultsGUI) {
			MariahResults frame = new MariahResults("Election Results", auditFile,
				new String[] { "Invalid Ballots" }, new String[] { "TODO INVALID BALLOTS FILE" },
				"Election Winner: " + winner + "\n",
				new String[][] { { "A1", "B1", "C1" }, { "A2", "B2", "C2" } },
				new String[] { "Title 1", "Title 2", "Title 3" },
				"Official Mariah Election Processor Report", "Print Report TODO");

			// Ensures thread safety with GUI
			SwingUtilities.invokeAndWait(() -> frame.setVisible(true));
		    }
		    break;
		} else {
		    // Audit the current party votes
		    final StringBuilder curPartyVotes = new StringBuilder();
		    for (final IRVCandidate curCan : this.candidates) {
			if (!curCan.isEliminated()) {
			    curPartyVotes.append(String.format("\t%s - %d%n", curCan.getName(), curCan.getNumVotes()));
			}
		    }
		    this.auditor.auditProcess("\nRemaining Candidate - Votes:\n" + curPartyVotes.toString());

		    // On the first run only eliminate all candidates who did not receive votes
		    if (firstRun) {
			int numElim = eliminateAllNoVoteCandidates();
			remainingCandidates -= numElim;
			firstRun = false;
		    }

		    // Eliminate the candidate with the least number of votes and update voter pool
		    final IRVCandidate can = findMinimumCandidate();
		    this.voterPool = can.eliminate();
		    remainingCandidates--;
		}
	    }
	} else {
	    throw new RuntimeException("An election can only be run once for a given voting system.\n");
	}
	return auditFile;
    }
}