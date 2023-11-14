package vn.devpro.fashionshopmanagement.session;

import vn.devpro.fashionshopmanagement.update.product.Product;
import vn.devpro.fashionshopmanagement.update.product.ProductManagement;

public class CartProduct {
	private int productId;
	private double amount;
	
	public void display() {
		Product product = ProductManagement.getProductById(productId);
		
		System.out.printf("%-25s %,15.2f %,12.2f  %,15.2f%n", product.getName(), product.getPrice(), 
				this.amount, this.amount * product.getPrice());
	}
	
	public double total() {
		Product product = ProductManagement.getProductById(productId);
		return this.amount * product.getPrice();
	}
	
	public CartProduct() {
		super();
	}
	public CartProduct(int productId, double amount) {
		super();
		this.productId = productId;
		this.amount = amount;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
