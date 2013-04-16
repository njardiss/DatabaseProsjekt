import java.sql.*;
import java.util.ArrayList;

class ClientMethods {
 	String dbdriver = "org.apache.derby.jdbc.ClientDriver";
    String dbname = "jdbc:derby://localhost:1527/CateringBuddy;user=db;password=db";
    ParentWindow parent = new ParentWindow();
    
	public boolean regNewCustomer() throws Exception {
		Class.forName(dbdriver);
	    Connection connection = DriverManager.getConnection(dbname);
	    
		CustomerRegistration registration = new CustomerRegistration(parent);
		registration.setLocation(350, 350);
		registration.setVisible(true);
	
		if(registration.regCustomer(connection)) {
			ConnectionManager.closeConnection(connection);
			return true;
		} else {
			ConnectionManager.closeConnection(connection);
			return false;
		}
	}
	
	public boolean editCustomer(int kid) throws Exception {
		Class.forName(dbdriver);
	    Connection connection = DriverManager.getConnection(dbname);
	    
		ConnectionManager.setAutoCommit(connection); //turns off autocommit
		
		Customer customer = getCustomer(kid);
		CustomerRegistration registration = new CustomerRegistration(parent);
		if(registration.editCustomer(customer, connection)) {
			ConnectionManager.setAutoCommit(connection); //turns on autocommit
			ConnectionManager.closeConnection(connection);
			return true;
		} else {
			ConnectionManager.rollback(connection); //rollback if fail
			ConnectionManager.setAutoCommit(connection); //turns on autocommit
			ConnectionManager.closeConnection(connection);
			return false;
		}
	}
	
	public Customer getCustomer(int kid) throws Exception {
		Class.forName(dbdriver);
	    Connection connection = DriverManager.getConnection(dbname);
	    Statement state = connection.createStatement();
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
		ConnectionManager.closeResSet(res);
		Customer hanher = new Customer(kid, navn, telefonnr, adresse, typen);
		ConnectionManager.closeStatement(state);
		ConnectionManager.closeConnection(connection);
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
	
	public boolean listDishes() { // må gjøres //
		return true;	
	}
	
	
	
	
	public boolean setNewDish(String name, String ingredients, double price) throws Exception { //add dish//
		Class.forName(dbdriver);
	    Connection connection = DriverManager.getConnection(dbname);
	    Statement state = connection.createStatement();
	 	String sql = "INSERT INTO Dish(name, ingredients, price)values('" + name + "'" + "" +
	 			"," + "'" + ingredients + "'" + "," + price + "')";
	 	int answer = state.executeUpdate(sql);
		if(answer>0){
			state.close();
			connection.close();
			return true;
		}else{
			state.close();
			connection.close();
			return false;
		}
	}
		
	public Dish findDish(String name) throws Exception{
		Class.forName(dbdriver);
	    Connection connection = DriverManager.getConnection(dbname);
	    Statement state = connection.createStatement();
	    String sql = "SELECT * from dish where name = " + name + "";
	    ResultSet res = state.executeQuery(sql);
	    int dishID = 0;
	    ArrayList<Integer> ingredientID = new ArrayList<>();
	    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		double price = 0;
		while(res.next()){
			dishID = Integer.parseInt(res.getString("dishID"));
			price = Double.parseDouble(res.getString("price"));
			System.out.println(dishID + ":" + name + "" + "" + price);		
		}
		res.close();
		sql = "SELECT ingredientid FROM dishcontent WHERE dishID = " + dishID + "";
		res = state.executeQuery(sql);
		while(res.next()){
			ingredientID.add(Integer.parseInt(res.getString("IngredientID")));
		}
		res.close();
		int x;
		String ingredname;
		String metric;
		int amount;
		for(int i = 0; i < ingredientID.size(); i++) {
			x = ingredientID.get(i);
			sql = "SELECT * FROM ingredients WHERE ingredientid = " + x + "";
			res = state.executeQuery(sql);
			while(res.next()) {
				ingredname = res.getString("name");
				metric = res.getString("metric");
				amount = Integer.parseInt(res.getString("amount"));
				Ingredient namm = new Ingredient(ingredname, x, metric, amount);
				ingredients.add(namm);
			}
		}
		Dish retten = new Dish(dishID, name, ingredients, price);
		state.close();
		connection.close();
		return retten;
	}

}