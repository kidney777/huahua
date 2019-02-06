/**
 * 
 */
package com.java.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
* @author Kidney
* 创建时间：2019年1月28日 下午11:14:54
* Description:返回NULL 数据正确
*/
/**
 * @author KIDNEY
 *
 */
public class Validator {
	public static Map<String, Object> fieldValidator(BindingResult errorResult) {
		boolean flag = errorResult.hasErrors();
		Map<String, Object> errorMap = null;
		if (flag == true) {
			errorMap = new HashMap<String, Object>();

			List<FieldError> errorList = errorResult.getFieldErrors();

			for (FieldError error : errorList) {
				String fieldName = error.getField();
				String errorMessage = error.getDefaultMessage();
				System.out.println(fieldName + "=" + errorMessage);
				errorMap.put(fieldName, errorMessage);
			}
		}
		return errorMap;

	}
}
