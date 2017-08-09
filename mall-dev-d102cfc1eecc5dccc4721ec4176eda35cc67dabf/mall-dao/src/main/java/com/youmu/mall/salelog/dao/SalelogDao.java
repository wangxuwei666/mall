/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.salelog.dao;

import java.util.List;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.salelog.domain.Salelog;
import com.youmu.mall.salelog.query.SalelogQuery;
import com.youmu.mall.salelog.vo.SalelogSysVo;

/**
 * 
 * @author yujiahao
 * @version $Id: SalelogDao.java, v 0.1 2017年3月15日 下午5:20:56 yujiahao Exp $
 */
public interface SalelogDao {
    /**
     * 销售统计列表
     * @param query
     * @return
     */
    List<SalelogSysVo> getSalelogSysList(SalelogQuery query);

    /**
     * 保存一条销售记录
     * @param salelog
     */
    void insert(Salelog salelog);

    /**
     * 查询销售统计条数
     * @param query
     * @return
     */
    Long getByCount(SalelogQuery query);
}
