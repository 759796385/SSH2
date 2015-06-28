package com.tq.dao;

import java.util.List;

import com.tq.entity.Employee;
import com.tq.entity.Payment;

public interface IPaymentDAO extends BaseDAO<Payment> {
	/**
	 * 根据员工查询薪水
	 * 
	 * @param emp
	 * @return
	 */
	List<Payment> findByEmp(Employee emp);

	/**
	 * 根据员工和发薪月份查询月结薪水
	 * 
	 * @param payMonth
	 * @param emp
	 * @return
	 */
	Payment findByMonthAndEmp(String payMonth, Employee emp);
}
