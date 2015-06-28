package com.tq.dao;

import java.util.List;

import com.tq.entity.Attend;
import com.tq.entity.AttendType;
import com.tq.entity.Employee;

public interface IAttendDAO extends BaseDAO<Attend> {
	/**
	 * ����Ա�����·ݲ�ѯ��Ա���ĳ��ڼ�¼
	 * 
	 * @param emp
	 *            Ա��
	 * @param month
	 *            �·�
	 * @return ȫ�����ڼ�¼
	 */
	public List<Attend> findByEmpAndMonth(Employee emp, String month);

	/**
	 * ����Ա�������ڲ�ѯ��Ա���Ĵ򿨼�¼����
	 * 
	 * @param emp
	 *            Ա��
	 * @param dutyDay
	 *            ����
	 * @return ��Ա��ĳ��Ĵ򿨼�¼����
	 */
	public List<Attend> findByEmpAndDutyDay(Employee emp, String dutyDay);

	/**
	 * ����Ա�������ڡ����°��ѯ��Ա���Ĵ򿨼�¼����
	 * 
	 * @param emp
	 *            Ա��
	 * @param dutyDay
	 *            ����
	 * @param isCome
	 *            �Ƿ��ϰ�
	 * @return ��Ա��ĳ���ϰ���°�Ĵ򿨼�¼
	 */
	public Attend findByEmpAndDutyDayAndCome(Employee emp, String dutyDay,
			boolean isCome);

	/**
	 * �鿴Ա��ǰ����ķ�������
	 * 
	 * @param emp
	 *            Ա��
	 * @param type
	 * @return ��Ա��ǰ�����������
	 */
	List<Attend> findByEmpUnAttend(Employee emp, AttendType type);
}
