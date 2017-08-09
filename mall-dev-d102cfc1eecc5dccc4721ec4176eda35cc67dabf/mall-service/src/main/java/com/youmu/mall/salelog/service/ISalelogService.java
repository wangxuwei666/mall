/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.salelog.service;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.salelog.query.SalelogQuery;
import com.youmu.mall.salelog.vo.SalelogSysVo;

/**
 * 
 * @author yujiahao
 * @version $Id: ISalelogService.java, v 0.1 2017年3月15日 下午6:03:06 yujiahao Exp $
 */
public interface ISalelogService {

    /**
     * 管理后台-商品销售列表
     * @param query
     * @return
     */
    Page<SalelogSysVo> getSalelogSysList(SalelogQuery query);
    
}
