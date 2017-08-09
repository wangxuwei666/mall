/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.result.utils;

/**
 * ajax结果.
 * @author zh
 * @version $Id: AjaxResult.java, v 0.1 2017年2月25日 下午3:59:05 zh Exp $
 */
public class JsonResult {
    
    private int code;
    
    private String message;
    
    private Object data;
    
    public JsonResult(int code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

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
