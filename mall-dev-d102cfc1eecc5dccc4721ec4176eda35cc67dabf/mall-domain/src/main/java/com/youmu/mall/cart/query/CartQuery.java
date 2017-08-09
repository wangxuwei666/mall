/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.cart.query;

import com.youmu.mall.base.query.BaseQuery;
import com.youmu.mall.base.query.PageQuery;

/**
 * 
 * @author yujiahao
 * @version $Id: CartQuery.java, v 0.1 2017年2月28日 上午11:48:25 yujiahao Exp $
 */
public class CartQuery extends PageQuery{
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    
}
