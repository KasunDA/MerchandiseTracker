package com.fh.lab3;

import java.io.*;
import javax.swing.JOptionPane;

public class InventorySystem {
	private static final int MAXELECTRONICMERCHANDISE = 4096;
	private String storeName;
	private eMerchandiseItem[] items;
	private int itemCount;

	public InventorySystem() {
		seteMerchandiseItems(new eMerchandiseItem[MAXELECTRONICMERCHANDISE]);
	}

	public InventorySystem(String storeName) {
		seteMerchandiseItems(new eMerchandiseItem[MAXELECTRONICMERCHANDISE]);
		this.storeName = storeName;
	}

	public static int getMaxElectronicMerchandise() {
		return MAXELECTRONICMERCHANDISE;
	}

	public void init() {
		/*
		 * read a text file to load the Inventory and invoke sort methods to
		 * sort the arrays of merchandise and orders. Note: to sort
		 */

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("order.text"));
			String line;
			String delim = "[:]+";
			Order[] orders;
			int count = 0, quantity, orderCount;
			String ID, description;
			double price;

			while ((line = br.readLine()) != null) {
				// first reading the first line to get eMerchandiseItem's info
				String[] fields = line.split(delim);
				ID = fields[0];
				description = fields[1];
				price = Double.parseDouble(fields[2]);
				quantity = Integer.parseInt(fields[3]);
				orderCount = Integer.parseInt(fields[4]);
				items[count] = new eMerchandiseItem(ID, description, price,
						quantity, orderCount);
				// now reading the orders based on orderCount
				String customer, city, orderNumber;
				int orderQuantity;

				if (orderCount > 0) {
					orders = new Order[orderCount];
					for (int i = 0; i < orderCount; i++) {
						line = br.readLine();
						fields = line.split("[;]+");

						customer = fields[0];
						city = fields[1];
						orderNumber = fields[2];
						orderQuantity = Integer.parseInt(fields[3]);

						orders[i] = new Order(customer, city, orderNumber,
								orderQuantity);
					}
					// set the orders for the merchandise item
					items[count].setOrders(orders);
				}
				count++;
			}
			setCount(count);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sort(items);

	}

	public void setCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public int getCount() {
		return itemCount;
	}

	private void sort(eMerchandiseItem[] list) {
		eMerchandiseItem tmp;
		int walker;

		for (int curr = 0; curr < itemCount - 1; curr++) {
			for (walker = itemCount - 1; walker > curr; walker--) {
				if (list[walker].getID().compareTo(list[walker - 1].getID()) < 0) {
					// swap
					tmp = list[walker];
					list[walker] = list[walker - 1];
					list[walker - 1] = tmp;
				}
			}
		}
	}

	private eMerchandiseItem findItem(String target, eMerchandiseItem[] list) {
		int first = 0;
		int last = itemCount - 1;
		int mid;

		while (first <= last) {
			mid = (first + last) / 2;
			if (target.equals(list[mid].getID())) {
				return list[mid]; // found a match, return the item
			} else if (target.compareTo(list[mid].getID()) > 0)
				first = mid + 1;
			else
				last = mid - 1;
		}

		return null; // not found
	}

	public void run() {
		// display the combo box of choices
		String buffer = "";
		int option = 0;
		String[] choices = { "List all merchandise", "Add merchandise", "Query merchandise", "Process order", "Quit" };
		while (buffer != null) {
			buffer = (String) JOptionPane.showInputDialog(null,
					"Please select an option:", storeName + " Inventory System",
					JOptionPane.PLAIN_MESSAGE, null, choices, choices[0]);
			if (buffer == "List all merchandise") {
				option = 1;
			} else if (buffer == "Add merchandise") {
				option = 2;
			} else if (buffer == "Query merchandise") {
				option = 3;
			} else if (buffer == "Process order") {
				option = 4;
			} else if (buffer == "Quit" || buffer == null) {
				option = 5;
			}

			switch (option) {
			case 1:
				listItems();
				break;
			case 2:
				addItem();
				break;
			case 3:
				queryItem();
				break;
			case 4:
				processOrder();
				break;
			case 5:
				System.exit(0);
			default:
				break;
			}

		}

	}

	public void listItems() {
		// display all merchandise items in proper format
		String s = "";
		for (int i = 0; i < itemCount; i++) {
			s += items[i].toString() + "\n";
		}
		JOptionPane.showMessageDialog(null, s);
	}

	public void addItem() {
		/*
		 * ask for merchandise ID. Invoke findItem to see if the item exists. If
		 * so ask for new quantity and update the quantity of the merchandise.
		 * If merchandise ID does not exist in the array this is a new item.
		 * First check if there is room in the array to add. If not display an
		 * error message (“Inventory full!!!”). Otherwise ask for all
		 * information about the new merchandise, create a new merchandise
		 * instance, add it to the end of the array and then invoke sort
		 */
		String buffer = "";
		String ID = "";
		String description = "";
		double price = 0.0;
		int quantity = 0;
		int totalOrders = 0;


		buffer = JOptionPane.showInputDialog(null, "Enter merchandise ID:", "Add Merchandise", JOptionPane.INFORMATION_MESSAGE);

		if (buffer == null){
			return;
		}

		ID = buffer;
		String target = buffer;
		eMerchandiseItem item = findItem(target, items);
		if (item != null) {
			// ask for new quantity
			buffer = JOptionPane.showInputDialog(null, "Item already exists. Add more of the item:", "Add Merchandise", JOptionPane.INFORMATION_MESSAGE);
			if (buffer == null){
				return;
			}
			quantity = Integer.parseInt(buffer);
			// update quantity of merchandise
			item.setQuantity(item.getQuantity() + quantity);

		} else {
			// room for new item?
			if (itemCount < MAXELECTRONICMERCHANDISE) {
				buffer = JOptionPane.showInputDialog(null, "Enter description:", "Add Merchandise", JOptionPane.INFORMATION_MESSAGE);
				if (buffer == null){
					return;
				}
				description = buffer;
				buffer = JOptionPane.showInputDialog(null, "Enter price:", "Add Merchandise", JOptionPane.INFORMATION_MESSAGE);
				if (buffer == null){
					return;
				}
				price = Double.parseDouble(buffer);
				buffer = JOptionPane.showInputDialog(null, "Enter quantity:", "Add Merchandise", JOptionPane.INFORMATION_MESSAGE);
				if (buffer == null){
					return;
				}
				quantity = Integer.parseInt(buffer);
				buffer = JOptionPane.showInputDialog(null, "Enter total orders:", "Add Merchandise", JOptionPane.INFORMATION_MESSAGE);
				if (buffer == null){
					return;
				}
				totalOrders = Integer.parseInt(buffer);

				items[itemCount] = new eMerchandiseItem(ID, description, price, quantity, totalOrders);
				itemCount++;
				sort(items);

			} else {
				JOptionPane.showMessageDialog(null, "Inventory full!", "Add Merchandise", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	public void queryItem() {
		// ask for merchandise ID. Invoke findItem. If exist display all
		// merchandise information including the orders
		String buffer = "";
		String target = "";
		String s = "";
		buffer = JOptionPane.showInputDialog(null, "Enter merchandise ID:", "Query Merchandise", JOptionPane.INFORMATION_MESSAGE);

		if (buffer == null){
			return;
		}

		target = buffer;
		eMerchandiseItem eItem = new eMerchandiseItem();
		eItem = findItem(target, items);

		if (eItem != null) {
			s += eItem.toString() + "\n";
			Order[] order = eItem.getOrder();

			for (int i = 0; i < eItem.getTotalOrders(); i++) {
				s += order[i].toString() + "\n";
			}
			JOptionPane.showMessageDialog(null, s);
		} else {
			JOptionPane.showMessageDialog(null, "Item not found", "Query Merchandise", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void processOrder() {
		// ask for merchandise ID. Invoke findItem. If found, invoke handleOrder
		// method on the merchandise object.
		String buffer = "";
		String ID;
		buffer = JOptionPane.showInputDialog(null, "Enter merchandise ID:",
				"Process Order", JOptionPane.INFORMATION_MESSAGE);

		if (buffer == null){
			return;
		}

		ID = buffer;
		eMerchandiseItem eItem = findItem(ID, items);

		if (eItem == null) {
			JOptionPane.showMessageDialog(null, "Item not found", "Process Order", JOptionPane.ERROR_MESSAGE);
		}
		else if (eItem.getTotalOrders() == 0) {
			JOptionPane.showMessageDialog(null, "There are no orders for this item!", "Process Order", JOptionPane.ERROR_MESSAGE);
		} 
		else {
			eItem.handleOrder();
		} 

	}

	public eMerchandiseItem[] geteMerchandiseItems() {
		return items;
	}

	public void seteMerchandiseItems(eMerchandiseItem[] eMerchandiseItems) {
		items = eMerchandiseItems;
	}


}