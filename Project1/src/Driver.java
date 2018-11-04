import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) throws IOException {
		Scanner consoleReader = new Scanner(System.in);
		System.out.print("Enter Name of Ballot File: ");
		String in_BallotFile = consoleReader.nextLine();
		consoleReader.close();
		File file = new File(in_BallotFile);
		Scanner fileReader = new Scanner(file);
		
		String in_VotingSystem = fileReader.nextLine();
		
		VotingSystem vs = null;
		
		if (in_VotingSystem.equals("IR")) {
			// Instant Run-off Voting
			int in_NumCandidates = Integer.valueOf(fileReader.nextLine());
			
			String in_Candidates = fileReader.nextLine();
			String[] cpPairs = in_Candidates.split(",(?![^\\(\\[]*[\\]\\)]) *");			
			int in_NumBallots = Integer.valueOf(fileReader.nextLine());
			ArrayList<ArrayList<Integer>> in_Ballots = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i < in_NumBallots; i++) {
				int[] balVotesOrg = new int[in_NumCandidates];
				String[] ballotInfo = fileReader.nextLine().split(", *");
				int numVotes = 0;
				for (int j = 0; j < ballotInfo.length; j++) {
					if (!ballotInfo[j].equals("")) {
						balVotesOrg[Integer.parseInt(ballotInfo[j]) - 1] = j;
						numVotes++;
					}
				}
				ArrayList<Integer> balVotes = new ArrayList<Integer>();
				for (int j = 0; j < numVotes; j++) {
					balVotes.add(balVotesOrg[j]);
				}
				in_Ballots.add(balVotes);
			}
			vs = new IRV(in_NumBallots, in_NumCandidates, cpPairs, in_Ballots);
		} else {
			// Open Party Listing
			int in_NumCandidates = Integer.valueOf(fileReader.nextLine());
			String in_Candidates = fileReader.nextLine();
			
			
			String[] cpPairs = in_Candidates.split(",(?![^\\(\\[]*[\\]\\)]) *");
			ArrayList<String> candidates = new ArrayList<String>();
			ArrayList<String> parties = new ArrayList<String>();
			for (int i = 0; i < in_NumCandidates; i++) {
				String[] pair = cpPairs[i].substring(1, cpPairs[i].length()-1).split(", *");
				candidates.add(pair[0]);
				parties.add(pair[1]);
			}
			
			int in_NumSeats = Integer.valueOf(fileReader.nextLine());
			int in_NumBallots = Integer.valueOf(fileReader.nextLine());
			ArrayList<Integer> in_Ballots = new ArrayList<Integer>();
			for (int i = 0; i < in_NumBallots; i++) {
				String[] ballotInfo = fileReader.nextLine().split(", *");
				for (int j = 0; j < ballotInfo.length; j++) {
					if (!ballotInfo[j].equals("")) {
						in_Ballots.add(j);
						break;
					}
				}
				
			}
			vs = new OPLV(in_NumBallots, in_NumCandidates, in_NumSeats, candidates, parties, in_Ballots);
		}
		vs.runElection();
		fileReader.close();
	}
}