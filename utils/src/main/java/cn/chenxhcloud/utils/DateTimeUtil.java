package cn.chenxhcloud.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
*   
* 项目名称：chenxh-utils  
* 类名称：cn.chenxhcloud.utils.DateTimeUtil  
* @author：chenxh  
* 创建时间：2018年1月9日 下午2:45:10
* 描述：
*
 */
public class DateTimeUtil {
	
	private static final int WEEK_MIN_DAY = 1;
	private static final int WEEK_MAX_DAY = 7;
	private static final String MAX ="max";
	private static final String MIN = "min";
	
	
	/**
	 * 私有化构造器，不允许创建该类实例
	 */
	private DateTimeUtil() {
		
	}

	private static final SimpleDateFormat YMDHMS_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	private static final SimpleDateFormat YMDHMS_FORMAT2 = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

	private static final SimpleDateFormat YMD_FORMAT = new SimpleDateFormat("yyyyMMdd");

	private static final SimpleDateFormat YMD_FORMAT2 = new SimpleDateFormat("yyyy-MM-dd");

	private static final SimpleDateFormat HMS_FORMAT = new SimpleDateFormat("HHmmss");

	private static final SimpleDateFormat YM_FORMAT = new SimpleDateFormat("yyyyMM");

	private static Calendar c;

	public static Date dateOnly(Date date) {
		return yyyyMMddToDate(parseToyyyyMMdd(date));
	}

	/**
	 * 转换为 yyyyMMddHHmmss格式
	 */
	public static synchronized String parseToyyyyMMddHHmmss(Date date) {

		if (date == null) {
			return null;
		}
		return YMDHMS_FORMAT.format(date);

	}

	/**
	 * 转换为 yyyyMMdd HH:mm:ss格式
	 */
	public static synchronized String parseToyyyyMMddHHmmss2(Date date) {

		if (date == null) {
			return null;
		}
		return YMDHMS_FORMAT2.format(date);

	}

	/**
	 * 转换为HHmmss格式
	 */
	public static synchronized String parseToHHmmss(Date date) {
		if (date == null) {
			return null;
		}
		return YMD_FORMAT.format(date);
	}

	/**
	 * 转换为yyyyMMdd格式
	 */
	public static synchronized String parseToyyyyMMdd(Date date) {
		if (date == null) {
			return null;
		}

		return YMD_FORMAT.format(date);
	}

	/**
	 * 转换为yyyyMM格式
	 */
	public static int parseToyyyyMM(Date date) {
		if (date == null) {
			return 0;
		}

		return Integer.valueOf(YM_FORMAT.format(date));
	}

	public static Date yyyyMMddHHmmssToDate(String yyyyMMddHHmmss) {
		try {

			return YMDHMS_FORMAT.parse(yyyyMMddHHmmss);
		} catch (Exception e) {
			return null;
		}

	}

	public static Date yyyyMMddToDate(String yyyyMMdd) {
		try {

			return YMD_FORMAT.parse(yyyyMMdd);
		} catch (Exception e) {
			return null;
		}

	}

