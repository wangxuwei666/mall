/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.dcr.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.youmu.mall.base.domain.BaseDomain;
import com.youmu.mall.user.domain.BusinessUser;

/**
 * 装修贷款合同.
 * @author zh
 * @version $Id: DCRCompact.java, v 0.1 2017年3月5日 下午4:13:36 zh Exp $
 */
@SuppressWarnings("serial")
public class DCRCompact extends BaseDomain {
    
    /** 合同金额 */
    private BigDecimal totalAmount;
    
    /** 合同对应的订单编号  */
    private String orderNo;

    /** 身份证照片 逗号分割*/
    private String idCardImgs;
    
    /** 合同照片 , 分割 */
    private String  compactImgs;
    
    /** 营业执照图片 */
    private String licenseImgs;
    
    /** 提交商户 */
    private BusinessUser submitUser;
    
    /** 提交时间 */
    private Date gmtSubmit;

    public String getIdCardImgs() {
        return idCardImgs;
    }

    public void setIdCardImgs(String idCardImgs) {
        this.idCardImgs = idCardImgs;
    }

    public String getCompactImgs() {
        return compactImgs;
    }

    public void setCompactImgs(String compactImgs) {
        this.compactImgs = compactImgs;
    }

    public BusinessUser getSubmitUser() {
        return submitUser;
    }

    public void setSubmitUser(BusinessUser submitUser) {
        this.submitUser = submitUser;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getLicenseImgs() {
        return licenseImgs;
    }

    public void setLicenseImgs(String licenseImgs) {
        this.licenseImgs = licenseImgs;
    }

    public Date getGmtSubmit() {
        return gmtSubmit;
    }

    public void setGmtSubmit(Date gmtSubmit) {
        this.gmtSubmit = gmtSubmit;
    }
}
