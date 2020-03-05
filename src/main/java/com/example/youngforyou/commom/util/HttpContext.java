package com.example.youngforyou.commom.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

/**
* <p>Title: HttpContext</p>
* <p>Description: 快捷获取HttpServletRequest,HttpServletResponse</p>
* <p>Company: XSQ</p> 
* @author tyw
* @date 2019年1月2日下午8:02:58
*/
public class HttpContext {

    /**
     * 获取ip
     * @return
     */
    public static String getIp() {
        HttpServletRequest request = HttpContext.getRequest();
        if (request == null) {
            return "127.0.0.1";
        } else {
            return WebUtil.getRemoteIP(request);
        }
    }

    /**
     * 获取当前请求的Request对象
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        } else {
            return requestAttributes.getRequest();
        }
    }

    /**
     * 
     * 获取当前请求的Response对象
     * @return
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        } else {
            return requestAttributes.getResponse();
        }
    }

    /**
     * 获取所有请求的值
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static Map<String, String> getRequestParameters() {
        HashMap<String, String> values = new HashMap<>();
        HttpServletRequest request = HttpContext.getRequest();
        if (request == null) {
            return values;
        }
        Enumeration enums = request.getParameterNames();
        while (enums.hasMoreElements()) {
            String paramName = (String) enums.nextElement();
            String paramValue = request.getParameter(paramName);
            values.put(paramName, paramValue);
        }
        return values;
    }

    /**
     * 获取所有请求体
     * @return
     */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> getRequestBody() {
        HttpServletRequest request = HttpContext.getRequest();
        try(InputStream in=request.getInputStream()){
        	if(in!=null) {
        		String json=new BufferedReader(new InputStreamReader(in)).lines().parallel().collect(Collectors.joining(""));
        		return JSON.parseObject(json,Map.class);
        	}
        }catch(Exception e){
        	
        }
        return null;
    }
}

