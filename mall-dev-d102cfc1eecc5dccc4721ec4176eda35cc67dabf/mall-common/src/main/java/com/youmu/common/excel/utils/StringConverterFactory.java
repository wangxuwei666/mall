/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.excel.utils;

import java.util.Date;

import org.apache.http.client.utils.DateUtils;

/**
 * 转换成字符串的工具类.
 * @author zh
 * @version $Id: StringConverterFactory.java, v 0.1 2017年3月8日 下午8:22:29 zh Exp $
 */
public class StringConverterFactory {
    
    /** 默认的字符串转换器 */
    public static StringConverter getDefaultStringConverter() {
        
        return new StringConverter() {
            @Override
            public String convert(Object obj) {
                if(obj==null)
                    return "";
                return obj.toString();
            }
        };
    }
    
    /** 时间装换器 */
    public static StringConverter getDateStringConverter(final String pattern) {
        
        return new StringConverter() {
            @Override
            public String convert(Object obj) {
                if(obj == null) 
                    return "";
                Date date = (Date) obj;
                return DateUtils.formatDate(date, pattern);
            }
        };
    }
}
