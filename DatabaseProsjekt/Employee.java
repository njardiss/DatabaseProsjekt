import java.util.ArrayList;
import java.sql.*;

public class Employee {
	private int phone;
	private String name;
	private double monthlySalary;
	private final String dateHired;
	public Employee(int phone, String name, double monthlySalary, String dateHired) {
		this.phone = phone;
		this.name = name;
		this.monthlySalary = monthlySalary;
		this.dateHired = dateHired;
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
	public Chef(int phone, String name, double monthlySalary, String dateHired) {
		super(phone, name, monthlySalary, dateHired);
	}
}
class Salesman extends Employee { //type 1 (from database)
	private double commission;
	public Salesman(int phone, String name, double monthlySalary, String dateHired) {
		super(phone, name, monthlySalary, dateHired);
	}
	public double getCommission() {
		return commission;
	}
	public boolean addCommission(double newCommission) {
		commission += newCommission;
		return true;
	}
}
class Driver extends Employee { //type 2 (from database)
	public Driver(int phone, String name, double monthlySalary, String dateHired) {
		super(phone, name, monthlySalary, dateHired);
	}
}
class CEO extends Employee { //type 0 (from database)
	public CEO(String birthdate, int phone, String name, double monthlySalary, String dateHired) {
		super(phone, name, monthlySalary, dateHired);
	}
}
class Secretary extends Employee { //type 3 (from database)
	public Secretary(String birthdate, int phone, String name, double monthlySalary, String dateHired) {
		super(phone, name, monthlySalary, dateHired);
	}
}