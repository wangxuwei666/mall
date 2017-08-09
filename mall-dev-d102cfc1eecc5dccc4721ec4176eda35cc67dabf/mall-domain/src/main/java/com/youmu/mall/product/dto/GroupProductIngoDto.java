/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.dto;

/**
 * 商品活动组明细 id,当前库存,控制版本
 * @author yujiahao
 * @version $Id: GroupProductIngoDto.java, v 0.1 2017年3月13日 下午5:18:25 yujiahao Exp $
 */
public class GroupProductIngoDto {
    
    /**商品活动组id*/
    private long groupId;
    
    /**商品活动组明细id*/
    private long groupDetailId;
    
    /**当前库存*/
    private Integer quantity;
    
    /**明细版本*/
    private long version;
    
    

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getGroupDetailId() {
        return groupDetailId;
    }

    public void setGroupDetailId(long groupDetailId) {
        this.groupDetailId = groupDetailId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "GroupProductIngoDto [groupId=" + groupId + ", groupDetailId=" + groupDetailId + ", quantity=" + quantity + ", version=" + version + "]";
    }
    
    
    
    
}
