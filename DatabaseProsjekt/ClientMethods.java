import java.sql.*;

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
	
	
	
	
	/*public boolean setNewDish(String name, String ingredients, double price) throws Exception { //add dish//
		Class.forName(dbdriver);
	    Connection connection = DriverManager.getConnection(dbname);
	    Statement state = connection.createStatement();
	 	String sql = "INSERT INTO Dish(name, ingredients, price)values('" + name + "'
	 	,'"' + ingredients + ', ' + price + '"');
	 	int answer = state.executeUpdate(sql);
		if(answer>0){
			state.close();
			connection.close();
			return true;
		}else{
			state.close();
			connection.close();
			return false;
	}*/
		
	/*	public boolean findDish(String name){
			Class.forName(dbdriver);
		    Connection connection = DriverManager.getConnection(dbname);
		    Statement state = connection.createStatement();
		    String sql = "SELECT * from dish where name = " + name + "";
		    ResultSet res = state.executeQuery(sql);
		    int dishID = 0;
			String name = "";
			String ingredient = 0;
			double price = "";
			while(res.next()){
				dishID = Integer.parseInt(res.getString("dishID"));
				name = res.getString("name");
				ingredient = (res.getString("ingredient"));
				price = double.parseDouble(res.getString("price"));
				System.out.println(dishId + ":" + name + "" + ingredients + "" + price);
				
				
			}
			res.close();
			Dish retten = new Dish(dishID, name, ingredient, price);
			state.close();
			connection.close();
			return retten;
		}*/
		
}
