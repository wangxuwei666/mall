/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.excel.utils;

/**
 * 数据装换器.
 * @author zh
 * @version $Id: DataConverter.java, v 0.1 2017年3月8日 下午7:57:21 zh Exp $
 */
public interface StringConverter {
    
    /** 将对象转换为String  */
    public String convert(Object obj);
}
