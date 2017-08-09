/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.dcr.vo;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youmu.common.upload.utils.ImageUploadUtils;

/**
 * 装修贷款合同详情.
 * @author zh
 * @version $Id: DCRCompactSysVo.java, v 0.1 2017年3月6日 上午11:50:35 zh Exp $
 */
public class DCRCompactSysVo {
    
    /** 订单的id */
    private Long id;
    
    /** 订单编号 */
    private String orderNo;
    
    /** 用户手机号码 */
    private String userMobile;
    
    /** 总金额 */
    private BigDecimal totalAmount;
    
    /** 身份证照片  */
    private List<String> idCardImgs;
    
    /** 营业执照照片 */
    private List<String> licenseImgs;
    
    /** 装修合同照片 */
    private List<String> compactImgs;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        if(totalAmount != null) {
            this.totalAmount = totalAmount.divide(new BigDecimal(10000));
        }
    }

    public List<String> getIdCardImgs() {
        return ImageUploadUtils.fillPath(idCardImgs);
    }

    public void setIdCardImgs(String idCardImgs) {
        if(idCardImgs != null) {
            this.idCardImgs = Arrays.asList(idCardImgs.split(","));
        }
    }

    public List<String> getLicenseImgs() {
        return ImageUploadUtils.fillPath(licenseImgs);
    }

    public void setLicenseImgs(String licenseImgs) {
        if(licenseImgs != null) {
            this.licenseImgs = Arrays.asList(licenseImgs.split(","));
        }
    }

    public List<String> getCompactImgs() {
        return ImageUploadUtils.fillPath(compactImgs);
    }

    public void setCompactImgs(String compactImgs) {
        if(compactImgs != null) {
            this.compactImgs = Arrays.asList(compactImgs.split(","));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
