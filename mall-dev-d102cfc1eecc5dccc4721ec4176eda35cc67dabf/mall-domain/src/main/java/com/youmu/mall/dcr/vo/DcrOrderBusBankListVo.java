/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.dcr.vo;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youmu.common.context.GlobalConstant;

/**
 * 银行订单展示对象
 * @author zh
 * @version $Id: DcrOrderBusBankListVo.java, v 0.1 2017年3月6日 下午2:44:25 zh Exp $
 */
public class DcrOrderBusBankListVo {
    
    /** 订单的id */
    private Long id;
    
    /** 订单编号 */
    private String orderNo;
       
    private String mobile;
    
    /** 订单创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private String gmtCreate;
    
    /** 订单审核的状态  0 待审核  1 审核通过 2 审核不通过*/
    private Integer status;
    
    /** 审核不通过时的备注 */
    private String remark;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        if(status != null) {
            // 待审核
            if(status == GlobalConstant.DCROrderStatus.SYS_AUDIT_CONTRACT) {
                this.status = 0;
            }
            // 审核通过
            if(status >= GlobalConstant.DCROrderStatus.BANK_AUDIT) {
                this.status = 1;
            }
            // 审核不通过
            if(status == GlobalConstant.DCROrderStatus.BANK_AUDIT_REJECT) {
                this.status = 2;
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
