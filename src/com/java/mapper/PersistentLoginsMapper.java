package com.java.mapper;


import com.java.entity.PersistentLogins;

public interface PersistentLoginsMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(PersistentLogins record);

	int insertSelective(PersistentLogins record);

	PersistentLogins selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(PersistentLogins record);

	int updateByPrimaryKey(PersistentLogins record);

	/**
	 * 通过用户名和UUID值查询自动登录记录
	 * 
	 * @param username
	 *            用户名
	 * @param series
	 *            UUID值
	 */
	PersistentLogins selectByUsernameAndSeries( String username, String series);

	/**
	 * 通过用户名查询自动登录记录
	 * 
	 * @param username
	 *            用户名
	 */
	PersistentLogins selectByUsername(String username);
}