package com.example.youngforyou.commom.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSON;

public class HttpsUtil {

	/**
	 * https 请求
	 * 
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
		System.out.println(str_return);
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

	public static File httpsDown(String requestUrl, String requestMethod, String postData, String savePath) {
		InputStream in = null;
		OutputStream out = null;
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

			in = conn.getInputStream();
			int byteread = 0;
			
			File file = new File(savePath);
			FileOutputStream fs = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			while ((byteread = in.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
			}
			fs.close();
			conn.disconnect();
			return file;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
	}

}
