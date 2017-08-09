/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.dcr.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.youmu.mall.base.domain.BaseDomain;
import com.youmu.mall.bus.domain.Business;
import com.youmu.mall.coupon.domain.Coupon;
import com.youmu.mall.user.domain.BusinessUser;
import com.youmu.mall.user.domain.SysUser;
import com.youmu.mall.user.domain.User;

/**
 * 装修申请订单
 * @author zh
 * @version $Id: DCROrder.java, v 0.1 2017年3月2日 下午4:06:30 zh Exp $
 */
@SuppressWarnings("serial")
public class DCROrder extends BaseDomain {

    /** 申请账号的用户 */
    private User   user;
    
    /** 真实姓名 */
    private String realName;
    
    /** 优惠券的id */
    private Coupon coupon;
    
    /** 优惠券编号  */
    private String couponNumber;

    /** 手机号码 */
    private String mobile;
    
    /** 装修区域编码 */
    private Integer dcDistrict;
    
    /** 装修区域地址 */
    private String dcArea;

    /** 装修地址 */
    private String dcAddress;

    /** 订单编号 */
    private String orderNo;
    
    /** 订单的主状态 */
    private Integer orderStatus;
    
    /** 二维码核销的时间 */
    private Date gmtVerifi;
    
    /** 二维码核销人员的编号 */
    private BusinessUser verifiUser;
    
    /** 二维码核销商户 */
    private Business verifiBusiness;
    
    /** 装修贷款的申请的处理时间  */
    private Date gmtHandle;
    
    /** 后台请求的处理的人员 */
    private SysUser handleUser;
    
    /** 专修贷款合同 */
    private DCRCompact compact;
    
    /** 总金额 */
    private BigDecimal totalAmount;
    
    /** 系统合同审核用户 */
    private SysUser sysVerifiUser;
    
    /** 合同后台审核备注 */
    private String sysVerifiRremark;
    
    /** 后台人员合同审核的时间 */
    private Date gmtSysVerifi;
    
    /** 银行审核时间 */
    private Date       gmtBankVerifi;
    
    /** 银行用户审核id */
    private BusinessUser       bankVerifiUser;
    
    /** 银行审核备注  */
    private String       bankVerifiRemark;
    
    /** 订单正常完成时间  */
    private Date gmtFinish;
    
    public Integer getDcDistrict() {
        return dcDistrict;
    }

    public void setDcDistrict(Integer dcDistrict) {
        this.dcDistrict = dcDistrict;
    }

    public String getDcArea() {
        return dcArea;
    }

    public void setDcArea(String dcArea) {
        this.dcArea = dcArea;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDcAddress() {
        return dcAddress;
    }

    public void setDcAddress(String dcAddress) {
        this.dcAddress = dcAddress;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public String getCouponNumber() {
        return couponNumber;
    }

    public void setCouponNumber(String couponNumber) {
        this.couponNumber = couponNumber;
    }

    public Date getGmtVerifi() {
        return gmtVerifi;
    }

    public void setGmtVerifi(Date gmtVerifi) {
        this.gmtVerifi = gmtVerifi;
    }

    public BusinessUser getVerifiUser() {
        return verifiUser;
    }

    public void setVerifiUser(BusinessUser verifiUser) {
        this.verifiUser = verifiUser;
    }

    public Date getGmtHandle() {
        return gmtHandle;
    }

    public void setGmtHandle(Date gmtHandle) {
        this.gmtHandle = gmtHandle;
    }

    public SysUser getHandleUser() {
        return handleUser;
    }

    public void setHandleUser(SysUser handleUser) {
        this.handleUser = handleUser;
    }

    public DCRCompact getCompact() {
        return compact;
    }

    public void setCompact(DCRCompact compact) {
        this.compact = compact;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public SysUser getSysVerifiUser() {
        return sysVerifiUser;
    }

    public void setSysVerifiUser(SysUser sysVerifiUser) {
        this.sysVerifiUser = sysVerifiUser;
    }

    public String getSysVerifiRremark() {
        return sysVerifiRremark;
    }

    public void setSysVerifiRremark(String sysVerifiRremark) {
        this.sysVerifiRremark = sysVerifiRremark;
    }

    public Date getGmtSysVerifi() {
        return gmtSysVerifi;
    }

    public void setGmtSysVerifi(Date gmtSysVerifi) {
        this.gmtSysVerifi = gmtSysVerifi;
    }

    public Date getGmtBankVerifi() {
        return gmtBankVerifi;
    }

    public void setGmtBankVerifi(Date gmtBankVerifi) {
        this.gmtBankVerifi = gmtBankVerifi;
    }

    public String getBankVerifiRemark() {
        return bankVerifiRemark;
    }

    public void setBankVerifiRemark(String bankVerifiRemark) {
        this.bankVerifiRemark = bankVerifiRemark;
    }

    public BusinessUser getBankVerifiUser() {
        return bankVerifiUser;
    }

    public void setBankVerifiUser(BusinessUser bankVerifiUser) {
        this.bankVerifiUser = bankVerifiUser;
    }

    public Business getVerifiBusiness() {
        return verifiBusiness;
    }

    public void setVerifiBusiness(Business verifiBusiness) {
        this.verifiBusiness = verifiBusiness;
    }

    public Date getGmtFinish() {
        return gmtFinish;
    }

    public void setGmtFinish(Date gmtFinish) {
        this.gmtFinish = gmtFinish;
    }
}
