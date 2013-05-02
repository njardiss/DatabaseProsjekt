import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JOptionPane.*;

public class BasicDialog extends JDialog {
	private boolean ok = false;
	private JButton okButton  = new JButton("OK");
	private ButtonPanel buttonpanel = new ButtonPanel();

	protected BasicDialog(JFrame parent, String title) {
		super(parent, title, true);
		addWindowListener(new WindowListener());

		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		JRootPane pane = getRootPane();
    	pane.setDefaultButton(okButton);
    }
	protected boolean isOk() {
		return ok;
	}
	protected void setOk(boolean verdi) {
		ok = verdi;
	}
	protected JPanel getButtonpanel() { //to be placed in another window
		return buttonpanel;
	}
	protected boolean okData() { //can be replaced by subclasses to control data
		return true;
	}
	//panel for the two buttons
	private class ButtonPanel extends JPanel {
		public ButtonPanel() {
			JButton cancelButton = new JButton("Cancel");
			ButtonListener buttonListener = new ButtonListener();
			add(okButton);
			add(cancelButton);
			okButton.addActionListener(buttonListener);
			cancelButton.addActionListener(buttonListener);
			KeyStroke escapeButton = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
			InputMap inputMap = cancelButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
			inputMap.put(escapeButton, "Cancel");
			ActionMap actionMap = cancelButton.getActionMap();
			actionMap.put("Cancel", buttonListener);
		}
	}

	//programs the OK and Cancel button
	private class ButtonListener extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			String button = event.getActionCommand();
			if (button.equals("OK")){
				if (okData()) {  //checks if data is acceptable
					ok = true;
					setVisible(false);
				}
			} else {  //Cancel button
				ok = false;
				setVisible(false);
			}
		}
	}

	//programs what happens when user press exit window
	private class WindowListener extends WindowAdapter {
		public void windowClosing(WindowEvent event) {
			int answer = showConfirmDialog(BasicDialog.this,
                 "Do you want to exit? ",
                 "Exiting", YES_NO_OPTION);
			if (answer == YES_OPTION) {
				setVisible(false);
			} else {
				ok = false;
				setVisible(false);
			}
		}
	}
}