/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.service;

import java.util.List;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.product.domain.ProductReviews;
import com.youmu.mall.product.query.ProductReviewsQuery;

/**
 * 商品评价
 * @author zh
 * @version $Id: IProductCommentService.java, v 0.1 2017年5月3日 下午2:53:56 zh Exp $
 */
public interface IProductReviewsService {
    
    /**
     * 添加一个商品评价
     * 
     * @param reviews
     */
    void save(ProductReviews reviews);
    
    /**
     * 删除一个商品评价
     * 
     * @param id
     */
    void remove(Long id);

    /**
     * 查询一页数据
     * @param pageQuery
     */
    List<ProductReviews> findPage(ProductReviewsQuery pageQuery);

    /**
     * 后台分页查询
     * @param pageQuery
     * @return
     */
    Page<ProductReviews> findSysPage(ProductReviewsQuery pageQuery);
    
}
