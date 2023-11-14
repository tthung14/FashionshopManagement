package vn.devpro.fashionshopmanagement.update.customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CustomerManagement {
private static ArrayList<Customer> list = new ArrayList<Customer>();
	
	
	public static ArrayList<Customer> getList() {
		return list;
	}

	public static void setList(ArrayList<Customer> list) {
		CustomerManagement.list = list;
	}

	public static int autoId = 1;
	
	public static void init() {
		list.add(new Customer(autoId++, "Trần Tuấn Hùng", "0987654322"));
		list.add(new Customer(autoId++, "Tô Văn Mạnh", "0987652352"));
		list.add(new Customer(autoId++, "Nguyễn Thị Thoa", "0987612324"));
		list.add(new Customer(autoId++, "Chu Văn Tuấn", "0928453321"));
		list.add(new Customer(autoId++, "Đặng Thị Hoa", "0987274632"));
	}
	
	static Scanner sc = new Scanner(System.in);
	public static void customerUpdate() {	
		do {		
			System.out.println("\n------UPDATE CUSTOMER INFORMATION------");
			System.out.println("Select Action");
			System.out.println("1. Display the list of customers");
			System.out.println("2. Add a new customer to the list");
			System.out.println("3. Edit customer information in the list");
			System.out.println("4. Delete the customer from the list");
			System.out.println("5. Sort by customer name");
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
		System.out.println("\nLIST OF CUSTOMERS");
		System.out.printf("%-5s %-25s %-10s%n", "Id", "Name", "Phone");
		for (Customer customer : list) {
			customer.display();
		}
	}
	
	// case 2
	private static void add() {
		System.out.println("\n------ADD A NEW CUSTOMER TO THE LIST------");
		System.out.print("Enter customer name: ");
		String name = sc.nextLine();
		if (name == null || name.trim().length() == 0) {
			System.out.println("Customer name cannot be left blank");
			return;
		}
		
		System.out.print("Enter customer phone: ");
		String phone = sc.nextLine();
		if (phone == null || phone.trim().length() == 0) {
			System.out.println("Customer phone cannot be left blank");
			return;
		}
		
		Customer newCustomer = new Customer(autoId++, name, phone);
		list.add(newCustomer);
		System.out.println("Add new customer successfully");
	}
	
	// case 3
	private static void edit() {
		System.out.println("\n------EDIT CUSTOMER INFORMATION IN THE LIST------");
		System.out.print("Enter customer id to edit: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = indexOfCustomer(id);

		if (indexOfCustomer(index) == -1) {
			System.out.println("Invalid customer id");
			return;
		}
		
		System.out.print("Enter customer name: ");
		String name = sc.nextLine();
		System.out.print("Enter customer phone: ");
		String phone = sc.nextLine();
		
		list.get(index).setName(name);
		list.get(index).setPhone(phone);
		System.out.println("Edit new customer successfully");
	}
	
	// case 4
	private static void remove() {
		System.out.println("\n------DELETE THE CUSTOMER FROM THE LIST------");
		System.out.print("Enter the customer id to delete: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = indexOfCustomer(id);
		if (index == -1) {
			System.out.println("Invalid customer id");
			return;
		}
		
		list.remove(index);
		System.out.println("Delete customer successfully");
	}
	// case 5
	private static void sort() {
		System.out.println("\n------SORT BY CUSTOMER NAME------");
		Collections.sort(list, (a, b) -> a.getName().trim().substring(a.getName().lastIndexOf(' ') + 1).compareTo(b.getName().trim().substring(b.getName().lastIndexOf(' ') + 1)));
		System.out.println("Sort customer successfully");
	}
	
	public static int indexOfCustomer(int id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}	
	public static String getNameById(int id) {
		for (Customer customer : list) {
			if (customer.getId() == id) {
				return customer.getName();
			}
		}
		return null;
	}
	public static String getPhoneById(int id) {
		for (Customer customer : list) {
			if (customer.getId() == id) {
				return customer.getPhone();
			}
		}
		return null;
	}
}
