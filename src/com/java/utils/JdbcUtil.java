/**
 * 
 */
package com.java.utils;
/**
* @author Kidney
* 创建时间：2019年2月2日 上午4:07:57
* Description:
*/
/**
 * @author KIDNEY
 *
 */

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class JdbcUtil {
	 private static String driver = null;// 驱动

	    private static String url = null;// 连接地址

	    private static String username = null;// 用户名

	    private static String password = null;// 密码

	    static {
	        try {
	            Properties props = new Properties();
	            InputStream ins = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
	            props.load(ins);
	            
	            driver = props.getProperty("driver");
	            url = props.getProperty("url");
	            username = props.getProperty("username");
	            password = props.getProperty("password");

	            Class.forName(driver);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * 
	     * Description: 获取数据库连接<br/>
	     *
	     * @author dingP
	     * @return
	     * @throws Exception
	     */
	    public static Connection getConnection() throws Exception {
	        return DriverManager.getConnection(url, username, password);// 硬编码
	    }

	 
	
	
	
}
