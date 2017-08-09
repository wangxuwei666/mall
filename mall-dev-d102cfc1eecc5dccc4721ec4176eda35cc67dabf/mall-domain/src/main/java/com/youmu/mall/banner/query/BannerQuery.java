/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.banner.query;

import com.youmu.mall.base.query.PageQuery;

/**
 * 
 * @author yujiahao
 * @version $Id: BannerQuery.java, v 0.1 2017年3月6日 下午7:33:45 yujiahao Exp $
 */
public class BannerQuery extends PageQuery{
    private Integer bannerType;

    public Integer getBannerType() {
        return bannerType;
    }

    public void setBannerType(Integer bannerType) {
        this.bannerType = bannerType;
    }
    
    
}
