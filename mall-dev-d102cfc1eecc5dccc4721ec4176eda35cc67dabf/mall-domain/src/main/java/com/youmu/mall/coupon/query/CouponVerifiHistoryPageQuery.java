/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.coupon.query;

import java.util.Arrays;
import java.util.List;

import com.youmu.common.context.GlobalConstant;
import com.youmu.mall.base.query.PageQuery;

/**
 * 核销历史查询对象.
 * @author zh
 * @version $Id: CouponVerifiHistoryPageQuery.java, v 0.1 2017年3月5日 上午11:40:58 zh Exp $
 */
public class CouponVerifiHistoryPageQuery extends PageQuery {
    
    /** 核销用户的id */
    private Long verifiUserId;
    
    /** 是否是装修贷款的优惠券 */
    private Boolean isDcr;
    
    /** 订单的状态 */
    private Integer orderStatus;
    
    /** 订单的状态 */
    private Integer minOrderStatus;
    
    private List<Integer> orderStatusLsit;

    public Boolean getIsDcr() {
        return isDcr;
    }

    public void setIsDcr(Boolean isDcr) {
        this.isDcr = isDcr;
    }

    public Long getVerifiUserId() {
        return verifiUserId;
    }

    public void setVerifiUserId(Long verifiUserId) {
        this.verifiUserId = verifiUserId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public void setStatus(Integer status) {
        if(status != null && this.isDcr != null && this.isDcr) {
            switch (status) {
                case 0:
                    // 商户全部可提交数据
                    this.minOrderStatus = GlobalConstant.DCROrderStatus.USER_USE_QRCODE;
                    break;
                case 1:
                    // 还未提交数据 3 5 7
                    this.setOrderStatusLsit(Arrays.asList(GlobalConstant.DCROrderStatus.USER_USE_QRCODE, GlobalConstant.DCROrderStatus.SYS_CONTRACT_REJECT, GlobalConstant.DCROrderStatus.BANK_AUDIT_REJECT));
                    break;
                case 2:
                    // 已经提交了数据
                    this.setOrderStatusLsit(Arrays.asList(
                        GlobalConstant.DCROrderStatus.BUSINESS_SUBMIT_COMPACT, 
                        GlobalConstant.DCROrderStatus.SYS_AUDIT_CONTRACT, 
                        GlobalConstant.DCROrderStatus.BANK_AUDIT,
                        GlobalConstant.DCROrderStatus.BUSINESS_DECORATING,
                        GlobalConstant.DCROrderStatus.ORDER_FINISHED
                     ));
                    break;
            }
        }
    }

    public Integer getMinOrderStatus() {
        return minOrderStatus;
    }

    public void setMinOrderStatus(Integer minOrderStatus) {
        this.minOrderStatus = minOrderStatus;
    }

    public List<Integer> getOrderStatusLsit() {
        return orderStatusLsit;
    }

    public void setOrderStatusLsit(List<Integer> orderStatusLsit) {
        this.orderStatusLsit = orderStatusLsit;
    }
}
