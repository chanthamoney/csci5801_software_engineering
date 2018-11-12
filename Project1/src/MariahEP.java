
/**
 * File: MariahEP.java
 * Date Created: 11/08/2018
 * Last Update: Nov 11, 2018 2:41:23 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import mariahgui.MariahFileChooser;
import votingsystems.IRV;
import votingsystems.OPLV;
import votingsystems.VotingSystem;

/**
 * The Main Class which generates and runs an election utilizing Mariah GUI.
 */
public class MariahEP {

    /**
     * Generates a voting system from a standardized voting system file.
     *
     * @param fileName the file name
     * @param noGUI    maintains if no GUI component should be utilized
     * @return the voting system
     * @throws FileNotFoundException the file not found exception
     * @throws ParseException        the parse exception
     */
    private static VotingSystem votingSystemFromFile(String fileName, boolean noGUI)
	    throws FileNotFoundException, ParseException {
	File file = new File(fileName);
	final Scanner scanner = new Scanner(file);

	final String in_VotingSystem = scanner.nextLine();

	if ("IR".equals(in_VotingSystem.toUpperCase())) {
	    // Retrieve instant runoff voting information from file
	    final int in_NumCandidates = Integer.valueOf(scanner.nextLine());
	    final String in_Candidates = scanner.nextLine();
	    final String[] cpPairs = in_Candidates.split(",(?![^\\(\\[]*[\\]\\)]) *");
	    final int in_NumBallots = Integer.valueOf(scanner.nextLine());
	    final LinkedList<ArrayList<Integer>> in_Ballots = IRVBallotsFromFile(in_NumBallots, in_NumCandidates,
		    scanner);
	    scanner.close();
	    return new IRV(in_NumBallots, in_NumCandidates, cpPairs, in_Ballots, !noGUI);
	} else if ("OPL".equals(in_VotingSystem.toUpperCase())) {
	    // Retrieve Open Party List Voting from file
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
	    return new OPLV(in_NumBallots, in_NumCandidates, in_NumSeats, candidates, parties, in_Ballots, !noGUI);
	} else {
	    scanner.close();
	    throw new ParseException("Invalid Election Type", 0);
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

	// For each ballot perform the following set of operations:
	// 1) Create an organization array in length of the number of ballots
	// 2) Split votes by commas and any amount of white space
	// 3) For each split value, if it is not empty (meaning the candidates
	// associated with the current index has been ranked), store the candidate's
	// index (the current index) in the index corresponding to the rank in the
	// organizational array
	// 4) Create an array to store the processed ballots
	// 5) Store the subset of the organizational array up to the number of votes
	// cast as the votes for that ballot
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
	// For each ballot find the index of where the vote was cast and add as the vote
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
	boolean noGUI = false;
	// Determine if optional command line arguments of file name and indicator for
	// no gui were provided
	if (args.length > 0) {
	    fileName = args[0].trim();
	    if (args.length == 2 && "NoGUI".equals(args[1])) {
		noGUI = true;
	    }
	}
	while (true) {
	    // If user did not restrict GUI and filename was not provided generate file
	    // chooser gui
	    if (!noGUI && fileName == null) {
		MariahFileChooser frame = new MariahFileChooser("MARIAH ELECTION PROCESSOR");
		frame.setVisible(true);
		Thread.sleep(5000);
		while (frame.getFileName() == null) {
		    Thread.sleep(1000);
		}
		frame.setVisible(false);
		fileName = frame.getFileName();
		frame.dispose();
	    }

	    VotingSystem vs = null;
	    try {
		vs = votingSystemFromFile(fileName, noGUI);
	    } catch (ParseException e) {
		e.printStackTrace();
	    }

	    String auditFile = vs.runElection();
	    System.out.print(String.format("Audit File: %s", auditFile));

	    // If there is a GUI we set the file name to null and open file chooser
	    if (noGUI) {
		return;
	    }
	    fileName = null;
	}
    }
}
