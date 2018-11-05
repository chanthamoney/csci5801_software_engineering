import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Auditor {
	/**
	 * 
	 */
	private String _auditProcess = "";
	/**
	 * 
	 */
	private String _auditResult = "";
	/**
	 * 
	 */
	private String _auditSetup = "";

	/**
	 * @param audit
	 */
	public void auditProcess(String audit) {
		this._auditProcess += audit;
	}

	/**
	 * @param audit
	 */
	public void auditResult(String audit) {
		this._auditResult += audit;
	}

	/**
	 * @param audit
	 */
	public void auditSetup(String audit) {
		this._auditSetup += audit;
	}

	/**
	 * @param name
	 * @throws IOException
	 */
	public void createAuditFile(String name) throws IOException {
		final File file = new File(name);
		final FileWriter writer = new FileWriter(file);
		writer.write(this._auditSetup + "\n\n- - - - - - - - - - - - - - - - - - - -\n\n" + this._auditProcess
				+ "\n\n- - - - - - - - - - - - - - - - - - - -\n\n" + this._auditResult);
		writer.close();
	}
}