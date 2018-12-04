/**
 * File: MariahElectionProcessor.java
 * Date Created: 11/08/2018
 * Last Update: Nov 29, 2018 8:27:47 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */
package main;

import java.io.IOException;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

/**
 * The Class MariahElectionProcessor.
 */
public class MariahElectionProcessor extends mariahgui.MariahFileChooser {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1899308877580175870L;

    /** The j menu file. */
    private javax.swing.JMenu jMenuEdit;

    /** The j menu bar. */
    private javax.swing.JMenuBar jMenuBar;

    private javax.swing.JSlider slider;

    private javax.swing.JPanel sliderPanel;

    private javax.swing.JLabel sliderLabel;

    private int validBallotQuotient;

    /**
     * Instantiates a new mariah election processor.
     *
     * @param title      the title
     * @param filePrompt the file prompt
     */
    public MariahElectionProcessor(String title, String filePrompt, int validBallotQuotient) {
	super(title, filePrompt);

	jMenuBar = new javax.swing.JMenuBar();
	jMenuEdit = new javax.swing.JMenu();
	sliderLabel = new javax.swing.JLabel("IRV Valid Ballot Quotient: " + validBallotQuotient + "%");
	this.validBallotQuotient = validBallotQuotient;

	jMenuEdit.setText("Edit");

	slider = new javax.swing.JSlider(JSlider.HORIZONTAL, 100);
	ChangeListener cl = e -> {
	    JSlider x = (JSlider) e.getSource();
	    this.validBallotQuotient = x.getValue();
	    sliderLabel.setText("IRV Valid Ballot Quotient: " + this.validBallotQuotient + "%");
	};
	slider.addChangeListener(cl);

	sliderPanel = new javax.swing.JPanel();

	sliderPanel.add(new javax.swing.JLabel("0%"));
	sliderPanel.add(slider);
	sliderPanel.add(new javax.swing.JLabel("100%"));

	jMenuEdit.add(sliderLabel);

	jMenuEdit.add(sliderPanel);

	jMenuBar.add(jMenuEdit);

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

    public int getValidBallotQuotient() {
	return this.validBallotQuotient;
    }
}
