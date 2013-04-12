import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ParentWindow extends JFrame {
  public ParentWindow() {
    setTitle("Parent window");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(false);
  }
}
class TestPersonDialog {
	static public void main(String[] args) {
		ParentWindow parent = new ParentWindow();
		CustomerRegistration registration = new CustomerRegistration(parent);
		String[] ans = new String[4];
		ans = registration.regCustomer();
		for(int i = 0; i< ans.length; i++) {
			System.out.println(ans[i]);
		}
		System.exit(0);
	}
}