package com.example.youngforyou.commom.result;

/**
* <p>Title: ErrorEnum</p>
* <p>Description:系统级错误枚举 </p>
* <p>Company: XSQ</p> 
* @author tyw
* @date 2019年3月26日下午4:49:56
*/
public enum ErrorEnum {
	
	UN_LOGIN(201,"未登录"),
	UN_PERFECT_INFO(202,"未完善个人信息"),
	REQUEST_ERROR(400, "请求有错误"),
	SESSION_TIMEOUT(400, "会话超时"),
	TIMEOUT(400, "系统网络忙"),
	NO_PERMISSION(420, "访问无权限"),
	REPEAT_SUBMIT(400, "操作过于频繁，请稍后再试"),//频繁重复提交
	UNCOMPLETED_REPEAT_SUBMIT(400, "系统处理中，请稍后再试"),//未完成的重复提交
	SERVER_ERROR(500, "服务器异常");

	ErrorEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	private int code;
	private String message;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
