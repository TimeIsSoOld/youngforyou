package com.example.youngforyou.commom.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
* <p>Title: WebUtil</p>
* <p>Description: </p>
* <p>Company: XSQ</p> 
* @author tyw
* @date 2019年1月14日下午5:20:01
*/
public class WebUtil {

	/**
	 * 获取请求参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String,String> getRequestParam() {
		HttpServletRequest request = HttpContext.getRequest();
		if (request == null) {
			return null;
		}
		HashMap<String, String> values = new HashMap<>();
        Enumeration enums = request.getParameterNames();
        while ( enums.hasMoreElements()){
            String paramName = (String) enums.nextElement();
            String paramValue = request.getParameter(paramName);
            values.put(paramName, paramValue);
        }
        return values;
	}
	
	
	public static String getTargetURL(String url, Map<String, String> paramMap) {

		StringBuffer buff = new StringBuffer();
		for (String key : paramMap.keySet()) {
			buff.append(key).append("=").append(paramMap.get(key)).append("&");
		}

		String para = buff.substring(0, buff.length()-1);
		StringBuffer targetURL = new StringBuffer(url);
		if (para!=null && !para.trim().equals("")) {
			int sPos = targetURL.indexOf("?");
			if (sPos == -1) {
				targetURL.append("?");
			} else if (sPos != targetURL.length() - 1) {
				targetURL.append("&");
			}
			targetURL.append(para);
		}
		return targetURL.toString();
	}
	
	
	
	public static String getHost(String url){
		//使用正则表达式过滤，
        String re = "((http|ftp|https)://)(([a-zA-Z0-9._-]+)|([0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}))(([a-zA-Z]{2,6})|(:[0-9]{1,4})?)";
        String str = "";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(re);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        //若url==http://127.0.0.1:9040或www.baidu.com的，正则表达式表示匹配
        if (matcher.matches()) {
            str = url;
        } else {
            String[] split2 = url.split(re);
            if (split2.length > 1) {
                String substring = url.substring(0, url.length() - split2[1].length());
                str = substring;
            } else {
                str = split2[0];
            }
        }
        return str;
	}
	


//	public static void main(String[] args) {
//		System.out.println(getHost("http://172.16.17.22:8080/1.html"));
//		
//	}


	
	public static String getHostWithProPrefix(String url){
		String host = getHost(url);
		
		String hostIP = url.replace("http://","").replace("https://","");//去除http和https前缀
		String [] arr = hostIP.split("/");//按‘/’分隔，取第一个
		String projectname = arr[1];
		return host + "/" + projectname;
	}
	
	
	public static boolean isWxbrowser(HttpServletRequest request) {
		boolean validation = false;
		if (request.getHeader("user-agent")!=null&&request.getHeader("user-agent").toLowerCase().indexOf("micromessenger") > 0) {
			validation = true;
		}
		return validation;
	}
	
	
	public static Boolean isMoblieBrowser(HttpServletRequest request) {
        Boolean isMoblie = false;
        String[] mobileAgents = { "iphone", "android", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
                "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
                "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
                "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
                "techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
                "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
                "pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
                "240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
                "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
                "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
                "mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
                "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
                "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
                "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
                "Googlebot-Mobile" };
        String ua = request.getHeader("user-agent");
        if (ua!=null && !ua.trim().equals("")) {
            for (String mobileAgent : mobileAgents) {
                if (ua.toLowerCase().indexOf(mobileAgent) >= 0) {
                    isMoblie = true;
                    break;
                }
            }
        }

        return isMoblie;
    }
	
	
	/**
	 * 获取客户端的类型 
	 * @param request
	 * @return  1=PC  2=微信 3=APP
	 */
	public static String getClientType(HttpServletRequest request){
		String clientType = "1";
		if(isWxbrowser(request)){
			clientType="2";
		}else if(isMoblieBrowser(request)){
			clientType="3";
		}
		return clientType;
	}
	
	
	
	public static String getRemoteIP(HttpServletRequest request) {

		String fromSource = "X-Forwarded-For";
		String ip = request.getHeader(fromSource);;
		
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			fromSource = "X-Real-IP";
			ip = request.getHeader(fromSource);
		}
	
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			fromSource = "Proxy-Client-IP";
			ip = request.getHeader(fromSource);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			fromSource = "WL-Proxy-Client-IP";
			ip = request.getHeader(fromSource);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			fromSource = "request.getRemoteAddr";
			ip = request.getRemoteAddr();
		}
		//System.out.println("App Client IP:" + ip + ", fromSource:" + fromSource);
		return ip.split(",")[0];
	
	}
	
	
	

}
