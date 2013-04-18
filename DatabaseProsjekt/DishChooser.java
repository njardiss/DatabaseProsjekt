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
	/*private ArrayList<MainCourse> dish1 = new ArrayList<MainCourse>();
	private ArrayList<Appetizer> dish2 = new ArrayList<Appetizer>();
	private ArrayList<Dessert> dish3 = new ArrayList<Dessert>();*/
	ArrayList<Dish> dishes2;
	ArrayList<Dish> dishes;

	public DishChooser(JFrame parent) {
		super(parent, "Dish chooser");
		setLayout(new BorderLayout(5, 5));
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
	public ArrayList<Dish> getDish() {
		setOk(false);
		pack();
	    setVisible(true);
	    ClientMethods methods = new ClientMethods();
	    try {
			dishes = methods.listDishes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    for(Dish aDish : dishes) {
	    	if(aDish instanceof MainCourse) {
	    		mainCourseListModel.addElement((MainCourse) aDish);
	    	} if(aDish instanceof Appetizer) {
	    		appetizerListModel.addElement((Appetizer) aDish);
	    	} if(aDish instanceof Dessert) {
	    		dessertListModel.addElement((Dessert) aDish);
	    	}
	    }
	    setOk(false);
		pack();
	    setVisible(true);
	    if (isOk()) {
	    	if(list1.getSelectedValuesList() != null) {
	    		ArrayList<MainCourse> values = (ArrayList<MainCourse>) list1.getSelectedValuesList();
	    		for(Dish aDish : values) {
	    			dishes.add(aDish);
	    		}
	    	} else if (list2.getSelectedValuesList() != null) {
	    		ArrayList<Dessert> values = (ArrayList<Dessert>) list2.getSelectedValuesList();
	    		for(Dish aDish : values) {
	    			dishes.add(aDish);
	    		}
	    	} else if (list3.getSelectedValuesList() != null) {
	    		ArrayList<Appetizer> values = (ArrayList<Appetizer>) list3.getSelectedValuesList();
	    		for(Dish aDish : values) {
	    			dishes.add(aDish);
	    		}
	    	}
	    	return dishes;
	    } else {
	    	return null;
	    }
	}
	/*private class ListeBoksLytter implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent event) {
			if (isOk()) {
				list1.clearSelection();
				list2.clearSelection();
				list3.clearSelection();
			}
		}
	}*/
}
