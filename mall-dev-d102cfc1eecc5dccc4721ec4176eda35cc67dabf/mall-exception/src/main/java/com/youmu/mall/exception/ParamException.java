/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.exception;

/**
 * 参数异常.
 * @author zh
 * @version $Id: ParamException.java, v 0.1 2017年2月25日 下午4:14:57 zh Exp $
 */
@SuppressWarnings("serial")
public class ParamException extends RuntimeException {

    public ParamException() {
        super();
    }

    public ParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(Throwable cause) {
        super(cause);
    }
}
