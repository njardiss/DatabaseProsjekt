import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;

import javax.swing.*;

import java.security.NoSuchAlgorithmException;
import java.text.*;
import java.util.*;

public class LoginView extends BasicDialog {
	private JTextField usernameField = new JTextField(15);
	private JPasswordField passwordField = new JPasswordField(15);
	
	public LoginView(JFrame parent){
		super(parent, "Customer registration");
		add(new LoginPanel(), BorderLayout.NORTH);
		add(getButtonpanel(), BorderLayout.SOUTH);
	    pack();
	}
	private class LoginPanel extends JPanel {
		public LoginPanel() {
			setLayout(new GridLayout(2, 2, 5, 5));
			add(new JLabel("Username: ", JLabel.RIGHT));
			add(usernameField);
			add(new JLabel("Password: ", JLabel.RIGHT));
			add(passwordField);
		}
	}
	public Employee login() {
		setOk(false);
	    setVisible(true);
	    if (isOk()) {
	    	String hash = "";
	    	String username = usernameField.getText();
	    	try {
				hash = DoSHA1.SHA1(new String (passwordField.getPassword()));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	ClientMethods methods = new ClientMethods();
	    	Employee employee = null;
	    	boolean login = false;
			try {
				employee = methods.login(username, hash);
				if(employee == null) {
					throw new NullPointerException();
				} else {
					login = true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	if(login) {
	    		return employee;
	    	} else {
	    		return null;
	    	}
	    } else {
	    	return null;
	    }
	}
}
