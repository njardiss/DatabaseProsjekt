import java.util.ArrayList;
import javax.swing.*;

import java.awt.event.ActionListener;

public class recipe extends JFrame {
	
	ArrayList<Ingredient> ingredients = ClientMethods.listIngredients();
	private JLabel tell = new JLabel("Velg alle ingredienser som er i retten: ");
	private JList ingredientlist = new JList(ingredients);
	
	recipe(){
		JPanel recipePanel = new JPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 200);
		
		recipePanel.add(tell);
		recipePanel.add(ingredientlist);
		this.add(recipePanel);
	}
	
	public String[] getRecipe(){
	Object[] list = ingredientlist.getSelectedValuesList().toArray();
	String[] recipe = new String[ingredients.size()]
	for(int i=0; i<list.length; i++){
		recipe[i]=list[i].toString();
	}
	return recipe;
	} 
	
	void displayErrorMessage(String errorMessage){
		JOptionPane.showMessageDialog(this, errorMessage);
	}

}
