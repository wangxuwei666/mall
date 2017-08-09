/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.bus.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 商户后台列表对象.
 * @author zh
 * @version $Id: Industry.java, v 0.1 2017年2月26日 下午3:18:58 zh Exp $
 */
public class BusinessListVo{
    
    /** 商户id */
    private Long id;
    
    private int businessTypeId;
    
    /** 商户类型名称 */
    private String typeName;
    
    /** 商户名称  */
    private String name;
    
    /** 协议截止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date gmtEnd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public int getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(int businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

}
