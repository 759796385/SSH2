package com.tq.entity;

import java.io.Serializable;

/**
 * нˮ��Ϣ
 * 
 * @author lenovo
 *
 */
public class Payment implements Serializable {
	private Integer id;
	// ֧��ʱ��
	private String payMonth;
	// ��н������
	private double amount;
	// ��н��Ա��
	private Employee employee;

	public Payment(Integer id, String payMonth, double amount, Employee employee) {
		this.id = id;
		this.payMonth = payMonth;
		this.amount = amount;
		this.employee = employee;
	}

	public Payment() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
