package com.rapd4j.util;
/** 
 * @author 作者Lelonta: 
 * @version
 * 创建时间：2017-2-26 上午10:27:51 
 * 类说明 
 */
import java.lang.reflect.Field;
import net.sf.json.util.PropertyFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JsonPropertyFilter
  implements PropertyFilter
{
  private String[] filterProperties;
  private boolean ignoreColl = false;

  public JsonPropertyFilter()
  {
  }

  public JsonPropertyFilter(String[] filterProperties)
  {
    this.filterProperties = filterProperties;
  }

  public JsonPropertyFilter(boolean ignoreColl, String[] filterProperties)
  {
    this.filterProperties = filterProperties;
    this.ignoreColl = ignoreColl;
  }

  public JsonPropertyFilter(boolean ignoreColl)
  {
    this.ignoreColl = ignoreColl;
  }

  public boolean apply(Object source, String name, Object value)
  {
    Field declaredField = null;
    Log log = LogFactory.getLog(getClass());

    if ((value == null) || (value.toString().trim().equals(""))) {
      return true;
    }

    if ((this.filterProperties != null) && (this.filterProperties.length > 0))
    {
      return juge(this.filterProperties, name);
    }

    return false;
  }

  public boolean juge(String[] s, String s2)
  {
    boolean b = false;
    for (String sl : s) {
      if (s2.equals(sl)) {
        b = true;
      }
    }
    return b;
  }

  public String[] getFilterProperties() {
    return this.filterProperties;
  }

  public void setFilterProperties(String[] filterProperties) {
    this.filterProperties = filterProperties;
  }
  public boolean isIgnoreColl() {
    return this.ignoreColl;
  }

  public void setIgnoreColl(boolean ignoreColl) {
    this.ignoreColl = ignoreColl;
  }
}