import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;

class GUI {
	public static void main(String[] args) throws Exception {
    ClientMethods methods = new ClientMethods();

    String[] choices = {"CUSTOMERS", "ORDERS", "DISHES",  "STOCKPILE", "ECONOMY", "EXIT"};
    String[] cust = {"Add customer", "Find customer", "Edit customer", "Back"};
    String[] ord = {"LIST_ORDERS", "Add order", "Edit order", "BACK"};
    String[] dish = {"LIST_DISHES", "ADD_DISH", "FIND_DISH", "BACK"};
    String[] stock = {"LIST_STOCKPILE", "ADD INGREDIENT", "EDIT_INGREDIENT", "BACK"};
    String[] eco = {"CHECK_FUNDS", "WITHDRAW", "DEPOSIT", "CHECK_PROFIT"};

    int choice = showOptionDialog(null, "Choose Sub-menu: ", "CateringBuddy", 0, PLAIN_MESSAGE, null, choices, choices[0]);

    switch(choice) {
		case 0: //Customers
			int cuschoice = showOptionDialog(null, "Choose Customer function: ", "CateringBuddy", 0, PLAIN_MESSAGE, null, cust, cust[0]);
			switch(cuschoice){
				case 0: //Add Customer
					boolean check = methods.regNewCustomer();
					if(check){
						showMessageDialog(null,"Registreringen er vellykket");
					}else{
						showMessageDialog(null,"Ingen oppdatering gjort"); //meldingen m� utvides elnz
					}
					break;
				case 1: //Find Customer
					String kundeid = showInputDialog(null,"Skriv inn kunde identifikasjon nr:");
					int kid = Integer.parseInt(kundeid);
					Customer hanher = methods.getCustomer(kid);
					String kundeinfo = hanher.toString();
					System.out.println(kundeinfo);
					break;
				case 2: //Edit Customer
					kid = Integer.parseInt(showInputDialog(null,"Skriv inn kunde identifikasjon nr:"));
					if(methods.editCustomer(kid)) {
						showMessageDialog(null,"The changes are saved successfully!");
					}else{
						showMessageDialog(null,"No update saved."); //meldingen m� utvides elnz
					}
					break;
				case 3:
					break;
				}
			break;
		case 1: //Orders
			int ordchoice = showOptionDialog(null, "Choose Order function: ", "CateringBuddy", 0, PLAIN_MESSAGE, null, ord, ord[0]);
			switch(ordchoice){
				case 0: //List orders
					break;
				case 1: //add order
					int phone = Integer.parseInt(showInputDialog(null,"Input the customers phone number"));
					boolean check = methods.addOrder(phone);
					if(check){
						showMessageDialog(null,"Registreringen er vellykket");
					}else{
						showMessageDialog(null,"Ingen oppdatering gjort"); //meldingen m� utvides elnz
					}
					break;
				case 2: //edit order
					
					break;
			}
			break;
		case 2:
			int dischoice = showOptionDialog(null, "Choose Dish function: ", "CateringBuddy", 0, PLAIN_MESSAGE, null, dish, dish[0]);
			switch(dischoice){
				case 0: // list dishes //
					ArrayList<Dish> dishes = methods.listDishes();
					for(Dish aDish : dishes) {
						System.out.println(aDish);
					}
					break;
				case 1: /*
					DishRegistration registration = new DishRegistration(this);
					registration.setLocation(350, 350);
					registration.setVisible(true);
					String[] dish = new String[2];
					if((dish = registration.setNewDish()) == null) {
						showMessageDialog(null,"Ingen oppdatering gjort"); //dunooo
					}
					String name = dish[0];
					String ingredients = dish[1];
					double price = Double.parseDouble(dish[2]);
					Boolean check = methods.setNewDish(name, ingredients, price);
					if(check){
						showMessageDialog(null,"Registreringen er vellykket");
					}else{
						showMessageDialog(null,"Ingen oppdatering gjort"); //meldingen m� utvides elnz
					}*/
					
					
					
					
				case 2: // find dish//
					String rettnavn = showInputDialog(null,"Skriv inn rettens navn:");
					Dish retten = methods.findDish(rettnavn);
					String rettinfo = retten.toString();
					System.out.println(rettinfo);
					break;
				}
				break;
		case 3:
			int stochoice = showOptionDialog(null, "Choose Stockpile function: ", "CateringBuddy", 0, PLAIN_MESSAGE, null, stock, stock[0]);
			switch(stochoice){
				case 0:
					break;
				case 1: // add ingredient //
					boolean check = methods.addIngredient();
					if(check){
						showMessageDialog(null,"Registreringen er vellykket");
					}else{
						showMessageDialog(null,"Ingen oppdatering gjort"); 
					}
					break;
				}
		case 4:
			int ecochoice = showOptionDialog(null, "Choose Economic function: ", "CateringBuddy", 0, PLAIN_MESSAGE, null, eco, eco[0]);
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
