class clientMethods {
	public String regNewCustomer(String name, int phone, String adress, String deliveryAdress, int type) {
		String sql = "INSERT INTO customer(name, phone, adress, deliveryadress, type) values(('" + name + "'),
		('" + phone + "'), ('" + adress + "'), ('" + deliveryAdress + "'),('" + type + "'))";
		return sql;
	}
	
	// njardiss sicke ducks
	public String getCustomer(int kid) {
		String sql = "SELECT * from customer where kid = ('" + kid + "')";
		return sql;
	}
	
	public String findOrders(int kid) {
		String sql = "SELECT * from orders where kid = ('" + kid + "')";
		return sql;
	}
	
	public String setNewDish(Dishes newDish) {
	 	String sql = "INSERT INTO Dish(dishID, name, ingredients, price)values('" + newDish.getDishID() + "',
		'" + newDish.getName() + "', '" + newDish.getIngredients() + "', '" + newDish.getPrice();
    return sql;
 }
}