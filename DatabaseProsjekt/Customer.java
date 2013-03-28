class Customer {
	private int kid;
	private String name;
	private int phone;
	private String adress;
	private final int type;

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
	public String setName(String newName) {
		name = newName;
		String sql = "update customer set name = '" + name + "' where kid = " + kid + "";
		return sql;
	}
	public String setPhone(int newPhone) {
		phone = newPhone;
		String sql = "update customer set phone = '" + phone + "' where kid = " + kid + "";
		return sql;
	}
	public String setAdress(String newAdress) {
		adress = newAdress;
		String sql = "update customer set adress = '" + adress + "' where kid = " + kid + "";
		return sql;
	}
}