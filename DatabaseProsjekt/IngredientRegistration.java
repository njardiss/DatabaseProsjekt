import java.awt.*;
import javax.swing.*;
import static javax.swing.JOptionPane.*;

public class IngredientRegistration extends BasicDialog {  // peace//
	private JTextField name = new JTextField();
	private JTextField ingredientid = new JTextField();
	private JTextField amount = new JTextField();
	private JRadioButton kg = new JRadioButton("kg", false);
	private JRadioButton g = new JRadioButton("gram", false);
	private JRadioButton l = new JRadioButton("liter", false);
	
	

	public IngredientRegistration(JFrame parent) {
		super(parent, "Add Ingredient");
		add(new JPanel(), BorderLayout.NORTH);  // litt "luft"
	    add(new IngredientDatapanel(), BorderLayout.CENTER);
	    add(getButtonpanel(), BorderLayout.SOUTH);
	    pack();
	}
	private class IngredientDatapanel extends JPanel {
		public IngredientDatapanel() {
			setLayout(new GridLayout(5, 2, 5, 5));
			add(new JLabel("IngredientID (automatic): ", JLabel.RIGHT));
		    add(ingredientid);
		    ingredientid.setEditable(false); // brukeren kan ikke endre nummeret
			add(new JLabel("Ingredient name:", JLabel.RIGHT));
		    add(name);
		    add(new JLabel("Amount: ", JLabel.RIGHT));
		    add(amount);
		    
		    ButtonGroup group = new ButtonGroup();
		    group.add(kg);
		    group.add(g);
		    group.add(l);
		    add(kg);
		    add(g);
		    add(l);
		}
	}
	
	  
	    
	    
	public String addIngredient() {
	    setOk(false);
	    pack();
	    name.requestFocusInWindow();
	    setVisible(true);
	    String metric = "";
	    if (isOk()) { 
	    	if(kg.isSelected){
	    		String = "kg";
	    	}
	    		
	    	else if(g.isSelected){
	    		String = "g";
	    			
	    	}
	    	else if(l.isselected){
	    		String = "l";
	    	}
	    	String sql = "INSERT INTO ingredients(name, amount, metric) values('" + name.getText() + "'," 
	    + Integer.parseInt(amount.getText()) + ", '" + metric + "')";
	    	return sql;
	    } else {
	    	return null;
	    }
	}
	protected boolean okData() { //trenger bedre kontroll av data
		String name2 = name.getText().trim();
		String amount2 = amount.getText().trim();
		boolean kgButton = kg.isSelected();
		boolean gButton = g.isSelected();
		boolean lButton = l.isSelected();
	    if (name2.equals("") || metric2.equals("") || amount2.equals("")) {
	    	if (name2.equals("")) {
	    		showMessageDialog(IngredientRegistration.this, "You have to input an ingredient name!");
	    		name.requestFocusInWindow();
	    	} else if(amount2.equals("")) {
	    		showMessageDialog(IngredientRegistration.this, "You have to input an amount!");
	    		amount.requestFocusInWindow();
	    	}else if(!kgButton && !gButton && !lButton) {
	    		showMessageDialog(IngredientRegistration.this, "You have to choose a metric type!");
	    		kg.requestFocusInWindow();
	
	    	return false;
	    } else {
	    	return true;
	    }
	}
}