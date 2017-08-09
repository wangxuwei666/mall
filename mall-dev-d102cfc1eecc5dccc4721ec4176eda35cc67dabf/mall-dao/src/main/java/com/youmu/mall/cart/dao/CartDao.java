/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.cart.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youmu.mall.cart.domain.Cart;
import com.youmu.mall.cart.query.CartQuery;
import com.youmu.mall.cart.vo.CartVo;

/**
 * 
 * @author yujiahao
 * @version $Id: CartDao.java, v 0.1 2017年2月28日 上午11:21:26 yujiahao Exp $
 */
public interface CartDao {

    /**
     * 查看购物车
     * @param query
     * @return
     */
    List<CartVo> getCartVo(CartQuery query);

    /**
     * 
     * @param cart
     * @return
     */
    long saveCart(Cart cart);

    /**
     * 
     * @param cart
     */
    void update(Cart cart);

    /**
     * 
     * @param id
     */
    void deleteCart(@Param("cartId")Long cartId,@Param("userId")Long userId);

    /**
     * 查询是否有相同的商品被加入购物车
     * @param cart
     * @return
     */
    Long checkIfRepeat(Cart cart);

    /**
     * 根据id查询购物车
     * @param cartId
     * @return
     */
    Cart getById(@Param("cartId")Long cartId);

    /**
     * 
     * @param longUserId
     * @return
     */
    Integer getCountByUserId(Long userId);


}
