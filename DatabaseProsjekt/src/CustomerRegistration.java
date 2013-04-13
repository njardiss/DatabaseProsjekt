import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import static javax.swing.JOptionPane.*;

public class CustomerRegistration extends BasicDialog {
	private JTextField kid = new JTextField();
	private JTextField name = new JTextField();
	private JTextField phone = new JTextField();
	private JTextField adress = new JTextField();
	private JTextField type = new JTextField();

	public CustomerRegistration(JFrame parent) {
		super(parent, "Customer registration");
		add(new JPanel(), BorderLayout.NORTH);  // litt "luft"
	    add(new CustomerDatapanel(), BorderLayout.CENTER);
	    add(getButtonpanel(), BorderLayout.SOUTH);
	    pack();
	}
	private class CustomerDatapanel extends JPanel {
		public CustomerDatapanel() {
			setLayout(new GridLayout(7, 2, 5, 5));
			add(new JLabel("Kundenummer (tildeles automatisk): ", JLabel.RIGHT));
		    add(kid);
		    kid.setEditable(false); // brukeren kan ikke endre nummeret
		    add(new JLabel("Full name:", JLabel.RIGHT));
		    add(name);
		    add(new JLabel("Phone number:", JLabel.RIGHT));
		    add(phone);
		    add(new JLabel("Adress: ", JLabel.RIGHT));
		    add(adress);
		    add(new JLabel("Customer type:", JLabel.RIGHT));
		    add(type);
		}
	}
	/**
	   * Metode som viser et dialogvindu med data fra argumentet personen.
	   * Hvis personnr er negativt, betyr det at nummer ikke er definert.
	   * Brukeren kan editere i feltene for for- og etternavn.
	   */
	public boolean editCustomer(Customer customer2) {
		type.setEditable(false); // brukeren kan ikke endre nummeret
		String tekstNrFelt = (customer2.getKid() < 0)
	                                  ? "ikke registrert" : "" + customer2.getKid();
	    kid.setText(tekstNrFelt);
	    name.setText(customer2.getName());
	    phone.setText(Integer.toString(customer2.getPhone()));
	    adress.setText(customer2.getAdress());
	    type.setText(Integer.toString(customer2.getType()));
	    setOk(false);
	    pack();
	    name.requestFocusInWindow();
	    setVisible(true);
	    if (isOk()) {
	      customer2.setName(name.getText());
	      customer2.setPhone(Integer.parseInt(phone.getText()));
	      customer2.setAdress(adress.getText());
	      return true;
	    } else {
	      return false;
	    }
	}
	public String[] regCustomer() {
		type.setEditable(true);
	    setOk(false);
	    String[] customer = new String [4];
	    pack();
	    name.requestFocusInWindow();
	    setVisible(true);
	    if (isOk()) {
	    	customer[0] = name.getText();
	    	customer[1] = phone.getText();
	    	customer[2] = adress.getText();
	    	customer[3] = type.getText();
	    	for(int i =0; i < customer.length; i++) {
				  System.out.println(customer[i]);
			  }
	    	return customer;
	    } else {
	    	return null;
	    }
	}
	protected boolean okData() {
		String newName = name.getText().trim();
	    if (name.equals("")) {
	    		showMessageDialog(CustomerRegistration.this, "B�de for- og etternavn m� fylles ut!");
	    	if (!name.equals("")) {
	    		name.requestFocusInWindow();
	    	} else {
	    	}
	    	return false; // data er ikke ok (for- og/eller etternavn er blankt)
	    } else {
	    	return true;  // data er ok
	    }
	}
}