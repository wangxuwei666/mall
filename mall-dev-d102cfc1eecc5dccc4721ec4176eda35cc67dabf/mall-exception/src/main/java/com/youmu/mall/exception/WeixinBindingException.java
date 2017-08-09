/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.exception;

/**
 * 
 * @author yujiahao
 * @version $Id: WeixinBindingException.java, v 0.1 2017年4月13日 下午4:02:19 yujiahao Exp $
 */
@SuppressWarnings("serial")
public class WeixinBindingException extends RuntimeException{
    public WeixinBindingException() {
        super();
    }

    public WeixinBindingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public WeixinBindingException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeixinBindingException(String message) {
        super(message);
    }

    public WeixinBindingException(Throwable cause) {
        super(cause);
    }
}
