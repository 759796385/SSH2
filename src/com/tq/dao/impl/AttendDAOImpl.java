package com.tq.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.tq.dao.IAttendDAO;
import com.tq.entity.Attend;
import com.tq.entity.AttendType;
import com.tq.entity.Employee;

public class AttendDAOImpl extends BaseDAOHibernate4<Attend> implements
		IAttendDAO {
	/**
	 * ����Ա�����·ݲ�ѯ��Ա���ĳ��ڼ�¼
	 * 
	 * @param emp
	 *            Ա��
	 * @param month
	 *            �·ݣ��·�������"2012-02"��ʽ���ַ���
	 * @return ��Ա����ָ���·ݵ�ȫ�����ڼ�¼
	 */
	@Override
	public List<Attend> findByEmpAndMonth(Employee emp, String month) {
		return find(
				"from Attend a where a.employee=?0 and substring(a.dutyDay,0,7)=?1",
				emp, month);
	}

	/**
	 * ����Ա�������ڲ�ѯ��Ա���Ĵ򿨼�¼����
	 * 
	 * @param emp
	 *            Ա��
	 * @param dutyDay
	 *            ����
	 * @return ��Ա����ĳ��Ĵ򿨼�¼����
	 */
	@Override
	public List<Attend> findByEmpAndDutyDay(Employee emp, String dutyDay) {
		return find("from Attend a where a.employee=?0 and a.dutyDay=?1", emp,
				dutyDay);
	}

	/**
	 * ����Ա�������� �����°��ѯ��Ա���Ĵ򿨼�¼����
	 * 
	 * @param emp
	 *            Ա��
	 * @param dutyDay
	 *            ����
	 * @param isCome
	 *            �Ƿ��ϰ�
	 * @return ��Ա����ĳ���ϰ���°�Ĵ򿨼�¼
	 */
	@Override
	public Attend findByEmpAndDutyDayAndCome(Employee emp, String dutyDay,
			boolean isCome) {
		List<Attend> al = findByEmpAndDutyDay(emp, dutyDay);
		if (al != null || al.size() > 1) {
			for (Attend attend : al) {
				if (attend.isCome() == isCome) {
					return attend;
				}
			}
		}
		return null;
	}

	/**
	 * �鿴Ա��ǰ����ķ�������
	 * 
	 * @param emp
	 *            Ա��
	 * @return ��Ա����ǰ����ķ�������
	 */
	@Override
	public List<Attend> findByEmpUnAttend(Employee emp, AttendType type) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy--MM-dd");
		Calendar c = Calendar.getInstance();
		String end = sdf.format(c.getTime());
		c.add(Calendar.DAY_OF_MONTH, -3);
		String start = sdf.format(c.getTime());
		return find("from Attend a where a.employee=?0 and "
				+ "a.type!= ?1 and  a.dutyDay between ?2 and ?3", emp, type,
				start, end);
	}

}
