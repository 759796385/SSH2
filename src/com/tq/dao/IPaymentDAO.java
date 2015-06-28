package com.tq.dao;

import java.util.List;

import com.tq.entity.Employee;
import com.tq.entity.Payment;

public interface IPaymentDAO extends BaseDAO<Payment> {
	/**
	 * ����Ա����ѯнˮ
	 * 
	 * @param emp
	 * @return
	 */
	List<Payment> findByEmp(Employee emp);

	/**
	 * ����Ա���ͷ�н�·ݲ�ѯ�½�нˮ
	 * 
	 * @param payMonth
	 * @param emp
	 * @return
	 */
	Payment findByMonthAndEmp(String payMonth, Employee emp);
}
