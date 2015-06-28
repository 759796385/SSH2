package com.tq.dao;

import java.util.List;

import com.tq.entity.Manager;

public interface IManagerDAO extends BaseDAO<Manager> {
	/**
	 * 根据用户名和密码查询经理
	 * 
	 * @param mgr
	 * @return
	 */
	List<Manager> findByNameAndPass(Manager mgr);

	/**
	 * 根据用户名查询经理
	 * 
	 * @param name
	 * @return
	 */
	Manager findByName(String name);
}
