package com.tq.dao.impl;

import java.util.List;

import com.tq.dao.IPaymentDAO;
import com.tq.entity.Employee;
import com.tq.entity.Payment;

public class PaymentDAOImpl extends BaseDAOHibernate4<Payment> implements
		IPaymentDAO {
	/**
	 * ����Ա����ѯ�½�нˮ
	 * 
	 * @return ��Ա����Ӧ���½�нˮ����
	 */
	@Override
	public List<Payment> findByEmp(Employee emp) {
		return find("from Payment p where p.employee=?0", emp);
	}

	/**
	 * ����Ա���ͷ�н�·�����ѯ�½�нˮ
	 * 
	 * @param payMonth
	 *            ��н�·�
	 * @param emp
	 *            ��н��Ա��
	 * @return ָ��Ա����ָ���·ݵ��½�нˮ
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
