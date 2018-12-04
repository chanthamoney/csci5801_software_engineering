/**
 * File: MariahElectionProcessor.java
 * Date Created: 11/08/2018
 * Last Update: Dec 4, 2018 5:46:45 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */
package main;

import javax.swing.JSlider;
import javax.swing.SwingConstants;
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

    /** The slider. */
    private javax.swing.JSlider slider;

    /** The slider panel. */
    private javax.swing.JPanel sliderPanel;

    /** The slider label. */
    private javax.swing.JLabel sliderLabel;

    /** The valid ballot quotient. */
    private int validBallotQuotient;

    /**
     * Instantiates a new mariah election processor.
     *
     * @param title               the title
     * @param filePrompt          the file prompt
     * @param validBallotQuotient the valid ballot quotient
     */
    public MariahElectionProcessor(String title, String filePrompt, int validBallotQuotient) {
	super(title, filePrompt);

	jMenuBar = new javax.swing.JMenuBar();
	jMenuEdit = new javax.swing.JMenu();
	sliderLabel = new javax.swing.JLabel("IRV Valid Ballot Quotient: " + validBallotQuotient + "%");
	this.validBallotQuotient = validBallotQuotient;

	jMenuEdit.setText("Edit");

	slider = new javax.swing.JSlider(SwingConstants.HORIZONTAL, 100);
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
     * Gets the valid ballot quotient.
     *
     * @return the valid ballot quotient
     */
    public int getValidBallotQuotient() {
	return this.validBallotQuotient;
    }
}
