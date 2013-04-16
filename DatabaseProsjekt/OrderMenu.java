import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.ArrayList;
import java.sql.*;

import static javax.swing.JOptionPane.*;
import java.sql.*;

public class OrderMenu extends BasicDialog {
	private DefaultListModel<Dish> dishListModel = new DefaultListModel<Dish>();
	private JList<Dish> list = new JList<Dish>(dishListModel);
	private JButton newDish = new JButton("Add dish");
	private JFrame parent;
	private ArrayList<Dish> dish = new ArrayList<Dish>();
	Connection connection;

	public OrderMenu(JFrame parent, Connection connection) {
		super(parent, "Order menu");
	    add(new ListPanel(), BorderLayout.CENTER);
	    add(getButtonpanel(), BorderLayout.SOUTH);
	    add(new newDish(), BorderLayout.NORTH);
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
	private class newDish extends JPanel {
		public newDish() {
			setLayout(new BorderLayout());
			newDish.setPreferredSize(new Dimension(30, 30));
			add(new JLabel(" "), BorderLayout.NORTH);
			add(newDish, BorderLayout.CENTER);
			ButtonListener listener = new ButtonListener();
			newDish.addActionListener(listener);
		}
	}
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
			DishChooser dishChooser = new DishChooser(parent);
			Dish newDish = dishChooser.getDish(connection);
			dishListModel.addElement(newDish);
			list.clearSelection();
		}
	}
}