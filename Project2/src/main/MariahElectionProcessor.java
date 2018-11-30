/**
 * File: MariahElectionProcessor.java
 * Date Created: 11/08/2018
 * Last Update: Nov 29, 2018 8:27:47 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */
package main;

import java.io.IOException;

/**
 * The Class MariahElectionProcessor.
 */
public class MariahElectionProcessor extends mariahgui.MariahFileChooser {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1899308877580175870L;

    /** The j menu file. */
    private javax.swing.JMenu jMenuFile;

    /** The j menu bar. */
    private javax.swing.JMenuBar jMenuBar;

    /** The j menu item new dynamic election. */
    private javax.swing.JMenuItem jMenuItemNewDynamicElection;

    /**
     * Instantiates a new mariah election processor.
     *
     * @param title      the title
     * @param filePrompt the file prompt
     */
    public MariahElectionProcessor(String title, String filePrompt) {
	super(title, filePrompt);

	jMenuBar = new javax.swing.JMenuBar();
	jMenuFile = new javax.swing.JMenu();
	jMenuItemNewDynamicElection = new javax.swing.JMenuItem();

	jMenuFile.setText("File");

	jMenuItemNewDynamicElection.setAccelerator(
		javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.META_MASK));
	jMenuItemNewDynamicElection.setText("New Dynamic Election");
	jMenuItemNewDynamicElection.addActionListener((java.awt.event.ActionEvent evt) -> {
	    try {
		jMenuItemNewDynamicElectionActionPerformed(evt);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	});
	jMenuFile.add(jMenuItemNewDynamicElection);

	jMenuBar.add(jMenuFile);

	setJMenuBar(jMenuBar);
    }

    /**
     * J button 1 action performed.
     *
     * @param evt the evt
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void jMenuItemNewDynamicElectionActionPerformed(java.awt.event.ActionEvent evt) throws IOException {// GEN-FIRST:event_jButton1ActionPerformed
	// TODO OPEN JFRAME OF DYNAMIC ELECTION
    }// GEN-LAST:event_jButton1ActionPerformed
}
