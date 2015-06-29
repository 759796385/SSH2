package com.tq.dao;

import java.util.List;

import com.tq.entity.Application;
import com.tq.entity.Employee;

public interface IApplicationDAO extends BaseDAO<Application> {
	/**
	 * ����Ա����ѯδ������춯����
	 * 
	 * @param emp
	 *            ��Ҫ��ѯ��Ա��
	 * @return ��Ա����Ӧ��δ������춯����
	 */
	List<Application> findByEmp(Employee emp);
}
