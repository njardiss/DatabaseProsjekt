import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.text.*;
import java.util.ArrayList;
import static javax.swing.JOptionPane.*;

public class OrderMenu extends BasicDialog {
	private DefaultListModel<Dish> dishListModel = new DefaultListModel<Dish>();
	private JList<Dish> list = new JList<Dish>(dishListModel);
	private JButton newDish = new JButton("Add dish");
	private JFrame parent;
	private ArrayList<Dish> dish = new ArrayList<Dish>();
	private JTextField deliveryAdress = new JTextField();
	private JTextField date = new JTextField(10);
	private JTextField hour = new JTextField(2);
	private JTextField minutes = new JTextField(2);
	
	public OrderMenu(JFrame parent) {
		super(parent, "Order menu");
	    add(new ListPanel(), BorderLayout.CENTER);
	    add(getButtonpanel(), BorderLayout.SOUTH);
	    add(new newDish(), BorderLayout.NORTH);
	    pack();
	}
	private class CustomerDatapanel extends JPanel {
		public CustomerDatapanel() {
			setLayout(new GridLayout(5, 6, 5, 5));
			add(new JLabel("Delivery date (dd.mm.yyyy): ", JLabel.RIGHT));
		    add(date);
		    add(new JLabel("Time:", JLabel.RIGHT));
		    add(hour);
		    add(new JLabel(":", JLabel.RIGHT));
		    add(minutes);
		    add(new JLabel("Delivery adress: ", JLabel.RIGHT));
		    add(deliveryAdress);
		}
	}
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
			ArrayList <Dish> newDish = dishChooser.getDish();
			for(Dish aDish : newDish) {
				dishListModel.addElement(aDish);
			}
			list.clearSelection();
		}
	}
	public Order getOrder() {
		setOk(false);
		pack();
	    setVisible(true);
		Order order;
	    if (isOk()) {
	    	String deliveryTime = "";
	    	order = new Order(1, 1, "Registered", "Placeholder", deliveryTime, deliveryAdress.getText(), dish); //
	    	return order;
	    } else {
	    	return  null;
	    }
	}
}