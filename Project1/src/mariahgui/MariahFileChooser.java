/**
 * File: MariahFileChooser.java
 * Date Created: 11/08/2018
 * Last Update: Nov 12, 2018 12:36:34 AM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

package mariahgui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The GUI component which prompts the user to select an election file.
 *
 * @author janippert
 */
public class MariahFileChooser extends javax.swing.JFrame {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1703036202595575064L;

    /** The file path. */
    private String filePath = null;

    /** The file path. */
    private String title = null;

    /** The file path. */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.TextField filePathInput;

    /** The main file chooser 1. */
    private javax.swing.JFileChooser jFileChooser1;

    /** The label to select a file. */
    private javax.swing.JLabel fcLabel;

    /** The title / header of the frame. */
    private javax.swing.JLabel headerLabel;

    /** The panel for manual file entry. */
    private javax.swing.JPanel manualFileEntryPanel;

    /** The panel used to keep space balanced in header. */
    private javax.swing.JPanel headerSpacePanel;

    /** The main panel of the frame. */
    private MariahSnowFallPanel mainPanel;

    /** The panel for the title / header. */
    private javax.swing.JPanel headerPanel;

    /** The j separator 1. */
    private javax.swing.JSeparator jSeparator1;

    /** The j separator 2. */
    private javax.swing.JSeparator jSeparator2;

    /** A button which makes it snow on the panel. */
    private JButton snowButton;

    /** The manual open button. */
    private javax.swing.JButton manualOpenButton;

    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form MariahGUI.
     *
     * @param title the title to place in the header
     */
    public MariahFileChooser(String title) {
	this.title = title;
	initComponents();
	jFileChooser1.setAcceptAllFileFilterUsed(false);
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
	jFileChooser1.addChoosableFileFilter(filter);
    }

    /**
     * Gets the file path.
     *
     * @return the file path
     */
    public String getFilePath() {
	return filePath;
    }

