package com.example.youngforyou.commom.result;

/**
* <p>Title: Success</p>
* <p>Description:返回给前台的成功提示 </p>
* <p>Company: XSQ</p> 
* @author tyw
* @date 2018年12月17日上午11:27:47
*/
public class SuccessResult extends Result{
	
	public SuccessResult(){
		super.code = 200;
		super.message = "成功";
	}
	
	public SuccessResult(String message){
		super.code = 200;
		super.message = message;
	}
	
	public SuccessResult(Object data) {
		super.code = 200;
		super.message = "成功";
		super.data = data;
	}
	
	public SuccessResult(String message,Object data){
		super.code = 200;
		super.message = message;
		super.data = data;
	}
}
