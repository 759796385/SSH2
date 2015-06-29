package com.tq.vo;

import java.util.Date;

public class AttendBean {
	private int id;
	private String dutyDay;
	private String unType;
	private Date time;

	public AttendBean(int id, String dutyDay, String unType, Date time) {
		this.id = id;
		this.dutyDay = dutyDay;
		this.unType = unType;
		this.time = time;
	}

	public AttendBean() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDutyDay() {
		return dutyDay;
	}

	public void setDutyDay(String dutyDay) {
		this.dutyDay = dutyDay;
	}

	public String getUnType() {
		return unType;
	}

	public void setUnType(String unType) {
		this.unType = unType;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
