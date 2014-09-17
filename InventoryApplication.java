/**
	@version 2.0 07-18-2014
	@author David Shan

	File name: InventoryApplication.java, InventorySystem.java, eMerchandiseItem.java, Order.Java

	Program purpose:  The inventory system should keep track of all electronics merchandise items (objects of eMerchandiseItem class) 
	in the store and is capable of listing all merchandise items, adding new merchandise, querying a merchandise as well as process orders 
	on the inventory. Each electronics merchandise item may contain zero or more orders up to a max of 32 orders (Order class).

	Revision history: 
	Date		Programmer		Description
	07-18-14	David Shan		Created all 4 classes
	07-19-14	David Shan		Finished Inventory Application	
	07-20-14	David Shan		Finished Order
	07-21-14	David Shan		Halfway done with init method
	07-22-14	David Shan		Finished addItem method
	07-23-14	David Shan		Finished findItem and sort methods
	07-24-14	David Shan		Finished Inventory System
	07-25-14	David Shan		Finished eMerchandiseItem, finished init method
	07-28-14	David Shan		Fixed findItem method
	07-30-14 	David Shan		Modified behavior of queryItem, now after processing an item totalOrder changes
	07-30-14	David Shan		Finalized.

 */
package com.fh.lab3;

public class InventoryApplication {

	public static void main(String[] args) {

		InventorySystem footHillInventorySystem = new InventorySystem("Foothill");
		footHillInventorySystem.init();
		footHillInventorySystem.run();
	}

}
