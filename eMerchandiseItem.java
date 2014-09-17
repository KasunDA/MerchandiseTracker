package com.fh.lab3;

import javax.swing.JOptionPane;

public class eMerchandiseItem {
	private String merchandiseID;
	private String description;
	private double price;
	private int quantity;
	private int totalOrders;
	private Order[] Order;

	public eMerchandiseItem(){ }
	public eMerchandiseItem(String merchandiseID, String description, double price, int quantity, int totalOrders){
		this.merchandiseID = merchandiseID;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.totalOrders = totalOrders;
	}
	private Order findOrder(String target){
		//take an order number, search the Order array and return a reference to an Order object if found
		for (int i=0; i < Order.length; i++) {

			if (Order[i].getOrderNumber().equals(target)){
				return Order[i];
			}
		}

		return null;  // not found
	}
	public void handleOrder(){
		/*
		 * ask for order number. Invoke sequentialSearch. If found verify the merchandise has sufficient quantity for the order. 
		 * If so process it by subtracting the quantity in the merchandise item and delete the order from the array. 
		 * The best way to do it is to assign a new order number for it say -1
		 */
		String buffer;
		String orderNumber;

		buffer = JOptionPane.showInputDialog(null, "Enter order number:", "Process Order", JOptionPane.INFORMATION_MESSAGE);
		orderNumber = buffer;
		Order oItem = findOrder(orderNumber);


		if (oItem != null) {
			//verify the merchandise has sufficient quantity for the order
			if (getQuantity() > oItem.getOrderQuantity()){
				quantity -= oItem.getOrderQuantity();
				oItem.setOrderNumber("-1");
				setTotalOrders(getTotalOrders() - 1);
				JOptionPane.showMessageDialog(null, "Order processed. Thank you!", "Process Order", JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null, "Not enough in stock, sorry!", "Process Order", JOptionPane.ERROR_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Order not found", "Process Order", JOptionPane.ERROR_MESSAGE);
		}

	}
	public Order[] getOrder(){
		return Order;
	}
	public void setOrders(Order[] Order){
		this.Order = Order;			
	}
	public String getID(){
		return merchandiseID;
	}
	public int getTotalOrders(){
		return totalOrders;
	}
	public void setTotalOrders(int totalOrders){
		this.totalOrders = totalOrders;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	public String toString(){
		return merchandiseID + ", " + description + ", "+ price + ", "+ quantity + ", "+ totalOrders;

	}
}
