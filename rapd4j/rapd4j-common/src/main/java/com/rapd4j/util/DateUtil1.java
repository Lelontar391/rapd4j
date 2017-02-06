package com.rapd4j.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil1
{
  private static final Logger logger = LoggerFactory.getLogger(DateUtil1.class);

  public static Date string2Date(String dateString, String model)
  {
    Date date = null;
    SimpleDateFormat sdf = new SimpleDateFormat(model);
    try {
      date = sdf.parse(dateString);
    } catch (ParseException e) {
      logger.error("DateUtil.String2Date error!");
      e.printStackTrace();
    }
    return date;
  }

  public static Date nDaysAfterNowDate(int n)
  {
    Calendar rightNow = Calendar.getInstance();
    rightNow.add(5, n);
    return rightNow.getTime();
  }

  public static Date getFormatDate(Date date, String model)
  {
    SimpleDateFormat sdf = new SimpleDateFormat(model);
    Date formatdate = null;
    try {
      formatdate = sdf.parse(sdf.format(date));
    } catch (ParseException e) {
      logger.error("日期转换失败", e);
    }
    return formatdate;
  }

  public static String Date2String(Date date, String model)
  {
    SimpleDateFormat sdf = new SimpleDateFormat(model);
    String strdate = sdf.format(date);
    return strdate;
  }
}