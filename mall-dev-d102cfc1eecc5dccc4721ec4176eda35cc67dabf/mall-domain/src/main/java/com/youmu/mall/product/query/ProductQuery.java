/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.query;

import com.youmu.mall.base.query.PageQuery;

/**
 * 商品查询对象
 * @author yujiahao
 * @version $Id: ProductQuery.java, v 0.1 2017年2月25日 下午5:00:04 yujiahao Exp $
 */
public class ProductQuery extends PageQuery{
    
    /**商户类别id */
    private Long businessTypeId;
    
    /**商户id */
    private Long businessId;
    
    /**商户状态 */
    private Integer status;
    
    /**库存量(默认值为5，表示小于等于5的库存对应的商品)*/
    private Integer stock;
    
    /**价格类型 (null-普通 ，1-秒杀，2-今日特价 )*/
    private Integer priceType;
    
    /**是否过滤家装商品*/
    private Integer isFilter;
    
    

    public Integer getIsFilter() {
        return isFilter;
    }

    public void setIsFilter(Integer isFilter) {
        this.isFilter = isFilter;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    
      
    
    
    
}
