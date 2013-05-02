import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;


public class OrderCustListMenu extends BasicDialog {
	private DefaultListModel<Order> orderListModel = new DefaultListModel<Order>();
	private JList<Order> list1 = new JList<Order>(orderListModel);
	ArrayList<Order> orderList;
	Order orderSelection;
	
	public OrderCustListMenu(JFrame parent, int phone2) {
		super(parent, "Order List");
		ClientMethods methods = new ClientMethods();
		try {
			orderList = methods.listOrdersOnCustomer(phone2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    for(Order enOrder : orderList) {
	    		orderListModel.addElement((Order) enOrder);
	    }
		setLayout(new BorderLayout(5, 5));
	    add(new ListPanel(), BorderLayout.CENTER);
	    add(getButtonpanel(), BorderLayout.SOUTH);
	    pack();
	}
	private class ListPanel extends JPanel {
		public ListPanel() {
			setLayout(new BorderLayout(5, 5));
			add(new JLabel("Orders", JLabel.RIGHT));
			list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			JScrollPane listScroller1 = new JScrollPane(list1);
			add(listScroller1, BorderLayout.CENTER);
		}
	}
	public Order getOrder() {
	    setOk(false);
		pack();
	    setVisible(true);
	    if (isOk()) {
	    	if(!list1.isSelectionEmpty()) {
	    		int[] index = list1.getSelectedIndices();
	    		for(int i = 0; i< index.length; i++) {
	    			orderSelection = orderListModel.get(index[i]);
	    		}
	    	}
	    	return orderSelection;
	    } else {
	    	return orderSelection;
	    }
	}
}
