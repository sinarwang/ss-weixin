package com.ss.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	/**
	 * 获取当月月初时间字符串。
	 * @return 当月月初时间字符串。格式"yyyy-MM-dd HH:mm:ss"。
	 */
	public static String beginMonth() {
		Calendar c = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1, 0, 0, 0);
		Date d = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}
	/**
	 * 获取下月月初时间字符串。
	 * @return 下月月初时间字符串。格式"yyyy-MM-dd HH:mm:ss"。
	 */
	public static String nextMonth() {
		Calendar c = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		if(c.get(Calendar.MONTH) != 11) {
			calendar.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, 1, 0, 0, 0);
		} else {
			calendar.set(c.get(Calendar.YEAR)+1, 0, 1, 0, 0, 0);
		}
		Date d = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}
	/**
	 * 获取当天0点时间字符串。
	 * @return 当月月初时间字符串。格式"yyyy-MM-dd HH:mm:ss"。
	 */
	public static String beginDay() {
		Calendar c = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		Date d = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}
	/**
	 * 获取上月月初时间字符串。
	 * @return 上月月初时间字符串。格式"yyyy-MM-dd HH:mm:ss"。
	 */
	public static String lastMonth() {
		Calendar c = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		if(c.get(Calendar.MONTH) != 0) {
			calendar.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH)-1, 1, 0, 0, 0);
		} else {
			calendar.set(c.get(Calendar.YEAR)-1, 11, 1, 0, 0, 0);
		}
		
		Date d = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}
	/**
	 * 获取两月前的月初时间字符串
	 * @return 两月前的月初时间字符串。格式"yyyy-MM-dd HH:mm:ss"。
	 */
	public static String twoMonthAgo() {
		Calendar c = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		if(c.get(Calendar.MONTH) == 0) {
			calendar.set(c.get(Calendar.YEAR)-1, 10, 1, 0, 0, 0);
		} else if(c.get(Calendar.MONTH) == 1) {
			calendar.set(c.get(Calendar.YEAR)-1, 11, 1, 0, 0, 0);
		} else {
			calendar.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH)-2, 1, 0, 0, 0);
		}
		
		Date d = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}
	
}
