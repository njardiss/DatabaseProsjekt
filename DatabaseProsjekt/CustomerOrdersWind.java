import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

public class CustomerOrdersWind extends JFrame {
	
	ClientMethods methods = new ClientMethods();
	int tlf;
   
   public CustomerOrdersWind(int tlf){
	   this.tlf = tlf;
   }
   
	ArrayList<Order> ordersCustomer = methods.getCustomerOrders(tlf);
	static final String [][] DISHES = new String[ordersCustomer.size()][];
	Object[] tabledata = new Object[ordersCustomer.size()];
	tabledata = ordersCustomer.toArray();
	
	for(int i=0; tabledata.lentgh; i++){
		for(int k= 0; k< tabledata[i].length, k++){
			DISHES[i][k] = tabledata[i].getname(), tabledata[i].getOrderTime();
		}
	}
  
  //FEIL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!//////
		   
   static final String [] KOLONNENAVN = {"Dish", "OrderDate"}; 
   
   private JLabel chosen = new JLabel ("Choose a dish, please");
   private JTable dishtable = new JTable(DISHES, KOLONNENAVN); 
   
   
   public CustomerOrdersWind(String title){
	   setTitle(title);
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	   JLabel headline = new JLabel("Choose a dish");
	   add(headline, BorderLayout.NORTH);
	   
	   /*puts it in a scroll*/
	   JScrollPane scrollTable = new JScrollPane(dishtable);
	   add(scrollTable, BorderLayout.CENTER);
	   
	   dishtable.setPreferredScrollableViewportSize(new Dimension(300,100));
	   
	   dishtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // only choose 1 dish at a time//
	   
	   ListSelectionModel linechoice = dishtable.getSelectionModel();
	   LineListener listener = new LineListener();
	   linechoice.addListSelectionListener(listener);
	   
	   add(chosen, BorderLayout.SOUTH);
	   pack();
	      
   }
   
   /* Listener catches all the clicks on lines in table */
   
   private class LineListener implements ListSelectionListener {
	   public void valueChanged(ListSelectionEvent action){
		   int line = dishtable.getSelectedRow();
		   choice.setText("You have chosen dish " + dishtable.getValueAt(line, 0) + "ordered at" + dishtable.getValueAt(line,  1) + "time.");
		   
		   
	   }
   }
}



