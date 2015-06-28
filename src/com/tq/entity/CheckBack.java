package com.tq.entity;

/**
 * ��Ӧ����
 * 
 * @author lenovo
 *
 */
public class CheckBack {
	private Integer id;
	// �Ƿ�ͬ������
	private boolean result;
	// ��������
	private String reason;
	// ��������Ӧ������
	private Application app;
	// �����ľ���
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
