import java.util.ArrayList;

class Order {
	private final int orderid;
	private int kid;
	private String status;
	private String orderTime;
	private String deliveryTime; // peace/////  out ///
	private String deliveryAdress;
	private ArrayList<Dish> orderContent;
	
	public Order(int orderid,int kid, String status, String orderTime, String deliveryTime, String deliveryAdress, ArrayList<Dish> orderContent) {
		this.orderid = orderid;
		this.kid = kid;
		this.status = status;
		this.orderTime = orderTime;
		this.deliveryTime = deliveryTime;
		this.deliveryAdress = deliveryAdress;
		this.orderContent = new ArrayList<Dish>();
	}
	public int getOrderid() {
		return orderid;
	}
	public int getKid() {
		return kid;
	}
	public String getStatus() {
		return status;
	}
	public String orderTime() {
		return orderTime;
	}
	public String deliveryTime() {
		return deliveryTime;
	}
	public String getDeliveryAdress() {
		return deliveryAdress;
	}
	public ArrayList<Dish> getOrderContent() {
		return orderContent;
	}
	public String setStatus(String newStatus) {
		status = newStatus;
		String sql = "update orders set status = '" + status + "' where orderid = " + orderid + "";
		return sql;
	}
	public String setDeliveryTime(String newTime) {
		deliveryTime = newTime;
		String sql = "update orders set deliverytime = '" + deliveryTime + "' where orderid = " + orderid + "";
		return sql;
	}
	public String setDeliveryAdress(String newDeliveryAdress) {
		deliveryAdress = newDeliveryAdress;
		String sql = "update orders set deliveryadress = '" + deliveryAdress + "' where kid = " + kid + " and where orderid = " + orderid + "";
		return sql;
	}
	public String toString() {
		String x = "Order ID: " + orderid + ". Kid: " + kid + ". Status: " + status + 
				". Ordertime: " + orderTime + ". Deliverytime: " + deliveryTime +
				". Delivery Adress: " + deliveryAdress + ". OrderContent: " + orderContent;
		return x;
	}
}