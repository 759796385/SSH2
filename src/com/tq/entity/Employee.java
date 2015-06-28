package com.tq.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 普通雇员
 * 
 * @author lenovo
 *
 */
public class Employee implements Serializable {
	private Integer id;
	private String name;
	private String pass;
	private double salary;
	private Manager manager;

	// 员工的出勤记录
	private Set<Attend> attends = new HashSet<>();
	// 工资支付记录
	private Set<Payment> payments = new HashSet<>();

	public Employee(Integer id, String name, String pass, double salary,
			Manager manager, Set<Attend> attends, Set<Payment> payments) {
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.salary = salary;
		this.manager = manager;
		this.attends = attends;
		this.payments = payments;
	}

	public Employee() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Set<Attend> getAttends() {
		return attends;
	}

	public void setAttends(Set<Attend> attends) {
		this.attends = attends;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		return true;
	}

}
