import java.sql.*;

public class Employee {
	private int phone;
	private String name;
	private double monthlySalary;
	private String dateHired;
	public Employee(int phone, String name, double monthlySalary) {
		this.phone = phone;
		this.name = name;
		this.monthlySalary = monthlySalary;
	}
	public int getPhone() {
		return phone;
	}
	public String getName() {
		return name;
	}
	public double getMonthlySalary() {
		return monthlySalary;
	}
	public String getDateHired() {
		return dateHired;
	}
	public boolean setPhone(int newPhone) {
		phone = newPhone;
		return true;
	}
	public boolean setName(String newName){
		name = newName;
		return true;
	}
	public boolean setSalary(double newSalary) {
		monthlySalary = newSalary;
		return true;
	}
}
class Chef extends Employee { //type 4 (from database)
	public Chef(int phone, String name, double monthlySalary) {
		super(phone, name, monthlySalary);
	}
}
class Salesman extends Employee { //type 1 (from database)
	private double commission;
	public Salesman(int phone, String name, double monthlySalary, double commission) {
		super(phone, name, monthlySalary);
		this.commission = commission;
	}
	public double getCommission() {
		return commission;
	}
	public boolean addCommission(double newCommission, Connection connection) throws Exception {
		Statement state = connection.createStatement();
		commission += newCommission;
		String sql = "UPDATE employees SET commission = " + commission + " where phone = " + getPhone() + "";
			
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
class Driver extends Employee { //type 2 (from database)
	public Driver(int phone, String name, double monthlySalary) {
		super(phone, name, monthlySalary);
	}
}
class CEO extends Employee { //type 0 (from database)
	public CEO(int phone, String name, double monthlySalary) {
		super(phone, name, monthlySalary);
	}
}
class Secretary extends Employee { //type 3 (from database)
	public Secretary(int phone, String name, double monthlySalary) {
		super(phone, name, monthlySalary);
	}
}