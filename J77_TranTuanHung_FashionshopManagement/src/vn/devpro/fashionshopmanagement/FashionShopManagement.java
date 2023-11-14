package vn.devpro.fashionshopmanagement;

import java.util.Scanner;

import vn.devpro.fashionshopmanagement.order.OrderManagement;
import vn.devpro.fashionshopmanagement.session.CartManagement;
import vn.devpro.fashionshopmanagement.update.SystemInformationManagement;
import vn.devpro.fashionshopmanagement.update.customer.CustomerManagement;
import vn.devpro.fashionshopmanagement.update.product.ProductManagement;
import vn.devpro.fashionshopmanagement.update.provider.ProviderManagement;
import vn.devpro.fashionshopmanagement.update.type.TypeManagement;

public class FashionShopManagement {
	public static void main(String[] args) {
		ProviderManagement.init();
		TypeManagement.init();
		CustomerManagement.init();
		ProductManagement.init();
		
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("\n------FASHION SHOP MANAGEMENT PROGRAM------");
			System.out.println("Select Action");
			System.out.println("1. Update system information");
			System.out.println("2. Trading sessions management");
			System.out.println("3. Order management");
			System.out.println("0. Exit");
			System.out.print("----Enter your choose: ");
			int choose = Integer.parseInt(sc.nextLine());
			switch (choose) {
			case 1: 
				SystemInformationManagement.execute();
				break;
			case 2: 
				CartManagement.cartManagement();
				break;
			case 3: 
				OrderManagement.orderManagerment();
				break;
			case 0:
				System.out.println("Program Exit");
				System.exit(0);
				break;
			default:
				System.out.println("Please Reentered Your Choose");
			}
		} while(true);
	}
}
