/**
 * 
 */
package com.java.controller.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.entity.User;
import com.java.mapper.UserMapper;
import com.java.service.UserService;
import com.java.service.impl.UserServiceImpl;

/**
* @author Kidney
* 创建时间：2019年1月22日 上午7:08:18
* Description:  http://localhost:8080/huahua//user/selectAll.shtml
*/
/**
 * @author KIDNEY
 *
 */
@Controller
@RequestMapping("/user/")
public class UserController {

	@Autowired
	private UserServiceImpl us;
	
	@RequestMapping("check")
	@ResponseBody // 用来返回特定的值
	public User login(String username, String password,  HttpServletResponse response){
		User u = new User();
		u.setPassword("123");
		u.setUserName("123");
		boolean rememberme = false;
		return us.login(u, rememberme,response);
	}
	
	@RequestMapping("login")
	public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam(name = "remember-me", required = false) boolean rememberme, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		User user = new User();
		user.setUserName(username);
		user.setPassword(password);

		User result = us.login(user, rememberme, response);
		if (result != null) {
			ModelAndView mAndView = null;
			//登录之前地址
			String callback = (String) session.getAttribute("callback");
			session.removeAttribute("callback"); // 获取之后移除
			// 基本路径
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();
			if (StringUtils.isNotBlank(callback)) {
				String[] urls = callback.split(basePath);
				if (urls.length == 2 && StringUtils.isNotBlank(urls[1])) {
					mAndView = new ModelAndView("redirect:" + urls[1]);
				}else{
					mAndView = new ModelAndView("redirect:/pages/front/userHome.jsp");
				}
			}else{
				mAndView = new ModelAndView("redirect:/pages/front/userHome.jsp");
			}
			
			session.setAttribute("user", result); // 登录成功之后加入session中
			redirectAttributes.addFlashAttribute("user", result); 

			return mAndView;
		} else {
			return new ModelAndView("redirect:/pages/front/login.jsp");
		}
	}
	
	@RequestMapping("logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mAndView = new ModelAndView("redirect:/pages/front/login.jsp");

		us.logout(request, response);

		return mAndView;
	}
	
	
	
	
	/**
	 * 用户主页
	 */
	@RequestMapping("home")
	public ModelAndView userIndex() {
		 ModelAndView mav = new ModelAndView();	
		  mav.setViewName("/pages/front/userHome.jsp");
		return mav;
	}
	


	@RequestMapping("selectByPrimaryKey")
	@ResponseBody // 用来返回特定的值
	public User selectByPrimaryKey(Integer id) {
		return us.selectByPrimaryKey(id);

	}

	@RequestMapping("selectAll")
	@ResponseBody // 用来返回特定的值
	public List<User> selectAll() {
		return us.selectAll();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.java.mapper.UserMapper#deleteByPrimaryKey(java.lang.Integer)
	 */
	@ResponseBody // 用来返回特定的值
	@RequestMapping("deleteByPrimaryKey")
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return us.deleteByPrimaryKey(id);
	}

	/*
	 * (non-Javadoc)
	 * http://localhost:8080/huahua//user/insertSelective.shtml?userName=asdasd&
	 * password=12312321&address=asdasdasd&tele=123456789
	 * 
	 * @see com.java.mapper.UserMapper#insertSelective(com.java.entity.User)
	 */
	@ResponseBody // 用来返回特定的值
	@RequestMapping("insertSelective")
	public int insertSelective(String userName, String password, String address, String tele) {

		User record = new User();
		record.setAddress(address);
		record.setPassword(password);
		record.setTele(tele);
		record.setUserName(userName);

		return us.insertSelective(record);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.java.mapper.UserMapper#updateByPrimaryKeySelective(com.java.entity.
	 * User)
	 */

	@ResponseBody // 用来返回特定的值
	@RequestMapping("updateByPrimaryKeySelective")
	public int updateByPrimaryKeySelective(String userName, String password, String address, String tele) {
		User record = new User();
		record.setAddress(address);
		record.setPassword(password);
		record.setTele(tele);
		record.setUserName(userName);
		return us.updateByPrimaryKeySelective(record);
	}

	@ResponseBody // 用来返回特定的值
	@RequestMapping("selectByUser")
	public User selectByUser(String userName, String password) {
		User record = new User();
		record.setPassword(password);
		record.setUserName(userName);
		return us.selectByUser(record);
	}
	
	
	
	@ResponseBody // 用来返回特定的值
	@RequestMapping("selectByName")
	public User selectByName(String userName) {
		// TODO Auto-generated method stub
		return us.selectByName(userName);
	}

}
