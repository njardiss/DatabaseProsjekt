class Ingredients {
	private static String name;
	private static int ingredientid;
	private static String metric;
	private int amount;

	public Ingredients(String name, int ingredientid, String metric, int amount) {
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
	public String setAmount(int refilledAmount) {
		amount += refilledAmount;
		String sql = "update ingredients set amount = '" + amount + "' where ingredientid = " + ingredientid + "";
		return sql;
	}
}
