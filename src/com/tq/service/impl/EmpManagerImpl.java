package com.tq.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.tq.dao.IApplicationDAO;
import com.tq.dao.IAttendDAO;
import com.tq.dao.IAttendTypeDAO;
import com.tq.dao.ICheckBackDAO;
import com.tq.dao.IEmployeeDAO;
import com.tq.dao.IManagerDAO;
import com.tq.dao.IPaymentDAO;
import com.tq.entity.Application;
import com.tq.entity.Attend;
import com.tq.entity.AttendType;
import com.tq.entity.Employee;
import com.tq.entity.Manager;
import com.tq.entity.Payment;
import com.tq.service.EmpManager;
import com.tq.vo.AttendBean;
import com.tq.vo.PaymentBean;

public class EmpManagerImpl implements EmpManager {
	private IApplicationDAO appDao;
	private IAttendDAO attendDao;
	private IAttendTypeDAO typeDao;
	private ICheckBackDAO checkDao;
	private IEmployeeDAO empDao;
	private IManagerDAO mgrDao;
	private IPaymentDAO payDao;

	public void setAppDao(IApplicationDAO appDao) {
		this.appDao = appDao;
	}

	public void setAttendDao(IAttendDAO attendDao) {
		this.attendDao = attendDao;
	}

	public void setTypeDao(IAttendTypeDAO typeDao) {
		this.typeDao = typeDao;
	}

	public void setCheckDao(ICheckBackDAO checkDao) {
		this.checkDao = checkDao;
	}

	public void setEmpDao(IEmployeeDAO empDao) {
		this.empDao = empDao;
	}

	public void setMgrDao(IManagerDAO mgrDao) {
		this.mgrDao = mgrDao;
	}

	public void setPayDao(IPaymentDAO payDao) {
		this.payDao = payDao;
	}

	@Override
	public int validLogin(Manager mgr) {
		// 如果以经理方式登陆成功
		if (mgrDao.findByNameAndPass(mgr).size() >= 1) {
			return LOGIN_MGR;
		} else if (empDao.findByNameAndPass(mgr).size() >= 1) {
			// 员工登陆
			return LOGIN_EMP;
		} else {
			// 登录失败
			return LOGIN_FAIL;
		}
	}

