package com.example.youngforyou.commom.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class FileUtils {
	
	
	
	/**
	 * 允许的默认通用文件格式
	 */
	private static List<String> fileList = new ArrayList<String>() {{
		add("jpg");add("png");add("gif");add("tif");
		add("bmp");add("doc");add("vsd");add("mdb");
		add("pdf");add("rmvb");add("flv");add("mp4");
		add("mp3");add("mpg");add("wmv");add("wav");
		add("avi");add("mid");add("rar");add("ini");
		add("docx");add("xlsx");add("xls");add("zip");
		add("xml");add("wps");add("torrent");add("mov");
		add("wpd");add("ram");
		
	}};
	
	/**
	 * 允许的图片格式
	 */
	private static List<String> imageTypeList = new ArrayList<String>() {{
		add("jpg");add("png");add("gif");add("bmp");add("tif");
	}};
	
	/**
	 * 允许的视频格式
	 */
	private static List<String> videoTypeList = new ArrayList<String>() {{
		add("mp4");
		add("mpg");add("wmv");add("wav");
		add("avi");add("mov");
	}};
	
	
	
	/**
	 * 检查通用文件格式是否正确
	 * @param file
	 * @return
	 */
	public static boolean checkFileExt(String extName) {

		if (extName!=null && fileList.contains(extName.toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 检查通用文件格式
	 * @param file
	 * @return
	 */
	public static boolean checkFileType(File file) {

		String type = FileTypeUtil.getType(file);
		if (type!=null && fileList.contains(type)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 检查图片格式
	 * @param file
	 * @return
	 */
	public static boolean checkImageType(File file) {
		String type = FileTypeUtil.getType(file);
		if (type!=null && imageTypeList.contains(type)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * 检查zip压缩包格式
	 * @param file
	 * @return
	 */
	public static boolean checkZipType(File file) {
		String type = FileTypeUtil.getType(file);
		if (type==null || !"zip".equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 检查视频格式是否正确
	 * @param file
	 * @return
	 */
	public static boolean checkVideoType(File file) {
		String type = FileTypeUtil.getType(file);
		if (type!=null && videoTypeList.contains(type)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 通过文件后缀判断是否视频格式
	 * @param file
	 * @return
	 */
	public static boolean checkVideoTypeByFileSuffix(File file) {
		try {
			String ext=file.getName().substring(file.getName().lastIndexOf(".") + 1);
			if (ext!=null && videoTypeList.contains(ext)) return true;
		}catch (Exception e) {
		}
		return false;
	}
	
	
	/************************************************测试******************************************************/
	
	
	
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	   /**
	    * 根据文件流读取图片文件真实类型
	    * 
	    * @param 
	    * @return  jpg、jpeg、png、gif、bmp
	    */
	public static String getTypeByStream(InputStream is) {
		byte[] b = new byte[10];
		try {
			is.read(b, 0, b.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String type = bytesToHexString(b).toUpperCase();
		
		return type;
	}

	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("d:/tt.mp4");
		System.out.println(file.exists());
		
		boolean flag = checkVideoType(file);
		System.out.println(flag);
		
		String type = getTypeByStream(new FileInputStream(file));
		System.out.println(type);
	}
	
}
