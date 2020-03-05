package com.example.youngforyou.commom.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSON;

public class HttpUtils {

	private static final int CONNECT_TIMEOUT = 5000;
	private static final int READ_TIMEOUT = 60000;

	public static String postResult(String urlStr, String content) {

		URL url = null;
		HttpURLConnection connection = null;

		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);

			connection.setConnectTimeout(CONNECT_TIMEOUT); // 连接主机超时（单位：毫秒）
			connection.setReadTimeout(READ_TIMEOUT); // 从主机读取数据超时（单位：毫秒）
			connection.setInstanceFollowRedirects(false);
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Charset", "UTF-8");
			// connection.setRequestProperty("Accept-Encoding", "gzip, deflate");

			connection.connect();

			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.write(content.getBytes("utf-8"));
			out.flush();
			out.close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;
	}
	
	public static String postResult(String urlStr, Map<String,String> params) {

		
		
		String content = "";;
		StringBuffer buf = new StringBuffer();
		for(String key : params.keySet()) {
			buf.append(key).append("=").append(params.get(key)).append("&");
		}
		if(buf.length()>0) {
			content = buf.substring(0, buf.length()-1);
		}
		URL url = null;
		HttpURLConnection connection = null;

		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);

			connection.setConnectTimeout(CONNECT_TIMEOUT); // 连接主机超时（单位：毫秒）
			connection.setReadTimeout(READ_TIMEOUT); // 从主机读取数据超时（单位：毫秒）
			connection.setInstanceFollowRedirects(false);
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Charset", "UTF-8");
			// connection.setRequestProperty("Accept-Encoding", "gzip, deflate");

			connection.connect();

			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.write(content.getBytes("utf-8"));
			out.flush();
			out.close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;
	}

	public static String postGeneralUrl(String generalUrl,  String params)
            throws Exception {
        URL url = new URL(generalUrl);
        // 打开和URL之间的连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        // 设置通用的请求属性
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);

        // 得到请求的输出流对象
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.write(params.getBytes("UTF-8"));
        out.flush();
        out.close();

        // 建立实际的连接
        connection.connect();
        // 获取所有响应头字段
        Map<String, List<String>> headers = connection.getHeaderFields();
        // 遍历所有的响应头字段
        for (String key : headers.keySet()) {
            System.err.println(key + "--->" + headers.get(key));
        }
        // 定义 BufferedReader输入流来读取URL的响应
        BufferedReader in = null;
        in = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String result = "";
        String getLine;
        while ((getLine = in.readLine()) != null) {
            result += getLine;
        }
        in.close();
        System.err.println("result:" + result);
        return result;
    }
	
	public static String getResult(String urlStr) {

		URL url = null;
		HttpURLConnection connection = null;

		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();

			connection.setConnectTimeout(CONNECT_TIMEOUT); // 连接主机超时（单位：毫秒）
			connection.setReadTimeout(READ_TIMEOUT); // 从主机读取数据超时（单位：毫秒）
			connection.setInstanceFollowRedirects(false);
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Charset", "UTF-8");

			connection.connect();

			if (200 == connection.getResponseCode()) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
				StringBuffer buffer = new StringBuffer();
				String line = "";
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				reader.close();
				return buffer.toString();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;
	}
	
	
	public static String getResult(String urlStr,String sessionid) {

		URL url = null;
		HttpURLConnection connection = null;

		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();

			connection.setConnectTimeout(CONNECT_TIMEOUT); // 连接主机超时（单位：毫秒）
			connection.setReadTimeout(READ_TIMEOUT); // 从主机读取数据超时（单位：毫秒）
			connection.setInstanceFollowRedirects(false);
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setRequestProperty("Cookie", sessionid);      

			connection.connect();

			if (200 == connection.getResponseCode()) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
				StringBuffer buffer = new StringBuffer();
				String line = "";
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				reader.close();
				return buffer.toString();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;
	}
	
	
	
	/**
	 * https 请求
	 * @param requestUrl
	 * @param requestMethod
	 * @param postData
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> httpsRequest(String requestUrl, String requestMethod, String postData) {
		InputStream in = null;
		OutputStream out = null;
		String str_return = "";
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
			URL console = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setRequestMethod(requestMethod.toUpperCase());

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);

			if (requestMethod.equalsIgnoreCase("POST") && postData.length() != 0) {

				conn.getOutputStream().write(postData.getBytes("utf-8"));
				conn.getOutputStream().flush();
				conn.getOutputStream().close();
			}
			conn.connect();
			InputStream is = conn.getInputStream();

			StringBuilder sb = new StringBuilder();

			BufferedReader inresult = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = inresult.readLine()) != null) {
				sb.append(line);
			}

			/*
			DataInputStream indata = new DataInputStream(is);
			byte[] buf = new byte[1024];
			for (int count = indata.read(buf); count >= 0; count = indata.read(buf)) {
				sb.append(new String(buf, "UTF-8"), 0, count);
			}
			*/
			conn.disconnect();

			str_return = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		
		return JSON.parseObject(str_return, Map.class);
	}
	
	
	private static class TrustAnyTrustManager implements X509TrustManager {
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
	
}
