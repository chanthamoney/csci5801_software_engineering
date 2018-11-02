import java.util.ArrayList;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class Party {
	private String _name;
	private int _numCandidates = 0;
	private int _numSeats = 0;
	private int _numVotes = 0;
	public ArrayList<OPLVCandidate> _candidates = new ArrayList<OPLVCandidate>();

	Party(String name) {
		this._name = name;
	}
	
	public void addCandidate(OPLVCandidate candidate) {
		this._candidates.add(candidate);
		this._numCandidates += 1;
	}

	public void addVote() {
		this._numVotes += 1;
	}

	public String getName() {
		return this._name;
	}

	public int getNumVotes() {
		return this._numVotes;
	}

	public void setNumSeats(int num) {
		this._numSeats = num;
	}

	public int getNumSeats() {
		return this._numSeats;
	}

	public String rankCandidates() {
		throw new UnsupportedOperationException();
	}

	public ArrayList<OPLVCandidate> getWinningCandidates() {
		ArrayList<OPLVCandidate> winners = new ArrayList<OPLVCandidate>();
		for (int i = 0; i < this._numSeats && i < this._numCandidates; i++) {
			winners.add(this._candidates.get(i));
		}
		return winners;
	}
}