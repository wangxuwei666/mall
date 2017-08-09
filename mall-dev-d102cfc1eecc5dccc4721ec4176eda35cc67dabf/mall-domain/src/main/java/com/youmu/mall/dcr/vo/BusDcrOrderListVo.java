/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.dcr.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 商户装修贷款订单的列表对象.
 * @author zh
 * @version $Id: BusDcrOrderListVjava; v 0.1 2017年3月7日 上午11:18:50 zh Exp $
 */
public class BusDcrOrderListVo {

    /** 订单的id */
    private Long id;
    
    /** 当前装修阶段的id */
    private Long phaseId;

    /** 用户手机号码 */
    private String mobile;

    /** 订单的编号 */
    private String orderNo;

    /** 真实姓名 */
    private String realName;

    /** 总金额 */
    private BigDecimal totalAmount;

    /** 订单的状态 */
    private String status;

    /** 流程名称 */
    private String processName;

    /** 当前进度 */
    private String progress;

    /** 系统审核状态 */
    private Integer sysVerifiStatus;

    /** 用户审核状态 */
    private String userVerifiStatus;

    /** 流程的状态 */
    private String precessStatus;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date gmtCreate;
    
    /** 订单完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date gmtFinish;

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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Integer getSysVerifiStatus() {
        return sysVerifiStatus;
    }

    public void setSysVerifiStatus(Integer sysVerifiStatus) {
        this.sysVerifiStatus = sysVerifiStatus;
    }

    public String getUserVerifiStatus() {
        return userVerifiStatus;
    }

    public void setUserVerifiStatus(String userVerifiStatus) {
        this.userVerifiStatus = userVerifiStatus;
    }

    public String getPrecessStatus() {
        return precessStatus;
    }

    public void setPrecessStatus(String precessStatus) {
        this.precessStatus = precessStatus;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(Long phaseId) {
        this.phaseId = phaseId;
    }

    public Date getGmtFinish() {
        return gmtFinish;
    }

    public void setGmtFinish(Date gmtFinish) {
        this.gmtFinish = gmtFinish;
    }
 }
