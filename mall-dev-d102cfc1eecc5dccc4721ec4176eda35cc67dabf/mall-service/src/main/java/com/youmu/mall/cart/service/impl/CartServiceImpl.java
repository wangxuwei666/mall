/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.cart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youmu.common.context.UserContext;
import com.youmu.mall.cart.dao.CartDao;
import com.youmu.mall.cart.domain.Cart;
import com.youmu.mall.cart.query.CartQuery;
import com.youmu.mall.cart.service.ICartService;
import com.youmu.mall.cart.vo.CartVo;
import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.product.dao.ProductDao;
import com.youmu.mall.product.domain.Product;

/**
 * 
 * @author yujiahao
 * @version $Id: CartServiceImpl.java, v 0.1 2017年2月28日 下午2:15:21 yujiahao Exp $
 */
@Service
public class CartServiceImpl implements ICartService{
    
    @Autowired
    private CartDao cartDao;
    
    @Autowired
    private ProductDao productDao;

    /** 
     * @see com.youmu.mall.cart.service.ICartService#getCartVo(com.youmu.mall.cart.query.CartQuery)
     */
    @Override
    public List<CartVo> getCartVo(CartQuery query) {
        query.setUserId(UserContext.getLongUserId());
        return cartDao.getCartVo(query);
    }

    /** 
     * @see com.youmu.mall.cart.service.ICartService#saveCart(com.youmu.mall.cart.domain.Cart)
     */
    @Override
    public long saveCart(Cart cart) {
        //绑定用户
        cart.setUserId(UserContext.getLongUserId());
        //判断商品数量是否超过库存
        Long productId = cart.getProductId();   
        Product product = productDao.getById(productId);
        Integer total = product.getTotal();
        //查询购物车里是否有重复的商品
        Long cartId = cartDao.checkIfRepeat(cart);
        
        //若有重复，则根据cartId获取原来的购物车项oldCart，数量修改为加cart.getQuantity()  + oldCart.getQuantity()
        if(cartId != null){
            Cart oldCart = cartDao.getById(cartId);
            //a、判断商品数量是否超过库存
            if(total < oldCart.getQuantity()+cart.getQuantity()){
                throw new BusinessException("存库不足");
            }
            //b、将原来的购物车的数量增加
            cartDao.update(new Cart(cartId,oldCart.getQuantity()+cart.getQuantity(),UserContext.getLongUserId()));
            return cartId;
        }else{
            //判断商品数量是否超过库存
            if(total < cart.getQuantity()){
                throw new BusinessException("存库不足");
            }
            return cartDao.saveCart(cart);
        }
    }

    /** 
     * @see com.youmu.mall.cart.service.ICartService#updateCart(com.youmu.mall.cart.domain.Cart)
     */
    @Override
    public void updateCart(Cart cart) {
        //判断商品数量是否超过库存
        Long productId = cart.getProductId();
        Product product = productDao.getById(productId);
        Integer total = product.getTotal();
        if(total < cart.getQuantity()){
            throw new BusinessException("存库不足");
        }
        //绑定用户
        cart.setUserId(UserContext.getLongUserId());
        cartDao.update(cart);
    }

    /** 
     * @see com.youmu.mall.cart.service.ICartService#deleteCart(java.lang.Long)
     */
    @Override
    public void deleteCart(Long id) {
        cartDao.deleteCart(id,UserContext.getLongUserId());
    }

    /** 
     * @see com.youmu.mall.cart.service.ICartService#getCountByUserId()
     */
    @Override
    public Integer getCountByUserId() {
        return cartDao.getCountByUserId(UserContext.getLongUserId());
    }
    
}
