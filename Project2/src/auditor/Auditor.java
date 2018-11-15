/**
 * File: Auditor.java
 * Date Created: 11/08/2018
 * Last Update: Nov 13, 2018 6:47:00 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

package auditor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents an auditor for a process. Can store information regarding the
 * setup, processing and results of a process and generate a file when complete.
 */
public class Auditor {
    /** Maintains the audit information for the main processing of information. */
    private final StringBuilder auditProcess = new StringBuilder();

    /** Maintains the audit information for results of a process. */
    private final StringBuilder auditResult = new StringBuilder();

    /** Maintains the audit information for setup of a process. */
    private final StringBuilder auditSetup = new StringBuilder();

    /**
     * Stores audit information in audit's process.
     *
     * @param audit the information to be stored
     */
    public void auditProcess(final String audit) {
	this.auditProcess.append(audit);
    }

    /**
     * Stores audit information in the audit's results.
     *
     * @param audit the information to be stored
     */
    public void auditResult(final String audit) {
	this.auditResult.append(audit);
    }

    /**
     * Stores audit information in the audit's setup.
     *
     * @param audit the information to be stored
     */
    public void auditSetup(final String audit) {
	this.auditSetup.append(audit);
    }

    /**
     * Generates an audit file in the current directory under a specified name. This
     * file contains separated audit setup, process and result information.
     *
     * @param name the name of the output file
     * @return the name of the file that was created
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public String createAuditFile(final String name) throws IOException {
	final File file = new File(name);
	final FileWriter writer = new FileWriter(file);
	final StringBuilder fileOutput = new StringBuilder();

	fileOutput.append(this.auditSetup);
	if ((this.auditSetup.length() != 0)
		&& ((this.auditProcess.length() != 0) || (this.auditResult.length() != 0))) {
	    fileOutput.append("\n\n- - - - - - - - - - - - - - - - - - - -\n\n");
	}
	fileOutput.append(this.auditProcess);
	if ((this.auditProcess.length() != 0) && (this.auditResult.length() != 0)) {
	    fileOutput.append("\n\n- - - - - - - - - - - - - - - - - - - -\n\n");
	}
	fileOutput.append(this.auditResult);
	writer.write(fileOutput.toString());
	writer.close();
	return name;
    }
}