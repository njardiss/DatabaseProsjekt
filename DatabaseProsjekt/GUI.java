import static javax.swing.JOptionPane.*;
import javax.swing.*;
import java.sql.*;

public class GUI {
	public static void main(String[] args) throws Exception {
	 	String dbdriver = "org.apache.derby.jdbc.ClientDriver";
		Class.forName(dbdriver);
	    String dbname = "jdbc:derby://localhost:1527/CateringBuddy;user=db;password=db";
	    Connection connect = DriverManager.getConnection(dbname);
	    Statement state = connect.createStatement();

	    String[] choices = {"CUSTOMERS", "ORDERS", "DISHES",  "STOCKPILE", "ECONOMY", "EXIT"};
	    String[] cust = {"FIND_CUSTOMER", "ADD_CUSTOMER", "EDIT_CUSTOMER", "BACK"}; // debt/payment remaining
	    String[] ord = {"LIST_ORDERS", "ADD_ORDER", "FIND_ORDER", "BACK"};
	    String[] dish = {"LIST_DISHES", "ADD_DISH", "FIND_DISH", "BACK"};
	    String[] stock = {"LIST_STOCKPILE", "FIND_INGREDIENT", "EDIT_INGREDIENT", "BACK"};
	    String[] eco = {"CHECK_FUNDS", "WITHDRAW", "DEPOSIT", "CHECK_PROFIT"};
		int choice = showOptionDialog(null, "Choose Sub-menu: ", "CateringBuddy", 0, JOptionPane.INFORMATION_MESSAGE, null, choices, choices[0]);

	    switch(choice){
			case 0:
				int cuschoice = showOptionDialog(null, "Choose Customer function: ", "CateringBuddy", 0, JOptionPane.INFORMATION_MESSAGE, null, cust, cust[0]);
				switch(cuschoice){
					case 0:
						String find = showInputDialog("Skriv inn KID til ønsket kunde: ");
						int kid = Integer.parseInt(find);
						System.out.println();
					case 1:

				}
			case 1:
				int ordchoice = showOptionDialog(null, "Choose Order function: ", "CateringBuddy", 0, JOptionPane.INFORMATION_MESSAGE, null, ord, ord[0]);
				switch(ordchoice){
					case 0:}
			case 2:
				int dischoice = showOptionDialog(null, "Choose Dish function: ", "CateringBuddy", 0, JOptionPane.INFORMATION_MESSAGE, null, dish, dish[0]);
				switch(dischoice){
					case 0:}
			case 3:
				int stochoice = showOptionDialog(null, "Choose Stockpile function: ", "CateringBuddy", 0, JOptionPane.INFORMATION_MESSAGE, null, stock, stock[0]);
				switch(stochoice){
					case 0:}
			case 4:
				int ecochoice = showOptionDialog(null, "Choose Economic function: ", "CateringBuddy", 0, JOptionPane.INFORMATION_MESSAGE, null, eco, eco[0]);
				switch(ecochoice){
					case 0:}
			case 5:
			case -1:
				System.exit(0);
			default:
				JOptionPane.showMessageDialog(null, "Unexpected Choice " + choice);
			}
		}
	}



