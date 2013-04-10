import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Foreldrevindu extends JFrame {
  private Customer aCustomer = new Customer(10000, "Arne Johansen", 12345678, "Gamle Åsvei 34C", 1);
  private CustomerRegistration registration = new CustomerRegistration(this);

  public Foreldrevindu() {
    setTitle("Dialogtest");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());
    JButton knapp = new JButton("Registrer ny kunde");
    add(knapp);
    JButton knapp2 = new JButton("Endre registrert kunde");
    add(knapp2);
    knapp.addActionListener(new KnappeLytter());
    knapp2.addActionListener(new KnappeLytter());
    setLocation(300, 300); // plasserer foreldrevinduet
    registration.setLocation(350, 350);  // plasserer dialogen
  }

  private class KnappeLytter implements ActionListener {
	  public void actionPerformed(ActionEvent event) {
		  JButton pressedButton = (JButton) event.getSource();
		  String buttonName = pressedButton.getText();
		  if(buttonName.equals("Endre registrert kunde")) {
			  if (registration.editCustomer(aCustomer)) {
				  System.out.println("OK trykket ...");
			  } else {
				  System.out.println("Avbryt trykket ...");
    		  }
    		  System.out.println(aCustomer); // bruker toString()
		  } else if(buttonName.equals("Registrer ny kunde")) {
			  String[] customer = registration.regCustomer();
			  
			  System.out.println("OK trykket ...");
		  } else {
		  	System.out.println("Avbryt trykket ...");
		  }
		  System.out.println(aCustomer); // bruker toString()
	  }
  }
  public static void main(String[] args) {
	  Foreldrevindu test = new Foreldrevindu();
	  test.setSize(300, 200);  // for å få litt størrelse på vinduet
	  test.setVisible(true);
  	}
}