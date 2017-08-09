/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youmu.common.context.UserContext;
import com.youmu.common.page.utils.Page;
import com.youmu.mall.product.dao.ProductReviewsDao;
import com.youmu.mall.product.domain.ProductReviews;
import com.youmu.mall.product.query.ProductReviewsQuery;
import com.youmu.mall.product.service.IProductReviewsService;
import com.youmu.mall.redis.login.dao.RedisLoginDao;
import com.youmu.mall.user.domain.User;

/**
 * 商品评价实现类
 * @author zh
 * @version $Id: ProductReviewsImpl.java, v 0.1 2017年5月3日 下午2:58:15 zh Exp $
 */
@Service
@Transactional(readOnly=true)
public class ProductReviewsImpl implements IProductReviewsService {
    
    @Resource
    private RedisLoginDao redisLoginDao;
    
    @Resource
    private ProductReviewsDao productReviewsDao;

    /** 
     * @see com.youmu.mall.product.service.IProductReviewsService#save(com.youmu.mall.product.domain.ProductReviews)
     */
    @Override
    @Transactional
    public void save(ProductReviews reviews) {
        //填充评论信息
        reviews.setUser(redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), User.class));
        productReviewsDao.save(reviews);
    }

    /** 
     * @see com.youmu.mall.product.service.IProductReviewsService#remove(java.lang.Long)
     */
    @Override
    @Transactional
    public void remove(Long id) {
        productReviewsDao.remove(id);
    }

    /** 
     * @see com.youmu.mall.product.service.IProductReviewsService#findPage(com.youmu.mall.base.query.PageQuery)
     */
    @Override
    public List<ProductReviews> findPage(ProductReviewsQuery pageQuery) {
        return productReviewsDao.findPage(pageQuery);
    }

    /** 
     * @see com.youmu.mall.product.service.IProductReviewsService#findSysPage(com.youmu.mall.product.query.ProductReviewsQuery)
     */
    @Override
    public Page<ProductReviews> findSysPage(ProductReviewsQuery pageQuery) {
        Page<ProductReviews> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        page.setTotal(productReviewsDao.countSys(pageQuery));
        if(page.getTotal() > 0) {
            page.setData(productReviewsDao.findSysPage(pageQuery));
        }
        return page;
    }

}
