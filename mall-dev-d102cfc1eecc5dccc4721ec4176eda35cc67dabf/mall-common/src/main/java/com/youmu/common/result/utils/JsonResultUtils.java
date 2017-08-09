/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.result.utils;

/**
 * ajax返回结果工具类.
 * @author zh
 * @version $Id: AjaxResultUtils.java, v 0.1 2017年2月24日 下午2:28:20 zh Exp $
 */
public class JsonResultUtils {
    
    /** 操作成功 */
    public static final int SUCCESS = 200;
    
    /** 系统内部错误 */
    public static final int SYS_EXCEPTION = 201;
    
    /** 参数错误 */
    public static final int PARAM_EXCEPTION = 202;
    
    /** 业务异常 */
    public static final int BUS_EXCEPTION = 203;
    
    /** 权限校验错误  */
    public static final int AUTH_EXCEPTION = 204;
    
    /** 未知的错误 */
    public static final int UNKOWN_EXCEPTION = 205;

    /** 未绑定微信 */
    public static final int WEIXIN_BINDING_EXCEPTION = 206;

    /** 成功返回有数据  */
    public static JsonResult ok(Object data) {
        return new JsonResult(200, "ok", data);
    }
    
    /** 成功返回无数据  */
    public static JsonResult ok() {
        return new JsonResult(200, "ok", null);
    }
    
    /** 直接错误没有异常 */
    public static JsonResult fail(int code, String message) {
        return new JsonResult(code, message, null);
    }
    
    /** 异常错误 */
    public static JsonResult fail(int code, Throwable e) {
        return new JsonResult(code, e.getMessage(), null);
    }
}
