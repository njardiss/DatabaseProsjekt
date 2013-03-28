import java.sql.*;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;

class GUI {
	public static void main(String[] args) throws Exception {
 	String dbdriver = "org.apache.derby.jdbc.ClientDriver";
	Class.forName(dbdriver);
    String dbname = "jdbc:derby://localhost:1527/CateringBuddy;user=db;password=db";
    Connection connect = DriverManager.getConnection(dbname);
    Statement state = connect.createStatement();
    
    ClientMethods methods = new ClientMethods();
    
    String[] choices = {"CUSTOMERS", "ORDERS", "DISHES",  "STOCKPILE", "ECONOMY", "EXIT"};
    String[] cust = {"Add customer", "Find customer", "EDIT_CUSTOMER", "BACK"}; // debt/payment remaining
    String[] ord = {"LIST_ORDERS", "ADD_ORDER", "FIND_ORDER", "BACK"};
    String[] dish = {"LIST_DISHES", "ADD_DISH", "FIND_DISH", "BACK"};
    String[] stock = {"LIST_STOCKPILE", "FIND_INGREDIENT", "EDIT_INGREDIENT", "BACK"};
    String[] eco = {"CHECK_FUNDS", "WITHDRAW", "DEPOSIT", "CHECK_PROFIT"};
    
    int choice = showOptionDialog(null, "Choose Sub-menu: ", "CateringBuddy", 0, PLAIN_MESSAGE, null, choices, choices[0]);

    switch(choice){
		case 0:
			int cuschoice = showOptionDialog(null, "Choose Customer function: ", "CateringBuddy", 0, PLAIN_MESSAGE, null, cust, cust[0]);
			switch(cuschoice){
				case 0:
					String name = showInputDialog(null,"Skriv inn kundens navn:");
					int phone = Integer.parseInt(showInputDialog(null,"Skriv inn kundens telefonnummer:"));
					String adress = showInputDialog(null,"Skriv inn kundens bolig adresse:");
					int type = Integer.parseInt(showInputDialog(null,"Velg om kunden er en bedriftskunde(0) eller privatkunde(1):"));
					String sql = methods.regNewCustomer(name, phone, adress, type);
					int i = state.executeUpdate(sql);
					if(i>0){
						showMessageDialog(null,"Registreringen er vellykket");
					}else{
						showMessageDialog(null,"Ingen oppdatering gjort");
					}
				}	
		case 1:
			int ordchoice = showOptionDialog(null, "Choose Order function: ", "CateringBuddy", 0, PLAIN_MESSAGE, null, ord, ord[0]);
			switch(ordchoice){
				case 0:}
		case 2:
			int dischoice = showOptionDialog(null, "Choose Dish function: ", "CateringBuddy", 0, PLAIN_MESSAGE, null, dish, dish[0]);
			switch(dischoice){
				case 0:}
		case 3:
			int stochoice = showOptionDialog(null, "Choose Stockpile function: ", "CateringBuddy", 0, PLAIN_MESSAGE, null, stock, stock[0]);
			switch(stochoice){
				case 1:
					/*String name = showInputDialog(null, "Skriv inn rettens navn:");
					String ingredients = showInputDialog(null, "skriv inn ingredient:");
					double price = Double.parseDouble(showInputDialog(null, "Skriv inn pris:"));
					String sql = methods.setNewDish(name, ingredients, price);
					int i = state.executeUpdate(sql);
					if(i>0){
						showMessageDialog(null,"Registreringen er vellykket");
					}else{
						showMessageDialog(null,"Ingen oppdatering gjort");
					}*/
				}
		case 4:
			int ecochoice = showOptionDialog(null, "Choose Economic function: ", "CateringBuddy", 0, PLAIN_MESSAGE, null, eco, eco[0]);
			switch(ecochoice){
				case 0:}
		case 5:
		case -1:
			state.close();
			connect.close();
			System.exit(0);
		default:
			JOptionPane.showMessageDialog(null, "Unexpected Choice " + choice);
		}
	}
}


