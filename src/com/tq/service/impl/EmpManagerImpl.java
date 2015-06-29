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
		// ����Ծ���ʽ��½�ɹ�
		if (mgrDao.findByNameAndPass(mgr).size() >= 1) {
			return LOGIN_MGR;
		} else if (empDao.findByNameAndPass(mgr).size() >= 1) {
			// Ա����½
			return LOGIN_EMP;
		} else {
			// ��¼ʧ��
			return LOGIN_FAIL;
		}
	}

	/*
	 * �Զ��򿨣���һ�����壬����7������¼
	 * 
	 * @see com.tq.service.EmpManager#autoPunch()
	 */
	@Override
	public void autoPunch() {
		System.out.println("�Զ����������¼");
		List<Employee> emps = empDao.findAll(Employee.class);
		String dutyDay = new Date().toString();
		for (Employee e : emps) {
			AttendType type = typeDao.get(AttendType.class, 6);
			Attend a = new Attend();
			a.setDutyDay(dutyDay);
			a.setType(type);
			// �����ǰʱ�������ϣ���Ӧ�ϰ��
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
	 * �Զ����㹤�ʣ�ÿ��һ��
	 * 
	 * @see com.tq.service.EmpManager#autoPay()
	 */
	@Override
	public void autoPay() {
		System.out.println("�Զ����빤�ʽ���");
		List<Employee> emps = empDao.findAll(Employee.class);
		// ��ȡ����ʱ��
		Calendar c = Calendar.getInstance();
		// �������еĹ���
		c.add(Calendar.DAY_OF_MONTH, -15);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String payMonth = sdf.format(c.getTime());
		for (Employee e : emps) {
			Payment pay = new Payment();
			double amount = e.getSalary();
			// ��ȡ���³��ڼ�¼
			List<Attend> attends = attendDao.findByEmpAndMonth(e, payMonth);
			// �ڹ����м��ϳ��ڽ���
			for (Attend a : attends) {
				amount += a.getType().getAmerce();
			}
			// ��ӹ��ʽ����¼
			pay.setPayMonth(payMonth);
			pay.setEmployee(e);
			pay.setAmount(amount);
			payDao.save(pay);
		}
	}

	/*
	 * ��֤ĳ��Ա���Ƿ���Դ�
	 * 
	 * @see com.tq.service.EmpManager#validPunch(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public int validPunch(String user, String dutyDay) {
		Employee emp = empDao.findByName(user);
		if (emp == null) {
			return NO_PUNCH;// �޷���
		}
		List<Attend> attends = attendDao.findByEmpAndDutyDay(emp, dutyDay);
		if (attends == null || attends.size() <= 0) {
			return NO_PUNCH;
		}
		// ��ʼ��
		else if (attends.size() == 1 && attends.get(0).isCome()
				&& attends.get(0).getPunchTime() == null) {
			return COME_PUNCH;// �ϰ��
		} else if (attends.size() == 1 && attends.get(0).getPunchTime() == null) {
			return LEAVE_PUNCH;// �°��
		} else if (attends.size() == 2) {
			// �����ϰ�򿨣��°��
			if (attends.get(0).getPunchTime() == null
					&& attends.get(1).getPunchTime() == null) {
				return BOTH_PUNCH;
			} else if (attends.get(1).getPunchTime() == null) {
				return LEAVE_PUNCH;// �°��
			} else {
				return NO_PUNCH;
			}
		}
		return NO_PUNCH;
	}

	/*
	 * ��
	 * 
	 * @see com.tq.service.EmpManager#punch(java.lang.String, java.lang.String,
	 * boolean) Ա���� ������ �Ƿ����ϰ��
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
		// �Ѿ���
		if (attend.getPunchTime() != null) {
			return PUNCHED;
		}
		System.out.println("=======��=========");
		int punchHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		attend.setPunchTime(new Date());
		if (isCome) {// �ϰ��
			if (punchHour < COME_LIMIT) {// �����ϰ�
				attend.setType(typeDao.get(AttendType.class, 1));
			} else if (punchHour < LATE_LIMIT) {// �°�֮ǰ��
				attend.setType(typeDao.get(AttendType.class, 4));
			}// ֮�������
		} else {// �°��
			if (punchHour >= LEAVE_LIMIT) {
				// �����°�
				attend.setType(typeDao.get(AttendType.class, 1));
			} else if (punchHour >= EARLY_LIMIT) {
				// ����
				attend.setType(typeDao.get(AttendType.class, 5));
			}
		}
		attendDao.update(attend);
		return PUNCH_SUCC;
	}

	/*
	 * �鿴Ա������
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
	 * �鿴��������������
	 * 
	 * @see com.tq.service.EmpManager#unAttend(java.lang.String)
	 */
	@Override
	public List<AttendBean> unAttend(String empName) {
		AttendType type = typeDao.get(AttendType.class, 1);
		Employee emp = empDao.findByName(empName);
		// �ҳ����������ڼ�¼
		List<Attend> attends = attendDao.findByEmpUnAttend(emp, type);
		List<AttendBean> result = new ArrayList<AttendBean>();
		// ��װvo
		for (Attend a : attends) {
			result.add(new AttendBean(a.getId(), a.getDutyDay(), a.getType()
					.getName(), a.getPunchTime()));
		}
		return result;
	}

	// ����ȫ����������
	@Override
	public List<AttendType> getAllType() {
		return typeDao.findAll(AttendType.class);
	}

	/*
	 * �������
	 * 
	 * @see com.tq.service.EmpManager#addApplication(int, int, java.lang.String)
	 */
	@Override
	public boolean addApplication(int attId, int typeId, String reason) {
		Application app = new Application();
		// ��ȡ��Ҫ�ı�ĳ��ڼ�¼
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
