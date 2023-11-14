package vn.devpro.fashionshopmanagement.update.provider;

public class Provider {
	private int id;
	private String name;
	private String address;
	
	public void display() {
		System.out.printf("%5d %-20s %-15s%n", this.id, this.name, this.address);
	}

	public Provider() {
		super();
	}

	public Provider(int id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
