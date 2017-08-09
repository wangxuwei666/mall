/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.user.query;

import com.youmu.mall.base.query.PageQuery;

/**
 * 商户账户查询对象
 * @author zh
 * @version $Id: BusinessUserQuery.java, v 0.1 2017年2月28日 下午12:37:43 zh Exp $
 */
public class BusinessUserQuery extends PageQuery {
    
    /** 商户id */
    private Long businessId;
    
    /** 账户的类型 */
    private Integer type;
    
    /** 是否是装修公司 */
    private Boolean isDCR;


        /**
         * Setter method for property <tt>typeId</tt>.
         * 
         * @param typeId value to be assigned to property typeId
         */
    public void setTypeId(Integer typeId) {
        if(typeId != null) {
            switch (typeId) {
                
                // 普通商户 除了装修公司
                case 1:
                    this.type = 1;
                    this.isDCR = false;
                    break;
               
                // 装修公司
                case 2:
                    this.type = 1;
                    this.isDCR = true;
                    break;
                
               // 银行商户账户
                case 3:
                    this.type = 2;
                    break;
               
               // 后台商户
                case 4:
                    this.type = 3;
                    break;
                default:
                    break;
            }
        }
    }

            /**
             * Getter method for property <tt>isDCR</tt>.
             * 
             * @return property value of isDCR
             */
        public Boolean getIsDCR() {
            return isDCR;
        }

            /**
             * Setter method for property <tt>isDCR</tt>.
             * 
             * @param isDCR value to be assigned to property isDCR
             */
        public void setIsDCR(Boolean isDCR) {
            this.isDCR = isDCR;
        }

                /**
                 * Getter method for property <tt>type</tt>.
                 * 
                 * @return property value of type
                 */
            public Integer getType() {
                return type;
            }

                /**
                 * Setter method for property <tt>type</tt>.
                 * 
                 * @param type value to be assigned to property type
                 */
            public void setType(Integer type) {
                this.type = type;
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
