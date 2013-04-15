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
        this.ingredients = new ArrayList<Ingredient>();
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
        String sql = "' Update dish price '" + price + "' where dishID is ='" + dishID;
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