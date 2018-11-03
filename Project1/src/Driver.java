import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
	public VotingSystem _vs;

	public static void main(String[] args) throws IOException {
		Scanner consoleReader = new Scanner(System.in);
		System.out.print("Enter Name of Ballot File: ");
		String in_BallotFile = consoleReader.nextLine();
		consoleReader.close();
		File file = new File(in_BallotFile);
		Scanner fileReader = new Scanner(file);
		
		String in_VotingSystem = fileReader.nextLine();

		if (in_VotingSystem.equals("IR")) {
			// Instant Run-off Voting
			int in_NumCandidates = Integer.valueOf(fileReader.nextLine());
			String in_Candidates = fileReader.nextLine();
			int in_NumBallots = Integer.valueOf(fileReader.nextLine());
		} else {
			// Open Party Listing
			int in_NumCandidates = Integer.valueOf(fileReader.nextLine());
			String in_Candidates = fileReader.nextLine();
			int in_NumSeats = Integer.valueOf(fileReader.nextLine());
			int in_NumBallots = Integer.valueOf(fileReader.nextLine());
		}
		fileReader.close();
	}
}