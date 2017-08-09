/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.user.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.youmu.mall.base.query.PageQuery;

/**
 * 用户查询对象 后台
 * @author zh
 * @version $Id: UserQuery.java, v 0.1 2017年2月27日 下午6:54:18 zh Exp $
 */
public class UserQuery extends PageQuery {
    
    /** 注册时间 开始 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date gmtCreateStart;
    
    /** 注册时间最大值 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date gmtCreateEnd;
    
    /** 用户手机号码模糊查询 */
    private String mobile;

    public Date getGmtCreateStart() {
        return gmtCreateStart;
    }

    public void setGmtCreateStart(Date gmtCreateStart) {
        this.gmtCreateStart = gmtCreateStart;
    }

    public Date getGmtCreateEnd() {
        return gmtCreateEnd;
    }

    public void setGmtCreateEnd(Date gmtCreateEnd) {
        this.gmtCreateEnd = gmtCreateEnd;
    }

        /**
         * Getter method for property <tt>mobile</tt>.
         * 
         * @return property value of mobile
         */
    public String getMobile() {
        return mobile;
    }

        /**
         * Setter method for property <tt>mobile</tt>.
         * 
         * @param mobile value to be assigned to property mobile
         */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
