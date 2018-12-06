/**
 * File: MariahEP.java
 * Date Created: 11/08/2018
 * Last Update: Dec 4, 2018 5:46:40 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

package main;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

import javax.swing.SwingUtilities;

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
     * @param filePath            the file name
     * @param gui                 maintains if the GUI should be utilized
     * @param validBallotQuotient the valid ballot quotient
     * @return the voting system
     * @throws InvalidFileException the invalid file exception
     * @throws IOException          Signals that an I/O exception has occurred.
     */
    private static VotingSystem votingSystemFromFile(String filePath, boolean gui, int validBallotQuotient)
	    throws InvalidFileException, IOException {
	File file = new File(filePath);
	final Scanner scanner = new Scanner(file);

	final String in_VotingSystem = scanner.nextLine();
	if ("IR".equals(in_VotingSystem.toUpperCase())) {
	    // Retrieve instant runoff voting information from file

	    // Retrieve number of candidates
	    final int in_NumCandidates;
	    try {
		in_NumCandidates = Integer.valueOf(scanner.nextLine());
	    } catch (NoSuchElementException e) {
		scanner.close();
		throw new InvalidFileException("No line of number of candidates found.");
	    } catch (NumberFormatException e) {
		scanner.close();
		throw new InvalidFileException("Invalid number of candidates");
	    }

	    // Retrieve list of candidates
	    final String in_Candidates;
	    try {
		in_Candidates = scanner.nextLine();
	    } catch (NoSuchElementException e) {
		scanner.close();
		throw new InvalidFileException("No line of candidates found.");
	    }

	    // Split candidates
	    final String[] cpPairs;
	    try {
		cpPairs = in_Candidates.split(",(?![^\\(\\[]*[\\]\\)]) *");
	    } catch (PatternSyntaxException e) {
		scanner.close();
		throw new InvalidFileException("Line of candidates was not parseable.");
	    }

	    // Retrieve number of ballots
	    final int in_NumBallots;
	    try {
		in_NumBallots = Integer.valueOf(scanner.nextLine());
	    } catch (NoSuchElementException e) {
		scanner.close();
		throw new InvalidFileException("No line of number of ballots found.");
	    } catch (NumberFormatException e) {
		scanner.close();
		throw new InvalidFileException("Invalid number of ballots.");
	    }

	    // Retrieve all ballots
	    final LinkedList<ArrayList<Integer>> in_Ballots = IRVBallotsFromFile(in_NumBallots, in_NumCandidates,
		    scanner);

	    scanner.close();
	    return new IRV(in_NumBallots, in_NumCandidates, cpPairs, in_Ballots, gui, validBallotQuotient);
	} else if ("OPL".equals(in_VotingSystem.toUpperCase())) {
	    // Retrieve Open Party List Voting from file

	    // Retrieve number of candidates
	    final int in_NumCandidates;
	    try {
		in_NumCandidates = Integer.valueOf(scanner.nextLine());
	    } catch (NoSuchElementException e) {
		scanner.close();
		throw new InvalidFileException("No line of number of candidates found.");
	    } catch (NumberFormatException e) {
		scanner.close();
		throw new InvalidFileException("Invalid number of candidates");
	    }

	    // Retrieve list of candidates
	    final String in_Candidates;
	    try {
		in_Candidates = scanner.nextLine();
	    } catch (NoSuchElementException e) {
		scanner.close();
		throw new InvalidFileException("No line of candidates found.");
	    }

	    // Split candidates
	    final String[] cpPairs;
	    try {
		cpPairs = in_Candidates.split(",(?![^\\(\\[]*[\\]\\)]) *");
	    } catch (PatternSyntaxException e) {
		scanner.close();
		throw new InvalidFileException("Line of candidates was not parseable.");
	    }

	    final String[] candidates = new String[in_NumCandidates];
	    final String[] parties = new String[in_NumCandidates];
	    CandidateParyPairsSeparator(in_NumCandidates, cpPairs, candidates, parties);

	    // Retrieve number of seats
	    final int in_NumSeats;
	    try {
		in_NumSeats = Integer.valueOf(scanner.nextLine());
	    } catch (NoSuchElementException e) {
		scanner.close();
		throw new InvalidFileException("No line of number of seats found.");
	    } catch (NumberFormatException e) {
		scanner.close();
		throw new InvalidFileException("Invalid number of seats.");
	    }

	    // Retrieve number of ballots
	    final int in_NumBallots;
	    try {
		in_NumBallots = Integer.valueOf(scanner.nextLine());
	    } catch (NoSuchElementException e) {
		scanner.close();
		throw new InvalidFileException("No line of number of ballots found.");
	    } catch (NumberFormatException e) {
		scanner.close();
		throw new InvalidFileException("Invalid number of ballots.");
	    }

	    final LinkedList<Integer> in_Ballots = OPLVBallotsFromFile(in_NumBallots, scanner);
	    scanner.close();
	    return new OPLV(in_NumBallots, in_NumCandidates, in_NumSeats, candidates, parties, in_Ballots, gui);
	} else {
	    scanner.close();
	    throw new InvalidFileException("Invalid Election Type");
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
     * Retrieves the instant runoff ballots from a file. Ballots are represented as
     * a list of ordered preferences for candidates identified by their unique
     * index.
     *
     * @param numBallots    the number of ballots cast in the election
     * @param numCandidates the number of candidates participating in the election
     * @param scanner       the scanner reading the specified file
     * @return the list of ballots as a list of votes
     * @throws InvalidFileException the invalid file exception
     */
    private static LinkedList<ArrayList<Integer>> IRVBallotsFromFile(int numBallots, int numCandidates, Scanner scanner)
	    throws InvalidFileException {
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
	    final String[] ballotInfo;
	    try {
		ballotInfo = scanner.nextLine().split(", *");
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
	    } catch (NoSuchElementException e) {
		scanner.close();
		throw new InvalidFileException(String.format("Ballot %d does not exist.", i + 1));
	    } catch (NumberFormatException | PatternSyntaxException e) {
		scanner.close();
		throw new InvalidFileException(String.format("Line of ballot %d was not parseable.", i + 1));
	    }
	}

	return in_Ballots;
    }

    /**
     * Retrieves the open party list voting ballots from a file. Ballots are
     * represented as the index of the candidate.
     *
     * @param numBallots the number of ballots cast in the election
     * @param scanner    the scanner reading the specified file
     * @return the list of ballots
     * @throws InvalidFileException the invalid file exception
     */
    private static LinkedList<Integer> OPLVBallotsFromFile(int numBallots, Scanner scanner)
	    throws InvalidFileException {
	LinkedList<Integer> in_Ballots = new LinkedList<>();
	// For each ballot find the index of where the vote was cast and add as the vote
	for (int i = 0; i < numBallots; i++) {
	    try {
		final String[] ballotInfo = scanner.nextLine().split(", *");
		for (int j = 0; j < ballotInfo.length; j++) {
		    if (!"".equals(ballotInfo[j])) {
			in_Ballots.add(j);
			break;
		    }
		}
	    } catch (NoSuchElementException e) {
		scanner.close();
		throw new InvalidFileException(String.format("Ballot %d does not exist.", i + 1));
	    } catch (PatternSyntaxException e) {
		scanner.close();
		throw new InvalidFileException(String.format("Line of ballot %d was not parseable.", i + 1));
	    }
	}
	return in_Ballots;
    }

    /**
     * The main method used to generate an election from a file (passed in as
     * command line argument or input upon running) and runs the election.
     *
     * @param args Optional command line file name argument and flag for no gui
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     * @throws InvocationTargetException the invocation target exception
     */
    public static void main(String[] args) throws IOException, InterruptedException, InvocationTargetException {
	String filePath = handleArgument("filePath", args);
	boolean gui = "true".equals(handleArgument("gui", args));
	String vbq = handleArgument("validBallotQuotient", args);
	int validBallotQuotient = "".equals(vbq) ? 50 : Integer.parseInt(vbq);

	if (gui) {
	    runElectionGUI(filePath, validBallotQuotient);
	} else {
	    runElectionCommandLine(filePath, validBallotQuotient);
	}
    }

    /**
     * Handle argument.
     *
     * @param setup the setup
     * @param args  the args
     * @return the string
     */
    private static String handleArgument(String setup, String[] args) {
	if ("gui".equals(setup)) {
	    for (String arg : args) {
		if ("--no-gui".equals(arg)) {
		    return "false";
		}
	    }
	    return "true";
	} else if ("filePath".equals(setup)) {
	    for (String arg : args) {
		if (!arg.startsWith("--")) {
		    return arg;
		}
	    }
	} else if ("validBallotQuotient".equals(setup)) {
	    for (String arg : args) {
		if (arg.startsWith("--valid-ballot-quotient=")) {
		    return arg.substring(24);
		}
	    }
	    return "";
	}
	return null;
    }

    /**
     * Runs the election processing without the use of the Graphical User Interface.
     *
     * @param filePath            the file path
     * @param validBallotQuotient the valid ballot quotient
     * @throws InvocationTargetException the invocation target exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws InterruptedException      the interrupted exception
     */
    private static void runElectionCommandLine(String filePath, int validBallotQuotient)
	    throws InvocationTargetException, IOException, InterruptedException {
	File file = new File(filePath.trim());
	VotingSystem vs;
	final Scanner consoleReader = new Scanner(System.in);
	boolean first = true;
	while (file == null || !file.isFile()) {
	    if (!first) {
		System.out.print("Invalid file name. Please enter the name of the ballot file: ");
	    } else {
		System.out.print("Enter Name of Ballot File: ");
		first = false;
	    }
	    final String in_BallotFile = consoleReader.nextLine().trim();
	    file = new File(in_BallotFile);
	}
	consoleReader.close();

	try {
	    vs = votingSystemFromFile(filePath, false, validBallotQuotient);
	} catch (InvalidFileException e) {
	    vs = null;
	}

	if (vs != null) {
	    System.out.print(String.format("Election File: %s%n%n", filePath));
	    String auditFile = vs.runElection();
	    System.out.print(String.format("Audit File: %s%n%n", auditFile));
	}
    }

    /**
     * Runs the election processing utilizing the Mariah Graphical User Interface.
     *
     * @param filePath            the file path
     * @param validBallotQuotient the valid ballot quotient
     * @throws InvocationTargetException the invocation target exception
     * @throws InterruptedException      the interrupted exception
     * @throws IOException               Signals that an I/O exception has occurred.
     */
    private static void runElectionGUI(String filePath, int validBallotQuotient)
	    throws InvocationTargetException, InterruptedException, IOException {
	MariahElectionProcessor frame = new MariahElectionProcessor("MARIAH ELECTION PROCESSOR",
		"Please select an election file from your file system or input the file path below.",
		validBallotQuotient);
	SwingUtilities.invokeAndWait(() -> frame.setVisible(true));
	while (true) {
	    // If user did not restrict GUI and filePath was not provided generate file
	    // chooser gui
	    if (filePath == null) {
		// Wait for user to input file
		while (frame.getFilePath() == null) {
		    Thread.sleep(500);
		}

		filePath = frame.getFilePath();
	    }

	    VotingSystem vs = null;

	    try {
		validBallotQuotient = frame.getValidBallotQuotient();

		vs = votingSystemFromFile(filePath, true, validBallotQuotient);
	    } catch (InvalidFileException e) {
		vs = null;

		// Thread safe way to open unsafe file dialog
		SwingUtilities.invokeAndWait(() -> frame.showDialog(
			String.format("Selected file is not a standardized IRV or OPLV election file.%n%nERROR: %s",
				e.getMessage())));
	    } catch (Exception e) {
		SwingUtilities.invokeAndWait(() -> frame.showDialog("An unexpected error has occured."));
		SwingUtilities.invokeAndWait(frame::dispose);
		runElectionGUI(null, validBallotQuotient);
		break;
	    }

	    if (vs != null) {
		System.out.print(String.format("Election File: %s%n%n", filePath));
		String auditFile = vs.runElection();
		System.out.print(String.format("Audit File: %s%n%n", auditFile));
	    }

	    // If there is a GUI we set the file name to null and open file chooser
	    // otherwise we return
	    filePath = null;

	    // Thread safe way to set file path
	    SwingUtilities.invokeAndWait(() -> frame.setFilePath(null));
	}

    }
}
