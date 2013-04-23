import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class newDishView extends JFrame {


	
	
	private JLabel nameLabel = new JLabel("Rettnavn:");
	private JTextField name = new JTextField(60);
	private JLabel priceLabel = new JLabel("Pris:");
	private JTextField price = new JTextField(60);
	private JRadioButton appetizer = new JRadioButton("Forrett");
	private JRadioButton dinner = new JRadioButton("Middag");
	private JRadioButton dessert = new JRadioButton("Dessert");
	
	
	newDishView(){
		JPanel dishPanel = new JPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 200);
		
		dishPanel.add(nameLabel);
		dishPanel.add(name);
		dishPanel.add(priceLabel);
		dishPanel.add(price);
		dishPanel.add(appetizer);
		dishPanel.add(dinner);
		dishPanel.add(dessert);
	/*	dishPanel.add(ingLabel);
		dishPanel.add(recipeList); */
		
		ButtonGroup group = new ButtonGroup();
		group.add(appetizer);
		group.add(dinner);
		group.add(dessert);
		
		this.add(dishPanel);
	}
	
	public String getName(){
		return name.getText();
	}
	
	public double getPrice(){
		return Double.parseDouble(price.getText());
	}
	
/*	public String[] getRecipe(){
		Object[] list = recipeList.getSelectedValuesList().toArray();
		String[] recipe = null;
		for(int i=0; i<list.length; i++){
			recipe[i]=list[i].toString();
		}
		return recipe;
	} */
	
	void displayErrorMessage(String errorMessage){
		JOptionPane.showMessageDialog(this, errorMessage);
	}
}
