/**
 * File: IRV.java
 * Date Created: 11/08/2018
 * Last Update: Nov 11, 2018 2:38:22 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

package votingsystems;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import mariahgui.MariahElectionResults;

// TODO: Auto-generated Javadoc
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
	this.candidates = new IRVCandidate[numCandidates];
	for (int i = 0; i < numCandidates; i++) {
	    this.candidates[i] = new IRVCandidate(candidates[i]);
	}
	this.ballots = new IRVBallot[numBallots];
	int i = 0;
	for (final ArrayList<Integer> bal : ballots) {
	    this.ballots[i] = new IRVBallot(bal, i + 1);
	    i++;
	}
	this.voterPool = this.ballots;
	calculateQuota(numBallots);

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
    private void calculateQuota(final int numBallots) {
	if ((numBallots % 2) == 0) {
	    this.quota = (numBallots / 2) + 1;
	} else {
	    this.quota = (int) Math.ceil(numBallots * 0.5);
	}
    }

    /**
     * Eliminate all candidates who received no votes.
     */
    private void eliminateAllNoVoteCandidates() {
	final LinkedList<String> eliminatedCandidates = new LinkedList<>();
	for (final IRVCandidate curCan : this.candidates) {
	    if (curCan.getNumVotes() == 0) {
		curCan.eliminate();
		eliminatedCandidates.add(curCan.getName());
	    }
	}
	if (eliminatedCandidates.size() > 0) {
	    final StringBuilder eliminatedCandidateNames = new StringBuilder(
		    "Eliminated the following candidates who received no votes:\n");
	    eliminatedCandidates
		    .forEach(curCanName -> eliminatedCandidateNames.append(String.format("\t%s%n", curCanName)));
	    this.auditor.auditProcess(eliminatedCandidateNames.toString());
	}
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
	    final IRVCandidate mcan = minCandidates.get(0);
	    this.auditor.auditProcess(String.format("%nCandidate %s is eliminated with only %d votes.%n",
		    mcan.getName(), mcan.getNumVotes()));
	    return mcan;
	} else {
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
    public String runElection() throws IOException {
	String auditFile;
	if (!this.wasRun.getAndSet(true)) {
	    final boolean firstRun = true;
	    while (true) {
		int numCandidatesRemaining = 0;
		final StringBuilder lastCan = new StringBuilder();
		for (final IRVCandidate curCan : this.candidates) {
		    if (!curCan.isEliminated()) {
			numCandidatesRemaining++;
			lastCan.append(curCan.getName());
		    }
		}
		if (numCandidatesRemaining < 2) {
		    this.auditor.auditProcess(
			    String.format("%nProcessing Complete!%nOnly one candidate has not been eliminated.%n"));
		    this.auditor.auditResult("Election Winner: " + lastCan.toString());
		    auditFile = this.auditor.createAuditFile(String.format("AUDIT_%d", System.currentTimeMillis()));
		    System.out.print("Election Winner: " + lastCan.toString() + "\n");
		    if (resultsGUI) {
			MariahElectionResults frame = new MariahElectionResults(auditFile,
				"Election Winner: " + lastCan.toString() + "\n");
			frame.setVisible(true);
		    }
		    break;
		}

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
		    this.auditor.auditResult("Election Winner: " + winner);
		    auditFile = this.auditor.createAuditFile(String.format("AUDIT_%d", System.currentTimeMillis()));
		    System.out.print("Election Winner: " + winner + "\n");
		    if (resultsGUI) {
			MariahElectionResults frame = new MariahElectionResults(auditFile,
				"Election Winner: " + winner + "\n");
			frame.setVisible(true);
		    }
		    break;
		} else {
		    final StringBuilder curPartyVotes = new StringBuilder();
		    for (final IRVCandidate curCan : this.candidates) {
			if (!curCan.isEliminated()) {
			    curPartyVotes.append(String.format("\t%s - %d%n", curCan.getName(), curCan.getNumVotes()));
			}
		    }
		    this.auditor.auditProcess("\nRemaining Candidate - Votes:\n" + curPartyVotes.toString());
		    if (firstRun) {
			eliminateAllNoVoteCandidates();
		    }
		    final IRVCandidate can = findMinimumCandidate();
		    this.voterPool = can.eliminate();
		}
	    }
	} else {
	    throw new RuntimeException("An election can only be run once for a given voting system.\n");
	}
	return auditFile;
    }
}
