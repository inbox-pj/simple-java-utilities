package com.clover.labs.xml;

public class Employee {
	private String receiptId;
	private String name;
	
	public Employee(String name) {
		this.name = name;
	}
	
	public String getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}