/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.cart.service;

import java.util.List;

import com.youmu.mall.cart.domain.Cart;
import com.youmu.mall.cart.query.CartQuery;
import com.youmu.mall.cart.vo.CartVo;

/**
 * 
 * @author yujiahao
 * @version $Id: ICartService.java, v 0.1 2017年2月28日 下午2:14:49 yujiahao Exp $
 */
public interface ICartService {

    /**
     * 我的购物车
     * @param query
     * @return
     */
    List<CartVo> getCartVo(CartQuery query);

    /**
     * 保存购物车
     * @param cart
     * @return
     */
    long saveCart(Cart cart);

    /**
     * 修改购物车
     * @param cart
     */
    void updateCart(Cart cart);

    /**
     * 删除购物车
     * @param id
     */
    void deleteCart(Long id);

    /**
     * 查询当前用户的购物车商品数量
     * @return
     */
    Integer getCountByUserId();


}
