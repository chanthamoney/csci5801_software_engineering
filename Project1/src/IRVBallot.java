import java.util.ArrayList;

public class IRVBallot extends Ballot {
	private ArrayList<Integer> _votes = new ArrayList<Integer>();
	private int _curVoteIndex = 0;
	private int _numVotes;

	IRVBallot(ArrayList<Integer> votes, int id) {
		super(id);
		this._votes.addAll(votes);
		this._numVotes = votes.size();
	}
	
	public int getNextVote() {
		this._curVoteIndex += 1;
		return this._votes.get(this._curVoteIndex -1);
	}

	public boolean isExhausted() {
		return this._numVotes <= this._curVoteIndex;
	}
}