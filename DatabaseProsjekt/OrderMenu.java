import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import static javax.swing.JOptionPane.*;
import java.sql.*;

public class OrderMenu extends BasicDialog {
	private DefaultListModel<Dish> dishListModel = new DefaultListModel<Dish>();
	private JList<Dish> list = new JList<Dish>(dishListModel);
	private JButton newDish = new JButton("Add dish");

	public OrderMenu(JFrame parent) {
		super(parent, "Order menu");
	    add(new ListPanel(), BorderLayout.CENTER);
	    add(getButtonpanel(), BorderLayout.SOUTH);
	    add(newDish, BorderLayout.NORTH);
	    ButtonListener listener = new ButtonListener();
	    newDish.addActionListener(listener);
	    pack();
	}
	/*private class CustomerDatapanel extends JPanel {
		public CustomerDatapanel() {
			setLayout(new GridLayout(5, 1, 5, 5));
			add();
			add(new JLabel("Customer ID (automatic): ", JLabel.RIGHT));
		    add(kid);
		    kid.setEditable(false); // brukeren kan ikke endre nummeret
		    add(new JLabel("Full name:", JLabel.RIGHT));
		    add(name);
		    add(new JLabel("Phone number:", JLabel.RIGHT));
		    add(phone);
		    add(new JLabel("Adress: ", JLabel.RIGHT));
		    add(adress);
		}
	}*/
	private class ListPanel extends JPanel {
		public ListPanel() {
			setLayout(new BorderLayout());
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane listScroller = new JScrollPane(list);
			add(listScroller, BorderLayout.CENTER);
		}
	}
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String button = event.getActionCommand();
		}
	}
	public static void main(String[] args) {
		ParentWindow window = new ParentWindow();
		OrderMenu etVindu = new OrderMenu(window);
		etVindu.setVisible(true);
	}
}