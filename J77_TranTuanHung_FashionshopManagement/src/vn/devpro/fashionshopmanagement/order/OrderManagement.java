package vn.devpro.fashionshopmanagement.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vn.devpro.fashionshopmanagement.session.Cart;
import vn.devpro.fashionshopmanagement.session.CartProduct;
import vn.devpro.fashionshopmanagement.update.customer.CustomerManagement;
import vn.devpro.fashionshopmanagement.update.product.ProductManagement;

public class OrderManagement {
	private static List<Cart> carts = new ArrayList<Cart>();

	public static List<Cart> getCarts() {
		return carts;
	}

	public static void setCarts(List<Cart> carts) {
		OrderManagement.carts = carts;
	}
	
	static Scanner sc = new Scanner(System.in);
	public static void orderManagerment() {
		do {		
			System.out.println("\n------ORDER MANAGEMENT------");
			System.out.println("Select Action");
			System.out.println("1. Display the list of order");
			System.out.println("2. Delete the order from the list");
			System.out.println("3. Display total revenue from all carts");
			System.out.println("4. Display total money earned by customer");
			System.out.println("5. Display total money earned by product");
			System.out.println("0. Exit");
			System.out.print("----Enter your choose: ");
			int choose = Integer.parseInt(sc.nextLine());
			switch (choose) {
			case 1: 
				displayOrder();
				break;
			case 2: 
				deleteOrder();
				break;
			case 3: 
				displayTotalRevenue();
				break;
			case 4: 
				displayEarnByCustomer();
				break;
			case 5: 
				displayEarnByProduct();
				break;
			case 0:
				return;
			default:
				System.out.println("Please Reentered Your Choose");
			}
		} while(true);
	}
	
	// case 1
	private static void displayOrder() {
		System.out.println("\n------LIST OF ORDER------");
		System.out.printf("%3s %8s %-25s %15s%n", "STT", "Order Id", "Customer Name", "Total Money");
		int stt = 1;
		for (Cart cart : carts) {
			System.out.printf("%3d %8d %-25s %,15.2f%n", stt++, cart.getId(), 
					CustomerManagement.getNameById(cart.getCustomerId()), cart.getTotalMoney());
		}
	}
	
	// case 2
	private static void deleteOrder() {
		System.out.println("\n------DELETE THE ORDER FROM THE LIST------");
		System.out.print("Enter the order id to delete: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = indexOfOrder(id);
		if (index == -1) {
			System.out.println("Invalid order id");
			return;
		}
		
		carts.remove(index);
		System.out.println("Delete order successfully");
	}
	
	// case 3
	private static double totalRevenue() {
		double total = 0;
		for (Cart cart : carts) {
			total += cart.getTotalMoney();
		}
		return total;
	}
	private static void displayTotalRevenue() {
		System.out.printf("\nTotal revenue: %,.2f%n", totalRevenue());
	}
	
	// case 4
	private static double totalEarnByCustomer(int id) {
		double total = 0;
		for (Cart cart : carts) {
			if (cart.getCustomerId() == id)
				total += cart.getTotalMoney();
		}
		return total;
	}
	private static void displayEarnByCustomer() {
		System.out.println("\n------TOTAL MONEY EARNED BY CUSTOMER------");
		System.out.printf("%3s %-25s %15s%n", "STT", "Customer Name", "Revenue");
		int stt = 1;
		// danh sach id
		List<Integer> listCustomerId = new ArrayList<>();
		
		for (Cart cart : carts) {
			int customerId = cart.getCustomerId();
			if (!listCustomerId.contains(customerId)) { // neu id khac trong danh sach vua tao thi in ra
				System.out.printf("%3d %-25s %,15.2f%n", stt++,
					CustomerManagement.getNameById(cart.getCustomerId()),
					totalEarnByCustomer(cart.getCustomerId()));
				
				listCustomerId.add(customerId);
			}
		}
	}
	
	// case 5
	private static double totalEarnByProduct(int id) {
		double total = 0;
		for (Cart cart : carts) { 
			for (CartProduct cartProduct : cart.getList()) {
	            if (cartProduct.getProductId() == id) {
	                total += cartProduct.total();
	            }
	        }
		}
		return total;
	}
	private static void displayEarnByProduct() {
		System.out.println("\n------TOTAL MONEY EARNED BY PRODUCT------");
		System.out.printf("%3s %-25s %15s%n", "STT", "Product Name", "Revenue");
		int stt = 1;
		
		List<Integer> listProductId = new ArrayList<>();
		
		for (Cart cart : carts) {
			for (CartProduct cartProduct : cart.getList()) {
	            int productId = cartProduct.getProductId();
	            if (!listProductId.contains(productId)) {
	                System.out.printf("%3d %-25s %,15.2f%n", stt++,
	                        ProductManagement.getNameById(productId),
	                        totalEarnByProduct(productId));

	                listProductId.add(productId);
	            }
	        }	
		}
	}
	
	public static int indexOfOrder(int id) {
		for (int i = 0; i < carts.size(); i++) {
			if (carts.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}	
}
