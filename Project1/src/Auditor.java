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
	 * get functions for testing
	 */
	public String getAuditProcess() {
		return this._auditProcess;
	}

	public String getAuditResult() {
		return this._auditResult;
	}

	public String getAuditSetup() {
		return this._auditSetup;
	}

	/**
	 * @param name
	 * @throws IOException
	 * Generates an audit file in the src directory named as "AUDIT_Timestamp". This
	 * file contains separated audit setup, process and result information.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void createAuditFile() throws IOException {
		final File file = new File(String.format("AUDIT_%d", System.currentTimeMillis()));
		final FileWriter writer = new FileWriter(file);
		StringBuilder fileOutput = new StringBuilder();

		fileOutput.append(this._auditSetup);
		if (this._auditSetup.length() != 0) {
			fileOutput.append("\n\n- - - - - - - - - - - - - - - - - - - -\n\n");
		}
		fileOutput.append(this._auditProcess);
		if (this._auditProcess.length() != 0) {
			fileOutput.append("\n\n- - - - - - - - - - - - - - - - - - - -\n\n");
		}
		fileOutput.append(this._auditResult);
		writer.write(fileOutput.toString());
		writer.close();
	}
}