    /**
     * Netbeans generated and adapted Swing Jframe initialization.
     */
    @SuppressWarnings("deprecation")
    private void initComponents() {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = (int) (screenSize.height * 0.667);
	int width = (int) (screenSize.width * 0.667);
	setSize(new Dimension(width, height));
	setPreferredSize(new Dimension(width, height));
	this.setLocation(screenSize.width / 2 - this.getSize().width / 2,
		screenSize.height / 2 - this.getSize().height / 2);

	mainPanel = new MariahSnowFallPanel();
	headerPanel = new javax.swing.JPanel();
	headerLabel = new javax.swing.JLabel();
	snowButton = new javax.swing.JButton();
	headerSpacePanel = new javax.swing.JPanel();
	jSeparator1 = new javax.swing.JSeparator();
	fcLabel = new javax.swing.JLabel();
	jFileChooser1 = new javax.swing.JFileChooser();
	jSeparator2 = new javax.swing.JSeparator();
	manualFileEntryPanel = new javax.swing.JPanel();
	manualOpenButton = new javax.swing.JButton();
	filePathInput = new java.awt.TextField();

	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	headerPanel.setBackground(new java.awt.Color(153, 0, 0));
	headerPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

	headerLabel.setFont(new java.awt.Font("Helvetica", 1, 32));
	headerLabel.setForeground(new java.awt.Color(255, 255, 255));
	headerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	headerLabel.setText(this.title);

	headerSpacePanel.setBackground(new java.awt.Color(153, 0, 0));
	headerSpacePanel.setMaximumSize(new java.awt.Dimension(80, 20));
	headerSpacePanel.setMinimumSize(new java.awt.Dimension(0, 0));

	javax.swing.GroupLayout headerSpacePanelLayout = new javax.swing.GroupLayout(headerSpacePanel);
	headerSpacePanel.setLayout(headerSpacePanelLayout);
	headerSpacePanelLayout.setHorizontalGroup(headerSpacePanelLayout
		.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 62, Short.MAX_VALUE));
	headerSpacePanelLayout.setVerticalGroup(headerSpacePanelLayout
		.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 30, Short.MAX_VALUE));

	snowButton.setFont(new java.awt.Font("Lucida Grande", 0, 24));
	snowButton.setIcon(new javax.swing.ImageIcon("img/snowflake_icon.png", "snow flake"));
	snowButton.addActionListener(this::snowButtonActionPerformed);

	javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
	headerPanel.setLayout(headerPanelLayout);
	headerPanelLayout
		.setHorizontalGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(headerPanelLayout.createSequentialGroup()
				.addComponent(headerSpacePanel, javax.swing.GroupLayout.PREFERRED_SIZE,
					javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(0, 0, 0)
				.addComponent(headerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
				.addGap(20, 20, 20).addComponent(snowButton).addGap(22, 22, 22)));
	headerPanelLayout
		.setVerticalGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
				.addComponent(headerSpacePanel, javax.swing.GroupLayout.PREFERRED_SIZE,
					javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(headerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
					javax.swing.GroupLayout.PREFERRED_SIZE))
			.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
				.addContainerGap().addComponent(snowButton).addContainerGap()));

	fcLabel.setText("Please select an election file from your file system or input the file path below.");
	fcLabel.setOpaque(true);
	fcLabel.setBackground(getBackground());

	jFileChooser1.setCurrentDirectory(new File(System.getProperty("user.dir")));
	jFileChooser1.addActionListener(this::jFileChooser1ActionPerformed);

	manualFileEntryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("  Manual File Path Entry  "));

	manualOpenButton.setActionCommand("Open");
	manualOpenButton.setLabel("Open");
	manualOpenButton.addActionListener(this::manualOpenButtonActionPerformed);

	filePathInput.setForeground(new java.awt.Color(153, 153, 153));

	javax.swing.GroupLayout manualFileEntryPanelLayout = new javax.swing.GroupLayout(manualFileEntryPanel);
	manualFileEntryPanel.setLayout(manualFileEntryPanelLayout);
	manualFileEntryPanelLayout.setHorizontalGroup(
		manualFileEntryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
			javax.swing.GroupLayout.Alignment.TRAILING,
			manualFileEntryPanelLayout.createSequentialGroup().addContainerGap()
				.addComponent(filePathInput, javax.swing.GroupLayout.DEFAULT_SIZE,
					javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(manualOpenButton)));
	manualFileEntryPanelLayout.setVerticalGroup(manualFileEntryPanelLayout
		.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manualFileEntryPanelLayout.createSequentialGroup()
			.addGap(0, 9, Short.MAX_VALUE)
			.addGroup(manualFileEntryPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(manualOpenButton).addComponent(filePathInput,
					javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
					javax.swing.GroupLayout.PREFERRED_SIZE))
			.addContainerGap()));

	javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
	mainPanel.setLayout(mainPanelLayout);
	mainPanelLayout.setHorizontalGroup(mainPanelLayout
		.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
			Short.MAX_VALUE)
		.addGroup(mainPanelLayout.createSequentialGroup().addContainerGap().addGroup(mainPanelLayout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(mainPanelLayout.createSequentialGroup().addComponent(fcLabel).addGap(0, 0,
				Short.MAX_VALUE))
			.addGroup(mainPanelLayout.createSequentialGroup().addGroup(mainPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
				.addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
				.addComponent(manualFileEntryPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
					javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))
				.addContainerGap()))));
	mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(mainPanelLayout.createSequentialGroup()
			.addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addGap(0, 0, 0).addComponent(fcLabel).addGap(0, 0, 0)
			.addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
				javax.swing.GroupLayout.PREFERRED_SIZE)
			.addGap(0, 0, 0)
			.addComponent(jFileChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
			.addGap(0, 0, 0)
			.addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
				javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(manualFileEntryPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addGap(0, 0, 0)));

	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	getContentPane().setLayout(layout);
	layout.setHorizontalGroup(
		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(mainPanel,
			javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	layout.setVerticalGroup(
		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(mainPanel,
			javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

	pack();
    }// </editor-fold>

    /**
     * Determines if user selected to cancel or open file. Replaces internal file
     * path with file if opened. Sends them to current directly when user presses
     * cancel.
     *
     * @param evt action performed on file chooser
     */
    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jFileChooser1ActionPerformed
	JFileChooser theFileChooser = (JFileChooser) evt.getSource();
	String command = evt.getActionCommand();
	if (command.equals(JFileChooser.APPROVE_SELECTION)) {
	    File selectedFile = theFileChooser.getSelectedFile();
	    this.filePath = selectedFile.getAbsolutePath();
	} else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
	    File workingDirectory = new File(System.getProperty("user.dir"));
	    jFileChooser1.setCurrentDirectory(workingDirectory);
	}
    }// GEN-LAST:event_jFileChooser1ActionPerformed

    /**
     * Determines if manually entered file has text and is a file. If the file
     * exists, replaces internal file path with value, otherwise shows a dialog
     * error message.
     *
     * @param evt open button selected for manual file entry
     */
    private void manualOpenButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_manualOpenButtonActionPerformed
	if (filePathInput.getText().isEmpty()) {
	    JOptionPane.showMessageDialog(null, "Please manually enter the file path.");
	    return;
	}
	File myfile = new File(filePathInput.getText().trim());
	if (!myfile.isFile()) {
	    JOptionPane.showMessageDialog(null, "Please enter a valid file path.");
	} else {
	    this.filePath = filePathInput.getText().trim();
	}
    }// GEN-LAST:event_manualOpenButtonActionPerformed

    /**
     * Generates snow after the snow button is clicked.
     *
     * @param evt click on the snow button
     */
    private void snowButtonActionPerformed(java.awt.event.ActionEvent evt) {
	mainPanel.snow();
	snowButton.setVisible(false);
    }

    /**
     * An example of the file chooser.
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
	/* Set the Nimbus look and feel */
	// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
	// (optional) ">
	/*
	 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
	 * look and feel. For details see
	 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
	 */
	try {
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	} catch (javax.swing.UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException
		| ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(MariahFileChooser.class.getName()).log(java.util.logging.Level.SEVERE,
		    null, ex);
	}
	// </editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(() -> new MariahFileChooser("Title Here").setVisible(true));
    }
}
