package com.tq.vo;

public class AppBean {
	private int id;
	private String emp;
	private String unAttend;
	private String toAttend;
	private String reason;

	public AppBean(int id, String emp, String unAttend, String toAttend,
			String reason) {
		this.id = id;
		this.emp = emp;
		this.unAttend = unAttend;
		this.toAttend = toAttend;
		this.reason = reason;
	}

	public AppBean() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmp() {
		return emp;
	}

	public void setEmp(String emp) {
		this.emp = emp;
	}

	public String getUnAttend() {
		return unAttend;
	}

	public void setUnAttend(String unAttend) {
		this.unAttend = unAttend;
	}

	public String getToAttend() {
		return toAttend;
	}

	public void setToAttend(String toAttend) {
		this.toAttend = toAttend;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
