/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.banner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youmu.mall.banner.domain.Banner;
import com.youmu.mall.banner.query.BannerQuery;
import com.youmu.mall.banner.vo.BannerShopVo;
import com.youmu.mall.banner.vo.BannerSysVo;

/**
 * 
 * @author yujiahao
 * @version $Id: BannerDao.java, v 0.1 2017年3月6日 下午7:31:04 yujiahao Exp $
 */
public interface BannerDao {

    /**
     * 管理后台-查询banner列表
     * @param query
     * @return
     */
    List<BannerSysVo> getBannerSysList(BannerQuery query);

    /**
     * 后台管理-统计banner个数
     * @param query
     * @return
     */
    Long getByCount(BannerQuery query);

    /**
     * 用户端-查询首页banner
     * @return
     */
    List<BannerShopVo> getBannerShopVo(@Param("bannerType")Integer bannerType);

    /**
     * 管理后台-删除banner
     * @param id
     */
    void delete(@Param("id")Long id);

    /**
     * 管理后台-修改banner
     * @param banenr
     */
    void update(Banner banenr);

    /**
     * 管理后台-添加banner
     * @param banenr
     */
    void save(Banner banenr);

    /**
     * 根据id获取banner
     * @param bannerId
     * @return
     */
    BannerSysVo getById(Long bannerId);

}
