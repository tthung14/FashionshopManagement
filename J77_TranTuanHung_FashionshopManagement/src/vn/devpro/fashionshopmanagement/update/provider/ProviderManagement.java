package vn.devpro.fashionshopmanagement.update.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProviderManagement {
	private static ArrayList<Provider> list = new ArrayList<Provider>();
	
	public static ArrayList<Provider> getList() {
		return list;
	}
	
	public static void setList(ArrayList<Provider> list) {
		ProviderManagement.list = list;
	}
	
	public static int autoId = 1;
	
	public static void init() {
		list.add(new Provider(autoId++, "Zara", "Hà Nội"));
		list.add(new Provider(autoId++, "H&M", "Bắc Ninh"));
		list.add(new Provider(autoId++, "Biti's", "TP Hồ Chí Minh"));
		list.add(new Provider(autoId++, "Louis Vuitton", "Đà Nẵng"));
		list.add(new Provider(autoId++, "Gucci", "Hải Phòng"));
	}
	
	static Scanner sc = new Scanner(System.in);
	public static void providerUpdate() {	
		do {		
			System.out.println("\n------UPDATE PROVIDER INFORMATION------");
			System.out.println("Select Action");
			System.out.println("1. Display the list of providers");
			System.out.println("2. Add a new provider to the list");
			System.out.println("3. Edit provider information in the list");
			System.out.println("4. Delete the provider from the list");
			System.out.println("5. Sort by provider name");
			System.out.println("0. Exit");
			System.out.print("----Enter your choose: ");
			int choose = Integer.parseInt(sc.nextLine());
			switch (choose) {
			case 1: 
				display();
				break;
			case 2: 
				add();
				break;
			case 3: 
				edit();
				break;
			case 4: 
				remove();
				break;
			case 5: 
				sort();
				break;
			case 0:
				return;
			default:
				System.out.println("Please Reentered Your Choose");
			}
		} while(true);
	}

	// case 1
	private static void display() {
		System.out.println("\n------LIST OF PROVIDERS------");
		System.out.printf("%-5s %-20s %-15s%n", "Id", "Name", "Address");
		for (Provider provider : list) {
			provider.display();
		}
	}
	
	// case 2
	private static void add() {
		System.out.println("\n------ADD A NEW PROVIDER TO THE LIST------");
		System.out.print("Enter provider name: ");
		String name = sc.nextLine();
		System.out.print("Enter provider address: ");
		String address = sc.nextLine();
		
		// kiem tra ten khong duoc de trong
		if (name == null || name.trim().length() == 0) {
			System.out.println("Provider name cannot be left blank");
			return;
		}
		if (address == null || address.trim().length() == 0) {
			System.out.println("Provider address cannot be left blank");
			return;
		}
		
		// kiem tra ten ton tai hay chua
		if (nameIsExist(name)) {
			System.out.println("This name already exists in the list");
			return;
		}
		Provider newProvider = new Provider(autoId++, name, address);
		list.add(newProvider);
		System.out.println("Add new provider successfully");
	}
	
	// case 3
	private static void edit() {
		System.out.println("\n------EDIT PROVIDER INFORMATION IN THE LIST------");
		System.out.print("Enter provider id to edit: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = indexOfProvider(id);

		if (index == -1) {
			System.out.println("Invalid provider id");
			return;
		}
		
		System.out.print("Enter provider name: ");
		String name = sc.nextLine();
		if (name == null || name.trim().length() == 0) {
			System.out.println("Provider name cannot be left blank");
			return;
		}
		if (nameIsExist(name)) {
			System.out.println("This name already exists in the list");
			return;
		}
		
		System.out.print("Enter provider address: ");
		String address = sc.nextLine();
		if (address == null || address.trim().length() == 0) {
			System.out.println("Provider address cannot be left blank");
			return;
		}
		
		// thay the ten cu bang ten moi
		list.get(index).setName(name);
		list.get(index).setAddress(address);
		System.out.println("Edit new provider successfully");
	}
	
	// case 4
	private static void remove() {
		System.out.println("\n------DELETE THE PROVIDER FROM THE LIST------");
		System.out.print("Enter the provider id to delete: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = indexOfProvider(id);
		if (index == -1) {
			System.out.println("Invalid provider id");
			return;
		}
		
		list.remove(index);
		System.out.println("Delete provider successfully");
	}
	
	// case 5
	private static void sort() {
		System.out.println("\n------SORT BY PROVIDER NAME------");
		Collections.sort(list, (a, b) -> a.getName().compareTo(b.getName()));
		System.out.println("Sort provider successfully");
	}
	
	// ham tra ve vi tri cua nha cung cap
	public static int indexOfProvider(int id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	// ham tim ten co trong khong
	public static boolean nameIsExist(String name) {
		for (Provider provider : list) {
			if (provider.getName().trim().equalsIgnoreCase(name.trim())) {
				return true;
			}
		}
		return false;
	}
	//ham lay ten theo id
	public static String getNameById(int id) {
		for (Provider provider : list) {
			if (provider.getId() == id) {
				return provider.getName();
			}
		}
		return null;
	}
}
