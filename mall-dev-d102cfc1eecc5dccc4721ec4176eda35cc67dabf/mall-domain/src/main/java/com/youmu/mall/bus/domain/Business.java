/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.bus.domain;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.youmu.mall.base.domain.BaseDomain;
import com.youmu.mall.bus.vo.BussnessBussVO;

/**
 * 商户.
 * 
 * @author zh
 * @version $Id: Business.java, v 0.1 2017年2月26日 下午2:54:57 zh Exp $
 */
@SuppressWarnings("serial")
public class Business extends BaseDomain {
    
    /** 商户名 */
    private String name;
    
    /** 商户地址 */
    private String address;
    
    /** 联系电话 */
    private String phone;
    
    /** 签约开始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date gmtStart;
    
    /** 签约结束时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date gmtEnd;
    
    /** 备注 */
    private String remark;
    
    /** 商户类型 */
    private BusinessType type;
    
    /** 银行类型 1 对公 2 对私 */
    private Integer bankType;
    
    /** 银行名称 */
    private String bankName;
    
    /** 银行账户 */
    private String bankAccount;
    
    /** 银行开户名 */
    private String bankUsername;
    
    /** 营业执照图片 */
    private String busLicense;
    
    /** 门店图片 */
    private String busStoreImgs;
    
    /** 是否通过了商户认证  */
    private Boolean authPass;
    
    private List<BussnessBussVO> bussnessBussVOList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(Date gmtStart) {
        this.gmtStart = gmtStart;
    }

    public Date getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public BusinessType getType() {
        return type;
    }

    public void setType(BusinessType type) {
        this.type = type;
    }

    public Integer getBankType() {
        return bankType;
    }

    public void setBankType(Integer bankType) {
        this.bankType = bankType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankUsername() {
        return bankUsername;
    }

    public void setBankUsername(String bankUsername) {
        this.bankUsername = bankUsername;
    }

        /**
         * Getter method for property <tt>busLicense</tt>.
         * 
         * @return property value of busLicense
         */
    public String getBusLicense() {
        return busLicense;
    }

        /**
         * Setter method for property <tt>busLicense</tt>.
         * 
         * @param busLicense value to be assigned to property busLicense
         */
    public void setBusLicense(String busLicense) {
        this.busLicense = busLicense;
    }

            /**
             * Getter method for property <tt>busStoreImgs</tt>.
             * 
             * @return property value of busStoreImgs
             */
        public String getBusStoreImgs() {
            return busStoreImgs;
        }

            /**
             * Setter method for property <tt>busStoreImgs</tt>.
             * 
             * @param busStoreImgs value to be assigned to property busStoreImgs
             */
        public void setBusStoreImgs(String busStoreImgs) {
            this.busStoreImgs = busStoreImgs;
        }

                /**
                 * Getter method for property <tt>authPass</tt>.
                 * 
                 * @return property value of authPass
                 */
            public Boolean getAuthPass() {
                return authPass;
            }

                /**
                 * Setter method for property <tt>authPass</tt>.
                 * 
                 * @param authPass value to be assigned to property authPass
                 */
            public void setAuthPass(Boolean authPass) {
                this.authPass = authPass;
            }

				public List<BussnessBussVO> getBussnessBussVOList() {
					return bussnessBussVOList;
				}

				public void setBussnessBussVOList(List<BussnessBussVO> bussnessBussVOList) {
					this.bussnessBussVOList = bussnessBussVOList;
				}
            
}