	public static Date yyyyMMToDate(String yyyyMM) {
		try {

			return YM_FORMAT.parse(yyyyMM);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * yyyy-MM-dd转换成date
	 * 
	 * @return
	 */
	public static Date yyyyMMddToDate2(String yyyyMMdd2) {
		try {

			return YMD_FORMAT2.parse(yyyyMMdd2);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date hh24mmssToDate(String hh24mmss) {
		try {

			return HMS_FORMAT.parse(hh24mmss);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date getDate(Date srcDate, Integer daysToAdd) {
		c.setTime(srcDate);
		c.add(Calendar.DATE, daysToAdd);
		return c.getTime();
	}

	public static Date yyyyMMddHHmmssToDate2(String yyyyMMddHHmmss) {
		try {
			return YMDHMS_FORMAT2.parse(yyyyMMddHHmmss);
		} catch (Exception e) {
			return null;
		}

	}

	public static final int daysBetween(Date early, Date late) {
		java.util.Calendar calst = java.util.Calendar.getInstance();
		java.util.Calendar caled = java.util.Calendar.getInstance();
		calst.setTime(early);
		caled.setTime(late);
		calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
		calst.set(java.util.Calendar.MINUTE, 0);
		calst.set(java.util.Calendar.SECOND, 0);
		caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
		caled.set(java.util.Calendar.MINUTE, 0);
		caled.set(java.util.Calendar.SECOND, 0);
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;
		return days;
	}

	public static Date getNextDayOfWeek(Date date, int dayOfWeek) {
		if (dayOfWeek == 0) {
			dayOfWeek = 7;
		}
		if (dayOfWeek > WEEK_MAX_DAY || dayOfWeek < WEEK_MIN_DAY) {
			throw new RuntimeException("星期：" + dayOfWeek + "不存在");
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		while (true) {
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if (preWeekDay(day) == dayOfWeek) {
				return cal.getTime();
			}
			cal.add(Calendar.DATE, 1);
		}
	}

	public static Date getNextMonthDate(Date date, int nextMonthDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int day = cal.get(Calendar.DATE);
		if (day <= nextMonthDate) {
			cal.set(Calendar.DATE, nextMonthDate);
		} else {
			cal.set(Calendar.DATE, 1);
			cal.add(Calendar.MONTH, 1);
			cal.set(Calendar.DATE, nextMonthDate);
		}
		return cal.getTime();
	}

	public static int nextWeekDay(int day) {
		if (day == WEEK_MAX_DAY) {
			return 1;
		} else {
			return day++;
		}
	}

	public static int preWeekDay(int day) {
		if (day == 1) {
			return 7;
		} else {
			return day - 1;
		}
	}

	public static long diffDate(Date beginDate, Date endDate) {
		Calendar theCa1 = Calendar.getInstance();
		Calendar theCa2 = Calendar.getInstance();
		theCa1.setTime(beginDate);
		theCa2.setTime(endDate);
		long betweenDays = (theCa2.getTimeInMillis() - theCa1.getTimeInMillis()) / (1000 * 3600 * 24);
		return betweenDays;
	}

	public static long diffMinute(Date beginDate, Date endDate) {
		Calendar theCa1 = Calendar.getInstance();
		Calendar theCa2 = Calendar.getInstance();
		theCa1.setTime(beginDate);
		theCa2.setTime(endDate);
		long betweenMinutes = (theCa2.getTimeInMillis() - theCa1.getTimeInMillis()) / (1000 * 60);
		return betweenMinutes;
	}

	public static Date getMonthFirstDate(Date date, int monthToAdd, String minOrMax) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, monthToAdd);
		if (MIN.equals(minOrMax)) {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		} else if (MAX.equals(minOrMax)) {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
		return calendar.getTime();
	}

	public static long getLastMonth(Date date) {
		Date lastDate = getMonthFirstDate(date, -1, "min");
		long lastMonth = parseToyyyyMM(lastDate);
		return lastMonth;
	}

	public static void main(String[] args) throws InterruptedException {
		// Calendar cal = Calendar.getInstance();
		// System.out.println(" cal.get(Calendar.DAY_OF_WEEK);:" +
		// cal.get(Calendar.DAY_OF_WEEK));
		// System.out.println(" cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);:" +
		// cal.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		//
		// System.out.println(getNextDayOfWeek(cal.getTime(), 0));
		// System.out.println(getNextDayOfWeek(cal.getTime(), 7));
		// System.out.println(getNextDayOfWeek(cal.getTime(), 1));
		// System.out.println(getNextDayOfWeek(cal.getTime(), 2));
		//
		// System.out.println(getNextMonthDate(cal.getTime(), 0));
		// System.out.println(parseToyyyyMMdd(getNextMonthDate(cal.getTime(), 15)));

		System.out.println(parseToyyyyMMdd(getMonthFirstDate(yyyyMMddToDate("20180218"), 0, "max")));

		// System.out.println(yyyyMMddToDate2("2012-09-01"));
		// Date start = new Date();
		// System.out.println(start);
		// Thread.sleep(60*1000*5+1000);
		// Date end = new Date();
		// System.out.println(end);
		// System.out.println(diffMinute(start,end));
	}
}
