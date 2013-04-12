import java.sql.*;

class Ingredient {
	private String name;
	private int ingredientid;
	private String metric;
	private int amount;
	private String dbdriver = "org.apache.derby.jdbc.ClientDriver";
    private String dbname = "jdbc:derby://localhost:1527/CateringBuddy;user=db;password=db";

	public Ingredient(String name, int ingredientid, String metric, int amount) {
		this.name = name;
		this.ingredientid = ingredientid;
		this.metric = metric;
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public int getIngredientId() {
		return ingredientid;
	}
	public String getMetric() {
		return metric;
	}
	public int getAmount() {
		return amount;
	}
	public boolean setAmount(int refilledAmount) {
		Class.forName(dbdriver);
	    Connection connect = DriverManager.getConnection(dbname);
	    Statement state = connect.createStatement();
		amount += refilledAmount;
		String sql = "update ingredients set amount = '" + amount + "' where ingredientid = " + ingredientid + "";
		
		int answer = state.executeUpdate(sql);
		if(answer>0){
			state.close();
			connect.close();
			return true;
		}else{
			state.close();
			connect.close();
			return false;
		}
	}
}
