package com.code.base.util.utils;

import java.util.List;

/**
 * 校验工具
 * @author baitao
 *
 */
public class ValidateUtils {

	/**
	 * 判断字符串是否为非空
	 * @param obj
	 * @return 非空:true 空:false
	 */
	public static boolean isNotNullStr(String obj) {
		if(obj !=null && !"".equals(obj)) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 判断Obj是否为0
	 * @param obj
	 * @return 非空:true 空:false
	 */
	public static boolean isNotNullObject(Object obj) {
		if(obj !=null ) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 判断集合是否为非空并且个数不为0
	 * @param lists
	 * @return非空:true 空:false
	 */
	public static boolean isNotNullList(List lists) {
		if(lists != null && lists.size() != 0) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 判断数组是否为非空并且个数不为0
	 * @param lists
	 * @return非空:true 空:false
	 */
	public static boolean isNotNullArray(String[] lists) {
		if(lists != null && lists.length != 0) {
			return true;
		}else {
			return false;
		}
	}
}
