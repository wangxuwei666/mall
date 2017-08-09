/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.salelog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.salelog.dao.SalelogDao;
import com.youmu.mall.salelog.query.SalelogQuery;
import com.youmu.mall.salelog.service.ISalelogService;
import com.youmu.mall.salelog.vo.SalelogSysVo;

/**
 * 
 * @author yujiahao
 * @version $Id: SalelogServiceImpl.java, v 0.1 2017年3月15日 下午6:03:47 yujiahao Exp $
 */
@Service
public class SalelogServiceImpl implements ISalelogService{
    
    @Resource
    private SalelogDao salelogDao;

    /** 
     * @see com.youmu.mall.salelog.service.ISalelogService#getSalelogSysList(com.youmu.mall.salelog.query.SalelogQuery)
     */
    @Override
    public Page<SalelogSysVo> getSalelogSysList(SalelogQuery query) {
        Page<SalelogSysVo> page = new Page<>(query.getPageNum(),query.getPageSize());
        page.setData(salelogDao.getSalelogSysList(query));
        page.setTotal(salelogDao.getByCount(query));
        return page;
    }

}
