/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.sys.order.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.page.utils.Page;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.order.domain.ProductOrder;
import com.youmu.mall.order.query.ProductOrderQuery;
import com.youmu.mall.order.service.IProductOrderService;
import com.youmu.mall.order.vo.ProductOrderDetailSysVo;
import com.youmu.mall.order.vo.ProductOrderShopVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author yujiahao
 * @version $Id: OrderController.java, v 0.1 2017年2月28日 下午6:19:35 yujiahao Exp $
 */
@RestController
@RequestMapping("/productorder/")
public class ProductOrderController {

       @Resource
       private IProductOrderService orderService;
       
       @ApiOperation(value = "管理后台-订单管理", httpMethod="POST")
       @ApiImplicitParams({
           @ApiImplicitParam(name = "keywords", value = "关键词", required = false, dataType="String",  paramType = "query"),
           @ApiImplicitParam(name = "status", value = "订单状态", required = false, dataType = "Integer", paramType = "query"),
           @ApiImplicitParam(name = "businessTypeId", value = "商户类别", required = false, dataType = "Long", paramType = "query"),
           @ApiImplicitParam(name = "beginDate", value = "订单日期(FROM)", required = false, dataType = "Date", paramType = "query"),
           @ApiImplicitParam(name = "endDate", value = "订单日期(TO)", required = false, dataType = "Date", paramType = "query"),
           @ApiImplicitParam(name = "pageNum", value = "当前页", required = true, dataType = "Integer", paramType = "query"),
           @ApiImplicitParam(name = "pageSize", value = "页码尺寸", required = true, dataType = "Integer", paramType = "query")
       })
       @RequestMapping(value ="getOrderList", method=RequestMethod.POST)
       public JsonResult getOrderList(ProductOrderQuery query) {
           Page<ProductOrderShopVo> orders = orderService.getOrderList(query);
           return JsonResultUtils.ok(orders);
       }
       
       @ApiOperation(value = "管理后台-订单管理-订单详情")
       @ApiImplicitParams({
           @ApiImplicitParam(name = "id", value = "运单id", required = true, paramType = "query"),
       })
       @RequestMapping(value ="getProductOrderDetailSysVo",method=RequestMethod.POST)
       public JsonResult getProductOrderDetailBusVo(Long id){
           ValidateUtils.checkNotNull("参数不能为空",id);
           ProductOrderDetailSysVo vo = orderService.getProductOrderDetailSysVo(id);
           return JsonResultUtils.ok(vo);
       }
       
}
