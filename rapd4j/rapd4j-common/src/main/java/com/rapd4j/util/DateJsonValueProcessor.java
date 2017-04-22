package com.rapd4j.util;
/** 
 * @author 作者Lelonta: 
 * @version
 * 创建时间：2017-2-26 上午10:24:50 
 * 类说明 
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor
  implements JsonValueProcessor
{
  public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
  private DateFormat dateFormat;

  public DateJsonValueProcessor(String datePattern)
  {
    if (("".equals(datePattern)) || (datePattern == null))
      this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    else
      this.dateFormat = new SimpleDateFormat(datePattern);
  }

  public Object processArrayValue(Object value, JsonConfig jsonConfig)
  {
    return process(value);
  }

  public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
    return process(value);
  }

  private Object process(Object value) {
    return this.dateFormat.format((Date)value);
  }
}