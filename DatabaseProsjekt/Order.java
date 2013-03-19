import java.util.ArrayList;

class Order {
	public final int orderid;
	public int kid;
	public String status;
	public int orderTime;
	public int deliveryTime;
	public ArrayList<Dishes> orderContent;
	
	public Order(int orderid,int kid, String status, int orderTime, int deliveryTime, ArrayList<Dishes> orderContent) {
		this.orderid = orderid;
		this.kid = kid;
		this.status = status;
		this.orderTime = orderTime;
		this.deliveryTime = deliveryTime;
		this.orderContent = new ArrayList<Dishes>();
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
	public int orderTime() {
		return orderTime;
	}
	public int deliveryTime() {
		return deliveryTime;
	}
	public ArrayList<Dishes> getOrderContent() {
		return orderContent;
	}
	public String setStatus(String newStatus) {
		status = newStatus;
		String sql = "update orders set status = '" + status + "' where orderid = " + orderid + "";
		return sql;
	}
	public String setDeliveryTime(int newTime) {
		deliveryTime = newTime;
		String sql = "update orders set deliverytime = '" + deliveryTime + "' where orderid = " + orderid + "";
		return sql;
	}
}