package com.tq.vo;

public class PaymentBean {
	private String payMonth;
	private double amount;

	public String getPayMonth() {
		return payMonth;
	}

	public void setPayMonth(String payMonth) {
		this.payMonth = payMonth;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public PaymentBean(String payMonth, double amount) {
		this.payMonth = payMonth;
		this.amount = amount;
	}

	public PaymentBean() {
	}

}
