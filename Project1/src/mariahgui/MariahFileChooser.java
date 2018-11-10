// File:         MariahFileChooser.java
// Created:      2018/11/09
// Last Changed: $Date: 2018/11/09 8:20:09 $
// Author:       <A HREF="mailto:nippe014@umn.edu">Jake Nippert</A>
//
// This code is copyright (c) 2018 University of Minnesota - Twin Cities
//

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

    /** The file name. */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.TextField fileName;

    /** The j file chooser 1. */
    private javax.swing.JFileChooser jFileChooser1;

    /** The j label 1. */
    private javax.swing.JLabel jLabel1;

    /** The j label 3. */
    private javax.swing.JLabel jLabel3;

    /** The j panel 1. */
    private javax.swing.JPanel jPanel1;

    /** The j panel 2. */
    private javax.swing.JPanel jPanel2;

    /** The j panel 3. */
    private MariahSnowFall jPanel3;

    /** The j panel 4. */
    private javax.swing.JPanel jPanel4;

    /** The j separator 1. */
    private javax.swing.JSeparator jSeparator1;

    /** The j separator 2. */
    private javax.swing.JSeparator jSeparator2;

    /** The j button 1. */
    private JButton jButton1;

    /** The manual open. */
    private javax.swing.JButton manualOpen;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form MariahGUI.
     */
    public MariahFileChooser() {
	initComponents();
	jFileChooser1.setAcceptAllFileFilterUsed(false);
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
	jFileChooser1.addChoosableFileFilter(filter);
    }

    /**
     * Gets the file name.
     *
     * @return the file name
     */
    public String getFileName() {
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

	jPanel3 = new MariahSnowFall();
	jPanel4 = new javax.swing.JPanel();
	jLabel3 = new javax.swing.JLabel();
	jButton1 = new javax.swing.JButton();
	jPanel2 = new javax.swing.JPanel();
	jSeparator1 = new javax.swing.JSeparator();
	jLabel1 = new javax.swing.JLabel();
	jFileChooser1 = new javax.swing.JFileChooser();
	jSeparator2 = new javax.swing.JSeparator();
	jPanel1 = new javax.swing.JPanel();
	manualOpen = new javax.swing.JButton();
	fileName = new java.awt.TextField();

	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	jPanel4.setBackground(new java.awt.Color(153, 0, 0));
	jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

	jLabel3.setFont(new java.awt.Font("Helvetica", 1, 36)); // NOI18N
	jLabel3.setForeground(new java.awt.Color(255, 255, 255));
	jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	jLabel3.setText("MARIAH VOTING SYSTEMS");

	jPanel2.setBackground(new java.awt.Color(153, 0, 0));
	jPanel2.setMaximumSize(new java.awt.Dimension(80, 20));
	jPanel2.setMinimumSize(new java.awt.Dimension(0, 0));

	javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
	jPanel2.setLayout(jPanel2Layout);
	jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGap(0, 62, Short.MAX_VALUE));
	jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGap(0, 30, Short.MAX_VALUE));

	jButton1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
	jButton1.setIcon(new javax.swing.ImageIcon("mariahgui/img/snowflake_icon.png")); // NOI18N
	jButton1.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		jButton1ActionPerformed(evt);
	    }
	});

	javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
	jPanel4.setLayout(jPanel4Layout);
	jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(jPanel4Layout.createSequentialGroup()
			.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addGap(0, 0, 0)
			.addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
			.addGap(18, 18, 18).addComponent(jButton1).addGap(22, 22, 22)));
	jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(jPanel4Layout.createSequentialGroup()
			.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
				.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
					javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(jButton1))
			.addGap(0, 0, 0)));

	jLabel1.setText("Please select an election file from your file system or input the file name below.");

	jFileChooser1.setCurrentDirectory(null);
	jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		jFileChooser1ActionPerformed(evt);
	    }
	});

	jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("  Manual File Name Entry  "));

	manualOpen.setActionCommand("Open");
	manualOpen.setLabel("Open");
	manualOpen.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		manualOpenActionPerformed(evt);
	    }
	});

	fileName.setForeground(new java.awt.Color(153, 153, 153));

	javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	jPanel1.setLayout(jPanel1Layout);
	jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
			jPanel1Layout.createSequentialGroup().addContainerGap()
				.addComponent(fileName, javax.swing.GroupLayout.DEFAULT_SIZE,
					javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(manualOpen)));
	jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
			.addGap(0, 9, Short.MAX_VALUE)
			.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(manualOpen).addComponent(fileName, javax.swing.GroupLayout.PREFERRED_SIZE,
					javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
			.addContainerGap()));

	javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
	jPanel3.setLayout(jPanel3Layout);
	jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
			Short.MAX_VALUE)
		.addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel3Layout.createSequentialGroup().addComponent(jLabel1).addGap(0, 0,
				Short.MAX_VALUE))
			.addGroup(jPanel3Layout.createSequentialGroup().addGroup(jPanel3Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
				.addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
					javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))
				.addContainerGap()))));
	jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(jPanel3Layout.createSequentialGroup()
			.addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addGap(0, 0, 0).addComponent(jLabel1).addGap(0, 0, 0)
			.addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
				javax.swing.GroupLayout.PREFERRED_SIZE)
			.addGap(0, 0, 0)
			.addComponent(jFileChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
			.addGap(0, 0, 0)
			.addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
				javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addGap(0, 0, 0)));

	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	getContentPane().setLayout(layout);
	layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
		jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
		jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

	pack();
    }// </editor-fold>

    /**
     * J file chooser 1 action performed.
     *
     * @param evt the evt
     */
    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jFileChooser1ActionPerformed
	JFileChooser theFileChooser = (JFileChooser) evt.getSource();
	String command = evt.getActionCommand();
	if (command.equals(JFileChooser.APPROVE_SELECTION)) {
	    File selectedFile = theFileChooser.getSelectedFile();
	    int index = selectedFile.getAbsolutePath().lastIndexOf("\\");
	    this.filePath = selectedFile.getAbsolutePath().substring(index + 1);
	} else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
	    File workingDirectory = new File(System.getProperty("user.dir"));
	    jFileChooser1.setCurrentDirectory(workingDirectory);
	}
    }// GEN-LAST:event_jFileChooser1ActionPerformed

    /**
     * Manual open action performed.
     *
     * @param evt the evt
     */
    private void manualOpenActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_manualOpenActionPerformed
	if (fileName.getText().isEmpty()) {
	    JOptionPane.showMessageDialog(null, "Please manually enter the file name.");
	    return;
	}
	File myfile = new File(fileName.getText().trim());
	if (!myfile.isFile()) {
	    JOptionPane.showMessageDialog(null, "Please enter a valid file name.");
	} else {
	    this.filePath = fileName.getText().trim();
	    ;
	}
    }// GEN-LAST:event_manualOpenActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
	jPanel3.snow();
	jButton1.setVisible(false);
	jPanel2.setMaximumSize(new java.awt.Dimension(60, 20));
	jPanel2.setPreferredSize(new java.awt.Dimension(60, 20));
	jPanel2.setSize((new java.awt.Dimension(60, 20)));
    }

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
	    java.util.logging.Logger.getLogger(MariahFileChooser.class.getName()).log(java.util.logging.Level.SEVERE,
		    null, ex);
	}
	// </editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(() -> new MariahFileChooser().setVisible(true));
    }
}
