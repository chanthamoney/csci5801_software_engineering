/**
 * File: MariahProcessResults.java
 * Date Created: 11/08/2018
 * Last Update: Nov 13, 2018 6:47:23 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

package mariahgui;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.JDialog;

/**
 * The GUI component for showing the results of an audited process. Shows the
 * output results as well as a button to open the output audit file.
 *
 * @author janippert
 */
public class MariahProcessResults extends JDialog {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7573327634981945907L;

    /** The audit file. */
    private String auditFile = null;

    /** The j button 1. */
    private javax.swing.JButton jButton1;

    /** The j panel 1. */
    private javax.swing.JPanel jPanel1;

    /** The j scroll pane 2. */
    private javax.swing.JScrollPane jScrollPane2;

    /** The j text area 1. */
    private javax.swing.JTextArea jTextArea1;

    /** The header name. */
    private String headerName;

    /**
     * Creates new form MariahProcessResults.
     *
     * @param headerName     the header name
     * @param auditFile      the name of the audit file produced
     * @param processResults the results of the process
     */
    public MariahProcessResults(String headerName, String auditFile, String processResults) {
	super();
	this.headerName = headerName;
	setModal(true);
	initComponents();
	this.auditFile = auditFile;
	jTextArea1.setText(processResults);
    }

    /**
     * Creates new form MariahProcessResults.
     */
    public MariahProcessResults() {
	initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = (int) (screenSize.height * 0.333);
	int width = (int) (screenSize.width * 0.333);
	setSize(new Dimension(width, height));
	setPreferredSize(new Dimension(width, height));
	this.setLocation(screenSize.width / 2 - this.getSize().width / 2,
		screenSize.height / 2 - this.getSize().height / 2);

	jPanel1 = new javax.swing.JPanel();
	jScrollPane2 = new javax.swing.JScrollPane();
	jTextArea1 = new javax.swing.JTextArea();
	jButton1 = new javax.swing.JButton();

	setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	setTitle(headerName);

	jPanel1.setBackground(new java.awt.Color(0, 51, 102));
	jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, headerName,
		javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
		new java.awt.Font("Helvetica", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

	jTextArea1.setEditable(false);
	jTextArea1.setColumns(20);
	jTextArea1.setFont(new java.awt.Font("Helvetica", 0, 13)); // NOI18N
	jTextArea1.setLineWrap(true);
	jTextArea1.setRows(5);
	jScrollPane2.setViewportView(jTextArea1);
	jTextArea1.getAccessibleContext().setAccessibleName("");
	jTextArea1.getAccessibleContext().setAccessibleDescription("");

	jButton1.setText("Open Audit File");
	jButton1.addActionListener((java.awt.event.ActionEvent evt) -> {
	    try {
		jButton1ActionPerformed(evt);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	});

	javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	jPanel1.setLayout(jPanel1Layout);
	jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
		.addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
			Short.MAX_VALUE));
	jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
			.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButton1,
				javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)));

	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	getContentPane().setLayout(layout);
	layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
		jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
		jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

	pack();
    }// </editor-fold>

    /**
     * J button 1 action performed.
     *
     * @param evt the evt
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {// GEN-FIRST:event_jButton1ActionPerformed
	Desktop.getDesktop().open(new File(auditFile));
    }// GEN-LAST:event_jButton1ActionPerformed

    /**
     * The main method.
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
	    java.util.logging.Logger.getLogger(MariahProcessResults.class.getName()).log(java.util.logging.Level.SEVERE,
		    null, ex);
	}
	// </editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(() -> new MariahProcessResults().setVisible(true));
    }
}
