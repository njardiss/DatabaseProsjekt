
import java.awt.*;
import javax.swing.*;
import static javax.swing.JOptionPane.*;
import java.sql.*;

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
			add(new JLabel("Ingredient name:", JLabel.RIGHT));
		    add(name);
		    add(new JLabel("IngredientID (automatic): ", JLabel.RIGHT));
		    add(dishid);
		    ingredientid.setEditable(false); // brukeren kan ikke endre nummeret
		    add(new JLabel("metric:", JLabel.RIGHT));
		    add(metric);
		    add(new JLabel("amount: ", JLabel.RIGHT));
		    add(amount);
		    
		  
		}
	}
	
	public boolean add1Ingredient(Connection connection) throws SQLException {
	    setOk(false);
	    int type;
	    pack();
	    name.requestFocusInWindow();
	    setVisible(true);
	    if (isOk()) {
	    
	    	}
	    	String sql = "INSERT INTO ingredients(name ,metric, amount) values('" + name.getText() + "'" +"" +
					"," + metric.getText() + ", '" + Integer.parseInt(amount.getText()) + ")";
	    	Statement state = connection.createStatement();
	    	int answer = state.executeUpdate(sql);
			if(answer>0){
				ConnectionManager.closeStatement(state);
				return true;
			} else {
				ConnectionManager.closeStatement(state);
				return false;
			}
	    } else {
	    	return false;
	    }
	}
	protected boolean okData() { //trenger bedre kontroll av data
		String name2 = name.getText().trim();
		String metric2 = metric.getText().trim();
		String amount2 = amount.getText().trim();
	    if (name2.equals("") || metric2.equals("") || amount2.equals("")) {
	    	if (name2.equals("")) {
	    		showMessageDialog(DishRegistration.this, "You have to input a dish name!");
	    		name.requestFocusInWindow();
	    	} else if(metric2.equals("")) {
	    		showMessageDialog(DishRegistration.this, "You have to input metric!");
	    		phone.requestFocusInWindow();
	    	} else if(metric2.equals("")) {
	    		showMessageDialog(DishRegistration.this, "You have to input amount!");
	    		adress.requestFocusInWindow();
	    	}
	
	    	return false;
	    } else {
	    	return true;
	    }
	}
}