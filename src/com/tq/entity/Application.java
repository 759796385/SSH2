package com.tq.entity;

/**
 * ��Ӧ��ͨԱ���Ŀ����������
 * 
 * @author lenovo
 *
 */
public class Application {
	private Integer id;
	// ��������
	private String reason;
	// �Ƿ���
	private boolean result;
	// �����ĳ��ڼ�¼
	private Attend attend;
	// ϣ����ָ�����ڸ�Ϊ��type����
	private AttendType type;
	// ����Ľ��
	private CheckBack check;

	public Application(Integer id, String reason, boolean result,
			Attend attend, AttendType type, CheckBack check) {
		this.id = id;
		this.reason = reason;
		this.result = result;
		this.attend = attend;
		this.type = type;
		this.check = check;
	}

	public Application() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public Attend getAttend() {
		return attend;
	}

	public void setAttend(Attend attend) {
		this.attend = attend;
	}

	public AttendType getType() {
		return type;
	}

	public void setType(AttendType type) {
		this.type = type;
	}

	public CheckBack getCheck() {
		return check;
	}

	public void setCheck(CheckBack check) {
		this.check = check;
	}

}
