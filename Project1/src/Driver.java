
// File:         Driver.java
// Created:      2018/11/08
// Last Changed: $Date: 2018/11/08 11:37:56 $
// Author:       <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
//
// This code is copyright (c) 2018 University of Minnesota - Twin Cities
//

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import mariahgui.MariahFileChooser;
import votingsystems.IRV;
import votingsystems.OPLV;
import votingsystems.VotingSystem;

/**
 * The Driver of main which generates and runs an election.
 *
 * @author <A HREF="mailto:nippe014@umn.edu">Team14 [Cassandra Chanthamontry
 *         (chant077), Jake Nippert (nippe014), Meghann Silagan (silag001),
 *         Christine Tsai (tsaix223)]</A>
 */
public class Driver {

    /**
     * Generates a voting system from a standardized voting system file.
     *
     * @param fileName the file name
     * @return the voting system
     * @throws FileNotFoundException the file not found exception
     */
    private static VotingSystem votingSystemFromFile(String fileName) throws FileNotFoundException {
	File file = new File(fileName);
	final Scanner scanner = new Scanner(file);

	final String in_VotingSystem = scanner.nextLine();

	if ("IR".equals(in_VotingSystem)) {
	    // Instant Run-off Voting
	    final int in_NumCandidates = Integer.valueOf(scanner.nextLine());
	    final String in_Candidates = scanner.nextLine();
	    final String[] cpPairs = in_Candidates.split(",(?![^\\(\\[]*[\\]\\)]) *");
	    final int in_NumBallots = Integer.valueOf(scanner.nextLine());
	    final LinkedList<ArrayList<Integer>> in_Ballots = IRVBallotsFromFile(in_NumBallots, in_NumCandidates,
		    scanner);
	    scanner.close();
	    return new IRV(in_NumBallots, in_NumCandidates, cpPairs, in_Ballots);
	} else {
	    // Open Party Listing
	    final int in_NumCandidates = Integer.valueOf(scanner.nextLine());
	    final String in_Candidates = scanner.nextLine();
	    final String[] cpPairs = in_Candidates.split(",(?![^\\(\\[]*[\\]\\)]) *");

	    final String[] candidates = new String[in_NumCandidates];
	    final String[] parties = new String[in_NumCandidates];
	    CandidateParyPairsSeparator(in_NumCandidates, cpPairs, candidates, parties);

	    final int in_NumSeats = Integer.valueOf(scanner.nextLine());
	    final int in_NumBallots = Integer.valueOf(scanner.nextLine());
	    final LinkedList<Integer> in_Ballots = OPLVBallotsFromFile(in_NumBallots, scanner);
	    scanner.close();
	    return new OPLV(in_NumBallots, in_NumCandidates, in_NumSeats, candidates, parties, in_Ballots);
	}
    }

    /**
     * Stores the separated strings of candidate party pairs into the designated
     * lists.
     *
     * @param numPairs   the number of pairs to separate
     * @param cpPairs    the candidate party pairs
     * @param candidates the list in which to store candidate names
     * @param parties    the list in which to store party names
     */
    private static void CandidateParyPairsSeparator(int numPairs, String[] cpPairs, String[] candidates,
	    String[] parties) {
	for (int i = 0; i < numPairs; i++) {
	    final String[] pair = cpPairs[i].substring(1, cpPairs[i].length() - 1).split(", *");
	    candidates[i] = pair[0];
	    parties[i] = pair[1];
	}
    }

    /**
     * Retrieves the instant runoff ballots from a file.
     *
     * @param numBallots    the number of ballots cast in the election
     * @param numCandidates the number of candidates participating in the election
     * @param scanner       the scanner reading the specified file
     * @return the list of ballots as a list of votes
     */
    private static LinkedList<ArrayList<Integer>> IRVBallotsFromFile(int numBallots, int numCandidates,
	    Scanner scanner) {
	LinkedList<ArrayList<Integer>> in_Ballots = new LinkedList<>();
	for (int i = 0; i < numBallots; i++) {
	    final int[] balVotesOrg = new int[numCandidates];
	    final String[] ballotInfo = scanner.nextLine().split(", *");
	    int numVotes = 0;
	    for (int j = 0; j < ballotInfo.length; j++) {
		if (!"".equals(ballotInfo[j])) {
		    balVotesOrg[Integer.parseInt(ballotInfo[j]) - 1] = j;
		    numVotes++;
		}
	    }
	    final ArrayList<Integer> balVotes = new ArrayList<>();
	    for (int j = 0; j < numVotes; j++) {
		balVotes.add(balVotesOrg[j]);
	    }
	    in_Ballots.add(balVotes);
	}
	return in_Ballots;
    }

    /**
     * Retrieves the open party list voting ballots from a file.
     *
     * @param numBallots the number of ballots cast in the election
     * @param scanner    the scanner reading the specified file
     * @return the list of ballots
     */
    private static LinkedList<Integer> OPLVBallotsFromFile(int numBallots, Scanner scanner) {
	LinkedList<Integer> in_Ballots = new LinkedList<>();
	for (int i = 0; i < numBallots; i++) {
	    final String[] ballotInfo = scanner.nextLine().split(", *");
	    for (int j = 0; j < ballotInfo.length; j++) {
		if (!"".equals(ballotInfo[j])) {
		    in_Ballots.add(j);
		    break;
		}
	    }
	}
	return in_Ballots;
    }

    /**
     * The main method used to generate an election from a file (passed in as
     * command line argument or input upon running) and runs the election.
     *
     * @param args Optional command line file name argument
     * @throws IOException          Signals that an I/O exception has occurred.
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws IOException, InterruptedException {
	String fileName = null;

	if (args.length > 0) {
	    fileName = args[0].trim();
	} else {
	    MariahFileChooser frame = new MariahFileChooser();
	    frame.setVisible(true);
	    while (frame.getFileName() == null) {
		Thread.sleep(1000);
	    }
	    frame.setVisible(false);
	    fileName = frame.getFileName();
	    frame.dispose();
	}

	VotingSystem vs = votingSystemFromFile(fileName);
	vs.runElection();
    }
}
