package com.tq.entity;

/**
 * 对应普通员工的考勤提出申请
 * 
 * @author lenovo
 *
 */
public class Application {
	private Integer id;
	// 申请理由
	private String reason;
	// 是否处理
	private boolean result;
	// 关联的出勤记录
	private Attend attend;
	// 希望将指定出勤改为的type类型
	private AttendType type;
	// 申请的结果
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
