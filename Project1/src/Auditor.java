import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents an auditor for a process. Can store information regarding the
 * setup, processing and results of a process and generate a file when complete.
 */
public class Auditor {
	/**
	 * Maintains the audit information for the main processing of information.
	 */
	private StringBuilder _auditProcess = new StringBuilder();
	/**
	 * Maintains the audit information for results of a process.
	 */
	private StringBuilder _auditResult = new StringBuilder();
	/**
	 * Maintains the audit information for setup of a process.
	 */
	private StringBuilder _auditSetup = new StringBuilder();

	/**
	 * Stores audit information in audit's process.
	 * 
	 * @param audit the information to be stored
	 */
	public void auditProcess(String audit) {
		this._auditProcess.append(audit);
	}

	/**
	 * Stores audit information in the audit's results.
	 * 
	 * @param audit the information to be stored
	 */
	public void auditResult(String audit) {
		this._auditResult.append(audit);
	}

	/**
	 * Stores audit information in the audit's setup.
	 * 
	 * @param audit the information to be stored
	 */
	public void auditSetup(String audit) {
		this._auditSetup.append(audit);
	}

	/**
	 * Generates an audit file in the src directory named as "AUDIT_Timestamp". This
	 * file contains separated audit setup, process and result information.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void createAuditFile() throws IOException {
		final File file = new File(String.format("AUDIT_%d", System.currentTimeMillis()));
		final FileWriter writer = new FileWriter(file);
		writer.write(this._auditSetup.toString() + "\n\n- - - - - - - - - - - - - - - - - - - -\n\n"
				+ this._auditProcess.toString() + "\n\n- - - - - - - - - - - - - - - - - - - -\n\n"
				+ this._auditResult.toString());
		writer.close();
	}
}