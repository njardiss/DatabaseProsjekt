import java.sql.*;

class Customer {
	private int kid;
	private String name;
	private int phone;
	private String adress;
	private final int type; //0 er privatkunde og 1 bedriftskunde
	private String dbdriver = "org.apache.derby.jdbc.ClientDriver";
    private String dbname = "jdbc:derby://localhost:1527/CateringBuddy;user=db;password=db";

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
		Class.forName(dbdriver);
	    Statement state = connection.createStatement();
		name = newName;
		String sql = "update customer set name = '" + name + "' where kid = " + kid + "";
		
		int answer = state.executeUpdate(sql);
		if(answer>0){
			state.close();
			return true;
		}else{
			state.close();
			return false;
		}
	}
	public boolean setPhone(int newPhone, Connection connection) throws Exception {
		Class.forName(dbdriver);
	    Statement state = connection.createStatement();
		phone = newPhone;
		String sql = "update customer set phone = " + phone + " where kid = " + kid + "";
		
		int answer = state.executeUpdate(sql);
		if(answer>0){
			state.close();
			return true;
		}else{
			state.close();
			return false;
		}
	}
	public boolean setAdress(String newAdress, Connection connection) throws Exception {
		Class.forName(dbdriver);
	    Statement state = connection.createStatement();
		adress = newAdress;
		String sql = "update customer set adress = '" + adress + "' where kid = " + kid + "";
		
		int answer = state.executeUpdate(sql);
		if(answer>0){
			state.close();
			return true;
		}else{
			state.close();
			return false;
		}
	}
	public String toString() {
		String x = "Kid: " + kid + ". Name: " + name + ". Phone: " + phone + ". Adress: " + adress + ". Type: " + type;
		return x;
	}
}