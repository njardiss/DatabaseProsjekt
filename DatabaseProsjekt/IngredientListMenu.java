import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;


public class IngredientListMenu extends BasicDialog {
	private DefaultListModel<Ingredient> ingredientListModel = new DefaultListModel<Ingredient>();
	private JList<Ingredient> list1 = new JList<Ingredient>(ingredientListModel);
	ArrayList<Ingredient> ingredientList;
	Ingredient ingredientSelection;
	
	public IngredientListMenu(JFrame parent) {
		super(parent, "Ingredient List");
		ClientMethods methods = new ClientMethods();
		try {
			ingredientList = methods.listIngredients();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    for(Ingredient enIngredient : ingredientList) {
	    		ingredientListModel.addElement((Ingredient) enIngredient);
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
	public Ingredient getIngredient() {
	    setOk(false);
		pack();
	    setVisible(true);
	    if (isOk()) {
	    	if(!list1.isSelectionEmpty()) {
	    		int[] index = list1.getSelectedIndices();
	    		for(int i = 0; i< index.length; i++) {
	    			ingredientSelection = ingredientListModel.get(index[i]);
	    		}
	    	}
	    	return ingredientSelection;
	    } else {
	    	return ingredientSelection;
	    }
	}
}
