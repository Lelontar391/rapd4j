package com.rapd4j.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.rapd4j.exception.GlobalException;



/**
 * 时间戳与字符串转换
 * 
 */
public class DdateUtil {

	/**
	 * 默认日期格式
	 */
	private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private static final String DEFAULT_FORMAT_S = "yyyyMMddHHmmss";
	
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public static Logger log = Logger.getLogger(DdateUtil.class);// 日志

	/**
	 * 默认构造函数
	 */
	private DdateUtil() {
	}

	/**
	 * 字符串转换成日期 如果转换格式为空，则利用默认格式进行转换操作
	 * 
	 * @param str
	 *            字符串
	 * @param format
	 *            日期格式
	 * @return 日期
	 * @throws java.text.ParseException
	 */
	public static Date str2Date(String str, String format) {
		try {
			if (null == str || "".equals(str)) {
				return null;
			}
			// 如果没有指定字符串转换的格式，则用默认格式进行转换
			if (null == format || "".equals(format)) {
				format = DEFAULT_FORMAT;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = null;
			try {
				date = sdf.parse(str);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(Date date, String format) {
		try {
			if (null == date) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(Date date) {
		try {
			if (null == date) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
			return sdf.format(date);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2StrHMS(Date date) {
		try {
			if (null == date) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
			return sdf.format(date);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2StrHMSSS(Date date) {
		try {
			if (null == date) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT_S);
			return sdf.format(date);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}
	
	/**
	 * 时间戳转换为字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String timestamp2Str(Timestamp time) {
		try {
			Date date = null;
			if (null != time) {
				date = new Date(time.getTime());
			}
			return date2Str(date, DEFAULT_FORMAT);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	/**
	 * 时间戳转换为字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String timestamp2StrDate(Timestamp time) {
		try {
			Date date = null;
			if (null != time) {
				date = new Date(time.getTime());
			}
			return date2Str(date, DATE_FORMAT_YYYY_MM_DD);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	/**
	 * 时间戳转换为字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String timestamp2DateStr(Timestamp time) {
		try {
			Date date = null;
			if (null != time) {
				date = new Date(time.getDate());
			}
			return date2Str(date, DATE_FORMAT_YYYY_MM_DD);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	/**
	 * 字符串转换时间戳 字符串格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param str
	 * @return
	 */
	public static Timestamp str2Timestamp(String str) {
		try {
			Date date = str2Date(str, DEFAULT_FORMAT);
			return new Timestamp(date.getTime());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	/**
	 * 字符串转换时间戳 字符串格式yyyy-MM-dd
	 * 
	 * @param str
	 * @return
	 */
	public static String str2str(String str) {
		try {
			Date date = str2Date(str, DATE_FORMAT_YYYY_MM_DD);
			long a = date.getTime() / 1000;
			String b = a + "";
			return b;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	/**
	 * 字符串转换时间戳 字符串格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param str
	 * @return
	 */
	public static String str2straa(String str) {
		try {
			Date date = str2Date(str, DEFAULT_FORMAT);
			long a = date.getTime() / 1000;
			String b = a + "";
			return b;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	/**
	 * 取得系统时间
	 * 
	 * @return yyyy-mm-dd hh:mm:ss
	 */
	public static String getDateTime() {
		try {
			Date date = new Date();
			DateFormat df = DateFormat.getDateTimeInstance();
			return df.format(date);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	public static long getCurrentLong() {
		return System.currentTimeMillis() / 1000;
	}

	// 取得当前时间
	public static String getCurrentTime() {
		try {
			return long2String(getCurrentLong());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	// linu获取当前时间
	public static String getLinuxTime() {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			return df.format(new Date());//
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	/**
	 * 获取当前年月日
	* @description:     
	* @author:xuzn   
	* @date:2017-1-22 上午8:53:54      
	* @modify:
	* @return   
	* @version:    
	*
	 */
	public static String getLocalTime() {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
			return df.format(new Date());//
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	/**
	 * long转换String日期
	 * 
	 * @param date
	 *            为空，返回当前时间
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String long2String(long date) {
		try {
			if (date > 0)
				return new java.text.SimpleDateFormat(DEFAULT_FORMAT)
						.format(new java.util.Date(date * 1000));
			else
				return getDateTime();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	/**
	 * 获取当前时间
	 * 
	 * @description:
	 * @author:xuzn
	 * @date:2017-1-22 上午8:52:55
	 * @modify:
	 * @return
	 * @version:
	 * 
	 */
	public static String getCurrentDate() {
		try {
			Date date = new Date();
			DateFormat df = DateFormat.getDateInstance();
			return df.format(date);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	/**
	 * 把时间转换为long
	 */
	public static long getDate2Long(String date) throws Exception {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
			Date date2 = sdf.parse(date);
			return date2.getTime();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	public static long getDateLong(String date) throws Exception {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
			Date date2 = sdf.parse(date);
			return date2.getTime();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	/**
	 * 把当前时间转换为long
	 * 
	 * @return
	 * @throws Exception
	 */
	public static long getDate2Long() {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
		try {
			Date date = sdf.parse(getDateTime());
			return date.getTime() / 1000;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GlobalException(e.getMessage());
		}
	}

	/**
	 * 得到给定日期N天后的日期 * @param num
	 */
	public static String getNumDate(int num) {
		long time = System.currentTimeMillis() + (1000L * 60 * 60 * 24 * num);
		String pattern = "yyyy-MM-dd";
		Date date = new Date();
		if (time > 0) {
			date.setTime(time);
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	public static String getNumDate(String dateStr, int num) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time = date.getTime() + (1000L * 60 * 60 * 24 * num);
		if (time > 0) {
			date.setTime(time);
		}
		return format.format(date);
	}

	/**
	 * 验证日期格式yyyy-mm-dd
	 * 
	 * @param sDate
	 * @return
	 */
	public static boolean isValidDate(String sDate) {
		String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
		String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
				+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
				+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		if ((sDate != null)) {
			Pattern pattern = Pattern.compile(datePattern1);
			Matcher match = pattern.matcher(sDate);
			if (match.matches()) {
				pattern = Pattern.compile(datePattern2);
				match = pattern.matcher(sDate);
				return match.matches();
			} else {
				return false;
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String dd = "2014-11-25 00:00:00.00";
//		Date last_pref_timestamp = sdf.parse(dd);
//
//		System.out.println(sdf.format(last_pref_timestamp));
		
		String string = getNumDate(5);//获取五天后的时间 2017-01-27
		System.err.println(string);
		
		String string2 = getCurrentDate();//获得的日期格式 2017-1-22
		System.out.println(string2);
		
		String string3 = getLocalTime();//获取的日期格式 20170122
		System.out.println(string3);
		
		String string4 = getDateTime();//获取的日期格式 2017-1-22 9:02:09
		System.out.println(string4);
		
		String string5 = date2StrHMSSS(new Date());
		System.err.println(string5);
	}
}
