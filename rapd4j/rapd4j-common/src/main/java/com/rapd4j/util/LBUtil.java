package com.rapd4j.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
/**
 * 此工具类中的方法有:
 * 				1.判断文件是否存在isExist
 * 				2.将内容一行一行写到文件中去writeFileByLine
 * 				3.将路径转换生成conversionSpecialCharacters
 * 				4.判断空isEmpty
 * 				5.获取工程目录getHomeDir
 * 				6.文件的读取readFile
 * 				7.随机生成六位数验证码 
 * 				8.检测字符串是否不为空(null,"","null")
 * 				9.字符串转换为字符串数组
 * 				10.用默认的分隔符(,)将字符串转换为字符串数组
 * 				11.按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
 * 				12.按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
 * 				13.按照参数format的格式，日期转字符串
 * 				14.把时间根据时、分、秒转换为时间段
 * 				15.单参数对list进行排序
 * 				16.多参数对list进行排序
 **/
public class LBUtil {
	//1.判断文件是否存在
	public static boolean isExist(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}
		
	//2.将内容一行一行写到文件中去
	public static void writeFileByLine(String content, String filename) {
		File file = new File(filename);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileOutputStream(file));
			writer.print(content);
			writer.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(writer!=null) {
				try{
					writer.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	//3.将路径转换生成
	public static String conversionSpecialCharacters(String string) {
		return string.replaceAll("\\\\", "/");
	}
	//4.判断空
	public static boolean isEmpty(String str) {
		return str==null || str.length() == 0 || str.equals("")
				|| str.matches("\\s*");
	}
	//	
	public static boolean isEmpty(Object object) {
		if (object == null || "".equals(object) || "undefined".equals(object) || "null".equals(object)) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	//5.获取工程目录
	public static String getHomeDir(String path) {
		if(isNotEmpty(path)) {
			return conversionSpecialCharacters(System.getProperty("user.dir")+"/"+path+"/");
		}else {
			return System.getProperty("user.dir");
		}
	}
	//6.文件的读取
	public static String readFile(String filename) {
		StringBuffer buffer = new StringBuffer();
		try{
			//文件输入流
			FileInputStream inputStream = new FileInputStream(new File(filename));
			//读取文件流
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			//创建缓冲流
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			//开始读取  当不为空的时候 循环结束
			while((tempString = reader.readLine()) != null) {
				buffer.append(tempString + "\n");
			}
			reader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		//最后将buffer编程字符串返回
		return buffer.toString();
	}
	
	/**
	 * 7.随机生成六位数验证码 
	 * @return
	 */
	public static int getRandomNum(){
		 Random r = new Random();
		 return r.nextInt(900000)+100000;//(Math.random()*(999999-100000)+100000)
	}
	
	/**
	 * 8.检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/**
	 * 9.字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}
	
	/**
	 * 10.用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}
	
	/**
	 * 11.按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 12.按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}
	
	/**
	 * 13.按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * 14.把时间根据时、分、秒转换为时间段
	 * @param StrDate
	 */
	public static String getTimes(String StrDate){
		String resultTimes = "";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    java.util.Date now;
	    
	    try {
	    	now = new Date();
	    	java.util.Date date=df.parse(StrDate);
	    	long times = now.getTime()-date.getTime();
	    	long day  =  times/(24*60*60*1000);
	    	long hour = (times/(60*60*1000)-day*24);
	    	long min  = ((times/(60*1000))-day*24*60-hour*60);
	    	long sec  = (times/1000-day*24*60*60-hour*60*60-min*60);
	        
	    	StringBuffer sb = new StringBuffer();
	    	//sb.append("发表于：");
	    	if(hour>0 ){
	    		sb.append(hour+"小时前");
	    	} else if(min>0){
	    		sb.append(min+"分钟前");
	    	} else{
	    		sb.append(sec+"秒前");
	    	}
	    		
	    	resultTimes = sb.toString();
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    
	    return resultTimes;
	}
	
	/**
	 * 15.单参数对list进行排序
	 * @param sortList 需要排序的list
	 * @param param1   排序的参数名称
	 * @param orderType 排序类型：正序-asc；倒序-desc  
	 */
	public static List sort(List sortList, String param1, String orderType){
		Comparator mycmp1 = ComparableComparator.getInstance ();
		if("desc".equals(orderType)){
			mycmp1 = ComparatorUtils. reversedComparator(mycmp1); //逆序（默认为正序）
		}
		
		ArrayList<Object> sortFields = new ArrayList<Object>();
		sortFields.add( new BeanComparator(param1 , mycmp1)); //主排序（第一排序）

		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort (sortList , multiSort);
		
		return sortList;
	}
	
	/**
	 * 16.多参数对list进行排序
	 * @param sortList 需要排序的list
	 * @param param1   排序的参数名称:参数长度
	 * @param param2   排序的参数名称:排序参数
	 * @param orderType 排序类型：正序-asc；倒序-desc  
	 */
	public static List sortParam2(List sortList, String param1,String param2, String orderType){
		Comparator mycmp1 = ComparableComparator.getInstance ();
		Comparator mycmp2 = ComparableComparator.getInstance ();
		if("desc".equals(orderType)){
			mycmp1 = ComparatorUtils. reversedComparator(mycmp1); //逆序（默认为正序）
		}
		
		ArrayList<Object> sortFields = new ArrayList<Object>();
		sortFields.add( new BeanComparator(param1 , mycmp1)); //主排序（第一排序）
		sortFields.add( new BeanComparator(param2 , mycmp2)); //主排序（第一排序）

		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort (sortList , multiSort);
		
		return sortList;
	}
	
	public static void main(String args[]) {
		//str2Date("2016-04-12");
		
//		Date date = str2Date("2016-04-12 10:10:10");
//		System.out.println("----------------"+date+"---------------");
//		String dateString = date2Str(new Date(),"yyyy-MM-dd HH:mm:ss");
//		System.out.println("========="+dateString+"==========");
//		
//		String[] s=str2StrArray("1111,2222,3333,4444,5555");
//		List telStrList = null ;
//		if(s.length>0){
//							List<String> telStrList1=Arrays.asList(s);
//							telStrList = new ArrayList(telStrList1);
//		}
//		
//		System.out.println("(((((((((("+telStrList+"))))))))))");
//		
//		String paString = getHomeDir("");
//		System.err.println(paString);
		
		
		String pathString = getHomeDir("");
		System.err.println(pathString);
	}
}
