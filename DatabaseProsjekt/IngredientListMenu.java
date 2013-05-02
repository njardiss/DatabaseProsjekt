import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;


public class IngredientListMenu extends BasicDialog {
	ClientMethods methods = new ClientMethods();
	private DefaultListModel<Ingredient> ingredientListModel = new DefaultListModel<Ingredient>();
	private JTextField refillAmount = new JTextField();
	private JList<Ingredient> list1 = new JList<Ingredient>(ingredientListModel);
	private JButton UpdateAmount = new JButton("Update Amount");
	ArrayList<Ingredient> ingredientList;
	Ingredient ingredientSelection;
	
	public IngredientListMenu(JFrame parent) {
		super(parent, "Ingredient List");
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
	    add(new Update(), BorderLayout.EAST);
	    add(getButtonpanel(), BorderLayout.SOUTH);
	    pack();
	}
	private class Update extends JPanel {
		public Update() {
			setLayout(new BorderLayout());
			//add(new JLabel(" "), BorderLayout.NORTH);
		    add(new JLabel("Full name:", JLabel.RIGHT));
		    add(refillAmount);
			add(UpdateAmount, BorderLayout.EAST);
			ButtonListener listener = new ButtonListener();
			UpdateAmount.addActionListener(listener);
		}
	}
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			boolean check = false;
			String button = event.getActionCommand();
			if(event.getSource() == UpdateAmount) { 
				Ingredient ingredient = getIngredient();
				double amount = 0;
				try {
					check = methods.executeStockpileUpdate(ingredient, amount);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
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
