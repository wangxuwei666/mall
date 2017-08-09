/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.exception;

/**
 * 登录异常.
 * @author zh
 * @version $Id: ParamException.java, v 0.1 2017年2月25日 下午4:14:57 zh Exp $
 */
@SuppressWarnings("serial")
public class AuthException extends RuntimeException {

    public AuthException() {
        super();
    }

    public AuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthException(String message) {
        super(message);
    }

    public AuthException(Throwable cause) {
        super(cause);
    }
}
