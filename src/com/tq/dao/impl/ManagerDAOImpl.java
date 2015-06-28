package com.tq.dao.impl;

import java.util.List;

import com.tq.dao.IManagerDAO;
import com.tq.entity.Manager;

public class ManagerDAOImpl extends BaseDAOHibernate4<Manager> implements
		IManagerDAO {
	/**
	 * 根据用户名和密码查询经理
	 * 
	 * @param emp
	 *            包含指定用户名、密码的经理
	 * @return 符合指定用户名和密码的经理
	 */
	@Override
	public List<Manager> findByNameAndPass(Manager mgr) {
		return find("from Manager m where m.name=?0 and m.pass=?1",
				mgr.getName(), mgr.getPass());
	}

	/**
	 * 根据用户名查找经理
	 * 
	 * @param name
	 *            经理的名字
	 * @return 名字对应的经理
	 */
	@Override
	public Manager findByName(String name) {
		List<Manager> mgs = find("from Manager m where m.name=?0", name);
		if (mgs != null && mgs.size() >= 1) {
			return mgs.get(0);
		}
		return null;
	}

}
