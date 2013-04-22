import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.ArrayList;

public class OrderMenu extends BasicDialog {
	private DefaultListModel<Dish> dishListModel = new DefaultListModel<Dish>();
	private JList<Dish> list = new JList<Dish>(dishListModel);
	private JButton newDish = new JButton("Add dish");
	private JFrame parent;
	private ArrayList<Dish> dish = new ArrayList<Dish>();
	private JTextField deliveryAdress = new JTextField();
	private JTextField day = new JTextField(2);
	private JTextField month = new JTextField(2);
	private JTextField year = new JTextField(4);
	private JTextField hour = new JTextField(2);
	private JTextField minutes = new JTextField(2);
	private JTextField priceField = new JTextField();
	private double price = 0;
	
	public OrderMenu(JFrame parent) {
		super(parent, "Order menu");
	    add(new ListPanel(), BorderLayout.CENTER);
	    add(getButtonpanel(), BorderLayout.SOUTH);
	    add(new newDish(), BorderLayout.NORTH);
	    pack();
	}
	private class CustomerDatapanel extends JPanel {
		public CustomerDatapanel() {
			setLayout(new GridLayout(4, 6, 5, 5));
			add(new JLabel("Delivery date (dd.mm.yyyy): ", JLabel.RIGHT));
		    add(year);
		    add(new JLabel("-", JLabel.RIGHT));
		    add(month);
		    add(new JLabel("-", JLabel.RIGHT));
		    add(day);
		    add(new JLabel("Time:", JLabel.RIGHT));
		    add(hour);
		    add(new JLabel(":", JLabel.RIGHT));
		    add(minutes);
		    add(new JLabel("", JLabel.RIGHT));
		    add(new JLabel("", JLabel.RIGHT));//moves deliveryAdress to the next line
		    add(new JLabel("Delivery adress: ", JLabel.RIGHT));
		    add(deliveryAdress);
		    add(new JLabel("Price: ", JLabel.RIGHT));
		    add(priceField);
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
				dish.add(aDish);
			}
			list.clearSelection();
			for(Dish aDish : dish) {
				price += aDish.getPrice();
			}
			priceField.setText(Double.toString(price));
		}
	}
	public Order getOrder() {
		setOk(false);
		pack();
	    setVisible(true);
		Order order;
	    if (isOk()) {
	    	String deliveryTime = "" + day.getText() + "-" + month.getText() + "-" + year.getText() + " " + hour.getText() + ":" + minutes.getText() + ":00";
	    	order = new Order(1, 1, "Registered", "Placeholder", deliveryTime, deliveryAdress.getText(), dish, price); //orderid, customer id and ordertime placeholders
	    	return order;
	    } else {
	    	return  null;
	    }
	}
}