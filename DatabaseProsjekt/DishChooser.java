import java.awt.BorderLayout;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class DishChooser extends BasicDialog {
	private DefaultListModel<Dish> dishListModel = new DefaultListModel<Dish>();
	private JList<Dish> list1 = new JList<Dish>(dishListModel);
	private JList<Dish> list2 = new JList<Dish>(dishListModel);
	private JList<Dish> list3 = new JList<Dish>(dishListModel);
	private JButton newDish = new JButton("Add dish");
	private ArrayList<Dish> dish1 = new ArrayList<Dish>();
	private ArrayList<Dish> dish2 = new ArrayList<Dish>();
	private ArrayList<Dish> dish3 = new ArrayList<Dish>();

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
			list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane listScroller1 = new JScrollPane(list1);
			add(listScroller1, BorderLayout.WEST);
			list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane listScroller2 = new JScrollPane(list2);
			add(listScroller2, BorderLayout.CENTER);
			list3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane listScroller3 = new JScrollPane(list3);
			add(listScroller3, BorderLayout.EAST);
		}
	}
	public Dish getDish(Connection connection) {
		setOk(false);
		return dish;
	}
	private class ListeBoksLytter implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent event) {
			if (isOk()) {
				list1.clearSelection();
				list2.clearSelection();
				list3.clearSelection();
			}
		}
}
