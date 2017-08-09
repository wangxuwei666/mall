/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.banner.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.banner.dao.BannerDao;
import com.youmu.mall.banner.domain.Banner;
import com.youmu.mall.banner.query.BannerQuery;
import com.youmu.mall.banner.service.IBannerService;
import com.youmu.mall.banner.vo.BannerShopVo;
import com.youmu.mall.banner.vo.BannerSysVo;

/**
 * 
 * @author yujiahao
 * @version $Id: BannerServiceImpl.java, v 0.1 2017年3月6日 下午7:29:01 yujiahao Exp $
 */
@Service
public class BannerServiceImpl implements IBannerService{
    
    @Resource
    private BannerDao bannerDao;

    /** 
     * @see com.youmu.mall.banner.service.IBannerService#getBannerList(java.lang.Integer)
     */
    @Override
    public Page<BannerSysVo> getBannerSysList(BannerQuery query) {
        Page<BannerSysVo> page = new Page<>();
        page.setPageNum(query.getPageNum());
        page.setPageSize(query.getPageSize());
        page.setData(bannerDao.getBannerSysList(query));
        page.setTotal(bannerDao.getByCount(query));
        return page;
    }

    /** 
     * @see com.youmu.mall.banner.service.IBannerService#getBannerShopVo()
     */
    @Override
    public List<BannerShopVo> getBannerShopVo(Integer bannerType) {
        return bannerDao.getBannerShopVo(bannerType);
    }

    /** 
     * @see com.youmu.mall.banner.service.IBannerService#deleteBannerById(java.lang.Long)
     */
    @Override
    public void deleteBannerById(Long id) {
        bannerDao.delete(id);
    }

    /** 
     * @see com.youmu.mall.banner.service.IBannerService#updateBanner(com.youmu.mall.banner.domain.Banner)
     */
    @Override
    public void updateBanner(Banner banenr) {
        bannerDao.update(banenr);
    }

    /** 
     * @see com.youmu.mall.banner.service.IBannerService#saveBanner(com.youmu.mall.banner.domain.Banner)
     */
    @Override
    public void saveBanner(Banner banenr) {
        bannerDao.save(banenr);
    }

    /** 
     * @see com.youmu.mall.banner.service.IBannerService#getById(java.lang.Long)
     */
    @Override
    public BannerSysVo getById(Long bannerId) {
        return bannerDao.getById(bannerId);
    }
    
}
