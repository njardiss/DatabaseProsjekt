import java.sql.*;
import java.util.ArrayList;

class ClientMethods {
 	private String dbdriver = "org.apache.derby.jdbc.ClientDriver";
    private String dbname = "jdbc:derby://localhost:1527/CateringBuddy;user=db;password=db";
    private ParentWindow parent = new ParentWindow();
    
	public boolean regNewCustomer() throws Exception {
		CustomerRegistration registration = new CustomerRegistration(parent);
		registration.setLocation(350, 350);
		registration.setVisible(true);
		String sql = "";
		try {
			sql = registration.regCustomer();
		} catch (NullPointerException e) {
			ConnectionManager.printMessage(e,"Some data is not present");
		}
		Class.forName(dbdriver);
	    Connection connection = DriverManager.getConnection(dbname);
		Statement state = connection.createStatement();
    	int answer = state.executeUpdate(sql);
		if(answer>0){
			ConnectionManager.closeStatement(state);
			ConnectionManager.closeConnection(connection);
			return true;
		} else {
			ConnectionManager.closeStatement(state);
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
		String[] data = new String[3];
		try {
			data = registration.editCustomer(customer);
		} catch (NullPointerException e) {
			ConnectionManager.printMessage(e,"Some data is not present");
		}
		boolean j = false, k = false, l = false;
		if(!data[0].equals(customer.getName())) {
    		j = customer.setName(data[0], connection);
    	}
    	if(Integer.parseInt(data[1]) != (customer.getPhone())) {
    		k = customer.setPhone(Integer.parseInt(data[1]), connection);
    	}
    	if(!data[2].equals(customer.getAdress())) {
    		l = customer.setAdress(data[2], connection);
    	}

		if(j && k && l) {
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
	public boolean addOrder() throws Exception {
		Order order;
		OrderMenu orderMenu = new OrderMenu(parent);
		orderMenu.setLocation(350, 350);
		orderMenu.setVisible(true);
		order = orderMenu.getOrder();
		String sql = "INSERT INTO orders(kid, status, ordertime, deliverytime, deliveryadress, price) values('" + name.getText() + "'" +"" +
				"," + Integer.parseInt(phone.getText()) + ", '" + adress.getText() + "'," + type + ")";
		return true;
	}
	
	public ArrayList<Dish> listDishes() throws Exception {
		Class.forName(dbdriver);
	    Connection connection = DriverManager.getConnection(dbname);
	    Statement state = connection.createStatement();
		ArrayList<Dish> dishes = new ArrayList<Dish>();
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		String sql = "SELECT * from dishes";
		ResultSet res = state.executeQuery(sql);
		while(res.next()) {
			int dishID = Integer.parseInt(res.getString("dishid"));
			String name = res.getString("name");
			double price = Double.parseDouble(res.getString("price"));
			String type = res.getString("type");
			String sql2 = "SELECT * from ingredients NATURAL JOIN dishcontent where dishcontent.dishid = " + dishID + " AND dishcontent.ingredientid = ingredients.ingredientid";
			ResultSet res2 = state.executeQuery(sql2);
			
			while(res2.next()) {
				String name2 = res2.getString("name");
				int ingredientid = Integer.parseInt(res2.getString("ingredientid"));
				String metric = res2.getString("metric");
				int amount = Integer.parseInt(res2.getString("amount"));

				Ingredient ingredient = new Ingredient(name2, ingredientid, metric, amount);
				ingredients.add(ingredient);
			}
			if(type.equals("Main course")) { //decides the subclass of Dish
				MainCourse dish = new MainCourse(dishID, name, ingredients, price);
				dishes.add(dish);
			} else if(type.equals("Appetizer")) {
				Appetizer dish = new Appetizer(dishID, name, ingredients, price);
				dishes.add(dish);
			} else if(type.equals("Dessert")) {
				Dessert dish = new Dessert(dishID, name, ingredients, price);
				dishes.add(dish);
			}			
			ConnectionManager.closeResSet(res2);
		}
		ConnectionManager.closeResSet(res);
		ConnectionManager.closeStatement(state);
		ConnectionManager.closeConnection(connection);
		
		return dishes;
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

	public boolean addIngredient() throws Exception{    
		IngredientRegistration ingredientRegistration = new IngredientRegistration(parent);
		ingredientRegistration.setLocation(350, 350);
		ingredientRegistration.setVisible(true);
		
		String sql = "";
		try {
			sql = ingredientRegistration.addIngredient();
		} catch (NullPointerException e) {
			ConnectionManager.printMessage(e,"Some data is not present");
		}
		Class.forName(dbdriver);
	    Connection connection = DriverManager.getConnection(dbname);
	    Statement state = connection.createStatement();
	    
	    int answer = state.executeUpdate(sql);
		if(answer>0){
			ConnectionManager.closeStatement(state);
			ConnectionManager.closeConnection(connection);
			return true;
		} else {
			ConnectionManager.closeStatement(state);
			ConnectionManager.closeConnection(connection);
			return false;
		}
	}
}