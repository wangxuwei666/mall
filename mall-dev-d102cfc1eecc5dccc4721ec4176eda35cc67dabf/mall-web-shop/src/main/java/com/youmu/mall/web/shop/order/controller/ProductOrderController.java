/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.shop.order.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.youmu.common.alipay.utils.AliPayQRCode;
import com.youmu.common.alipay.utils.QRCodePayParams;
import com.youmu.common.cofig.utils.ConfigUtils;
import com.youmu.common.page.utils.Page;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.common.wx.utils.WxPayUtil;
import com.youmu.common.wx.utils.WxPublicNumberUtil;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.order.domain.ProductOrder;
import com.youmu.mall.order.dto.OrderAmountDto;
import com.youmu.mall.order.query.ProductOrderQuery;
import com.youmu.mall.order.service.IProductOrderService;
import com.youmu.mall.order.vo.PayQRCodeStatus;
import com.youmu.mall.order.vo.ProductOrderDetailShopVo;
import com.youmu.mall.order.vo.ProductOrderShopVo;
import com.youmu.mall.pay.service.IAliPayService;
import com.youmu.mall.pay.vo.QRCodeResultVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户端
 * @author yujiahao
 * @version $Id: ProductOrderController.java, v 0.1 2017年3月1日 下午1:58:28 yujiahao Exp $
 */
@RestController
@RequestMapping("/productorder/")
public class ProductOrderController {
    @Resource
    private IProductOrderService orderService;
    
    @Resource
    private IAliPayService aliPayService;
    
    @ApiOperation(value = "我的订单", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "status", value = "订单状态", required = false, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageNum", value = "当前页", required = true, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "页码尺寸", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value ="getOrderShopVo", method=RequestMethod.POST)
    public JsonResult getOrderShopVo(ProductOrderQuery productOrderQuery) {
        Page<ProductOrderShopVo> orders = orderService.getOrderShopVo(productOrderQuery);
        return JsonResultUtils.ok(orders);
    }
    
    @ApiOperation(value = "订单详情", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "orderId", value = "订单id", required = false, dataType = "Integer", paramType = "query"),
    })
    @RequestMapping(value ="getOrderDetailShopVo", method=RequestMethod.POST)
    public JsonResult getOrderDetailShopVo(Long orderId) {
        ValidateUtils.checkNotNull("订单id不能为空", orderId);
        ProductOrderDetailShopVo vo = orderService.getOrderDetailShopVo(orderId);
        return JsonResultUtils.ok(vo);
    }
    
    @ApiOperation(value = "下单", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "receiverId", value = "收件地址id", required = true, dataType = "Long", paramType = "form"),
        @ApiImplicitParam(name = "remark", value = "备注", required = false, dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "totalAmount", value = "商品总金额", required = true, dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "paidAmount", value = "实际支付金额", required = true, dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "freight", value = "运费", required = false, dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "quantity", value = "商品数量", required = true, dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "pointsValue", value = "消费积分值", required = true, dataType = "Long", paramType = "form"),
        @ApiImplicitParam(name = "isGroupFour", value = "是否为积分组", required = false, dataType = "String", paramType = "form"),
               
        @ApiImplicitParam(name = "items[0].name", value = "商品项名称", required = false, dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "items[0].thumbnail", value = "商品项图片", required = false, dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "items[0].digest", value = "商品项备注", required = false, dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "items[0].quantity", value = "商品项数量", required = false, dataType = "Integer", paramType = "form"),
        @ApiImplicitParam(name = "items[0].price", value = "商品项价格", required = false, dataType = "Double", paramType = "form"),
        @ApiImplicitParam(name = "items[0].productId", value = "商品项商品id", required = true, dataType = "Long", paramType = "form"),
        @ApiImplicitParam(name = "items[0].businessId", value = "商家id", required = true, dataType = "Long", paramType = "form"),
        @ApiImplicitParam(name = "items[0].cartId", value = "购物车id", required = false, dataType = "Long", paramType = "form"),
        @ApiImplicitParam(name = "items[0].freight", value = "商品运费", required = false, dataType = "Long", paramType = "form"),
        @ApiImplicitParam(name = "items[0].specificationId", value = "商品分类详情id", required = false, dataType = "int", paramType = "form"),
        @ApiImplicitParam(name = "items[0].groupType", value = "商品活动组类别", required = false, dataType = "int", paramType = "form"),