	/*
	 * 自动打卡，周一到周五，早上7点插入记录
	 * 
	 * @see com.tq.service.EmpManager#autoPunch()
	 */
	@Override
	public void autoPunch() {
		System.out.println("自动插入旷工记录");
		List<Employee> emps = empDao.findAll(Employee.class);
		String dutyDay = new Date().toString();
		for (Employee e : emps) {
			AttendType type = typeDao.get(AttendType.class, 6);
			Attend a = new Attend();
			a.setDutyDay(dutyDay);
			a.setType(type);
			// 如果当前时间是早上，对应上班打卡
			if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < AM_LIMIT) {
				a.setCome(true);
			} else {
				a.setCome(false);
			}
			a.setEmployee(e);
			attendDao.save(a);
		}

	}

	/*
	 * 自动结算工资，每月一号
	 * 
	 * @see com.tq.service.EmpManager#autoPay()
	 */
	@Override
	public void autoPay() {
		System.out.println("自动插入工资结算");
		List<Employee> emps = empDao.findAll(Employee.class);
		// 获取上月时间
		Calendar c = Calendar.getInstance();
		// 结算月中的工资
		c.add(Calendar.DAY_OF_MONTH, -15);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String payMonth = sdf.format(c.getTime());
		for (Employee e : emps) {
			Payment pay = new Payment();
			double amount = e.getSalary();
			// 获取上月出勤记录
			List<Attend> attends = attendDao.findByEmpAndMonth(e, payMonth);
			// 在工资中加上出勤奖金
			for (Attend a : attends) {
				amount += a.getType().getAmerce();
			}
			// 添加工资结算记录
			pay.setPayMonth(payMonth);
			pay.setEmployee(e);
			pay.setAmount(amount);
			payDao.save(pay);
		}
	}

	/*
	 * 验证某个员工是否可以打卡
	 * 
	 * @see com.tq.service.EmpManager#validPunch(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public int validPunch(String user, String dutyDay) {
		Employee emp = empDao.findByName(user);
		if (emp == null) {
			return NO_PUNCH;// 无法打卡
		}
		List<Attend> attends = attendDao.findByEmpAndDutyDay(emp, dutyDay);
		if (attends == null || attends.size() <= 0) {
			return NO_PUNCH;
		}
		// 开始打卡
		else if (attends.size() == 1 && attends.get(0).isCome()
				&& attends.get(0).getPunchTime() == null) {
			return COME_PUNCH;// 上班打卡
		} else if (attends.size() == 1 && attends.get(0).getPunchTime() == null) {
			return LEAVE_PUNCH;// 下班打卡
		} else if (attends.size() == 2) {
			// 可以上班打卡，下班打卡
			if (attends.get(0).getPunchTime() == null
					&& attends.get(1).getPunchTime() == null) {
				return BOTH_PUNCH;
			} else if (attends.get(1).getPunchTime() == null) {
				return LEAVE_PUNCH;// 下班打卡
			} else {
				return NO_PUNCH;
			}
		}
		return NO_PUNCH;
	}

	/*
	 * 打卡
	 * 
	 * @see com.tq.service.EmpManager#punch(java.lang.String, java.lang.String,
	 * boolean) 员工名 打卡日期 是否是上班打卡
	 */
	@Override
	public int punch(String user, String dutyDay, boolean isCome) {
		Employee emp = empDao.findByName(user);
		if (emp == null) {
			return PUNCH_FAIL;
		}
		Attend attend = attendDao.findByEmpAndDutyDayAndCome(emp, dutyDay,
				isCome);
		if (attend == null) {
			return PUNCH_FAIL;
		}
		// 已经打卡
		if (attend.getPunchTime() != null) {
			return PUNCHED;
		}
		System.out.println("=======打卡=========");
		int punchHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		attend.setPunchTime(new Date());
		if (isCome) {// 上班打卡
			if (punchHour < COME_LIMIT) {// 正常上班
				attend.setType(typeDao.get(AttendType.class, 1));
			} else if (punchHour < LATE_LIMIT) {// 下班之前来
				attend.setType(typeDao.get(AttendType.class, 4));
			}// 之后算旷工
		} else {// 下班打卡
			if (punchHour >= LEAVE_LIMIT) {
				// 正常下班
				attend.setType(typeDao.get(AttendType.class, 1));
			} else if (punchHour >= EARLY_LIMIT) {
				// 早退
				attend.setType(typeDao.get(AttendType.class, 5));
			}
		}
		attendDao.update(attend);
		return PUNCH_SUCC;
	}

	/*
	 * 查看员工工资
	 * 
	 * @see com.tq.service.EmpManager#empSalary(java.lang.String)
	 */
	@Override
	public List<PaymentBean> empSalary(String empName) {
		Employee emp = empDao.findByName(empName);
		List<Payment> pays = payDao.findByEmp(emp);
		List<PaymentBean> result = new ArrayList<PaymentBean>();
		for (Payment pay : pays) {
			result.add(new PaymentBean(pay.getPayMonth(), pay.getAmount()));
		}
		return result;
	}

	/*
	 * 查看最近三天非正常打卡
	 * 
	 * @see com.tq.service.EmpManager#unAttend(java.lang.String)
	 */
	@Override
	public List<AttendBean> unAttend(String empName) {
		AttendType type = typeDao.get(AttendType.class, 1);
		Employee emp = empDao.findByName(empName);
		// 找出非正常出勤记录
		List<Attend> attends = attendDao.findByEmpUnAttend(emp, type);
		List<AttendBean> result = new ArrayList<AttendBean>();
		// 封装vo
		for (Attend a : attends) {
			result.add(new AttendBean(a.getId(), a.getDutyDay(), a.getType()
					.getName(), a.getPunchTime()));
		}
		return result;
	}

	// 返回全部出勤类型
	@Override
	public List<AttendType> getAllType() {
		return typeDao.findAll(AttendType.class);
	}

	/*
	 * 添加申请
	 * 
	 * @see com.tq.service.EmpManager#addApplication(int, int, java.lang.String)
	 */
	@Override
	public boolean addApplication(int attId, int typeId, String reason) {
		Application app = new Application();
		// 获取需要改变的出勤记录
		Attend attend = attendDao.get(Attend.class, attId);
		AttendType type = typeDao.get(AttendType.class, typeId);
		app.setAttend(attend);
		app.setType(type);
		if (reason != null) {
			app.setReason(reason);
		}
		appDao.save(app);
		return true;
	}

}
