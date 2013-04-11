import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class ClientMethods {
 	String dbdriver = "org.apache.derby.jdbc.ClientDriver";
    String dbname = "jdbc:derby://localhost:1527/CateringBuddy;user=db;password=db";
    
	public boolean regNewCustomer(String name,int phone, String adress, int type) throws Exception {
		Class.forName(dbdriver);
	    Connection connect = DriverManager.getConnection(dbname);
	    Statement state = connect.createStatement();
		String sql = "INSERT INTO customer(name, phone, adress, type) values('" + name + "'" +"" +
				"," + phone + ", '" + adress + "'," + type + ")";
		int answer = state.executeUpdate(sql);
		if(answer>0){
			return true;
		}else{
			return false;
		}
	}
	
	public Customer getCustomer(int kid) throws Exception {
		Class.forName(dbdriver);
	    Connection connect = DriverManager.getConnection(dbname);
	    Statement state = connect.createStatement();
		String sql = "SELECT * from customer where kid = " + kid + "";
		ResultSet res = state.executeQuery(sql);
		String navn = "";
		int telefonnr = 0;
		String adresse = "";
		int typen = 0;
		while(res.next()){
			kid = Integer.parseInt(res.getString("kid"));
			navn = res.getString("name");
			telefonnr = Integer.parseInt(res.getString("phone"));
			adresse = res.getString("adress");
			typen = Integer.parseInt(res.getString("type"));
		}
		res.close();
		Customer hanher = new Customer(kid, navn, telefonnr, adresse, typen);
		return hanher;
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
