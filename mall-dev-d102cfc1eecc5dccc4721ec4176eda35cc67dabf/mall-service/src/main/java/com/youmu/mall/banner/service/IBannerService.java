/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.banner.service;

import java.util.List;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.banner.domain.Banner;
import com.youmu.mall.banner.query.BannerQuery;
import com.youmu.mall.banner.vo.BannerShopVo;
import com.youmu.mall.banner.vo.BannerSysVo;

/**
 * 
 * @author yujiahao
 * @version $Id: IBannerService.java, v 0.1 2017年3月6日 下午7:28:47 yujiahao Exp $
 */
public interface IBannerService {

    /**
     * 管理后台-查询banner列表
     * @param query
     * @return
     */
    Page<BannerSysVo> getBannerSysList(BannerQuery query);

    /**
     * 用户端-查询首页banner列表
     * @return
     */
    List<BannerShopVo> getBannerShopVo(Integer bannerType);

    /**
     * 管理后台-删除banner
     * @param id
     */
    void deleteBannerById(Long id);

    /**
     * 管理后台-修改banner
     * @param banenr
     * @param image 
     */
    void updateBanner(Banner banenr);

    /**
     * 管理后台-添加banner
     * @param banenr
     */
    void saveBanner(Banner banenr);

    /**
     * 根据id查询Banner
     * @param bannerId
     * @return
     */
    BannerSysVo getById(Long bannerId);

}
