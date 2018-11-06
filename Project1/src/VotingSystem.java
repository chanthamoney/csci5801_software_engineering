import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

abstract public class VotingSystem {
	/**
	 *
	 */
	protected Auditor _auditor;
	/**
	 *
	 */
	protected int _numBallots;
	/**
	 *
	 */
	protected int _numCandidates;
	/**
	 *
	 */
	protected int _quota;
	/**
	 *
	 */
	protected AtomicBoolean wasRun = new AtomicBoolean(false);

	/**
	 * @param numBallots
	 * @param numCandidates
	 */
	VotingSystem(int numBallots, int numCandidates, String auditFileName) {
		this._numBallots = numBallots;
		this._numCandidates = numCandidates;
		this._auditor = new Auditor(auditFileName);
	}

	/**
	 * @throws IOException
	 */
	abstract protected void runElection() throws IOException;
}