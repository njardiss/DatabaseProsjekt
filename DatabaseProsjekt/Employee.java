import java.util.ArrayList;
import java.sql.*;

public class Employee {
	private final String birthdate;
	private double monthlySalary;
	private final String dateHired;
	public Employee(String birthdate, double monthlySalary, String dateHired) {
		this.birthdate = birthdate;
		this.monthlySalary = monthlySalary;
		this.dateHired = dateHired;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public double getMonthlySalary() {
		return monthlySalary;
	}
	public String getDateHired() {
		return dateHired;
	}
	public boolean setSalary(double newSalary) {
		monthlySalary = newSalary;
		return true;
	}
}
class Chef extends Employee {
	public Chef(String birthdate, double monthlySalary, String dateHired) {
		super(birthdate, monthlySalary, dateHired);
	}
}
class Salesman extends Employee {
	private double commission;
	public Salesman(String birthdate, double monthlySalary, String dateHired) {
		super(birthdate, monthlySalary, dateHired);
	}
	public double getCommission() {
		return commission;
	}
	public boolean addCommission(double newCommission) {
		commission += newCommission;
		return true;
	}
}
class Driver extends Employee {
	public Driver(String birthdate, double monthlySalary, String dateHired) {
		super(birthdate, monthlySalary, dateHired);
	}
}
class CEO extends Employee {
	public CEO(String birthdate, double monthlySalary, String dateHired) {
		super(birthdate, monthlySalary, dateHired);
	}
}
class Secretary extends Employee {
	public Secretary(String birthdate, double monthlySalary, String dateHired) {
		super(birthdate, monthlySalary, dateHired);
	}
}