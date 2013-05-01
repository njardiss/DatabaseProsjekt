import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class stockpileView extends BasicDialog {
	private ArrayList<Ingredient> ingredients;
	private ClientMethods methods = new ClientMethods();
	private String[] columnNames = {"name", "ID", "Amount", "Unit"};
	private Object[][] data;
	private final JTable table = new JTable(data, columnNames);
	
	stockpileView(JFrame parent) {
		super(parent, "Stockpile");
		add(new JPanel(), BorderLayout.NORTH);
		add(new Table(), BorderLayout.CENTER);
		add(getButtonpanel(), BorderLayout.SOUTH);
		pack();
	}
	private class Table extends JPanel {
		public Table() {
			try {
				ingredients = methods.listIngredients();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Object[][] data = new Object[ingredients.size()][4];
		
			for(int lars=0; lars<ingredients.size(); lars++){
				for(int i=lars;i<ingredients.size();i++){
					Ingredient aIng = ingredients.get(i);
					for(int y=0;y<4;y++){
						if(y==0){
							data[i][y]=aIng.getName();
						} else if(y==1){
							data[i][y]=aIng.getIngredientId();
						} else if(y==2){
							data[i][y]=aIng.getAmount();
						} else if(y==3){
							data[i][y]=aIng.getMetric();
						} else {
							System.out.println("Error!");
						}
					}
				}
			}
			table.setPreferredScrollableViewportSize(new Dimension(1500, 500));
			table.setFillsViewportHeight(true);
			JScrollPane scrollPane = new JScrollPane(table);
			add(scrollPane, BorderLayout.CENTER);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
	}
	public boolean createAndShowGUI() throws Exception {
		setOk(false);
		setVisible(true);
		if(isOk()){
			return true;
		} else {
			return false;
		}
	}
}
