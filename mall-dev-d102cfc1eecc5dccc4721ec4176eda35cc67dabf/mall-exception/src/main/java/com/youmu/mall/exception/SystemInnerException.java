/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.exception;

/**
 * 系统内部异常异常.
 * @author zh
 * @version $Id: ParamException.java, v 0.1 2017年2月25日 下午4:14:57 zh Exp $
 */
@SuppressWarnings("serial")
public class SystemInnerException extends RuntimeException {

    public SystemInnerException() {
        super();
    }

    public SystemInnerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SystemInnerException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemInnerException(String message) {
        super(message);
    }

    public SystemInnerException(Throwable cause) {
        super(cause);
    }
}
