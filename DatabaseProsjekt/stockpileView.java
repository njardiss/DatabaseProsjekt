import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;





public class stockpileView extends JPanel {


		stockpileView() throws Exception{
		ClientMethods methods = new ClientMethods();
		ArrayList<Ingredient> ingredients = methods.listIngredients();
		Object[][] data = new Object[ingredients.size()][4];
		String[] columnNames = {"name", "ID", "Amount", "Unit"};
		
		
	
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
					System.out.println("Feil!");
				 }
				}
			}
		 }

		
		final JTable table = new JTable(data, columnNames);
	    table.setPreferredScrollableViewportSize(new Dimension(1500, 500));
	    table.setFillsViewportHeight(true);
	    
	    JScrollPane scrollPane = new JScrollPane(table);
	    
		add(scrollPane);
		}
		
	    static void createAndShowGUI() throws Exception {
	        //Create and set up the window.
	        JFrame frame = new JFrame("Orderlist");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	        //Create and set up the content pane.
	        stockpileView newContentPane = new stockpileView();
	        newContentPane.setOpaque(true); //content panes must be opaque
	        frame.setContentPane(newContentPane);
	 
	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }
	 
	    public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                try {
						createAndShowGUI();
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }
	        });
	    }
	}
