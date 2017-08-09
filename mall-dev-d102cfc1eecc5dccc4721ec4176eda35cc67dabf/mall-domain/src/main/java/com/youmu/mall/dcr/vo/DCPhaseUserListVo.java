/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.dcr.vo;

/**
 * 装修阶段用户列表对象.
 * @author zh
 * @version $Id: DCPhaseUserListVo.java, v 0.1 2017年3月7日 下午3:17:42 zh Exp $
 */
public class DCPhaseUserListVo {
    
    /** 阶段id */
    private Long id;
    
    /** 名称 */
    private String name;
    
    /** 状态 0 不可审核 1 待审核  2 审核不通过  3 审核通过 */
    private Integer status;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
