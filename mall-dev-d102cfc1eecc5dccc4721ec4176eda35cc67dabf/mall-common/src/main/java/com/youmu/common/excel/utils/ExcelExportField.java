/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.excel.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel导出的字段.
 * @author zh
 * @version $Id: ExcelField.java, v 0.1 2017年3月8日 下午5:54:24 zh Exp $
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
public @interface ExcelExportField {
    
    /** 字段的名称/表名 放在类上 */
    String value();
    
}
