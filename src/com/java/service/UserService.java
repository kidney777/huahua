/**
 * 
 */
package com.java.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.entity.User;

/**
* @author Kidney
* 创建时间：2019年2月6日 下午8:43:13
* Description:
*/
/**
 * @author KIDNEY
 *
 */
public interface UserService{
	  int deleteByPrimaryKey(Integer userId);

	    int insert(User record);

	    int insertSelective(User record);

	    User selectByPrimaryKey(Integer userId);

	    int updateByPrimaryKeySelective(User record);

	    int updateByPrimaryKey(User record);
	    
	    List<User> selectAll();
	    
	    /**
	     * 根据用户信息查用户详情（登录）
	     * */
	    User selectByUser(User user);
	    /**
	     * 根据用户名查用户详情
	     * */
	    User selectByName(String name);
	    
	    
		/**
		 * 登录
		 * 
		 * @param user
		 *            登录的用户信息
		 * @param rememberme
		 *            是否记住登录
		 * @param response
		 *            HttpServletResponse
		 * @return 根据传递的用户信息在数据库中查询到的用户详情
		 */
		User login(User user,boolean rememberme, HttpServletResponse response);
		
		/**
		 * 退出登录
		 * */
		void logout(HttpServletRequest request,HttpServletResponse response);
	
	
}
