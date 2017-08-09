/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.excel.utils;

import java.lang.reflect.Field;

/**
 * excel字段
 * @author zh
 * @version $Id: ExcelFieldMap.java, v 0.1 2017年3月8日 下午7:43:56 zh Exp $
 */
public class ExcelField {
    
    private String name;
    
    private String property;
    
    private StringConverter converter;
    
    private Field field;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public StringConverter getConverter() {
        return converter;
    }

    public void setConverter(StringConverter converter) {
        this.converter = converter;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
