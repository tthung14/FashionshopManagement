package vn.devpro.fashionshopmanagement.update.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import vn.devpro.fashionshopmanagement.update.customer.Customer;
import vn.devpro.fashionshopmanagement.update.provider.ProviderManagement;
import vn.devpro.fashionshopmanagement.update.type.TypeManagement;

public class ProductManagement {
	private static ArrayList<Product> list = new ArrayList<Product>();
	
	public static ArrayList<Product> getList() {
		return list;
	}
	
	public static void setList(ArrayList<Product> list) {
		ProductManagement.list = list;
	}
	
	public static int autoId = 1;
	
	public static void init() {
		list.add(new Product(autoId++, 1, 3, "Áo Phông", 95, 15000000));
		list.add(new Product(autoId++, 3, 2, "Quần Jean", 90, 100000));
		list.add(new Product(autoId++, 4, 5, "Túi Yadou", 100, 500000));
		list.add(new Product(autoId++, 2, 1, "Váy Banamo", 80, 750000));
		list.add(new Product(autoId++, 5, 4, "Giầy Air Force 1", 85, 15000));
	}
	
	static Scanner sc = new Scanner(System.in);
	public static void productUpdate() {	
		do {		
			System.out.println("\n------UPDATE PRODUCT INFORMATION------");
			System.out.println("Select Action");
			System.out.println("1. Display the list of products");
			System.out.println("2. Add a new product to the list");
			System.out.println("3. Edit product information in the list");
			System.out.println("4. Delete the product from the list");
			System.out.println("5. Sort by product name");
			System.out.println("6. Search by product name");
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
			case 6: 
				search();
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
		System.out.println("\nLIST OF PRODUCTS");
		System.out.printf("%-5s %-20s %-15s %-25s %-12s %-15s%n", "Id", "Provider Name", "Type Name", "Product Name", "Amount", "Price");
		for (Product product : list) {
			product.display();
		}
	}
	
	// case 2
	private static void add() {
		System.out.println("\n------ADD A NEW PRODUCT TO THE LIST------");
		System.out.print("Enter provider id: ");
		int providerId = Integer.parseInt(sc.nextLine());
		if (ProviderManagement.indexOfProvider(providerId) == -1) {
			System.out.println("Provider id invalid");
			return;
		}
		
		System.out.print("Enter type id: ");
		int typeId = Integer.parseInt(sc.nextLine());
		if (TypeManagement.indexOfType(typeId) == -1) {
			System.out.println("Type id invalid");
			return;
		}
		
		System.out.print("Enter Product Name: ");
		String name = sc.nextLine();
		if (name == null || name.trim().length() == 0) {
			System.out.println("Product name cannot be left blank");
			return;
		}
		
		System.out.print("Enter amount: ");
		double amount = Double.parseDouble(sc.nextLine());
		if (amount < 0) {
			System.out.println("Amount cannot be negative");
			return;
		}
		
		System.out.print("Enter Price: ");
		double price = Double.parseDouble(sc.nextLine());
		if (price < 0) {
			System.out.println("Price cannot be negative");
			return;
		}
		
		Product newProduct = new Product(autoId++, providerId, typeId, name, amount, price);
		list.add(newProduct);
		System.out.println("Add new product successfully");
	}
	
	// case 3
	private static void edit() {
		System.out.println("\n------EDIT PRODUCT INFORMATION IN THE LIST------");
		System.out.print("Enter product id to edit: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = indexOfProduct(id);

		if (indexOfProduct(index) == -1) {
			System.out.println("Invalid category id");
			return;
		}
		
		do {
			System.out.println("\n------EDIT PRODUCT INFORMATION------");
			System.out.println("Select Action");
			System.out.println("1. Edit provider id");
			System.out.println("2. Edit type id");
			System.out.println("3. Edit product name");
			System.out.println("4. Edit amount");
			System.out.println("5. Edit price");
			System.out.println("0. Exit");
			System.out.print("----Enter your choose: ");
			int choose = Integer.parseInt(sc.nextLine());
			switch (choose) {
			case 1: 
				System.out.print("Enter provider id: ");
				int providerId = Integer.parseInt(sc.nextLine());
				if (ProviderManagement.indexOfProvider(providerId) == -1) {
					System.out.println("Invalid provider id ");
					return;
				}
				list.get(index).setProviderId(providerId);
				System.out.println("Edit provider id successfully");
				break;
			case 2: 
				System.out.print("Enter type id: ");
				int typeId = Integer.parseInt(sc.nextLine());
				if (TypeManagement.indexOfType(typeId) == -1) {
					System.out.println("Invalid type id ");
					return;
				}
				list.get(index).setTypeId(typeId);
				System.out.println("Edit type id successfully");
				break;
			case 3: 
				System.out.print("Enter product name: ");
				String name = sc.nextLine();
				if (name == null || name.trim().length() == 0) {
					System.out.println("product name cannot be left blank");
					return;
				}
				list.get(index).setName(name);
				System.out.println("Edit product name successfully");
				break;
			case 4: 
				System.out.print("Enter amount: ");
				double amount = Double.parseDouble(sc.nextLine());
				if (amount < 0) {
					System.out.println("Amount cannot be negative");
					return;
				}
				list.get(index).setAmount(amount);
				System.out.println("Edit amount successfully");
				break;
			case 5:
				System.out.print("Enter Price: ");
				double price = Double.parseDouble(sc.nextLine());
				if (price < 0) {
					System.out.println("Price cannot be negative");
					return;
				}
				list.get(index).setPrice(price);
				System.out.println("Edit price successfully");
				break;
			case 0:
				return;
			default:
				System.out.println("Please Reentered Your Choose");
			}
		} while(true);
	}
	
	// case 4
	private static void remove() {
		System.out.println("\n------DELETE THE PRODUCT FROM THE LIST------");
		System.out.print("Enter the product id to delete: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = indexOfProduct(id);
		if (index == -1) {
			System.out.println("Invalid product id");
			return;
		}
		
		list.remove(index);
		System.out.println("Delete product successfully");
	}
	
	// case 5
	private static void sort() {
		System.out.println("\n------SORT BY PRODUCT NAME------");
		Collections.sort(list, (a, b) -> TypeManagement.removeAccents(a.getName()).compareTo(TypeManagement.removeAccents(b.getName())));
		System.out.println("Sort product successfully");
	}
	
	// case 6
	private static void search() {
		System.out.println("\n------SEARCH BY PRODUCT NAME------");
		System.out.print("Enter the product name: ");
		String productName = sc.nextLine();
		if (productName == null || productName.trim().length() == 0) {
			System.out.println("product name cannot be left blank");
			return;
		}
		System.out.printf("%-5s %-20s %-15s %-25s %-12s %-15s%n", "Id", "Provider Name", "Type Name", "Product Name", "Amount", "Price");
		for (Product product : list) {
			if (product.getName().toLowerCase().contains(productName)) {
				product.display();
			}	
		}
	}
	
	public static int indexOfProduct(int id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}	
	public static String getNameById(int id) {
		for (Product product : list) {
			if (product.getId() == id) {
				return product.getName();
			}
		}
		return null;
	}
	// ham de tra ve doi tuong hang hoa
	public static Product getProductById(int id) {
		for (Product p : list) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}
}
