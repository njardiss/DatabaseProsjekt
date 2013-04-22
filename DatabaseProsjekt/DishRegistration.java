import java.awt.*;
import javax.swing.*;

import DishChooser.ListPanel;
import static javax.swing.JOptionPane.*;

public class DishRegistration extends BasicDialog {
	private JTextField dishId = new JTextField();
	private JTextField name = new JTextField();
	private DefaultListModel<Ingredients> ingredientsListModel = new DefaultListModel<Ingredients>();
	private JList<Ingredients> list1 = new JList<Ingredients>(ingredientsListModel); //usikker //
	private JTextField price = new JTextField();

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
		    kid.setEditable(false); // brukeren kan ikke endre nummeret
		    add(new JLabel("Name of dish:", JLabel.RIGHT));
		    add(name);
		    add(new JLabel("Ingredients :", JLabel.RIGHT));
		    add(list1); // usikker //
		    add(new JLabel("Price: ", JLabel.RIGHT));
		    add(price);
		 
		}
	}
	private class ListPanel extends JPanel {
		public ListPanel() {
			setLayout(new BorderLayout(5, 5));
			list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			JScrollPane listScroller1 = new JScrollPane(list1);
			add(listScroller1, BorderLayout.WEST);
		}
	

	
	public ArrayList<Ingredients> getIngredients() {
		setOk(false);
		pack();
	    setVisible(true);
	    ClientMethods methods = new ClientMethods();
	    try {
			ingredients = methods.listIngredients();   // må denne lages i clientmethods ?//
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    for(Ingredients aIngredient : ingredients) {
	    		ingredientsListModel.addElement((Ingredients) aIngredient);
	    
	    }
	    setOk(false);
		pack();
	    setVisible(true);
	    if (isOk()) {
	    	if(list1.getSelectedValuesList() != null) {
	    		ArrayList<Ingredients> values = (ArrayList<Ingredients>) list1.getSelectedValuesList();
	    		for(Ingredient aIngredient : values) {
	    			ingredients.add(aIngredient);
	    		}
	    	
	    	
	    	return ingredients;
	    } else {
	    	return null;
	    }
	}
	
	
	
	protected boolean okData() { //trenger bedre kontroll av data
		int dishID2 = Integer.parseInt(dishId.getText().trim());
		String name2 = name.getText().trim();
		int ingredients2 = getIngredients().trim();  //usikker på datatype//
		double price2 = Double.parseDouble(price.getText().trim());
		
	    if (name2.equals("") || ingredients2.equals("") || price2.equals("") ||  {
	    	if (name2.equals("")) {
	    		showMessageDialog(DishRegistration.this, "You have to input a dish name!");
	    		name.requestFocusInWindow();
	    	} else if(ingredients2.equals("")) {
	    		showMessageDialog(DishRegistration.this, "You have to choose ingredients !");
	    		ingredients2.requestFocusInWindow();
	    	} else if(price2.equals(null)) {
	    		showMessageDialog(DishRegistration.this, "You have to input a price!");
	    		price2.requestFocusInWindow();
	    	} 
	    	return false;
	    } else {
	    	return true;
	    }
	}
}