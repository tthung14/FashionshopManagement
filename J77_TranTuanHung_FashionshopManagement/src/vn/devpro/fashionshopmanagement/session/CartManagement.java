package vn.devpro.fashionshopmanagement.session;

import java.util.Scanner;

import vn.devpro.fashionshopmanagement.order.OrderManagement;
import vn.devpro.fashionshopmanagement.update.customer.Customer;
import vn.devpro.fashionshopmanagement.update.customer.CustomerManagement;

public class CartManagement {
	public static int autoId = 1;
	
	static Scanner sc = new Scanner(System.in);
	
	public static void cartManagement() {
		Cart cart = new Cart();
		
		System.out.println("\n------WELCOME------");
		do {
			System.out.println("\nSelect Action");
			System.out.println("1. See cart information");
			System.out.println("2. Add product into the cart");
			System.out.println("3. Edit product infomation in the cart");
			System.out.println("4. Delete product from cart");
			System.out.println("5. Payment");
			System.out.println("0. Exit");
			System.out.print("----Enter your choose: ");
			int choose = Integer.parseInt(sc.nextLine());
			switch (choose) {
			case 1: 
				cart.display();
				break;
			case 2: 
				cart.add();
				break;
			case 3: 
				cart.update();
				break;
			case 4: 
				cart.remove();
				break;
			case 5:
				if (payment(cart)) { // thanh toan thanh cong
					cart = new Cart(); // xoa gio hang sau khi thanh toan
				}
				break;
			case 0:
				return;
			default:
				System.out.println("Please Reentered Your Choose");
			}
		} while(true);
	}
	
	private static boolean payment(Cart cart) { // thanh toan gio hang
		if (cart == null || cart.getList().size() <= 0) {
			System.out.println("You haven't selected the product");
			return false;
		}
		System.out.println("\n------PAYMENT THE CART------");
		// cap nhat thong tin khach hang
		System.out.print("Enter the customer id: ");
		int customerId = Integer.parseInt(sc.nextLine());
		// kiem tra khach hang da co trong ds luu hay chua
		int index = CustomerManagement.indexOfCustomer(customerId);
		String customerName = null;
		String customerPhone = null;
		
		if (index == -1) { // khach hang chua co trong danh sach luu
			System.out.print("Enter the customer name: ");
			customerName = sc.nextLine();
			if (customerName.trim().length() <= 0) {
				System.out.println("Customer name cannot be left blank");
				return false;
			}
			System.out.print("Enter the customer phone: ");
			customerPhone = sc.nextLine();
			if (customerPhone.trim().length() <= 0) {
				System.out.println("Customer phone cannot be left blank");
				return false;
			}
			
			// them khach hang vao danh sach khach hang
			customerId = CustomerManagement.autoId++;
			CustomerManagement.getList().add(new Customer(customerId, customerName, customerPhone));
		}
		else { // khach hang da co trong danh sach
			customerName = CustomerManagement.getList().get(index).getName();
			customerPhone = CustomerManagement.getList().get(index).getPhone();
		}
		
		// cap nhat thong tin cho gio hang
		cart.setId(autoId++);
		cart.setCustomerId(customerId);
		
		// hien thi lai cho khach xem 
		cart.display();
		// luu gio hang vao danh sach
		OrderManagement.getCarts().add(cart);
		System.out.println("Payment cart successfully");
		return true;
	}
}
