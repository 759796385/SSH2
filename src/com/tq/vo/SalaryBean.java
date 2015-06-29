package com.tq.vo;

public class SalaryBean {
	private String empName;
	private double amount;

	public SalaryBean(String empName, double amount) {
		this.empName = empName;
		this.amount = amount;
	}

	public SalaryBean() {
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
