import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class DishChooser extends BasicDialog {
	private DefaultListModel<Dessert> dessertListModel = new DefaultListModel<Dessert>();
	private DefaultListModel<Appetizer> appetizerListModel = new DefaultListModel<Appetizer>();
	private DefaultListModel<MainCourse> mainCourseListModel = new DefaultListModel<MainCourse>();
	private JList<MainCourse> list1 = new JList<MainCourse>(mainCourseListModel);
	private JList<Dessert> list2 = new JList<Dessert>(dessertListModel);
	private JList<Appetizer> list3 = new JList<Appetizer>(appetizerListModel);
	ArrayList<Dish> dishesList;
	ArrayList<Dish> dishes;

	public DishChooser(JFrame parent) {
		super(parent, "Dish chooser");
		ClientMethods methods = new ClientMethods();
	    try {
			dishesList = methods.listDishes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    for(Dish aDish : dishesList) {
	    	if(aDish instanceof MainCourse) {
	    		mainCourseListModel.addElement((MainCourse) aDish);
	    	} if(aDish instanceof Appetizer) {
	    		appetizerListModel.addElement((Appetizer) aDish);
	    	} if(aDish instanceof Dessert) {
	    		dessertListModel.addElement((Dessert) aDish);
	    	}
	    }
		setLayout(new BorderLayout(5, 5));
		add(new Labels(), BorderLayout.NORTH);
	    add(new ListPanel(), BorderLayout.CENTER);
	    add(getButtonpanel(), BorderLayout.SOUTH);
	    pack();
	}
	private class ListPanel extends JPanel {
		public ListPanel() {
			setLayout(new BorderLayout(5, 5));
			list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			JScrollPane listScroller1 = new JScrollPane(list1);
			add(listScroller1, BorderLayout.WEST);
			list2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			JScrollPane listScroller2 = new JScrollPane(list2);
			add(listScroller2, BorderLayout.CENTER);
			list3.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			JScrollPane listScroller3 = new JScrollPane(list3);
			add(listScroller3, BorderLayout.EAST);
		}
	}
	private class Labels extends JPanel {
		public Labels() {
			setLayout(new BorderLayout());
			add(new JLabel(" "), BorderLayout.NORTH);
			add(new JLabel("Main courses:"), BorderLayout.WEST);
			add(new JLabel("Desserts:"), BorderLayout.CENTER);
			add(new JLabel("Appetizers:"), BorderLayout.EAST);
		}
	}
	public ArrayList<Dish> getDish() {
	    setOk(false);
		pack();
	    setVisible(true);
	    if (isOk()) {
	    	if(!list1.isSelectionEmpty()) {
	    		int[] index = list1.getSelectedIndices();
	    		for(int i = 0; i< index.length; i++) {
	    			dishes.add(mainCourseListModel.get(index[i]));
	    		}
	    	}
	    	if (!list2.isSelectionEmpty()) {
	    		int[] index = list2.getSelectedIndices();
	    		for(int i = 0; i< index.length; i++) {
	    			dishes.add(dessertListModel.get(index[i]));
	    		}
	    	}
	    	if (!list3.isSelectionEmpty()) {
	    		int[] index = list3.getSelectedIndices();
	    		for(int i = 0; i< index.length; i++) {
	    			dishes.add(appetizerListModel.get(index[i]));
	    		}
	    	}
	    	return dishes;
	    } else {
	    	return dishes;
	    }
	}
}
