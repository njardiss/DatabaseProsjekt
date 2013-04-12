import java.util.ArrayList;
import java.sql.*;

public class Dish {
    private int dishID; // genereres i database //
    private String name;
    private ArrayList<Ingredient> ingredients; // hentes fra database//
    private double price; // yo//
    private String dbdriver = "org.apache.derby.jdbc.ClientDriver";
    private String dbname = "jdbc:derby://localhost:1527/CateringBuddy;user=db;password=db";
    
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
    
    public String setName(String newName){
    	Class.forName(dbdriver);
	    Connection connect = DriverManager.getConnection(dbname);
	    Statement state = connect.createStatement();
        name = newName;
        String sql = " 'Update dish name '" + name + "' where dishID is = '" + dishID ;
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
    
    public String setPrice(double newPrice){
    	Class.forName(dbdriver);
	    Connection connect = DriverManager.getConnection(dbname);
	    Statement state = connect.createStatement();
        price = newPrice;
        String sql = "' Update dish price '" + price + "' where dishID is ='" + dishID;
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