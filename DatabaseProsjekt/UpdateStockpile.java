import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import OrderMenu.ButtonListener;   // and love //
import OrderMenu.ListPanel;
import OrderMenu.newDish;

import java.text.*;
import java.util.ArrayList;
import static javax.swing.JOptionPane.*;

private Ingredients [][] ingredients = new Ingredients[][];

public class UpdateStockpile extends BasicDialog {
	private DefaultListModel<Ingredient> ingredientListModel = new DefaultListModel<Ingredient>();
	private JList<Ingredient> list = new JList<Dish>(ingredientListModel);
	private JButton newIngredient = new JButton("Add Ingredient");
	private JFrame parent;
	
	
	public UpdateStockpile(JFrame parent) {
		super(parent, "Ingredient list");
	    add(new ListPanel(), BorderLayout.CENTER);
	    add(getButtonpanel(), BorderLayout.SOUTH);
	    add(new newIngredient(), BorderLayout.NORTH);
	    pack();
	    
	}

}
	
	private class newIngredient extends JPanel {
		public newIngredient() {
			setLayout(new BorderLayout());
			newIngredient.setPreferredSize(new Dimension(30, 30));
			add(new JLabel(" "), BorderLayout.NORTH);
			add(newIngredient, BorderLayout.CENTER);
			ButtonListener listener = new ButtonListener();
			newIngredient.getSelectedValuesList();
			
		}
	}
	private class ListPanel extends JPanel {
		public ListPanel() {
			setLayout(new BorderLayout());
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane listScroller = new JScrollPane(list);
			add(listScroller, BorderLayout.CENTER);
	
	
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String button = event.getActionCommand();
			IngredientChooser ingredientChooser = new IngredientChooser(parent);
			ArrayList <Ingredient> newIngredient = ingredientChooser.getIngredient();
			for(Ingredient aIngr : newIngredient) {
				ingredientListModel.addElement(aIngr);
				String amount = showInputDialog(" choose amount: ");
			    int amount2 = IntegerParseInt(amount);
			    
			    for(int i= 0; newIngredient.length, i++){
			    	ingredients [i][] = newIngredient;
			    	ingredients[][k] = amount2;
			    	
			    }
			   
			    }
			}
			list.clearSelection();
		}
	}
			
		
