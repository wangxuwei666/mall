/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.domain;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import com.youmu.mall.base.domain.BaseDomain;

/**
 * 
 * @author yujiahao
 * @version $Id: ProductGroup.java, v 0.1 2017年3月7日 上午11:34:46 yujiahao Exp $
 */
public class ProductGroup extends BaseDomain{
    /**商品组名称 */
    private String name;
    
    /**商品组内商品总数量 */
    private Integer totalQuantity;
    
    /**用户购买数量限制(共用) */
    private Integer limitQuantity;
    
    /**剩余数量 */
    private Integer restQuantity;
    
    /**活动组类别（1秒杀，2特价，3推荐，4积分） */
    private Integer groupType;
    
    /**商品组活动开始时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date gmtStart;
    
    /**商品组活动结束时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date gmtEnd;
    
    /**商品组图片Banner */
    private String groupImg;
    
    /**商品组商品明细 */
    private List<ProductGroupDetail> items;

    public Integer getRestQuantity() {
        return restQuantity;
    }

    public void setRestQuantity(Integer restQuantity) {
        this.restQuantity = restQuantity;
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(Integer limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    public Date getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(Date gmtStart) {
        this.gmtStart = gmtStart;
    }

    public Date getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public String getGroupImg() {
        return groupImg;
    }

    public void setGroupImg(String groupImg) {
        this.groupImg = groupImg;
    }

    public List<ProductGroupDetail> getItems() {
        return items;
    }

    public void setItems(List<ProductGroupDetail> items) {
        this.items = items;
    }
    
    
}
