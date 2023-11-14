package vn.devpro.fashionshopmanagement.update.product;

import vn.devpro.fashionshopmanagement.update.provider.ProviderManagement;
import vn.devpro.fashionshopmanagement.update.type.TypeManagement;

public class Product {
	private int id;
	private int providerId;
	private int typeId;
	private String name;
	private double amount;
	private double price;
	
	public void display() {
		System.out.printf("%5d %-20s %-15s %-25s %,12.2f %,15.2f%n", this.id, ProviderManagement.getNameById(providerId), TypeManagement.getNameById(typeId),this.name, this.amount, this.price);
	}
	
	public Product() {
		super();
	}

	public Product(int id, int providerId, int typeId, String name, double amount, double price) {
		super();
		this.id = id;
		this.providerId = providerId;
		this.typeId = typeId;
		this.name = name;
		this.amount = amount;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
