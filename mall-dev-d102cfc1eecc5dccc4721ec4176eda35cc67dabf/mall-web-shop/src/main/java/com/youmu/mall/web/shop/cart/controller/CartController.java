/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.shop.cart.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.cart.domain.Cart;
import com.youmu.mall.cart.query.CartQuery;
import com.youmu.mall.cart.service.ICartService;
import com.youmu.mall.coupon.query.CouponShopQuery;
import com.youmu.mall.exception.ParamException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author yujiahao
 * @version $Id: CartController.java, v 0.1 2017年2月28日 下午2:10:16 yujiahao Exp $
 */
@RestController
@RequestMapping("/cart/")
public class CartController {
    @Resource
    private ICartService cartService;
    
    @ApiOperation(value = "我的购物车", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "当前页", required = true, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "页码尺寸", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value ="getCartVo", method=RequestMethod.GET)
    public JsonResult getCartVo(CartQuery query) {
        return JsonResultUtils.ok(cartService.getCartVo(query));
    }
    
    @ApiOperation(value = "添加购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "商品id", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "quantity", value = "数量", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "price", value = "商品项单价价格", required = true, dataType = "int", paramType = "query"),
    })
    @RequestMapping(value = "saveCart",method=RequestMethod.POST)
    public JsonResult saveCart(Cart cart) {
        if(cart == null){
            throw new ParamException("购物车不能为空");
        }
        ValidateUtils.checkNotNull("参数不能为空", cart.getQuantity(),cart.getPrice());
        long cartId = cartService.saveCart(cart);
        return JsonResultUtils.ok(cartId);
    }
    
    @ApiOperation(value = "修改购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "productId", value = "商品id", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "quantity", value = "数量", required = true, dataType = "int", paramType = "query"),
    })
    @RequestMapping(value = "updateCart",method=RequestMethod.POST)
    public JsonResult updateCart(Cart cart) {
        if(cart == null || cart.getId()== null){
            throw new ParamException("id不能为空");
        }
        if(cart.getProductId()== null){
            throw new ParamException("商品id不能为空");
        }
        if(cart.getQuantity()== null){
            throw new ParamException("数量id不能为空");
        }
        cartService.updateCart(cart);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value = "删除购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "long", paramType = "query"),
    })
    @RequestMapping(value = "deleteCart",method=RequestMethod.POST)
    public JsonResult deleteCart(Long id) {
        if(id== null){
            throw new ParamException("id不能为空");
        }
        cartService.deleteCart(id);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value = "查看购物车当前数量")
    @RequestMapping(value = "getCountByUserId",method=RequestMethod.GET)
    public JsonResult getCountByUserId() {
        Integer cartCount = cartService.getCountByUserId();
        return JsonResultUtils.ok(cartCount);
    }
}
