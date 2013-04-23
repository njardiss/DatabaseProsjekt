import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.ArrayList;
import static javax.swing.JOptionPane.*;

public class UpdateStockpile extends BasicDialog {
	private DefaultListModel<Ingredient> ingredientListModel = new DefaultListModel<Ingredient>();
	private JList<Ingredient> list = new JList<Ingredient>(ingredientListModel);
	private JButton refillIngredient = new JButton("Refill Ingredient");
	private JFrame parent;
	private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
	private ClientMethods methods = new ClientMethods();
	private double amount;
	
	public UpdateStockpile(JFrame parent) {
		super(parent, "Ingredient list");
	    add(new ListPanel(), BorderLayout.CENTER);
	    add(getButtonpanel(), BorderLayout.SOUTH);
	    add(new refillIngredient(), BorderLayout.NORTH);
	    try {
			ingredients = methods.listIngredients();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Ingredient aIngr : ingredients) {
			ingredientListModel.addElement(aIngr);
		}
	    pack();
	}
	private class refillIngredient extends JPanel {
		public refillIngredient() {
			setLayout(new BorderLayout());
			refillIngredient.setPreferredSize(new Dimension(30, 30));
			add(new JLabel(" "), BorderLayout.NORTH);
			add(refillIngredient, BorderLayout.CENTER);
			ButtonListener listener = new ButtonListener();
			refillIngredient.addActionListener(listener);
		}
	}
	private class ListPanel extends JPanel {
		public ListPanel() {
			setLayout(new BorderLayout());
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane listScroller = new JScrollPane(list);
			add(listScroller, BorderLayout.CENTER);
		}
	}
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String button = event.getActionCommand();
			int index = list.getSelectedIndex();
			Ingredient chosenIngredient = ingredients.get(index);
			amount = Double.parseDouble(showInputDialog(" choose amount: "));
			try {
				boolean check = methods.executeStockpileUpdate(chosenIngredient, amount);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.clearSelection();
		}
	}
}