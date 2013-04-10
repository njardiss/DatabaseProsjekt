import java.util.ArrayList;

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
    
    public String setName(String newName){
        name = newName;
        String sql = " 'Update dish name '" + name + "' where dishID is = '" + dishID ;
        return sql;
    }
    
    public String setPrice(double newPrice){
        price = newPrice;
        String sql = "' Update dish price '" + price + "' where dishID is ='" + dishID;
        return sql;
    }
}