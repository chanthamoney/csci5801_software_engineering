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
    public MariahResults(String headerName, String auditFile, String[] otherOpenFileNames, String[] otherOpenFiles,
	    String processResults, String[][] resultData, String[] columnTitles, String reportHeader,
	    String reportText) {
	super();
	this.headerName = headerName;
	setModal(true);
	initComponents();
	this.auditFile = auditFile;
	jTextPane2.setText(processResults);
	this.reportHeader = reportHeader;
	this.reportText = new javax.swing.JTextArea();
	this.reportText.setText(reportText);

	javax.swing.table.DefaultTableModel tableModel = new javax.swing.table.DefaultTableModel(resultData,
		columnTitles) {
	    private static final long serialVersionUID = -4502218943713256620L;

	    @Override
	    public boolean isCellEditable(int row, int column) {
		// all cells false
		return false;
	    }
	};
	this.jTable2.setModel(tableModel);
	this.jTable2.getTableHeader().setReorderingAllowed(false);

	// add other open files
	for (int i = 0; i < otherOpenFiles.length; i++) {
	    javax.swing.JMenuItem tempMenuItem = new javax.swing.JMenuItem();
	    tempMenuItem.setText(otherOpenFileNames[i]);
	    String fileName = otherOpenFiles[i];
	    tempMenuItem.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
		    try {
			Desktop.getDesktop().open(new File(fileName));
		    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}
	    });
	    jMenu2.add(tempMenuItem);
	}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

	jPanel1 = new javax.swing.JPanel();
	jScrollPane4 = new javax.swing.JScrollPane();
	jTable2 = new javax.swing.JTable();
	jScrollPane5 = new javax.swing.JScrollPane();
	jTextPane2 = new javax.swing.JTextPane();
	jMenuBar1 = new javax.swing.JMenuBar();
	jMenu1 = new javax.swing.JMenu();
	jMenu2 = new javax.swing.JMenu();
	jMenuItem3 = new javax.swing.JMenuItem();
	jMenuItem1 = new javax.swing.JMenuItem();
	setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

	setTitle(headerName);
	setBackground(new java.awt.Color(0, 51, 102));

	jPanel1.setBackground(new java.awt.Color(0, 51, 102));
	jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Results",
		javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
		new java.awt.Font("Lucida Grande", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N

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
		.addGroup(jPanel1Layout.createSequentialGroup().addGap(10, 10, 10)
			.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 496,
						Short.MAX_VALUE)
					.addGap(10, 10, 10))
				.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jScrollPane5)
					.addContainerGap()))));
	jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
			.addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
			.addGap(20, 20, 20)
			.addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
			.addGap(10, 10, 10)));

	jMenu1.setText("File");

	jMenu2.setText("Open");

	jMenuItem3.setAccelerator(
		javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.META_MASK));
	jMenuItem3.setText("Audit File");
	jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		try {
		    jMenuItem3ActionPerformed(evt);
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	});
	jMenu2.add(jMenuItem3);

	jMenu1.add(jMenu2);

	jMenuItem1.setAccelerator(
		javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.META_MASK));
	jMenuItem1.setText("Print Report...");
	jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		try {
		    jMenuItem1ActionPerformed(evt);
		} catch (PrinterException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	});
	jMenu1.add(jMenuItem1);

	jMenuBar1.add(jMenu1);

	setJMenuBar(jMenuBar1);

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
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {// GEN-FIRST:event_jButton1ActionPerformed
	Desktop.getDesktop().open(new File(auditFile));
    }// GEN-LAST:event_jButton1ActionPerformed

    /**
     * J button 2 action performed.
     *
     * @param evt the evt
     * @throws PrinterException the printer exception
     */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) throws PrinterException {// GEN-FIRST:event_jButton2ActionPerformed
	this.reportText.print(new MessageFormat(reportHeader), new MessageFormat("Page - {0}"), true, null, null, true);
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
		new MariahResults("header title", "audit file", new String[] { "invalid ballots file path" },
			new String[] { "invalid ballots file" }, "process results",
			new String[][] { { "A1", "B1", "C1" }, { "A2", "B2", "C2" } },
			new String[] { "Title 1", "Title 2", "Title 3" }, "Print Header", "Print example")
				.setVisible(true);
	    }
	});
    }

    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextPane jTextPane2;
}
