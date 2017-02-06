package com.rapd4j.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * 防止SQL注入的拦截器
 *
 * @author xuzn
 */
public class SqlInjectInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = request.getParameterValues(name);
            for (String value : values) {
                value = clearXss(value);
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }

    /**
     * 处理字符转义
     *
     * @param value
     * @return
     */
    private String clearXss(String value) {
        if (value == null || "".equals(value)) {
            return value;
        }
        value = value.replaceAll("<", "<").replaceAll(">", ">");
        value = value.replaceAll("\\(", "(").replace("\\)", ")");
        value = value.replaceAll("'", "'");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']",
                "\"\"");
        value = value.replace("script", "");
        return value;
    }
}