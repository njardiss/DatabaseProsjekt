import java.awt.*;
import javax.swing.*;
import static javax.swing.JOptionPane.*;

public class IngredientRegistration extends BasicDialog {
	private JTextField name = new JTextField();
	private JTextField ingredientid = new JTextField();
	private JTextField metric = new JTextField();
	private JTextField amount = new JTextField();

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
		    add(new JLabel("Metric: ", JLabel.RIGHT));
		    add(metric);
		    add(new JLabel("Amount: ", JLabel.RIGHT));
		    add(amount);
		}
	}
	public String addIngredient() {
	    setOk(false);
	    pack();
	    name.requestFocusInWindow();
	    setVisible(true);
	    if (isOk()) { 
	    	String sql = "INSERT INTO ingredients(name, amount, metric) values('" + name.getText() + "'," 
	    + Integer.parseInt(amount.getText()) + ", '" + metric.getText() + "')";
	    	return sql;
	    } else {
	    	return null;
	    }
	}
	protected boolean okData() { //trenger bedre kontroll av data
		String name2 = name.getText().trim();
		String metric2 = metric.getText().trim();
		String amount2 = amount.getText().trim();
	    if (name2.equals("") || metric2.equals("") || amount2.equals("")) {
	    	if (name2.equals("")) {
	    		showMessageDialog(IngredientRegistration.this, "You have to input an ingredient name!");
	    		name.requestFocusInWindow();
	    	} else if(metric2.equals("")) {
	    		showMessageDialog(IngredientRegistration.this, "You have to input a metric!");
	    		metric.requestFocusInWindow();
	    	} else if(metric2.equals("")) {
	    		showMessageDialog(IngredientRegistration.this, "You have to input an amount!");
	    		amount.requestFocusInWindow();
	    	}
	
	    	return false;
	    } else {
	    	return true;
	    }
	}
}