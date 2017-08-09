/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.shop.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.mall.exception.AuthException;
import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.exception.SystemInnerException;
import com.youmu.mall.exception.WeixinBindingException;

/**
 * 异常处理控制器.
 * 
 * @author zh
 * @version $Id: ExceptionHandlerController.java, v 0.1 2017年2月27日 下午12:41:01 zh Exp $
 */
@ControllerAdvice
public class ExceptionHandlerController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @ExceptionHandler
    @ResponseBody
    public JsonResult handleException(HttpServletRequest request, Throwable ex) {
        logger.error("统一处理的异常： ", ex);
        int code = JsonResultUtils.UNKOWN_EXCEPTION;
        String message = ex.getMessage();
        if(ex instanceof BusinessException) {
            code = JsonResultUtils.BUS_EXCEPTION;
        } else if(ex instanceof ParamException){
           code = JsonResultUtils.PARAM_EXCEPTION;
        } else if(ex instanceof AuthException){
            code = JsonResultUtils.AUTH_EXCEPTION;
        } else if(ex instanceof SystemInnerException) {
            code = JsonResultUtils.SYS_EXCEPTION;
        } else if(ex instanceof WeixinBindingException) {
            code = JsonResultUtils.WEIXIN_BINDING_EXCEPTION;
        } else {
             message = "系统内部异常: " + ex.getMessage();
        }
        return JsonResultUtils.fail(code, message);
    }
}
