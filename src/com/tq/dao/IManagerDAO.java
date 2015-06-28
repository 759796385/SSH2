package com.tq.dao;

import java.util.List;

import com.tq.entity.Manager;

public interface IManagerDAO extends BaseDAO<Manager> {
	/**
	 * �����û����������ѯ����
	 * 
	 * @param mgr
	 * @return
	 */
	List<Manager> findByNameAndPass(Manager mgr);

	/**
	 * �����û�����ѯ����
	 * 
	 * @param name
	 * @return
	 */
	Manager findByName(String name);
}
