import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.JOptionPane.*;

public class DishRegistration extends BasicDialog {
	private JTextField dishID = new JTextField();
	private JTextField name = new JTextField();
	private DefaultListModel<Ingredient> ingredientsListModel = new DefaultListModel<Ingredient>();
	private JList<Ingredient> list = new JList<Ingredient>(ingredientsListModel);
	private DefaultListModel<Ingredient> ingredientsListModel2 = new DefaultListModel<Ingredient>();
	private JList<Ingredient> chosenList = new JList<Ingredient>(ingredientsListModel2);
	private JTextField price = new JTextField();
	private ArrayList<Ingredient> chosenIngredients;
	private ArrayList<Ingredient> allIngredients;
	private JRadioButton appetizer = new JRadioButton("Appetizer");
	private JRadioButton mainCourse = new JRadioButton("Dinner");
	private JRadioButton dessert = new JRadioButton("Dessert");
	private JButton addIngredient = new JButton("Add ingredient");
	private JButton removeIngredient = new JButton("Remove ingredient");
	
	public DishRegistration(JFrame parent) {
		super(parent, "Dish registration");
		ClientMethods methods = new ClientMethods();
	    try {
			allIngredients = methods.listIngredients();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    for(Ingredient aIngredient : allIngredients) {
	    		ingredientsListModel.addElement((Ingredient) aIngredient);
	    }
		add(new ButtonPanel(), BorderLayout.NORTH);
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
			group.add(mainCourse);
			group.add(dessert);
			add(appetizer);
			add(mainCourse);
			add(dessert);
		}
	}
	private class ButtonPanel extends JPanel {
		public ButtonPanel() {
			setLayout(new BorderLayout());
			add(new JLabel(" "), BorderLayout.NORTH); //air
			add(addIngredient, BorderLayout.EAST);
			add(removeIngredient, BorderLayout.WEST);
			ButtonListener listener = new ButtonListener();
			addIngredient.addActionListener(listener);
			removeIngredient.addActionListener(listener);
		}
	}
	private class ListPanel extends JPanel {
		public ListPanel() {
			setLayout(new BorderLayout(5, 5));
			list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			JScrollPane listScroller1 = new JScrollPane(list);
			add(listScroller1, BorderLayout.EAST);
			chosenList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			JScrollPane listScroller2 = new JScrollPane(chosenList);
			add(listScroller2, BorderLayout.WEST);
		}
	}
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String button = event.getActionCommand();
			if(event.getSource() == addIngredient) {
				if(list.getSelectedValuesList() != null) {
		    		ArrayList<Ingredient> values = (ArrayList<Ingredient>) list.getSelectedValuesList();
		    		for(Ingredient aIngredient : values) {
		    			chosenIngredients.add(aIngredient);
		    			ingredientsListModel2.addElement(aIngredient);
		    		}
		    	}
			} else if(event.getSource() == removeIngredient) {
				if(chosenList.getSelectedValuesList() != null) {
		    		ArrayList<Ingredient> values = (ArrayList<Ingredient>) chosenList.getSelectedValuesList();
		    		for(Ingredient aIngredient : values) {
		    			chosenIngredients.remove(aIngredient);
		    			ingredientsListModel2.removeElement(aIngredient);
		    		}
		    	}
			}
		}
	}
	public Dish editDish(Dish dish) {
		dishID.setText(Integer.toString(dish.getDishID()));
		name.setText(dish.getName());
		price.setText(Double.toString(dish.getPrice()));
		if(dish instanceof Appetizer) {
			appetizer.setSelected(true);
		} else if(dish instanceof MainCourse){
			mainCourse.setSelected(true);
		} else {
			dessert.setSelected(true);
		}
		chosenIngredients = dish.getIngredients();
		for(Ingredient anIngredient : chosenIngredients) {
			ingredientsListModel.addElement(anIngredient);
		}
		setOk(false);
		pack();
	    setVisible(true);
	    if(isOk()){
	    	if(appetizer.isSelected()) {
	    		Appetizer newDish = new Appetizer(dish.getDishID(), name.getText(), chosenIngredients, Double.parseDouble(price.getText()));
	    		return dish;
	    	} else if(mainCourse.isSelected()) {
	    		MainCourse newDish = new MainCourse(dish.getDishID(), name.getText(), chosenIngredients, Double.parseDouble(price.getText()));
	    		return dish;
	    	} else {
	    		Dessert newDish = new Dessert(dish.getDishID(), name.getText(), chosenIngredients, Double.parseDouble(price.getText()));
	    		return newDish;
	    	}
	    } else {
	    	return null;
	    }
	}
	public Dish newDish() {
	    setOk(false);
		pack();
	    setVisible(true);
	    if (isOk()) {
	    	if(appetizer.isSelected()) {
	    		Appetizer dish = new Appetizer(1, name.getText(), chosenIngredients, Double.parseDouble(price.getText())); //DishID placeholder
	    		return dish;
	    	} else if(mainCourse.isSelected()) {
	    		MainCourse dish = new MainCourse(1, name.getText(), chosenIngredients, Double.parseDouble(price.getText())); //DishID placeholder
	    		return dish;
	    	} else {
	    		Dessert dish = new Dessert(1, name.getText(), chosenIngredients, Double.parseDouble(price.getText())); //DishID placeholder
	    		return dish;
	    	}
	    } else {
	    	return null;
	    }
	}
	protected boolean okData() { //trenger bedre kontroll av data
		String name2 = name.getText().trim();
		double price2 = Double.parseDouble(price.getText());
		boolean dessert2 = dessert.isSelected();
		boolean dinner2 = mainCourse.isSelected();
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
	    		mainCourse.requestFocusInWindow();
	    	}
	    	return false;
	    } else {
	    	return true;
	    }
	}
}