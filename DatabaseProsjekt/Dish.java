import java.util.ArrayList;
import java.sql.*;

public class Dish {
    private int dishID; // genereres i database //
    private String name;
    private ArrayList<Ingredient> ingredients; // hentes fra database//
    private double price; // yo//
    
    public Dish(int dishID, String name,ArrayList<Ingredient> ingredients, double price){
        if(dishID == 0 || name== null || ingredients == null || price == 0){
            throw new IllegalArgumentException("Ingen av argumentene kan være null");
        }
        this.dishID = dishID;
        this.name = name.trim();
        this.ingredients = ingredients;
        this.price = price;
        
    }
    
    public int getDishID(){
        return dishID;
        
    }
    public String getName(){
        return name;
    }
    
    public ArrayList<Ingredient> getIngredients(){
        return ingredients;
    }
    
    public double getPrice(){
        return price;
    }
    public String toString() {
    	return name;
    }
    
    public boolean setName(String newName, Connection connection) throws Exception {
	    Statement state = connection.createStatement();
        name = newName;
        String sql = " 'Update dish name '" + name + "' where dishID is = '" + dishID ;
        int answer = state.executeUpdate(sql);
		if(answer>0){
			ConnectionManager.closeStatement(state);
			return true;
		}else{
			ConnectionManager.closeStatement(state);
			return false;
		}
    }
    
    public boolean setPrice(double newPrice, Connection connection) throws Exception {
	    Statement state = connection.createStatement();
        price = newPrice;
        String sql = "UPDATE dish price '" + price + "' where dishID is = " + dishID + "";
        int answer = state.executeUpdate(sql);
		if(answer>0){
			ConnectionManager.closeStatement(state);
			return true;
		}else{
			ConnectionManager.closeStatement(state);
			return false;
		}
    }
    public boolean setIngredients(ArrayList<Ingredient> newIngredients, Connection connection) throws Exception {
		Statement state = connection.createStatement();
		ingredients = newIngredients;
		int i = 1;
    	for(Ingredient anIngredient : newIngredients) { //TODO MÅ NOK FIKSES, MÅ ALT DISHCONTENT SLETTES FØRST KANSKJE?
    		String sql = "UPDATE dishcontent SET ingredientid = " + anIngredient.getIngredientId() + ", SET orderline =  WHERE dishid = " + dishID + "";
    		int answer = state.executeUpdate(sql);
    		i++;
    		if(answer<1){
    			ConnectionManager.closeStatement(state);
    			return false;
    		}
    	}
    	ConnectionManager.closeStatement(state);
    	return true;
	}
    public boolean equals(Dish dish) {
    	if(dishID == dish.getDishID()) {
    		if(name.equals(dish.getName())) {
    			if(price == dish.getPrice()) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
}
class MainCourse extends Dish {
	public MainCourse(int dishID, String name,ArrayList<Ingredient> ingredients, double price) {
		super(dishID, name, ingredients, price);
	}
}
class Dessert extends Dish {
	public Dessert(int dishID, String name,ArrayList<Ingredient> ingredients, double price) {
		super(dishID, name, ingredients, price);
	}
}
class Appetizer extends Dish {
	public Appetizer(int dishID, String name,ArrayList<Ingredient> ingredients, double price) {
		super(dishID, name, ingredients, price);
	}
}