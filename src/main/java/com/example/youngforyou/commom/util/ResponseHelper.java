package com.example.youngforyou.commom.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseHelper {
	private static ResponseHelper rh = null;
	public static String DATA = "data";
	public static String CROSS_DOMAIN = "cross_domain";
	public static String CALLBACK = "callback";
	public static String IFRAME_SIGN = "wj_iframe";
	public static String JSONP_SIGN = "wj_jsonp";
	public static String IMAGE_SIGN = "wj_image";
	
	private ResponseHelper(){}

	public static synchronized ResponseHelper getInstance() {
		if (rh == null) {
			rh = new ResponseHelper();
		}
		return rh;
	}

	
	
	public void callBack(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		PrintWriter out = null;
		try {
			String callback = String.valueOf(map.get(CALLBACK));
			
				
					
			out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			if(map.get(CROSS_DOMAIN) == null || "".equals(map.get(CROSS_DOMAIN))) {
				out.println("var parentWindow = ( window.parent == window ) ? window.opener : window.parent;");
			}else {
				out.println("var parentWindow = ( window.parent.parent == window ) ? window.opener.opener : window.parent.parent;");
			}
			out.println("eval('var data="+map.get(DATA)+", key=\""+callback+"\"')");
			out.println("parentWindow."+callback+"(data,key);");
			out.println("</script>");
				
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null){
				out.flush();
				out.close();
			}
		}
	}
	public void crossDomain(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		
		PrintWriter out = null;
		try {
			StringBuilder sd = new StringBuilder();
			sd.append("<script type=\"text/javascript\">");
			sd.append("document.write(\'<iframe name=\"crossDomainIframe\" style=\"display: none;\"></iframe>");
			sd.append("<form name=\"crossDomainForm\" action=\""+map.get(CROSS_DOMAIN)+"/domain/cross.htm?nowTime="+new Date().getTime()+"\" method=\"post\" target=\"crossDomainIframe\">");
			sd.append("<input type=\"hidden\" name="+DATA+" value="+map.get(DATA)+" />");
			sd.append("<input type=\"hidden\" name="+CROSS_DOMAIN+" value="+map.get(CROSS_DOMAIN)+" />");
			sd.append("<input type=\"hidden\" name="+CALLBACK+" value="+map.get(CALLBACK)+" />");
			sd.append("</form>");
			sd.append("\');</script>");
			sd.append("<script type=\"text/javascript\">document.crossDomainForm.submit();</script>");
			out = response.getWriter();
			out.println(sd);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null){
				out.flush();
				out.close();
			}
		}
	}
	
	
	
}
