package com.tq.entity;

/**
 * 对应批复
 * 
 * @author lenovo
 *
 */
public class CheckBack {
	private Integer id;
	// 是否同意申请
	private boolean result;
	// 批复理由
	private String reason;
	// 该批复对应的申请
	private Application app;
	// 批复的经理
	private Manager manager;

	public CheckBack(Integer id, boolean result, String reason,
			Application app, Manager manager) {
		this.id = id;
		this.result = result;
		this.reason = reason;
		this.app = app;
		this.manager = manager;
	}

	public CheckBack() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
