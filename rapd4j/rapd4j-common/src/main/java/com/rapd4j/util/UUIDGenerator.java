package com.rapd4j.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/** 
 * @author 作者Lelonta: 
 * @version
 * 创建时间：2016年9月18日 上午11:00:41 
 * 类说明 
 */
public class UUIDGenerator {

	public static synchronized String getUUID()
	  {
	    String s = UUID.randomUUID().toString();
	    return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + 
	      s.substring(19, 23) + s.substring(24);
	  }

	  public static synchronized String getUUID20()
	  {
	    String s = UUID.randomUUID().toString();
	    return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23);
	  }

	  public static int getIUID()
	  {
	    String s = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	    Integer i = Integer.valueOf("2014051914");
	    return i.intValue();
	  }

	  public static synchronized String getUID() {
	    String s = new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date());

	    return s;
	  }

	  public static synchronized long getTimeStamp()
	  {
	    return System.currentTimeMillis();
	  }

	  public static void main(String[] arg) {
	    for (int i = 0; i < 100; i++)
	      System.out.println(getUUID());
	  }
}
