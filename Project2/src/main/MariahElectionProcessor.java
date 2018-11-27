package main;

import java.io.IOException;

public class MariahElectionProcessor extends mariahgui.MariahFileChooser {

    private static final long serialVersionUID = 1899308877580175870L;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;

    public MariahElectionProcessor(String title, String filePrompt) {
	super(title, filePrompt);

	jMenuBar1 = new javax.swing.JMenuBar();
	jMenu1 = new javax.swing.JMenu();
	jMenuItem3 = new javax.swing.JMenuItem();

	jMenu1.setText("File");

	jMenuItem3.setAccelerator(
		javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.META_MASK));
	jMenuItem3.setText("New Dynamic Election");
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
	jMenu1.add(jMenuItem3);

	jMenuBar1.add(jMenu1);

	setJMenuBar(jMenuBar1);
    }

    /**
     * J button 1 action performed.
     *
     * @param evt the evt
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {// GEN-FIRST:event_jButton1ActionPerformed
	// TODO OPEN JFRAME OF DYNAMIC ELECTION
    }// GEN-LAST:event_jButton1ActionPerformed
}