package com.example.youngforyou.commom.result;

/**
* <p>Title: Result</p>
* <p>Description: 返回给前台的提示（最终转化为json形式）</p>
* <p>Company: XSQ</p> 
* @author tyw
* @date 2018年12月17日上午11:27:17
*/
public class Result {

    protected int code;
    protected String message;
    protected Object data;

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
 
    
}
