package com.rapd4j.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;


/**
 * String工具类
 * 
 * @author gaolinfa
 */
public class StringUtils {
	
	private static Logger log = Logger.getLogger(StringUtils.class);
	/**
	 * 是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (s == null || s.length() == 0 ||"null".equals(s.trim().toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		if (null == obj||obj==""||"".equals(obj)) {
			return true;
		}
		return false;
	}
	/**
	 * 是否为空
	 * 
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(Collection c) {
		if (c!=null&&c.size()>0) {
			return false;
		}
		return true;
	}
	public static boolean isEmpty(Map map){
		if (map!=null&&map.size()>0) {
			return false;
		}
		return true;
	}
	/**
	 * 判断两个对象是否相等
	 * 
	 * @param string
	 * @param object
	 * @return
	 */
	public static boolean isEqualsIgnoreCase(String string, Object object) {
		if (string == null && object == null) {
			return true;
		} else if (string!=null&&string.equalsIgnoreCase(String.valueOf(object))) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean isset(Map<String,?> map, String param){
		return map.containsKey(param);
	}
	
	public static String nullToString(Object s, String def) {
		return s == null ? def : String.valueOf(s);
	}

	public static int parseInt(String s) {
		int ret = 0;
		if(s==null)return ret;
		String regEx = "[+-]?([\\d]+)";
		Pattern p = Pattern.compile(regEx);
		Matcher isDigit = p.matcher(s);
		if (isDigit.matches()) {
			ret = Integer.parseInt(s);
		}
		return ret;
	}
	/*
	 * 判断是否为数字
	 */
	public static boolean isNum(String num){
		String regex = "^[0-9]*$";
		Pattern p = Pattern.compile(regex);
		Matcher isNum = p.matcher(num);
		return isNum.matches();
	}
	public static long parseLong(String s) {
		long ret = 0;
		String regEx = "[+-]?([\\d]+)";
		Pattern p = Pattern.compile(regEx);
		Matcher isDigit = p.matcher(s);
		if (isDigit.matches()) {
			ret = Long.parseLong(s);
		}
		return ret;
	}
	
	public static long parseLongDef(Object o,long l){
		long temp= l;
		if(o!=null){
			if(o instanceof String){
				try {
					temp = Long.valueOf((String) o).longValue();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}else if(o instanceof Date){
				try {
					temp = ((Date)o).getTime();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		return temp;
	}
	
	public static int strlen(String str){
		if(str==null)return 0;
		return str.length();
	}
	
	public static String escapeSql(String str){
		if(str==null||"".equals(str.trim())){
			return "";
		}
		return StringEscapeUtils.escapeSql(str);
	}
	
	
	/**
     * 将字符串编码格式转成GB2312
     *
     * @param str
     * @return
     */
    public static String getGB2312String(String str) {
        try {
            String temp = new String(str.getBytes(), "GBK");
            return temp;
        } catch (java.io.IOException ex) {
            return null;
        }
    }
    
	public static String getIso8859String(String message){
		String _message=message;
		try {
			if(message!=null){
				_message = new String(message.getBytes(),"iso8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			return _message;
		}
	}
	/**
     * 将字符串编码格式转成utf-8
     *
     * @param str
     * @return
     */
    public static String getUtf8String(String str) {
        try {
            String temp = new String(str.getBytes("iso8859_1"), "utf-8");
            return temp;
        } catch (java.io.IOException ex) {
            return null;
        }
    }
    /**
     * 去掉特殊字符
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String StringFilter(String str) throws PatternSyntaxException {     
        // 只允许字母和数字       
        // String   regEx = "[^a-zA-Z0-9]";                     
           // 清除掉所有特殊字符  
	       String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";  
	       Pattern   p   =   Pattern.compile(regEx);     
	       Matcher   m   =   p.matcher(str);     
	       return   m.replaceAll("").trim();     
  }
    /**
     * 校验字符串
     * @param regStr 需校验的关键字
     * @param valStr 目标字符串
     * @return
     */
    public static boolean verifyString(String regStr,String valStr){
    	
    	String now = null;
		Pattern p=Pattern.compile(regStr);
		Matcher m=p.matcher(valStr);
		boolean result=m.find();
		System.out.println(result);
		
		return result;
    	
    }
	/**
	 * 截取字符串，并获取字符串数组
	 * @param s
	 * @param sub
	 * @return 字符串数组
	 * @throws 
	 * @see getStringSplit
	 */
	public static String[] getStringSplit(String s,String sub) {
		String[] array=null;
		try {
			if (!StringUtils.isEmpty(s) && s.contains(sub)) {
				array = s.split(sub);
			}else {
				array=new String[] {s};
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("截取字符串失败"+e);
		}finally{
			return array;
		}
		 
		
	}
	/**
	 * 返回第一个逗号以前的字符串
	 * @param s
	 * @param regx
	 * @return
	 * @throws 
	 * @see getSubFirst
	 */
	public static String getSubFirst(String s,String regx){
		String substr ="";
		try {
			if (s.contains(regx)) {
				int index = s.indexOf(regx);//第一次出现逗号的地方
				substr = s.substring(0,index);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getSubFirst方法截取字符串报错："+e);
		}finally{
			return substr;
		}

	}
	/**
	 * 返回第一个逗号以后的字符串
	 * @param s
	 * @return
	 * @throws 
	 * @see getCommSplit
	 */
	public static String getSubLast(String s,String regx){
		String substr ="";
			try {
				if (s.contains(regx)) {
				int index = s.indexOf(regx);//第一次出现逗号的地方
				substr = s.substring(index+1);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("getSubFirst方法截取字符串报错："+e);
			}finally{
				return substr;
			}
		}
	
	/**
	 * 将汉字转为URLDecoder编码
	 * @param aURLFragment
	 * @return
	 */
	public static String forURL(String aURLFragment) {
        String result = null;
        try {
            result = URLEncoder.encode(aURLFragment, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("UTF-8 not supported", ex);
        }
        return result;
    }
	
	/**
	 *  TreeMap 默认是按降序排序
	 * @param map
	 * @return
	 */
	public static Map sortDesc(Map map) {  
		// TreeMap 默认是按升序排序
        Map<Object, Object> mapVK = new TreeMap<Object, Object>( 
            new Comparator<Object>() {  
                public int compare(Object obj1, Object obj2) { 
                    Integer v1 = Integer.valueOf(obj1.toString()); 
                    Integer v2 = Integer.valueOf(obj2.toString()); 
                    int s = v2.compareTo(v1); 
                    return -s; 
                } 
            } 
        );  
		Set col = map.keySet();  
		Iterator iter = col.iterator(); 
        while (iter.hasNext()) {  
        	String key = (String) iter.next();  
        	HashMap value = (HashMap) map.get(key); 
            mapVK.put(key, value); 
        }  
        return mapVK; 
    } 
	
	/**
	 * 搜索关键词高亮显示
	 * @param content
	 * @param keywords
	 * @return
	 */
	public static String replace(String content,String keywords){
		if(null!=content){
			String newPart="<font color='red'>"+keywords+"</font >";     
			   return  content.replace(keywords,newPart ); 
		}else{
			return "";
		}
	} 
	
	/**
	 * 转义特殊字符
	 * @param str
	 * @return
	 */
	public static String escapeString(String str){
		try {
			return StringEscapeUtils.escapeSql(str);
		} catch (Exception e) {
			return "";
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*String aa="12134-";
		double sss=1.123456;
		double ss=1.0;
		String s = 1.0+"".toString();*/
		String ss="1.8822354656E10";
		double sss=Double.parseDouble(ss);
		String aa=new DecimalFormat("0").format(sss);
		System.out.println(aa);
	}
	
	/**
	 * 将字符串str中的字符串arg0过滤掉
	 * */
	public static String stringFilter(String str, String arg0){
		
		Pattern p = Pattern.compile(arg0);
		Matcher m =p.matcher(str);
		String ss = m.replaceAll("");
		
		return ss;
	}
	
	// 去除字符串中头部和尾部所包含的空格（包括:空格(全角，半角)、制表符、换页符等）
	public static String trim(String str){
		String s = null;
		if(str!=null && !"".equals(str)){
			s = str.replaceAll("^[ *|　*| *| *|//s*]*", "").replaceAll("[ *|　*| *| *|//s*]*$", "");
		}
		return s;
	}
	
	
}
