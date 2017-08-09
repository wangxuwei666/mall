/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.dcr.query;

import com.youmu.common.context.GlobalConstant;
import com.youmu.mall.base.query.PageQuery;

/**
 * 装修订单银行商户查询对象.
 * @author zh
 * @version $Id: DcrOrderBusQuery.java, v 0.1 2017年3月6日 下午2:37:11 zh Exp $
 */
public class DcrOrderBusBankQuery extends PageQuery {
    
    /** 商户id */
    private Long businessId;
    
    /** 订单的状态 */
    private Integer orderStatus;
    
    /** 订单的最小状态 */
    private Integer minOrderStatus;
    
    /** 订单的最大状态 */
    private Integer maxOrderStatus;
    
    /** 排序 */
    private String orderBy = "o.gmt_create desc";

    public void setStatus(Integer status) {
        if(status != null) {
            switch (status) {
                case 0:
                    // 待审核
                    orderBy = "o.gmt_sysverfi desc";
                    orderStatus = GlobalConstant.DCROrderStatus.SYS_AUDIT_CONTRACT;
                    break;
                    // 已经审核
                case 1 :
                    orderBy = "o.gmt_bankverifi desc";
                    minOrderStatus = GlobalConstant.DCROrderStatus.BANK_AUDIT_REJECT;
                    break;
                    // 审核不通过
                case 2 :
                    orderBy = "o.gmt_bankverifi desc";
                    orderStatus = GlobalConstant.DCROrderStatus.BANK_AUDIT_REJECT;
                    break;
                   // 审核通过
                case 3:
                    orderBy = "o.gmt_bankverifi desc";
                    minOrderStatus = GlobalConstant.DCROrderStatus.BANK_AUDIT;
                    break;
            }
        }
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getMinOrderStatus() {
        return minOrderStatus;
    }

    public void setMinOrderStatus(Integer minOrderStatus) {
        this.minOrderStatus = minOrderStatus;
    }

    public Integer getMaxOrderStatus() {
        return maxOrderStatus;
    }

    public void setMaxOrderStatus(Integer maxOrderStatus) {
        this.maxOrderStatus = maxOrderStatus;
    }

        /**
         * Getter method for property <tt>orderBy</tt>.
         * 
         * @return property value of orderBy
         */
    public String getOrderBy() {
        return orderBy;
    }

        /**
         * Setter method for property <tt>orderBy</tt>.
         * 
         * @param orderBy value to be assigned to property orderBy
         */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

            /**
             * Getter method for property <tt>businessId</tt>.
             * 
             * @return property value of businessId
             */
        public Long getBusinessId() {
            return businessId;
        }

            /**
             * Setter method for property <tt>businessId</tt>.
             * 
             * @param businessId value to be assigned to property businessId
             */
        public void setBusinessId(Long businessId) {
            this.businessId = businessId;
        }
}
