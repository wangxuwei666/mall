/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.bus.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.page.utils.Page;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.order.query.ProductOrderQuery;
import com.youmu.mall.order.service.IProductOrderService;
import com.youmu.mall.order.vo.ProductOrderBusVo;
import com.youmu.mall.order.vo.ProductOrderDetailBusVo;
import com.youmu.mall.product.query.ProductQuery;
import com.youmu.mall.product.vo.ProductSysVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 商户端-商品订单
 * @author yujiahao
 * @version $Id: ProductOrderController.java, v 0.1 2017年3月9日 下午8:02:31 yujiahao Exp $
 */
@RestController
@RequestMapping("/productorder/")
public class ProductOrderController {
    @Autowired
    private IProductOrderService orderService;
    
    @ApiOperation(value = "商户端-管理后台-商品订单列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sn", value = "订单编号", required = false, paramType = "query"),
        @ApiImplicitParam(name = "beginDate", value = "开始日期", required = false, paramType = "query"),
        @ApiImplicitParam(name = "endDate", value = "结束日期", required = false, paramType = "query"),
        @ApiImplicitParam(name = "shippingStatus", value = "订单状态（1待发货，2已发货)", required = true, paramType = "query"),
        @ApiImplicitParam(name = "pageNum", value = "当前页", required = true, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "页码尺寸", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value ="getProductOrderListBusVo",method=RequestMethod.POST)
    public JsonResult getProductOrderListBusVo(ProductOrderQuery query){
        ValidateUtils.checkNotNull("状态不能为空", query.getShippingStatus());
        Page<ProductOrderBusVo> page = orderService.getProductOrderListBusVo(query);
        return JsonResultUtils.ok(page);
    }
    
    @ApiOperation(value = "商户端-管理后台-商品订单详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "运单id", required = true, paramType = "query"),
        @ApiImplicitParam(name = "status", value = "运单状态（1-待发货，2-已发货）", required = true, paramType = "query"),
    })
    @RequestMapping(value ="getProductOrderDetailBusVo",method=RequestMethod.POST)
    public JsonResult getProductOrderDetailBusVo(Long id,Integer status){
        ValidateUtils.checkNotNull("参数不能为空",id); 
        ProductOrderDetailBusVo vo = orderService.getProductOrderDetailBusVo(id,status);
        return JsonResultUtils.ok(vo);
    }
    
    @ApiOperation(value = "商户端-管理后台-商品订单发货")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "运单id", required = true, paramType = "query"),
        @ApiImplicitParam(name = "shippingSn", value = "物流编号", required = true, paramType = "query"),
        @ApiImplicitParam(name = "shippingMethod", value = "物流编号", required = true, paramType = "query")
    })
    @RequestMapping(value ="sendProductOrderById",method=RequestMethod.POST)
    public JsonResult sendProductOrderById(Long id,String shippingSn,String shippingMethod){
        ValidateUtils.checkNotNull("参数不能为空",id,shippingSn,shippingMethod);
        orderService.sendProductOrderById(id,shippingSn,shippingMethod);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value = "商户端-管理后台-待发货商品订单导出")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sn", value = "订单编号", required = false, paramType = "query"),
        @ApiImplicitParam(name = "beginDate", value = "开始日期", required = false, paramType = "query"),
        @ApiImplicitParam(name = "endDate", value = "结束日期", required = false, paramType = "query"),
        @ApiImplicitParam(name = "shippingStatus", value = "订单状态（1待发货，2已发货)", required = true, paramType = "query")
    })
    @RequestMapping(value ="exportProductOrders",method=RequestMethod.GET)
    public void exportProductOrders(ProductOrderQuery query,HttpServletResponse response){
        orderService.exportProductOrders(query,response);
    }
    
}
