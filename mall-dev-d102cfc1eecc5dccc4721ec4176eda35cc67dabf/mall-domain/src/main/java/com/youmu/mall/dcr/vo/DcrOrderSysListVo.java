/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.dcr.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 装修贷款系统列表对象.
 * @author zh
 * @version $Id: DcrOrderSysListVo.java v 0.1 2017年3月3日 下午7:37:57 zh Exp $
 */
public class DcrOrderSysListVo {
    
    /** 主键*/
    private Long id;
    
    /** 手机号 */
    private String mobile;
    
    /** 订单编号 */
    private String orderNo;
    
    /** 姓名 */
    private String realName;
    
    /** 装修地址 */
    private String address;
    
    /** 装修贷款申请时间  */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date applyTime;
    
    /** 提交装修贷款合同的时间  */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date gmtSubCompact;
    
    /** 订单的状态 */
    private Integer status;
    
    /** 系统审核备注 */
    private String sysCheckedRemark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtSubCompact() {
        return gmtSubCompact;
    }

    public void setGmtSubCompact(Date gmtSubCompact) {
        this.gmtSubCompact = gmtSubCompact;
    }

    public String getSysCheckedRemark() {
        return sysCheckedRemark;
    }

    public void setSysCheckedRemark(String sysCheckedRemark) {
        this.sysCheckedRemark = sysCheckedRemark;
    }
}
