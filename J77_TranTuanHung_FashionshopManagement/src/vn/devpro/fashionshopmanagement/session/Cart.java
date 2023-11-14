package vn.devpro.fashionshopmanagement.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vn.devpro.fashionshopmanagement.update.customer.CustomerManagement;
import vn.devpro.fashionshopmanagement.update.product.ProductManagement;

public class Cart {
	private int id;
	private int customerId;
	private List<CartProduct> list = new ArrayList<CartProduct>();
	private double totalMoney;
	Scanner sc = new Scanner(System.in);
	
	// case 1
	// hien thi gio hang cua khach
	public void display() {
		System.out.println("\n-----------YOUR CART-----------");
		System.out.println("Name customer: " + CustomerManagement.getNameById(customerId));
		System.out.println("Phone customer: " + CustomerManagement.getPhoneById(customerId));
		System.out.println("Cart has: " + list.size() + " category");
		System.out.printf("%3s %-25s %15s %12s %5s%n", "STT", "PRODUCT NAME", "PRICE", "QUANTITY", "INTO MONEY");
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("%-3d", i + 1);
			list.get(i).display();
		}
		System.out.printf("Total bill: %,.2f%n", cartTotal());
	}
	
	// case 2
	public void add() { // them mot hang vao gio hang
		System.out.println("\n------ADD A NEW PRODUCT TO THE CART------");
		System.out.print("Enter product id: ");
		int productId = Integer.parseInt(sc.nextLine());
		
		// kiem tra xem product id co trong danh sach hang ban hay khong
		int index = ProductManagement.indexOfProduct(productId);
		if (index == -1) {
			System.out.println("The product is not in the product list");
			return;
		}
		
		// co thi nhap so luong can mua
		System.out.print("Enter amount want to buy: ");
		double amount = Double.parseDouble(sc.nextLine());
		if (amount <= 0) {
			System.out.println("Amount cannot be negative");
			return;
		}
		
		// tim xem hang dinh mua co trong gio chua
		int cartIndex = indexOfCartProduct(productId);
		if (cartIndex != -1) { // hang dinh mua da co trong gio
			amount += list.get(cartIndex).getAmount(); // cong so luong trong gio voi so luong moi chon them
		}
		
		// kiem tra tong so luong mua co vuot qua tong so luong dang co ban
		if (amount > ProductManagement.getList().get(index).getAmount()) {
			System.out.println("Quantity to buy exceeds quantity to sell");
			return;
		}
		
		// them hang vao gio: 2 truong hop
		if (cartIndex != -1) { // hang da co trong gio
			list.get(cartIndex).setAmount(amount); // set lai so luong moi
		}
		else {
			list.add(new CartProduct(productId, amount)); // them hang moi vao gio
		}
		System.out.println("Add new product successfully");
	}
	
	// case 3
	public void update() { // sua hang trong gio hang
		System.out.println("\n------UPDATE PRODUCT INFORMATION IN THE CART------");
		System.out.print("Enter product id to edit: ");
		int productId = Integer.parseInt(sc.nextLine());
		
		// kiem tra hang co trong gio khong
		int cartIndex = indexOfCartProduct(productId);

		if (indexOfCartProduct(cartIndex) == -1) {
			System.out.println("The product is not in the cart");
			return;
		}
		
		// co
		System.out.print("Enter new quantity: ");
		double amount = Double.parseDouble(sc.nextLine());
		if (amount <= 0) {
			System.out.println("Amount cannot be negative");
			return;
		}
		
		// kiem tra so luong khong vuot qua so luong ban
		int index = ProductManagement.indexOfProduct(productId);
		if (amount > ProductManagement.getList().get(index).getAmount()) {
			System.out.println("Quantity to buy exceeds quantity to sell");
			return;
		}
		
		// thay so luong cu bang so luong moi
		list.get(cartIndex).setAmount(amount);
		System.out.println("Edit product successfully");
	}

	// case 4
	public void remove() { // xoa hang khoi gio hang
		System.out.println("\n------DELETE THE PRODUCT FROM THE CART------");
		System.out.print("Enter the the product id to delete: ");
		int productId = Integer.parseInt(sc.nextLine());
		
		// kiem tra hang co trong gio khong
		int cartIndex = indexOfCartProduct(productId);

		if (indexOfCartProduct(cartIndex) == -1) {
			System.out.println("The product is not in the cart");
			return;
		}
		
		// co
		list.remove(cartIndex);
		System.out.println("Delete product successfully");
	}
	
	// phuong thuc kiem tra mot hang hoa da ton tai trong gio chua
	public int indexOfCartProduct(int productId) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getProductId() == productId) {
				return i;
			}
		}
		return -1;
	}
	// tong tien o gio hang
	public double cartTotal() {
		for (CartProduct cp : list) {
			totalMoney += cp.total();
		}
		return totalMoney;
	}

	public Cart() {
		super();
	}

	public Cart(int id, int customerId, List<CartProduct> list, double totalMoney) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.list = list;
		this.totalMoney = totalMoney;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<CartProduct> getList() {
		return list;
	}

	public void setList(List<CartProduct> list) {
		this.list = list;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
}
