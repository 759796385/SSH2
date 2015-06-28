package com.tq.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 考勤信息
 * 
 * @author lenovo
 *
 */
public class Attend implements Serializable {
	private Integer id;
	// 出勤日期
	private String dutyDay;
	// 打卡程序
	private Date punchTime;
	// 本次打卡是否为上班打卡
	private boolean isCome;
	// 本次出勤类型
	private AttendType type;
	// 本次出勤关联的员工
	private Employee employee;

	public Attend() {
	}

	public Attend(Integer id, String dutyDay, Date punchTime, boolean isCome,
			AttendType type, Employee employee) {
		this.id = id;
		this.dutyDay = dutyDay;
		this.punchTime = punchTime;
		this.isCome = isCome;
		this.type = type;
		this.employee = employee;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDutyDay() {
		return dutyDay;
	}

	public void setDutyDay(String dutyDay) {
		this.dutyDay = dutyDay;
	}

	public Date getPunchTime() {
		return punchTime;
	}

	public void setPunchTime(Date punchTime) {
		this.punchTime = punchTime;
	}

	public boolean isCome() {
		return isCome;
	}

	public void setCome(boolean isCome) {
		this.isCome = isCome;
	}

	public AttendType getType() {
		return type;
	}

	public void setType(AttendType type) {
		this.type = type;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dutyDay == null) ? 0 : dutyDay.hashCode());
		result = prime * result
				+ ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + (isCome ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attend other = (Attend) obj;
		if (dutyDay == null) {
			if (other.dutyDay != null)
				return false;
		} else if (!dutyDay.equals(other.dutyDay))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (isCome != other.isCome)
			return false;
		return true;
	}

}
