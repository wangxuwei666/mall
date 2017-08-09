/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.dcr.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 装修流程的vo
 * @author zh
 * @version $Id: DCProcessVo.java, v 0.1 2017年3月7日 下午8:21:49 zh Exp $
 */
@ApiModel("装修流程对象")
public class DCProcessVo {
    
    @ApiModelProperty(value="装修流程id", name="id")
    private Long id;
    
    @ApiModelProperty(value="装修流程名称", name="id")
    private String name;
    
    @ApiModelProperty(value="装修流程在所有的里面占的比例", name="id")
    private BigDecimal proportion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    } 
}
