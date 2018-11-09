import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * The Driver of main which generates and runs an election.
 */
public class Driver {
    private static VotingSystem votingSystemFromFile(File file) throws FileNotFoundException {
	final Scanner scanner = new Scanner(file);

	final String in_VotingSystem = scanner.nextLine();

	if (in_VotingSystem.equals("IR")) {
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
	    final String[] parties = partiesFromFile(in_NumCandidates, cpPairs, candidates);
	    final int in_NumSeats = Integer.valueOf(scanner.nextLine());
	    final int in_NumBallots = Integer.valueOf(scanner.nextLine());
	    final LinkedList<Integer> in_Ballots = OPLVBallotsFromFile(in_NumBallots, scanner);
	    scanner.close();
	    return new OPLV(in_NumBallots, in_NumCandidates, in_NumSeats, candidates, parties, in_Ballots);
	}
    }

    private static String[] partiesFromFile(int numCandidates, String[] cpPairs, String[] candidates) {
	String[] parties = new String[numCandidates];
	for (int i = 0; i < numCandidates; i++) {
	    final String[] pair = cpPairs[i].substring(1, cpPairs[i].length() - 1).split(", *");
	    candidates[i] = pair[0];
	    parties[i] = pair[1];
	}
	return parties;
    }

    private static LinkedList<ArrayList<Integer>> IRVBallotsFromFile(int numBallots, int numCandidates,
	    Scanner scanner) {
	LinkedList<ArrayList<Integer>> in_Ballots = new LinkedList<ArrayList<Integer>>();
	for (int i = 0; i < numBallots; i++) {
	    final int[] balVotesOrg = new int[numCandidates];
	    final String[] ballotInfo = scanner.nextLine().split(", *");
	    int numVotes = 0;
	    for (int j = 0; j < ballotInfo.length; j++)
		if (!ballotInfo[j].equals("")) {
		    balVotesOrg[Integer.parseInt(ballotInfo[j]) - 1] = j;
		    numVotes++;
		}
	    final ArrayList<Integer> balVotes = new ArrayList<Integer>();
	    for (int j = 0; j < numVotes; j++)
		balVotes.add(balVotesOrg[j]);
	    in_Ballots.add(balVotes);
	}
	return in_Ballots;
    }

    private static LinkedList<Integer> OPLVBallotsFromFile(int numBallots, Scanner scanner) {
	LinkedList<Integer> in_Ballots = new LinkedList<Integer>();
	for (int i = 0; i < numBallots; i++) {
	    final String[] ballotInfo = scanner.nextLine().split(", *");
	    for (int j = 0; j < ballotInfo.length; j++)
		if (!ballotInfo[j].equals("")) {
		    in_Ballots.add(j);
		    break;
		}
	}
	return in_Ballots;
    }

    /**
     * The main method used to generate an election from a file (passed in as
     * command line argument or input upon running) and runs the election.
     *
     * @param args Optional command line file name argument
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void main(String[] args) throws IOException {
	final String in_BallotFile;

	if (args.length > 0)
	    in_BallotFile = args[0].trim();
	else {
	    final Scanner consoleReader = new Scanner(System.in);
	    System.out.print("Enter Name of Ballot File: ");
	    in_BallotFile = consoleReader.nextLine().trim();
	    consoleReader.close();
	}
	final File file = new File(in_BallotFile);
	VotingSystem vs = votingSystemFromFile(file);

	vs.runElection();
    }
}