package com.tq.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 经理
 * 
 * @author lenovo
 *
 */
public class Manager extends Employee implements Serializable {
	// 管理的部门
	private String dept;
	// 该经理的所有员工
	private Set<Employee> employees = new HashSet<>();
	// 该经理签署的所有批复
	private Set<CheckBack> checks = new HashSet<>();

	public Manager(String dept, Set<Employee> employees, Set<CheckBack> checks) {
		this.dept = dept;
		this.employees = employees;
		this.checks = checks;
	}

	public Manager() {
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Set<CheckBack> getChecks() {
		return checks;
	}

	public void setChecks(Set<CheckBack> checks) {
		this.checks = checks;
	}

}
