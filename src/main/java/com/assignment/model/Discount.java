package com.assignment.model;

import java.math.BigDecimal;

public class Discount {

	private String id;
	
	private BigDecimal discountedBill;
	
	private String userId;
	
	private String billId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getDiscountedBill() {
		return discountedBill;
	}

	public void setDiscountedBill(BigDecimal discountedBill) {
		this.discountedBill = discountedBill;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

}
