package com.bc.pmph_vedio.common;

import java.util.ArrayList;
import java.util.List;

/**
 *常量类
 *@author MrYang 
 *@CreateDate 2018年1月26日 上午10:35:11
 *
 **/
public class Const {
	/**
	 * 最大允许同时运行的线程数
	 */
	public static  Integer MAX_RUN = 5;
	
	/**
	 * 目标转换格式
	 */
	public static  String NEW_TYPE = "mp4";
	
	/**
	 * 用来装载new 对象里面拿不到的bean单例
	 */
	public static  List<Object> BEANS = new ArrayList<Object>(1);
	
}
