package com.tq.dao.impl;

import java.util.List;

import com.tq.dao.IPaymentDAO;
import com.tq.entity.Employee;
import com.tq.entity.Payment;

public class PaymentDAOImpl extends BaseDAOHibernate4<Payment> implements
		IPaymentDAO {
	/**
	 * 根据员工查询月结薪水
	 * 
	 * @return 该员工对应的月结薪水集合
	 */
	@Override
	public List<Payment> findByEmp(Employee emp) {
		return find("from Payment p where p.employee=?0", emp);
	}

	/**
	 * 根据员工和发薪月份来查询月结薪水
	 * 
	 * @param payMonth
	 *            发薪月份
	 * @param emp
	 *            领薪的员工
	 * @return 指定员工、指定月份的月结薪水
	 */
	@Override
	public Payment findByMonthAndEmp(String payMonth, Employee emp) {
		List<Payment> list = find(
				"from Payment p where p.employee=?0 and p.payMonth=?1 ", emp,
				payMonth);
		if (list != null && list.size() >= 1) {
			return list.get(0);
		}
		return null;
	}

}
