/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.dao;

import java.util.List;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.base.query.PageQuery;
import com.youmu.mall.product.domain.ProductReviews;
import com.youmu.mall.product.query.ProductReviewsQuery;

/**
 * 商品评价数据操作.
 * @author zh
 * @version $Id: ProductReviewsDao.java, v 0.1 2017年5月3日 下午3:07:31 zh Exp $
 */
public interface ProductReviewsDao {

    /**
     * 添加评价信息
     * @param reviews
     */
    void save(ProductReviews reviews);

    /**
     * 删除评价信息
     * @param id
     */
    void remove(Long id);

    /**
     * 查询一页商品评价
     * @param pageQuery
     * @return
     */
    List<ProductReviews> findPage(PageQuery pageQuery);

    /**
     * 后台分页查询.
     * @param pageQuery
     * @return
     */
    List<ProductReviews> findSysPage(ProductReviewsQuery pageQuery);

    /**
     * 后台统计评价总数
     * @param pageQuery
     * @return
     */
    Long countSys(ProductReviewsQuery pageQuery);
}
