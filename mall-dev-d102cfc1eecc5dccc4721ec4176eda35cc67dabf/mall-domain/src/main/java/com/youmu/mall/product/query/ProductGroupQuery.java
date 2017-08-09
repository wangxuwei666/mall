/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.query;

import com.youmu.mall.base.query.PageQuery;

/**
 * 
 * @author yujiahao
 * @version $Id: ProductGroupQuery.java, v 0.1 2017年3月7日 上午11:24:22 yujiahao Exp $
 */
public class ProductGroupQuery extends PageQuery{
    /**活动类型：1-秒杀，2-特价，3-推荐，4-积分*/
    private Integer groupType;
    
    /**商品组活动状态 (0未开始   1秒杀中  2已结束)*/
    private Integer status;
    
    

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }
    
    
}
