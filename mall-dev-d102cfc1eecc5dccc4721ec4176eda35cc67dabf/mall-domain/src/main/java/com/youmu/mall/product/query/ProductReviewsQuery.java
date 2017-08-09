/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.query;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youmu.mall.base.query.PageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 产品评价查询类
 * @author zh
 * @version $Id: ProductReviewsQuery.java, v 0.1 2017年5月3日 下午4:07:52 zh Exp $
 */
@ApiModel
public class ProductReviewsQuery extends PageQuery {
    
    /** 商品主键 */
    @ApiModelProperty(value = "商品id")
    private Long productId;
    
    /** 是否通过审核  */
    @ApiModelProperty(value = "是否通过审核")
    private Boolean passed;
    
    @ApiModelProperty(value = "上一页的最后一个创建时间,默认当前时间")
    private Date lastGmtCreate = new Date();
    
    @ApiModelProperty(value = "上一页的最后一个id, 默认大值")
    private Long lastId = Long.MAX_VALUE;
    
    @ApiModelProperty(value = "留言开始时间 yyyy-MM-dd")
    private Date gmtCreateStart;
    
    @ApiModelProperty(value = "留言结束时间 yyyy-MM-dd")
    private Date gmtCreateEnd;
    
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Boolean getPassed() {
        return passed;
    }
    
    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public Long getLastId() {
        return lastId;
    }

    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }

    public Date getLastGmtCreate() {
        return lastGmtCreate;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    public void setLastGmtCreate(Date lastGmtCreate) {
        this.lastGmtCreate = lastGmtCreate;
        System.err.println(lastGmtCreate.toLocaleString());
    }

    public Date getGmtCreateStart() {
        return gmtCreateStart;
    }

    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    public void setGmtCreateStart(Date gmtCreateStart) {
        this.gmtCreateStart = gmtCreateStart;
    }

        public Date getGmtCreateEnd() {
            return gmtCreateEnd;
        }
        
        @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
        public void setGmtCreateEnd(Date gmtCreateEnd) {
            this.gmtCreateEnd = gmtCreateEnd;
        }
}
