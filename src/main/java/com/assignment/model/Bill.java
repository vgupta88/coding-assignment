package com.assignment.model;

import java.math.BigDecimal;

public class Bill {
	
	private String id;
	
	private BigDecimal totalAmount;
	
	private BigDecimal groceriesAmount;
	
	private BigDecimal nonGroceriesAmount;
	
	private User user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getGroceriesAmount() {
		return groceriesAmount;
	}

	public void setGroceriesAmount(BigDecimal groceriesAmount) {
		this.groceriesAmount = groceriesAmount;
	}

	public BigDecimal getNonGroceriesAmount() {
		return nonGroceriesAmount;
	}

	public void setNonGroceriesAmount(BigDecimal nonGroceriesAmount) {
		this.nonGroceriesAmount = nonGroceriesAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
