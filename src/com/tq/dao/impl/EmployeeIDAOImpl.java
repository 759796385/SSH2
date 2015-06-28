package com.tq.dao.impl;

import java.util.List;

import com.tq.dao.IEmployeeDAO;
import com.tq.entity.Employee;

public class EmployeeIDAOImpl extends BaseDAOHibernate4<Employee> implements
		IEmployeeDAO {
	/**
	 * 根据用户名和密码查询员工
	 * 
	 * @param emp
	 *            包含指定用户名、密码的员工
	 * @return 符合指定用户名和密码的员工集合
	 */
	@Override
	public List<Employee> findByNameAndPass(Employee emp) {
		return find("from Employee e where e.name=?0 and e.pass=?1",
				emp.getName(), emp.getPass());
	}

	/**
	 * 根据用户名查询员工
	 * 
	 * @param name
	 *            员工的用户名
	 * @return 符合用户名的员工
	 */
	@Override
	public Employee findByName(String name) {
		List<Employee> list = find("from Employee e where e.name=?0", name);
		if (list != null && list.size() >= 1) {
			return list.get(0);
		}
		return null;
	}
}
