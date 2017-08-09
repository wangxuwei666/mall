/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.base.query;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分页查询基类.
 * 
 * @author zh
 * @version $Id: PageQuery.java, v 0.1 2017年2月24日 下午3:11:37 zh Exp $
 */
public class PageQuery extends BaseQuery {
    
    /** 默认的页码 */
    public static final int DEFAULT_PAGE_NUM = 1;
    
    /** 默认的数据条目  */
    public static final int DEFAULT_PAGE_SIZE = 10;
    
    /** 页码 */
    @ApiModelProperty("页码")
    private Integer pageNum;
    
    /** 页码尺寸 */
    @ApiModelProperty("每页多少条数据")
    private Integer pageSize;
    
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if(pageNum != null)
            this.pageNum = pageNum <= 0 ? DEFAULT_PAGE_NUM : pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize != null)
            this.pageSize = ((pageSize <= 0 || pageSize == Integer.MAX_VALUE) ? DEFAULT_PAGE_SIZE : pageSize);
    }
    
    public Integer getStartRow() {
        if(pageNum != null && pageSize != null)
            return Math.max(0, (pageNum - 1) * pageSize);
        return null;
    }
    
    public Integer getEndRow() {
        if(pageNum != null && pageSize != null)
            return Math.max(1, pageNum * pageSize);
        return null;
    }
}
