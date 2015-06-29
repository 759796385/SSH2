package com.tq.dao;

import java.util.List;

import com.tq.entity.Application;
import com.tq.entity.Employee;

public interface IApplicationDAO extends BaseDAO<Application> {
	/**
	 * 根据员工查询未处理的异动申请
	 * 
	 * @param emp
	 *            需要查询的员工
	 * @return 该员工对应的未处理的异动申请
	 */
	List<Application> findByEmp(Employee emp);
}
