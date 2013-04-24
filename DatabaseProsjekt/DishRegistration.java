import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import static javax.swing.JOptionPane.*;

public class DishRegistration extends BasicDialog {
	private JTextField dishID = new JTextField();
	private JTextField name = new JTextField();
	private DefaultListModel<Ingredient> ingredientsListModel = new DefaultListModel<Ingredient>();
	private JList<Ingredient> list = new JList<Ingredient>(ingredientsListModel); //usikker //
	private JTextField price = new JTextField();
	private ArrayList<Ingredient> ingredients; 
	private JRadioButton appetizer = new JRadioButton("Appetizer");
	private JRadioButton dinner = new JRadioButton("Dinner");
	private JRadioButton dessert = new JRadioButton("Dessert");

	public DishRegistration(JFrame parent) {
		super(parent, "Dish registration");
		add(new JPanel(), BorderLayout.NORTH);  // litt "luft"
	    add(new DishDatapanel(), BorderLayout.CENTER);
	    add(new ListPanel(), BorderLayout.CENTER);
	    add(getButtonpanel(), BorderLayout.SOUTH);
	    pack();
	}
	
	private class DishDatapanel extends JPanel {
		public DishDatapanel() {
			setLayout(new GridLayout(5, 2, 5, 5));
			add(new JLabel("Dish ID (automatic): ", JLabel.RIGHT));
		    add(dishID);
		    dishID.setEditable(false); // brukeren kan ikke endre nummeret
		    add(new JLabel("Name of dish:", JLabel.RIGHT));
		    add(name);
		    add(new JLabel("Price: ", JLabel.RIGHT));
		    add(price);
		    ButtonGroup group = new ButtonGroup();
			group.add(appetizer);
			group.add(dinner);
			group.add(dessert);
			add(appetizer);
			add(dinner);
			add(dessert);
		}
	}
	private class ListPanel extends JPanel {
		public ListPanel() {
			setLayout(new BorderLayout(5, 5));
			list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			JScrollPane listScroller1 = new JScrollPane(list);
			add(listScroller1, BorderLayout.WEST);
		}
	}
	public Dish newDish() {
		setOk(false);
		pack();
	    setVisible(true);
	    ClientMethods methods = new ClientMethods();
	    try {
			ingredients = methods.listIngredients();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    for(Ingredient aIngredient : ingredients) {
	    		ingredientsListModel.addElement((Ingredient) aIngredient);
	    
	    }
	    setOk(false);
		pack();
	    setVisible(true);
	    if (isOk()) {
	    	
	    	if(list.getSelectedValuesList() != null) {
	    		ArrayList<Ingredient> values = (ArrayList<Ingredient>) list.getSelectedValuesList();
	    		for(Ingredient aIngredient : values) {
	    			ingredients.add(aIngredient);
	    		}
	    	}
	    	Dish dish = new Dish(1, name.getText(), ingredients, Double.parseDouble(price.getText())); //DishID placeholder
	    	return dish;
	    } else {
	    	return null;
	    }
	}
	protected boolean okData() { //trenger bedre kontroll av data
		String name2 = name.getText().trim();
		double price2 = Double.parseDouble(price.getText());
		boolean dessert2 = dessert.isSelected();
		boolean dinner2 = dinner.isSelected();
		boolean appetizer2 = appetizer.isSelected();
		boolean values = list.isSelectionEmpty();
		
	    if (name2.equals("") || price2<1 || (!dessert2 && !dinner2 && !appetizer2)|| values){
	    	if (name2.equals("")) {
	    		showMessageDialog(DishRegistration.this, "You have to input a dish name!");
	    		name.requestFocusInWindow();
	    	} else if(values) {
	    		showMessageDialog(DishRegistration.this, "You have to choose ingredients!");
	    		list.requestFocusInWindow();
	    	} else if(price2<1) {
	    		showMessageDialog(DishRegistration.this, "You have to input a price!");
	    		price.requestFocusInWindow();
	    	} else if(!dessert2 && !dinner2 && !appetizer2) {
	    		showMessageDialog(DishRegistration.this, "You have to choose a dish type!");
	    		dinner.requestFocusInWindow();
	    	}
	    	return false;
	    } else {
	    	return true;
	    }
	}
}