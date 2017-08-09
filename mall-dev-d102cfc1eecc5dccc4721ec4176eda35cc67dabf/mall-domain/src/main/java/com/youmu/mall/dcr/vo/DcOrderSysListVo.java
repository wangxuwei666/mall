/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.dcr.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youmu.common.excel.utils.ExcelExportField;

/**
 * 装修贷款系统列表对象.
 * @author zh
 * @version $Id: DcrOrderSysListVo.java v 0.1 2017年3月3日 下午7:37:57 zh Exp $
 */
@ExcelExportField("装修贷款财务账单")
public class DcOrderSysListVo {
    
    /** 订单主键*/
    private Long id;
    
    /** 手机号 */
    @ExcelExportField("手机号码")
    private String mobile;
    
    /** 订单编号 */
    @ExcelExportField("订单编号")
    private String orderNo;
    
    /** 姓名 */
    @ExcelExportField("用户姓名")
    private String realName;
    
    @ExcelExportField("合同总金额")
    private BigDecimal totalAmount;
    
    /** 当前进度的金额 */
    @ExcelExportField("打款金额")
    private BigDecimal amount;
    
    /** 当前进度  */
    @ExcelExportField("进度")
    private String progress;
    
    /** 当前进度的id */
    private Long progressId;
    
    /** 当前进度的名称 */
    @ExcelExportField("进度名称")
    private String progressName;
    
    /** 银行用户名  */
    @ExcelExportField("银行账户名")
    private String bankUserName;
    
    /** 银行账户 */
    @ExcelExportField("银行账号")
    private String bankAccout;
    
    /** 银行名称 */
    @ExcelExportField("银行名称")
    private String bankName;
    
    /** 进度提交时间 */
    @ExcelExportField("进度提交时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date gmtSubmit;
    
    /** 系统审核的状态 */
    private Integer sysVerifiStatus;
    
    /** 系统审核备注  */
    private String sysVerifiRemark;
    
    /** 订单创建时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date gmtCreate;
   
    /** 2还未打款 3 已经打款 */
    private Integer status;
    
    /** 打款时间  */
    @ExcelExportField("打款时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date gmtRemit;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName;
    }

    public String getBankAccout() {
        return bankAccout;
    }

    public void setBankAccout(String bankAccout) {
        this.bankAccout = bankAccout;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Date getGmtSubmit() {
        return gmtSubmit;
    }

    public void setGmtSubmit(Date gmtSubmit) {
        this.gmtSubmit = gmtSubmit;
    }

    public Integer getSysVerifiStatus() {
        return sysVerifiStatus;
    }

    public void setSysVerifiStatus(Integer sysVerifiStatus) {
        this.sysVerifiStatus = sysVerifiStatus;
    }

    public String getSysVerifiRemark() {
        return sysVerifiRemark;
    }

    public void setSysVerifiRemark(String sysVerifiRemark) {
        this.sysVerifiRemark = sysVerifiRemark;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtRemit() {
        return gmtRemit;
    }

    public void setGmtRemit(Date gmtRemit) {
        this.gmtRemit = gmtRemit;
    }

    public String getProgressName() {
        return progressName;
    }

    public void setProgressName(String progressName) {
        this.progressName = progressName;
    }

    public Long getProgressId() {
        return progressId;
    }

    public void setProgressId(Long progressId) {
        this.progressId = progressId;
    }

}
