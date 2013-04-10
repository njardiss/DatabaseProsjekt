import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

class ClientMethods {
 	String dbdriver = "org.apache.derby.jdbc.ClientDriver";
    String dbname = "jdbc:derby://localhost:1527/CateringBuddy;user=db;password=db";
	public String regNewCustomer(String name,int phone, String adress, int type) {
		String sql = "INSERT INTO customer(name, phone, adress, type) values('" + name + "'" +"" +
				"," + phone + ", '" + adress + "'," + type + ")";
		return sql;
	}
	public String getCustomer(int kid) throws Exception {
		Class.forName(dbdriver);
	    Connection connect = DriverManager.getConnection(dbname);
	    Statement state = connect.createStatement();
		String sql = "SELECT * from customer where kid = " + kid + "";
		return sql;
	}
	public String findOrders(int kid) {
		String sql = "SELECT * from orders where kid = " + kid + "";
		return sql;
	}
	public String findOrdersByStatus(String status) {
		String sql = "SELECT * from orders where status = '" + status + "'";
		return sql;
	}
	/*public String setNewDish(String name, String ingredients, double price){
	 	String sql = "INSERT INTO Dish(name, ingredients, price)values('" + name + "'
	 	,'" + ingredients + "', '" + price + "');
		return sql;
	}*/
}
