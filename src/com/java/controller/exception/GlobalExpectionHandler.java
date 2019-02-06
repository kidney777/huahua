/**
 * 
 */
package com.java.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
* @author Kidney
* 创建时间：2019年1月21日 下午11:51:26
* Description:处理全部500错误从所有controller+
*/
/**
 * @author KIDNEY
 *
 */

@ControllerAdvice
public class GlobalExpectionHandler {
	@ExceptionHandler(Exception.class)
	public void exceptHand(Exception ex){
		ex.printStackTrace();
		System.out.println("error happended");
	}

}