//        @ApiImplicitParam(name = "items[1].name", value = "商品项名称", required = false, dataType = "String", paramType = "form"),
//        @ApiImplicitParam(name = "items[1].thumbnail", value = "商品项图片", required = false, dataType = "String", paramType = "form"),
//        @ApiImplicitParam(name = "items[1].digest", value = "商品项备注", required = false, dataType = "String", paramType = "form"),
//        @ApiImplicitParam(name = "items[1].quantity", value = "商品项数量", required = false, dataType = "Integer", paramType = "form"),
//        @ApiImplicitParam(name = "items[1].price", value = "商品项价格", required = false, dataType = "Double", paramType = "form"),
//        @ApiImplicitParam(name = "items[1].productId", value = "商品项商品id", required = true, dataType = "Long", paramType = "form"),
//        @ApiImplicitParam(name = "items[1].businessId", value = "商家id", required = true, dataType = "Long", paramType = "form"),
//        @ApiImplicitParam(name = "items[1].cartId", value = "购物车id", required = false, dataType = "Long", paramType = "form"),
//        @ApiImplicitParam(name = "items[1].freight", value = "商品运费", required = false, dataType = "Long", paramType = "form"),
//        
//        @ApiImplicitParam(name = "items[2].name", value = "商品项名称", required = false, dataType = "String", paramType = "form"),
//        @ApiImplicitParam(name = "items[2].thumbnail", value = "商品项图片", required = false, dataType = "String", paramType = "form"),
//        @ApiImplicitParam(name = "items[2].digest", value = "商品项备注", required = false, dataType = "String", paramType = "form"),
//        @ApiImplicitParam(name = "items[2].quantity", value = "商品项数量", required = false, dataType = "Integer", paramType = "form"),
//        @ApiImplicitParam(name = "items[2].price", value = "商品项价格", required = false, dataType = "Double", paramType = "form"),
//        @ApiImplicitParam(name = "items[2].productId", value = "商品项商品id", required = true, dataType = "Long", paramType = "form"),
//        @ApiImplicitParam(name = "items[2].businessId", value = "商家id", required = true, dataType = "Long", paramType = "form"),
//        @ApiImplicitParam(name = "items[2].cartId", value = "购物车id", required = false, dataType = "Long", paramType = "form"),
//        @ApiImplicitParam(name = "items[2].freight", value = "商品运费", required = false, dataType = "Long", paramType = "form"),
        
    })
    @RequestMapping(value = "createOrder",method=RequestMethod.POST, consumes="application/x-www-form-urlencoded")
    public JsonResult createOrder(ProductOrder order) {
        List<OrderAmountDto> dtos = orderService.createOrder(order);
        return JsonResultUtils.ok(dtos);
    }
    
    
    @ApiOperation(value = "取消订单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "订单Id", required = true,paramType="query", dataType = "long")
    })
    @RequestMapping(value = "cancelOrder",method=RequestMethod.POST)
    public JsonResult cancelOrder(Long id) {
        if(id == null){
            throw new ParamException("id不能为空");
        }
        orderService.cancelOrder(id);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value = "根据统一下单号获取订单金额")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "outTradeNumber", value = "统一下单号", required = true,paramType="query", dataType = "String")
    })
    @RequestMapping(value = "getAmountByOutTradeNumber",method=RequestMethod.POST)
    public JsonResult getAmountByOutTradeNumber(String outTradeNumber) {
        if(StringUtils.isBlank(outTradeNumber)){
            throw new ParamException("下单号不能为空");
        }
        return JsonResultUtils.ok(orderService.getAmountByOutTradeNumber(outTradeNumber));
    }
    
    @ApiOperation(value = "微信支付")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "openid", value = "openid", required = true ,paramType="query", dataType = "String")
    })
    @RequestMapping(value = "payWeixin", method = RequestMethod.POST)
    public JsonResult payWeixin(HttpServletRequest request,@RequestBody List<OrderAmountDto> dtos,String openid) {
        return JsonResultUtils.ok(orderService.payWeixin(dtos,request.getRemoteAddr(),openid));
    }
    
    @ApiOperation(value = "PC端微信支付")
    @RequestMapping(value = "payWeixinByPc", method = RequestMethod.POST)
    public JsonResult payWeixinByPc(HttpServletRequest request,@RequestBody List<OrderAmountDto> dtos) {
        String remoteAddr = request.getRemoteAddr();
        //根据支付订单进行业务处理
        QRCodePayParams params = orderService.payByPc(dtos);
        //微信统一下单
        String code_url = WxPayUtil.payUnifiedorder(
            "微信支付订单", 
            params.getOut_trade_no(),
            new BigDecimal(params.getTotal_amount()), 
            remoteAddr,
            null,params.getOut_trade_no());
        ValidateUtils.checkNotBlank(code_url, "二维码不能为空");
        //封装二维码支付对象
        QRCodeResultVo vo = new QRCodeResultVo(params.getOut_trade_no(),params.getTotal_amount(),code_url);
        return JsonResultUtils.ok(vo);
    }
    
    @ApiOperation(value = "PC端支付宝支付")
    @RequestMapping(value = "alipayByPc", method = RequestMethod.POST)
    public JsonResult alipayByPc(HttpServletRequest request,@RequestBody List<OrderAmountDto> dtos) {
        //根据支付订单进行业务处理
        QRCodePayParams params = orderService.payByPc(dtos);
        //返回支付宝生成的二维码
        AliPayQRCode generateAliPayQRCode = aliPayService.generateAliPayQRCode(params ,ConfigUtils.getAlipayNotifyUrl());
        //获取二维码
        String qrCode = generateAliPayQRCode.getQrCode();
        //封装二维码支付对象
        QRCodeResultVo vo = new QRCodeResultVo(params.getOut_trade_no(),params.getTotal_amount(),qrCode);
        return JsonResultUtils.ok(vo);
    }
    
    @ApiOperation(value = "支付宝支付回调")
    @RequestMapping(value = "notifyAlipay", method = RequestMethod.POST)
    public void notifyAlipay(HttpServletRequest request,HttpServletResponse response) {
        orderService.notifyAlipay(request,response);
    }
    
    @ApiOperation(value = "微信支付回调")
    @RequestMapping(value = "notifyWeixin", method = RequestMethod.POST)
    public String notifyWeixin(HttpServletRequest request,@RequestBody String body) {
        return orderService.notifyWeixin(body);
    }
    
    @ApiOperation(value = "支付流程测试接口")
    @RequestMapping(value = "testPay", method = RequestMethod.POST)
    public JsonResult testPay(@RequestBody List<OrderAmountDto> dtos) {
        orderService.testPay(dtos);
        return JsonResultUtils.ok("支付成功");
    }
    
    @ApiOperation(value = "查询订单的支付状态")
    @RequestMapping(value = "checkOrderStatus", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "outTradeNumber", value = "外部订单号", required = true ,paramType="query", dataType = "String")
    })
    public JsonResult checkOrderStatus(String outTradeNumber){
        return JsonResultUtils.ok(WxPublicNumberUtil.getWxOrderStatus(outTradeNumber));
    }
    
    @ApiOperation(value = "轮询支付宝支付二维码的状态")
    @RequestMapping(value = "/{outTradeNumber}/aliPayQRCodeStatus", method = RequestMethod.GET)
    public DeferredResult<PayQRCodeStatus>  pollingAliPayQRCodeStatus(@PathVariable String outTradeNumber) {
        DeferredResult<PayQRCodeStatus> result = new DeferredResult<PayQRCodeStatus>(5000L);
        orderService.pollingAliPayQRCodeStatus(result, outTradeNumber);
        return result;
    }
    
    @ApiOperation(value = "轮询支付宝支付二维码的状态")
    @RequestMapping(value = "/{outTradeNumber}/wxPayQRCodeStatus", method = RequestMethod.GET)
    public DeferredResult<PayQRCodeStatus>  wxPayQRCodeStatus(@PathVariable String outTradeNumber) {
        DeferredResult<PayQRCodeStatus> result = new DeferredResult<PayQRCodeStatus>(5000L);
        orderService.pollingWeXinPayQRCodeStatus(result, outTradeNumber);
        return result;
    }
}
