package com.example.youngforyou.commom.controller;

import com.example.youngforyou.commom.result.ErrorEnum;
import com.example.youngforyou.commom.result.ErrorResult;
import com.example.youngforyou.commom.result.Result;
import com.example.youngforyou.commom.result.SuccessResult;
import com.example.youngforyou.commom.util.HttpContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
* <p>Title: BaseController</p>
* <p>Description: controller基类</p>
* <p>Company: XSQ</p> 
* @author tyw
* @date 2018年12月24日下午1:29:19
*/
public class BaseController {

    protected HttpServletRequest getHttpServletRequest() {
        return HttpContext.getRequest();
    }

    protected HttpServletResponse getHttpServletResponse() {
        return HttpContext.getResponse();
    }
	
	public Result getSuccess() {
		return new SuccessResult();
	}
	
	public Result getSuccess(String message) {
		return new SuccessResult(message);
	}
	
	public Result getSuccess(Object data) {
		return new SuccessResult(data);
	}
	
	public Result getSuccess(String message, Object data) {
		return new SuccessResult(message,data);
	}
	
	public Result getError(ErrorEnum errorEnum) {
		return new ErrorResult(errorEnum);
	}
	
	public Result getError(int code,String message) {
		return new ErrorResult(code, message);
	}
	
	public Result getError(String message) {
		return new ErrorResult(400, message);
	}
	
	public Result getError(String message, Object data) {
		return new ErrorResult(400, message, data);
	}

    
}
