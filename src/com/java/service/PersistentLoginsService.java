package com.java.service;

import com.java.entity.PersistentLogins;;

public interface PersistentLoginsService {
    int deleteByPrimaryKey(Integer id);

    int insert(PersistentLogins pLogins);

    int insertSelective(PersistentLogins pLogins);

    PersistentLogins selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersistentLogins pLogins);

    int updateByPrimaryKey(PersistentLogins pLogins);
    /**
	 * 通过用户名和UUID值查询自动登录记录
	 * 
	 * @param username
	 *            用户名
	 * @param series
	 *            UUID值
	 */
	PersistentLogins selectByUsernameAndSeries(String username,String series);
	/**
	 * 通过用户名查询自动登录记录
	 * 
	 * @param username
	 *            用户名
	 */
	PersistentLogins selectByUsername(String username);	
}
