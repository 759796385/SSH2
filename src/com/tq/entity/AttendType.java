package com.tq.entity;

/**
 * ���������ٵ����ˣ�
 * 
 * @author lenovo
 *
 */
public class AttendType {
	private Integer id;
	// �������͵�����
	private String name;
	// ������ڶ�Ӧ�ķ���
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
