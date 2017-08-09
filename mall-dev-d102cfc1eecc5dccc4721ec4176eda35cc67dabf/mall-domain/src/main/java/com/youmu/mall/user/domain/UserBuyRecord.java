/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.user.domain;

import com.youmu.mall.base.domain.BaseDomain;

/**
 * 用户购买活动商品数量记录
 * @author yujiahao
 * @version $Id: UserBuyRecord.java, v 0.1 2017年3月14日 上午11:46:53 yujiahao Exp $
 */
public class UserBuyRecord extends BaseDomain {
    
    /**用户id*/
    private Long    userId;

    /**商品组id*/
    private Long    groupId;

    /**已购数量*/
    private Integer buyQuantity;

    public UserBuyRecord() {
        super();
    }

    /**
     * @param groupId
     * @param userId
     * @param buyQuantity
     */
    public UserBuyRecord(long groupId, Long userId, Integer buyQuantity) {
        this.groupId = groupId;
        this.userId = userId;
        this.buyQuantity = buyQuantity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Integer buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

}
