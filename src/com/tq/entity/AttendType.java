package com.tq.entity;

/**
 * 考勤类别（如迟到早退）
 * 
 * @author lenovo
 *
 */
public class AttendType {
	private Integer id;
	// 出勤类型的名称
	private String name;
	// 此类出勤对应的罚款
	private double amerce;

	public AttendType(Integer id, String name, double amerce) {
		this.id = id;
		this.name = name;
		this.amerce = amerce;
	}

	public AttendType() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmerce() {
		return amerce;
	}

	public void setAmerce(double amerce) {
		this.amerce = amerce;
	}

}
