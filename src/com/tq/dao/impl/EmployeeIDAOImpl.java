package com.tq.dao.impl;

import java.util.List;

import com.tq.dao.IEmployeeDAO;
import com.tq.entity.Employee;

public class EmployeeIDAOImpl extends BaseDAOHibernate4<Employee> implements
		IEmployeeDAO {
	/**
	 * �����û����������ѯԱ��
	 * 
	 * @param emp
	 *            ����ָ���û����������Ա��
	 * @return ����ָ���û����������Ա������
	 */
	@Override
	public List<Employee> findByNameAndPass(Employee emp) {
		return find("from Employee e where e.name=?0 and e.pass=?1",
				emp.getName(), emp.getPass());
	}

	/**
	 * �����û�����ѯԱ��
	 * 
	 * @param name
	 *            Ա�����û���
	 * @return �����û�����Ա��
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
