/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.base.query;

/**
 * 查询对象基类.
 * 
 * @author zh
 * @version $Id: BaseQuery.java, v 0.1 2017年2月24日 下午3:10:53 zh Exp $
 */
public class BaseQuery {
    
    /**
     * 关键词
     */
    private String keywords;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
