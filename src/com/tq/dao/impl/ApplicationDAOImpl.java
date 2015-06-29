package com.tq.dao.impl;

import java.util.List;

import com.tq.dao.IApplicationDAO;
import com.tq.entity.Application;
import com.tq.entity.Employee;

public class ApplicationDAOImpl extends BaseDAOHibernate4<Application>
		implements IApplicationDAO {

	@Override
	public List<Application> findByEmp(Employee emp) {
		return find("from Application a where a.attend.employee=?0", emp);
	}

}
