/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.domain;

import com.youmu.mall.base.domain.BaseDomain;
import com.youmu.mall.user.domain.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品评价
 * @author zh
 * @version $Id: ProductComment.java, v 0.1 2017年5月3日 下午2:48:43 zh Exp $
 */
@ApiModel("商品留言/评价")
public class ProductReviews extends BaseDomain {

    private static final long serialVersionUID = 1L;
    
    /** 评价的商品 */
    @ApiModelProperty(value="商品")
    private Product product;
    
    /** 评价的用户 */
    private User user;
    
    /** 用户手机号码 */
    @ApiModelProperty("评价者的手机号码")
    private String userMobile;
    
    /** 评价者 */
    @ApiModelProperty("评价者的昵称或用户名")
    private String critic;
    
    /** 评价内容  */
    @ApiModelProperty("留言/评价的文本内容")
    private String text;
    
    /** 是否通过审核 */
    @ApiModelProperty("是否通过审核")
    private Boolean passed = true;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCritic() {
        return critic;
    }

    public void setCritic(String critic) {
        this.critic = critic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ProductComment [product=" + product + ", user=" + user + ", critic=" + critic + ", text=" + text
               + ", getId()=" + getId() + ", getGmtCreate()=" + getGmtCreate() + ", getGmtModified()="
               + getGmtModified() + ", getDeleteFlag()=" + getDeleteFlag() + "]";
    }
    
    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }
}
