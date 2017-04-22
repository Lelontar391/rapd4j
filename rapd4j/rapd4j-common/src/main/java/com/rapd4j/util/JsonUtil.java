package com.rapd4j.util;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

/** 
 * @author 作者Lelonta: 
 * @version
 * 创建时间：2017-2-25 下午11:36:51 
 * 类说明 
 */
public class JsonUtil
{
  public static JsonConfig configJson(String datePattern)
  {
    JsonConfig jsonConfig = new JsonConfig();
    jsonConfig.setExcludes(new String[] { "" });
    jsonConfig.setIgnoreDefaultExcludes(false);
    jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
    jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(datePattern));
    return jsonConfig;
  }

  public static JSONObject generate(Object obj, String[] message)
  {
    Map map = new HashMap();
    map.put("obj", obj);
    map.put("msg", message);
    map.put("flag", Boolean.valueOf(true));
    return JSONObject.fromObject(map);
  }

  public static JSONObject generateObj(Object obj)
  {
    return JSONObject.fromObject(obj);
  }

  public static JSONObject generateObj(Object obj, String[] excludes)
  {
    JsonConfig jsonConfig = new JsonConfig();
    jsonConfig.setExcludes(excludes);
    jsonConfig.setIgnoreDefaultExcludes(true);
    jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(""));
    jsonConfig.setJsonPropertyFilter(new JsonPropertyFilter());
    return JSONObject.fromObject(obj, getJsonConfig(jsonConfig));
  }

  public static JSONObject generate(Boolean flag)
  {
    Map map = new HashMap();
    map.put("flag", flag);
    return JSONObject.fromObject(map);
  }

  public static JSONArray generateTree(List list)
  {
    return JSONArray.fromObject(list);
  }

  public static JSONObject generateEasyUI(List list, Integer num)
  {
    JsonConfig jsonConfig = new JsonConfig();
    jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(""));
    jsonConfig.setJsonPropertyFilter(new JsonPropertyFilter());
    Map map = new HashMap();
    map.put("rows", list);
    map.put("total", num);
    return JSONObject.fromObject(map, jsonConfig);
  }

  public static JSONObject generateMap(Map<String, Object> map, String[] excludes)
  {
    JsonConfig jsonConfig = new JsonConfig();
    jsonConfig.setExcludes(excludes);
    jsonConfig.setIgnoreDefaultExcludes(true);
    jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(""));
    return JSONObject.fromObject(map, getJsonConfig(jsonConfig));
  }

  public static JSONObject generatePatternMap(Map<String, Object> map, String[] excludes)
  {
    JsonConfig jsonConfig = new JsonConfig();
    jsonConfig.setExcludes(excludes);
    jsonConfig.setIgnoreDefaultExcludes(true);
    jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
    return JSONObject.fromObject(map, getJsonConfig(jsonConfig));
  }

  static JsonConfig getJsonConfig(JsonConfig jsonConfig)
  {
    PropertyFilter pf = new PropertyFilter()
    {
      public boolean apply(Object source, String name, Object value)
      {
        return (value == null) || (String.valueOf(value).equals(""));
      }
    };
    jsonConfig.setJsonPropertyFilter(pf);
    return jsonConfig;
  }

  public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
    JSONArray jsonArr = JSONArray.fromObject(jsonStr);
    List list = new ArrayList();
    Iterator it = jsonArr.iterator();
    while (it.hasNext()) {
      JSONObject json2 = (JSONObject)it.next();
      list.add(parseJSON2Map(json2.toString()));
    }
    return list;
  }

  public static Map<String, Object> parseJSON2Map(String jsonStr) {
    Map map = new HashMap();

    JSONObject json = JSONObject.fromObject(jsonStr);
    for (Iterator localIterator1 = json.keySet().iterator(); localIterator1.hasNext(); ) { Object k = localIterator1.next();
      Object v = json.get(k);

      if ((v instanceof JSONArray)) {
        List list = new ArrayList();
        Iterator it = ((JSONArray)v).iterator();
        while (it.hasNext()) {
          JSONObject json2 = (JSONObject)it.next();
          list.add(parseJSON2Map(json2.toString()));
        }
        map.put(k.toString(), list);
      } else {
        map.put(k.toString(), v);
      }
    }
    return map;
  }
  
  public static JSONObject generateData(Object data, String[] excludes)
  {
    JsonConfig jsonConfig = new JsonConfig();
    jsonConfig.setExcludes(excludes);
    jsonConfig.setIgnoreDefaultExcludes(true);
    jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(""));
    return JSONObject.fromObject(data, getJsonConfig(jsonConfig));
  }
}