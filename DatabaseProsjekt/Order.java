import java.util.ArrayList;

class Order {
	public final int orderid;
	public int kid;
	public String status;
	public int orderTime;
	public int deliveryTime;
	public ArrayList<Dishes> orderContent;
	
	public Order(int orderid,int kid, String status, int orderTime, int deliveryTime, Dishes orderContent) {
	}
}