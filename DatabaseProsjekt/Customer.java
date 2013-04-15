import java.sql.*;

class Customer {
	private int kid;
	private String name;
	private int phone;
	private String adress;
	private final int type; //0 er privatkunde og 1 bedriftskunde

	public Customer(int kid, String name, int phone, String adress, int type) {
		this.kid = kid;
		this.name = name;
		this.phone = phone;
		this.adress =  adress;
		this.type = type;
	}
	public int getKid() {
		return kid;
	}
	public String getName() {
		return name;
	}
	public int getPhone() {
		return phone;
	}
	public String getAdress() {
		return adress;
	}
	public int getType() {
		return type;
	}
	public boolean setName(String newName, Connection connection) throws Exception {
	    Statement state = connection.createStatement();
		name = newName;
		String sql = "update customer set name = '" + name + "' where kid = " + kid + "";
		
		int answer = state.executeUpdate(sql);
		if(answer>0){
			ConnectionManager.closeStatement(state);
			return true;
		}else{
			ConnectionManager.closeStatement(state);
			return false;
		}
	}
	public boolean setPhone(int newPhone, Connection connection) throws Exception {
	    Statement state = connection.createStatement();
		phone = newPhone;
		String sql = "update customer set phone = " + phone + " where kid = " + kid + "";
		
		int answer = state.executeUpdate(sql);
		if(answer>0){
			ConnectionManager.closeStatement(state);
			return true;
		}else{
			ConnectionManager.closeStatement(state);
			return false;
		}
	}
	public boolean setAdress(String newAdress, Connection connection) throws Exception {
	    Statement state = connection.createStatement();
		adress = newAdress;
		String sql = "update customer set adress = '" + adress + "' where kid = " + kid + "";
		
		int answer = state.executeUpdate(sql);
		if(answer>0){
			ConnectionManager.closeStatement(state);
			return true;
		}else{
			ConnectionManager.closeStatement(state);
			return false;
		}
	}
	public String toString() {
		String x = "Kid: " + kid + ". Name: " + name + ". Phone: " + phone + ". Adress: " + adress + ". Type: " + type;
		return x;
	}
}