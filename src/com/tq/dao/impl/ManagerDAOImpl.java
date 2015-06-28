package com.tq.dao.impl;

import java.util.List;

import com.tq.dao.IManagerDAO;
import com.tq.entity.Manager;

public class ManagerDAOImpl extends BaseDAOHibernate4<Manager> implements
		IManagerDAO {
	/**
	 * �����û����������ѯ����
	 * 
	 * @param emp
	 *            ����ָ���û���������ľ���
	 * @return ����ָ���û���������ľ���
	 */
	@Override
	public List<Manager> findByNameAndPass(Manager mgr) {
		return find("from Manager m where m.name=?0 and m.pass=?1",
				mgr.getName(), mgr.getPass());
	}

	/**
	 * �����û������Ҿ���
	 * 
	 * @param name
	 *            ���������
	 * @return ���ֶ�Ӧ�ľ���
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
