package vn.devpro.fashionshopmanagement.update.type;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TypeManagement {
	private static ArrayList<Type> list = new ArrayList<Type>();
	
	public static ArrayList<Type> getList() {
		return list;
	}
	
	public static void setList(ArrayList<Type> list) {
		TypeManagement.list = list;
	}
	
	public static int autoId = 1;
	
	public static void init() {
		list.add(new Type(autoId++, "Váy", "Ngắn"));
		list.add(new Type(autoId++, "Quần", "Dài"));
		list.add(new Type(autoId++, "Áo", "Cổ Ngắn"));
		list.add(new Type(autoId++, "Giầy", "Cao Su Mềm"));
		list.add(new Type(autoId++, "Túi", "Vải Mềm"));
	}
	
	static Scanner sc = new Scanner(System.in);
	public static void typeUpdate() {	
		do {		
			System.out.println("\n------UPDATE TYPE INFORMATION------");
			System.out.println("Select Action");
			System.out.println("1. Display the list of types");
			System.out.println("2. Add a new type to the list");
			System.out.println("3. Edit type information in the list");
			System.out.println("4. Delete the type from the list");
			System.out.println("5. Sort by type name");
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
		System.out.println("\n------LIST OF TYPES------");
		System.out.printf("%-5s %-15s %-15s%n", "Id", "Name", "Description");
		for (Type type : list) {
			type.display();
		}
	}
	
	// case 2
	private static void add() {
		System.out.println("\n------ADD A NEW TYPE TO THE LIST------");
		System.out.print("Enter type name: ");
		String name = sc.nextLine();
		System.out.print("Enter type description: ");
		String description = sc.nextLine();
		
		// kiem tra ten khong duoc de trong
		if (name == null || name.trim().length() == 0) {
			System.out.println("Type name cannot be left blank");
			return;
		}
		if (description == null || description.trim().length() == 0) {
			System.out.println("Type description cannot be left blank");
			return;
		}
		
		// kiem tra ten ton tai hay chua
		if (nameIsExist(name)) {
			System.out.println("This name already exists in the list");
			return;
		}
		Type newType = new Type(autoId++, name, description);
		list.add(newType);
		System.out.println("Add new type successfully");
	}
	
	// case 3
	private static void edit() {
		System.out.println("\n------EDIT TYPE INFORMATION IN THE LIST------");
		System.out.print("Enter type id to edit: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = indexOfType(id);

		if (index == -1) {
			System.out.println("Invalid type id");
			return;
		}
		
		System.out.print("Enter type name: ");
		String name = sc.nextLine();
		if (name == null || name.trim().length() == 0) {
			System.out.println("Type name cannot be left blank");
			return;
		}
		if (nameIsExist(name)) {
			System.out.println("This name already exists in the list");
			return;
		}
		
		System.out.print("Enter type description: ");
		String description = sc.nextLine();
		if (description == null || description.trim().length() == 0) {
			System.out.println("Type description cannot be left blank");
			return;
		}
		
		// thay the ten cu bang ten moi
		list.get(index).setName(name);
		list.get(index).setDescription(description);
		System.out.println("Edit new type successfully");
	}
	
	// case 4
	private static void remove() {
		System.out.println("\n------DELETE THE TYPE FROM THE LIST------");
		System.out.print("Enter the type id to delete: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = indexOfType(id);
		if (index == -1) {
			System.out.println("Invalid type id");
			return;
		}
		
		list.remove(index);
		System.out.println("Delete type successfully");
	}
	
	// case 5
	private static void sort() {
		System.out.println("\n------SORT BY TYPE NAME------");
		Collections.sort(list, (a, b) -> removeAccents(a.getName()).compareTo(removeAccents(b.getName())));
		System.out.println("Sort type successfully");
	}
	// ham bo dau
	public static String removeAccents(String text) {
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }
	
	// ham tra ve vi tri cua nha cung cap
	public static int indexOfType(int id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	// ham tim ten co trong khong
	public static boolean nameIsExist(String name) {
		for (Type Type : list) {
			if (Type.getName().trim().equalsIgnoreCase(name.trim())) {
				return true;
			}
		}
		return false;
	}
	//ham lay ten theo id
	public static String getNameById(int id) {
		for (Type Type : list) {
			if (Type.getId() == id) {
				return Type.getName();
			}
		}
		return null;
	}
}
