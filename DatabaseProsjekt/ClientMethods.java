class ClientMethods {
	public String regNewCustomer(String name, int phone, String adress, int type) {
		String sql = "INSERT INTO customer(name, phone, adress, type) values('" + name + "'" +"" +
				"," + phone + ", '" + adress + "'," + type + ")";
		return sql;
	}
	public String getCustomer(int kid) {
		String sql = "SELECT * from customer where kid = " + kid + "";
		return sql;
	}
	public String findOrders(int kid) {
		String sql = "SELECT * from orders where kid = " + kid + "";
		return sql;
	}
	/*public String setNewDish(String name, String ingredients, double price){
	 	String sql = "INSERT INTO Dish(name, ingredients, price)values('" + name + "'
	 	,'" + ingredients + "', '" + price + "');
		return sql;
	}*/
}
