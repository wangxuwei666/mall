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
import com.youmu.mall.user.domain.SysUser;
import com.youmu.mall.user.domain.User;

/**
 * 装修进度数据对象.
 * @author zh
 * @version $Id: DCProgress.java, v 0.1 2017年3月6日 下午7:28:50 zh Exp $
 */
@SuppressWarnings("serial")
public class DCPhase extends BaseDomain {

    /** 该进度的总金额 */
    private BigDecimal     amount;
    
    /** 订单编号 */
    private Long orderId;

    /** 装修订单id */
    private DCROrder order;

    /** 阶段名称  */
    private String     name;

    /** 当前状态  */
    private Integer     status;
    
    /** 资金比例 */
    private BigDecimal proportion;

    /** 进度 */
    private String     progress;
    
    /** 系统审核状态 */
    private Integer sysVerifiStatus;

    /** 总系统审核时间  */
    private Date     gmtSysVerifi;

    /** 审核的系统用户 */
    private SysUser sysVerifiUser;

    /** 系统审核的备注 */
    private String     sysVerifiRemark;

    /** 用户审核的时间 */
    private Date     gmtUserVerifi;

    /** 审核的用户  */
    private User     verifiUser;
    
    /** 用户审核状态 */
    private Integer userVerifiStatus;

    /** 用户审核的备注 */
    private String     userVerifiRemark;

    /** 放款的银行账户 用户名  */
    private String     busBankUserName;

    /** 银行名称 */
    private String     busBankName;

    /** 商户银行账号 */
    private String     busBankAccount;

    /** 打款时间 */
    private Date     gmtRemit;
    
    /** 打款时间 */
    private SysUser     sysRemitUser;
    
    /** 提交的商户 */
    private BusinessUser submitUser;
    
    /** 提交的时间 */
    private Date gmtSubmit;

    public DCROrder getOrder() {
        return order;
    }

    public void setOrder(DCROrder order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Date getGmtSysVerifi() {
        return gmtSysVerifi;
    }

    public void setGmtSysVerifi(Date gmtSysVerifi) {
        this.gmtSysVerifi = gmtSysVerifi;
    }

    public SysUser getSysVerifiUser() {
        return sysVerifiUser;
    }

    public void setSysVerifiUser(SysUser sysVerifiUser) {
        this.sysVerifiUser = sysVerifiUser;
    }

    public String getSysVerifiRemark() {
        return sysVerifiRemark;
    }

    public void setSysVerifiRemark(String sysVerifiRemark) {
        this.sysVerifiRemark = sysVerifiRemark;
    }

    public Date getGmtUserVerifi() {
        return gmtUserVerifi;
    }

    public void setGmtUserVerifi(Date gmtUserVerifi) {
        this.gmtUserVerifi = gmtUserVerifi;
    }

    public User getVerifiUser() {
        return verifiUser;
    }

    public void setVerifiUser(User verifiUser) {
        this.verifiUser = verifiUser;
    }

    public String getUserVerifiRemark() {
        return userVerifiRemark;
    }

    public void setUserVerifiRemark(String userVerifiRemark) {
        this.userVerifiRemark = userVerifiRemark;
    }

    public String getBusBankUserName() {
        return busBankUserName;
    }

    public void setBusBankUserName(String busBankUserName) {
        this.busBankUserName = busBankUserName;
    }

    public String getBusBankName() {
        return busBankName;
    }

    public void setBusBankName(String busBankName) {
        this.busBankName = busBankName;
    }

    public String getBusBankAccount() {
        return busBankAccount;
    }

    public void setBusBankAccount(String busBankAccount) {
        this.busBankAccount = busBankAccount;
    }

    public Date getGmtRemit() {
        return gmtRemit;
    }

    public void setGmtRemit(Date gmtRemit) {
        this.gmtRemit = gmtRemit;
    }

    public SysUser getSysRemitUser() {
        return sysRemitUser;
    }

    public void setSysRemitUser(SysUser sysRemitUser) {
        this.sysRemitUser = sysRemitUser;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    public Integer getUserVerifiStatus() {
        return userVerifiStatus;
    }

    public void setUserVerifiStatus(Integer userVerifiStatus) {
        this.userVerifiStatus = userVerifiStatus;
    }

    public Integer getSysVerifiStatus() {
        return sysVerifiStatus;
    }

    public void setSysVerifiStatus(Integer sysVerifiStatus) {
        this.sysVerifiStatus = sysVerifiStatus;
    }

    public BusinessUser getSubmitUser() {
        return submitUser;
    }

    public void setSubmitUser(BusinessUser submitUser) {
        this.submitUser = submitUser;
    }

    public Date getGmtSubmit() {
        return gmtSubmit;
    }

    public void setGmtSubmit(Date gmtSubmit) {
        this.gmtSubmit = gmtSubmit;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
}
