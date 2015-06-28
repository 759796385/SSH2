package com.tq.dao;

import java.util.List;

import com.tq.entity.Employee;

public interface IEmployeeDAO extends BaseDAO<Employee> {
	/**
	 * �����û����������ѯԱ��
	 * 
	 * @param emp
	 * @return
	 */
	List<Employee> findByNameAndPass(Employee emp);

	/**
	 * �����û�����ѯԱ��
	 * 
	 * @param name
	 * @return
	 */
	Employee findByName(String name);
}
