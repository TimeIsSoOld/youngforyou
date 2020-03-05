package com.example.youngforyou.commom.result;

/**
* <p>Title: Error</p>
* <p>Description:返回给前台的错误提示 </p>
* <p>Company: XSQ</p> 
* @author tyw
* @date 2018年12月17日上午11:28:05
*/
public class ErrorResult extends Result {

    public ErrorResult(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
    
    public ErrorResult(int code, String message,Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }
    
    public ErrorResult(ErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.message = errorEnum.getMessage();
    }
}
