package com.example.youngforyou.commom.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.StrUtil;

/**
* <p>Title: FileOperateUtil</p>
* <p>Description: </p>
* <p>Company: ENGC</p> 
* @author tyw
* @date 2017-5-12下午5:16:29
*/
public class FileDownloadUtil {
	
	/**
	 * <p> description: 文件下载（网络文件）</p>
	 * @param request
	 * @param response
	 * @param contentType
	 * @param filePath 网络地址
	 * @param fileName
	 */
	public static void download(HttpServletRequest request, HttpServletResponse response, 
			String contentType, String filePath, String fileName){
		try{
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		response.setContentType(contentType);
		setFileDownloadHeader(request, response, fileName);
		
		URL url = new URL(filePath);
		URLConnection conn = url.openConnection();
		bis = new BufferedInputStream(conn.getInputStream());
		
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * <p> description: 文件下载（本地文件）</p>
	 * @param req
	 * @param resp
	 * @param excelFile
	 * @param filename
	 * @throws Exception
	 */
	public static void download(HttpServletRequest req, HttpServletResponse resp,
			String contentType,File file,String filename) throws Exception{
		resp.reset();
		resp.setContentType(contentType);
		setFileDownloadHeader(req, resp, filename);

		int read = 0;
		byte[] bytes = new byte[1024 * 1];
		FileInputStream fis = new FileInputStream(file);
		ServletOutputStream sos = resp.getOutputStream();
		while ((read = fis.read(bytes)) != -1) {
			sos.write(bytes, 0, read);
		}
		sos.flush();
		fis.close();
		sos.close();
	}
	
	/**
	 * <p> description: 设置文件下载response header</p>
	 * @param request
	 * @param response
	 * @param fileName
	 * @throws Exception
	 */
	public static void setFileDownloadHeader(HttpServletRequest request, HttpServletResponse response, String fileName) throws Exception {
		final String userAgent = request.getHeader("USER-AGENT");
		try {
			String finalFileName = null;
			if (StrUtil.containsAny(userAgent, "MSIE")) {// IE浏览器
				finalFileName = URLEncoder.encode(fileName, "UTF8");
			} else if (StrUtil.containsAny(userAgent, "Mozilla")) {// google,火狐浏览器
				finalFileName = new String(fileName.getBytes(), "ISO8859-1");
			} else {
				finalFileName = URLEncoder.encode(fileName, "UTF8");// 其他浏览器
			}
			// 这里设置一下让浏览器弹出下载提示框，而不是直接在浏览器中打开
			response.setHeader("Content-Disposition", "attachment; filename=\"" + finalFileName + "\"");
		} catch (UnsupportedEncodingException e) {
		}
	}
}