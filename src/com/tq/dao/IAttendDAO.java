package com.tq.dao;

import java.util.List;

import com.tq.entity.Attend;
import com.tq.entity.AttendType;
import com.tq.entity.Employee;

public interface IAttendDAO extends BaseDAO<Attend> {
	/**
	 * 根据员工，月份查询该员工的出勤记录
	 * 
	 * @param emp
	 *            员工
	 * @param month
	 *            月份
	 * @return 全部出勤记录
	 */
	public List<Attend> findByEmpAndMonth(Employee emp, String month);

	/**
	 * 根据员工、日期查询该员工的打卡记录集合
	 * 
	 * @param emp
	 *            员工
	 * @param dutyDay
	 *            日期
	 * @return 该员工某天的打卡记录集合
	 */
	public List<Attend> findByEmpAndDutyDay(Employee emp, String dutyDay);

	/**
	 * 根据员工、日期、上下班查询该员工的打卡记录集合
	 * 
	 * @param emp
	 *            员工
	 * @param dutyDay
	 *            日期
	 * @param isCome
	 *            是否上班
	 * @return 该员工某天上班或下班的打卡记录
	 */
	public Attend findByEmpAndDutyDayAndCome(Employee emp, String dutyDay,
			boolean isCome);

	/**
	 * 查看员工前三天的非正常打卡
	 * 
	 * @param emp
	 *            员工
	 * @param type
	 * @return 该员工前三天非正常打卡
	 */
	List<Attend> findByEmpUnAttend(Employee emp, AttendType type);
}
