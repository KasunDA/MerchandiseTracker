package com.fh.lab3;

public class Order {
	private String customerName;
	private String deliveryCity;
	private String orderNumber;
	private int orderQuantity;

	public Order(){ }
	public Order(String customerName, String deliveryCity, String orderNumber, int orderQuantity){
		this.customerName = customerName;
		this.deliveryCity = deliveryCity;
		this.orderNumber = orderNumber;
		this.orderQuantity = orderQuantity;
	}
	public String toString(){
		return customerName + ", " + deliveryCity + ", " + orderNumber + ", " + orderQuantity ;
	}
	public String getOrderNumber(){
		return orderNumber;
	}
	public int getOrderQuantity(){
		return orderQuantity;
	}
	public void setOrderNumber(String orderNumber){
		this.orderNumber = orderNumber;
	}
}
