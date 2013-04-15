import java.sql.*;

class Ingredient {
	private String name;
	private int ingredientid;
	private String metric;
	private int amount;

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
	public boolean setAmount(int refilledAmount, Connection connection) throws Exception {
	    Statement state = connection.createStatement();
		amount += refilledAmount;
		String sql = "update ingredients set amount = '" + amount + "' where ingredientid = " + ingredientid + "";
		
		int answer = state.executeUpdate(sql);
		if(answer>0){
			ConnectionManager.closeStatement(state);
			return true;
		}else{
			ConnectionManager.closeStatement(state);
			return false;
		}
	}
}
