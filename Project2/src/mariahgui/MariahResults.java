/**
 * File: MariahResults.java
 * Date Created: 11/08/2018
 * Last Update: Dec 4, 2018 5:47:02 PM
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

/**
 * The Class MariahResults.
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

    /** The j menu file. */
    private javax.swing.JMenu jMenuFile;

    /** The j menu open. */
    private javax.swing.JMenu jMenuOpen;

    /** The j menu bar. */
    private javax.swing.JMenuBar jMenuBar;

    /** The j menu item print report. */
    private javax.swing.JMenuItem jMenuItemPrintReport;

    /** The j menu item audit file. */
    private javax.swing.JMenuItem jMenuItemAuditFile;

    /** The j panel. */
    private javax.swing.JPanel jPanel;

    /** The j scroll pane table. */
    private javax.swing.JScrollPane jScrollPaneTable;

    /** The j scroll pane results. */
    private javax.swing.JScrollPane jScrollPaneResults;

    /** The j table. */
    private javax.swing.JTable jTable;

    /** The j text pane. */
    private javax.swing.JTextPane jTextPane;

    /**
     * Creates new form MariahProcessResults.
     *
     * @param headerName         the header name
     * @param auditFile          the name of the audit file produced
     * @param otherOpenFileNames the other open file names
     * @param otherOpenFiles     the other open files
     * @param processResults     the results of the process
     * @param resultData         the result data
     * @param columnTitles       the column titles
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
	jTextPane.setText(processResults);
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
	this.jTable.setModel(tableModel);
	this.jTable.getTableHeader().setReorderingAllowed(false);

	// add other open files
	for (int i = 0; i < otherOpenFiles.length; i++) {
	    javax.swing.JMenuItem tempMenuItem = new javax.swing.JMenuItem();
	    tempMenuItem.setText(otherOpenFileNames[i]);
	    String fileName = otherOpenFiles[i];
	    tempMenuItem.addActionListener((java.awt.event.ActionEvent evt) -> {
		try {
		    Desktop.getDesktop().open(new File(fileName));
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    });
	    jMenuOpen.add(tempMenuItem);
	}

	pack();
    }

    /**
     * Inits the components.
     */
    private void initComponents() {

	jPanel = new javax.swing.JPanel();
	jScrollPaneTable = new javax.swing.JScrollPane();
	jTable = new javax.swing.JTable();
	jScrollPaneResults = new javax.swing.JScrollPane();
	jTextPane = new javax.swing.JTextPane();
	jMenuBar = new javax.swing.JMenuBar();
	jMenuFile = new javax.swing.JMenu();
	jMenuOpen = new javax.swing.JMenu();
	jMenuItemAuditFile = new javax.swing.JMenuItem();
	jMenuItemPrintReport = new javax.swing.JMenuItem();
	setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

	setTitle(headerName);
	setBackground(new java.awt.Color(0, 51, 102));

	jPanel.setBackground(new java.awt.Color(0, 51, 102));
	jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Results",
		javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
		new java.awt.Font("Lucida Grande", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N

	jTable.setModel(
		new javax.swing.table.DefaultTableModel(
			new Object[][] { { null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null } },
			new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
	jScrollPaneTable.setViewportView(jTable);

	jTextPane.setEditable(false);
	jScrollPaneResults.setViewportView(jTextPane);

	javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
	jPanel.setLayout(jPanelLayout);
	jPanelLayout.setHorizontalGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(jPanelLayout.createSequentialGroup().addGap(10, 10, 10)
			.addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanelLayout.createSequentialGroup()
					.addComponent(jScrollPaneTable, javax.swing.GroupLayout.DEFAULT_SIZE, 496,
						Short.MAX_VALUE)
					.addGap(10, 10, 10))
				.addGroup(jPanelLayout.createSequentialGroup().addComponent(jScrollPaneResults)
					.addContainerGap()))));
	jPanelLayout.setVerticalGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(jPanelLayout.createSequentialGroup().addContainerGap()
			.addComponent(jScrollPaneResults, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
			.addGap(20, 20, 20)
			.addComponent(jScrollPaneTable, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
			.addGap(10, 10, 10)));

	jMenuFile.setText("File");

	jMenuOpen.setText("Open");

	jMenuItemAuditFile.setAccelerator(
		javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.META_MASK));
	jMenuItemAuditFile.setText("Audit File");
	jMenuItemAuditFile.addActionListener((java.awt.event.ActionEvent evt) -> {
	    try {
		jMenuItemAuditFileActionPerformed(evt);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	});
	jMenuOpen.add(jMenuItemAuditFile);

	jMenuFile.add(jMenuOpen);

	jMenuItemPrintReport.setAccelerator(
		javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.META_MASK));
	jMenuItemPrintReport.setText("Print Report...");
	jMenuItemPrintReport.addActionListener((java.awt.event.ActionEvent evt) -> {
	    try {
		jMenuItemPrintReportActionPerformed(evt);
	    } catch (PrinterException e) {
		e.printStackTrace();
	    }
	});
	jMenuFile.add(jMenuItemPrintReport);

	jMenuBar.add(jMenuFile);

	setJMenuBar(jMenuBar);

	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	getContentPane().setLayout(layout);
	layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
		jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
		jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

	pack();
    }// </editor-fold>

    /**
     * Open the audit file using the user's OS default for handling text files.
     *
     * @param evt the evt
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void jMenuItemAuditFileActionPerformed(java.awt.event.ActionEvent evt) throws IOException {// GEN-FIRST:event_jButton1ActionPerformed
	Desktop.getDesktop().open(new File(auditFile));
    }// GEN-LAST:event_jButton1ActionPerformed

    /**
     * Print the quick print summary using the SWING default printing using the
     * print dialog.
     *
     * @param evt the evt
     * @throws PrinterException the printer exception
     */
    private void jMenuItemPrintReportActionPerformed(java.awt.event.ActionEvent evt) throws PrinterException {// GEN-FIRST:event_jButton2ActionPerformed
	this.reportText.print(new MessageFormat(reportHeader), new MessageFormat("Page - {0}"), true, null, null, true);
    }// GEN-LAST:event_jButton2ActionPerformed

    /**
     * An example of the mariah results.
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
	try {
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	} catch (javax.swing.UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException
		| ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(MariahResults.class.getName()).log(java.util.logging.Level.SEVERE, null,
		    ex);
	}
	// </editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(() -> new MariahResults("header title", "audit file",
		new String[] { "invalid data file" }, new String[] { "invalid data file path" }, "process results",
		new String[][] { { "A1", "B1", "C1" }, { "A2", "B2", "C2" } },
		new String[] { "Title 1", "Title 2", "Title 3" }, "Print Header", "Print example").setVisible(true));
    }
}
