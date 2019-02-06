/**
 * 
 */
package com.java.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.entity.PersistentLogins;
import com.java.entity.User;

import com.java.mapper.UserMapper;
import com.java.service.UserService;
import com.java.utils.CookieConstantTable;
import com.java.utils.CookieUtils;
import com.java.utils.EncryptionUtil;

/**
* @author Kidney
* 创建时间：2019年2月6日 下午8:44:17
* Description:
*/
/**
 * @author KIDNEY
 *
 */

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper um;

	@Autowired
	private PersistentLoginsServiceImpl plsl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.java.service.UserService#deleteByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public int deleteByPrimaryKey(Integer userId) {
		// TODO Auto-generated method stub
		return um.deleteByPrimaryKey(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.java.service.UserService#insert(com.java.entity.User)
	 */
	@Override
	public int insert(User record) {
		// TODO Auto-generated method stub
		return um.insert(record);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.java.service.UserService#insertSelective(com.java.entity.User)
	 */
	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return um.insertSelective(record);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.java.service.UserService#selectByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public User selectByPrimaryKey(Integer userId) {
		// TODO Auto-generated method stub
		return um.selectByPrimaryKey(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.java.service.UserService#updateByPrimaryKeySelective(com.java.entity.
	 * User)
	 */
	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return um.updateByPrimaryKeySelective(record);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.java.service.UserService#updateByPrimaryKey(com.java.entity.User)
	 */
	@Override
	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return um.updateByPrimaryKey(record);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.java.service.UserService#selectAll()
	 */
	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		return um.selectAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.java.service.UserService#selectByUser(com.java.entity.User)
	 */
	@Override
	public User selectByUser(User user) {
		// TODO Auto-generated method stub
		return um.selectByUser(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.java.service.UserService#selectByName(java.lang.String)
	 */
	@Override
	public User selectByName(String username) {
		// TODO Auto-generated method stub
		return um.selectByName(username);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.java.service.UserService#login(com.java.entity.User, boolean,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public User login(User user,boolean rememberme, HttpServletResponse response) {
		User result = new User();
		// 如果用户名和密码不为空，执行登录
		if (StringUtils.isNotBlank(user.getUserName()) && StringUtils.isNotBlank(user.getPassword())) {
			result = um.selectByUser(user);
			// 如果rememberme为true，则保存cookie值，下次自动登录
			if (result != null && rememberme == true) {
				// 有效期
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, 1); // 一个月
				Date validTime = calendar.getTime();
				// 精确到分的时间字符串
				String timeString = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-"
						+ calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.HOUR_OF_DAY) + "-"
						+ calendar.get(Calendar.MINUTE);

				// sha256加密用户信息
				String userInfoBySha256 = EncryptionUtil.sha256Hex(result.getUserName() + "_" + result.getPassword()
						+ "_" + timeString + "_" + CookieConstantTable.salt);
				// UUID值
				String uuidString = UUID.randomUUID().toString();
				// Cookie值
				String cookieValue = EncryptionUtil.base64Encode(result.getUserName() + ":" + uuidString);

				// 在数据库中保存自动登录记录（如果已有该用户的记录则更新记录）
				PersistentLogins pLogin = plsl.selectByUsername(result.getUserName());
				if (pLogin == null) {
					pLogin = new PersistentLogins();
					pLogin.setUsername(result.getUserName());
					pLogin.setSeries(uuidString);
					pLogin.setToken(userInfoBySha256);
					pLogin.setValidtime(validTime);
					plsl.insertSelective(pLogin);
				} else {
					pLogin.setSeries(uuidString);
					pLogin.setToken(userInfoBySha256);
					pLogin.setValidtime(validTime);
					plsl.updateByPrimaryKeySelective(pLogin);
				}

				// 保存cookie
				CookieUtils.addCookie(response, CookieConstantTable.RememberMe, cookieValue, null);
			}

		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.java.service.UserService#logout(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		//从session中获取用户详情
		User user = (User) request.getSession().getAttribute("user");
		//删除数据库中的自动登录记录
		PersistentLogins pLogins = plsl.selectByUsername(user.getUserName());
		if(pLogins != null)
			plsl.deleteByPrimaryKey(pLogins.getId());
		//清除session和用于自动登录的cookie
		request.getSession().removeAttribute("user");
		CookieUtils.delCookie(request, response, CookieConstantTable.RememberMe);

	}

}
