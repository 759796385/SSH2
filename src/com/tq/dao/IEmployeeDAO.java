package com.tq.dao;

import java.util.List;

import com.tq.entity.Employee;

public interface IEmployeeDAO extends BaseDAO<Employee> {
	/**
	 * 根据用户名和密码查询员工
	 * 
	 * @param emp
	 * @return
	 */
	List<Employee> findByNameAndPass(Employee emp);

	/**
	 * 根据用户名查询员工
	 * 
	 * @param name
	 * @return
	 */
	Employee findByName(String name);
}
