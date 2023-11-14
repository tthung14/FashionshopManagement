package vn.devpro.fashionshopmanagement.update;

import java.util.Scanner;

import vn.devpro.fashionshopmanagement.update.customer.CustomerManagement;
import vn.devpro.fashionshopmanagement.update.product.ProductManagement;
import vn.devpro.fashionshopmanagement.update.provider.ProviderManagement;
import vn.devpro.fashionshopmanagement.update.type.TypeManagement;

public class SystemInformationManagement {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("\n------UPDATE SYSTEM INFORMATION------");
			System.out.println("Select Action");
			System.out.println("1. Update provider information");
			System.out.println("2. Update type information");
			System.out.println("3. Update product information");
			System.out.println("4. Update customer information");
			System.out.println("0. Exit");
			System.out.print("----Enter your choose: ");
			int choose = Integer.parseInt(sc.nextLine());
			switch (choose) {
			case 1: 
				ProviderManagement.providerUpdate();
				break;
			case 2: 
				TypeManagement.typeUpdate();
				break;
			case 3: 
				ProductManagement.productUpdate();
				break;
			case 4: 
				CustomerManagement.customerUpdate();
				break;
			case 0:
				return;
			default:
				System.out.println("Please Reentered Your Choose");
			}
		} while(true);
	}
}
