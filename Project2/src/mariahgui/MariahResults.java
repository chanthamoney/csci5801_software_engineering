/**
 * File: MariahResults.java
 * Date Created: 11/08/2018
 * Last Update: Nov 26, 2018 5:29:20 PM
 * Author: <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
 * This code is copyright (c) 2018 University of Minnesota - Twin Cities
 */

package mariahgui;

import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

import javax.swing.JDialog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * The Class MariahResults.
 *
 * @author janippert
 */
public class MariahResults extends JDialog {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4550676819126664999L;

    /** The audit file. */
    private String auditFile = null;

    /** The header name. */
    private String headerName;

    /** The audit file. */
    private String invalidBallotsFile = null;

    /** The report header. */
    private String reportHeader;

    /** The text in the report to be printed. */
    private javax.swing.JTextArea reportText;

    /**
     * Creates new form MariahProcessResults.
     *
     * @param headerName         the header name
     * @param auditFile          the name of the audit file produced
     * @param invalidBallotsFile the invalid ballots file
     * @param processResults     the results of the process
     * @param reportHeader       the report header
     * @param reportText         the report text
     */
    public MariahResults(String headerName, String auditFile, String invalidBallotsFile, String processResults,
	    String[][] resultRows, String[] columnTitles, String reportHeader, String reportText) {
	super();
	this.headerName = headerName;
	setModal(true);
	initComponents();
	this.auditFile = auditFile;
	this.invalidBallotsFile = invalidBallotsFile;
	jTextPane2.setText(processResults);
	this.reportHeader = reportHeader;
	this.reportText = new javax.swing.JTextArea();
	this.reportText.setText(reportText);
	this.jTable2.setModel(new javax.swing.table.DefaultTableModel(resultRows, columnTitles));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

	jPanel1 = new javax.swing.JPanel();
	jButton1 = new javax.swing.JButton();
	jButton2 = new javax.swing.JButton();
	jButton3 = new javax.swing.JButton();
	jScrollPane4 = new javax.swing.JScrollPane();
	jTable2 = new javax.swing.JTable();
	jScrollPane5 = new javax.swing.JScrollPane();
	jTextPane2 = new javax.swing.JTextPane();

	setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

	setTitle(headerName);
	setBackground(new java.awt.Color(0, 51, 102));

	jPanel1.setBackground(new java.awt.Color(0, 51, 102));
	jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Results",
		javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
		new java.awt.Font("Lucida Grande", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N

	jButton1.setText("Open Audit File");
	jButton1.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		try {
		    jButton1ActionPerformed(evt);
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	});

	jButton2.setText("Print Report");
	jButton2.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		try {
		    jButton2ActionPerformed(evt);
		} catch (PrinterException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	});

	jButton3.setText("Invalid Ballots");
	jButton3.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		try {
		    jButton3ActionPerformed(evt);
		} catch (PrinterException | IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	});

	jTable2.setModel(
		new javax.swing.table.DefaultTableModel(
			new Object[][] { { null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null } },
			new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
	jScrollPane4.setViewportView(jTable2);

	jTextPane2.setEditable(false);
	jScrollPane5.setViewportView(jTextPane2);

	javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	jPanel1.setLayout(jPanel1Layout);
	jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(jPanel1Layout.createSequentialGroup()
			.addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addGap(20, 20, 20)
			.addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
			.addGap(20, 20, 20).addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		.addGroup(jPanel1Layout.createSequentialGroup().addGap(10, 10, 10)
			.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane5).addComponent(jScrollPane4))
			.addContainerGap()));
	jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(jPanel1Layout.createSequentialGroup().addGap(20, 20, 20)
			.addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
			.addGap(20, 20, 20)
			.addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
			.addGap(20, 20, 20)
			.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
				.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
					javax.swing.GroupLayout.PREFERRED_SIZE))));

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
     * J button 2 action performed.
     *
     * @param evt the evt
     * @throws PrinterException the printer exception
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws PrinterException {// GEN-FIRST:event_jButton2ActionPerformed
	this.reportText.print(new MessageFormat(reportHeader), new MessageFormat("Page - {0}"), true, null, null, true);
    }// GEN-LAST:event_jButton2ActionPerformed

    /**
     * J button 3 action performed.
     *
     * @param evt the evt
     * @throws PrinterException the printer exception
     * @throws IOException      Signals that an I/O exception has occurred.
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) throws PrinterException, IOException {// GEN-FIRST:event_jButton2ActionPerformed
	Desktop.getDesktop().open(new File(invalidBallotsFile));
    }// GEN-LAST:event_jButton2ActionPerformed

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
	} catch (ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(MariahResults.class.getName()).log(java.util.logging.Level.SEVERE, null,
		    ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(MariahResults.class.getName()).log(java.util.logging.Level.SEVERE, null,
		    ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(MariahResults.class.getName()).log(java.util.logging.Level.SEVERE, null,
		    ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(MariahResults.class.getName()).log(java.util.logging.Level.SEVERE, null,
		    ex);
	}
	// </editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new MariahResults("header title", "audit file", "invalid ballots file", "process results",
			new String[][] { { "A1", "B1", "C1" }, { "A2", "B2", "C2" } },
			new String[] { "Title 1", "Title 2", "Title 3" }, "Print Header", "Print example")
				.setVisible(true);
	    }
	});
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextPane jTextPane2;
}
