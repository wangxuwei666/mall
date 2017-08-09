/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.async.DeferredResult;

import com.youmu.common.alipay.utils.QRCodePayParams;
import com.youmu.common.page.utils.Page;
import com.youmu.mall.order.domain.ProductOrder;
import com.youmu.mall.order.dto.OrderAmountDto;
import com.youmu.mall.order.query.ProductOrderQuery;
import com.youmu.mall.order.vo.PayQRCodeStatus;
import com.youmu.mall.order.vo.ProductOrderBusVo;
import com.youmu.mall.order.vo.ProductOrderDetailBusVo;
import com.youmu.mall.order.vo.ProductOrderDetailShopVo;
import com.youmu.mall.order.vo.ProductOrderDetailSysVo;
import com.youmu.mall.order.vo.ProductOrderShopVo;

/**
 * 
 * @author yujiahao
 * @version $Id: IOrderService.java, v 0.1 2017年2月28日 下午6:32:59 yujiahao Exp $
 */
public interface IProductOrderService {

    /**
     * 管理后台-订单列表
     * @param query
     * @return
     */
    Page getOrderList(ProductOrderQuery query);

    /**
     * 用户段-订单列表
     * @param query
     * @return
     */
    Page<ProductOrderShopVo> getOrderShopVo(ProductOrderQuery productOrderQuery);

    /**
     * 用户端-订单详情
     * @param orderId
     * @return
     */
    ProductOrderDetailShopVo getOrderDetailShopVo(Long orderId);

    /**
     * 商户端-商品订单下单
     * @param order
     * @return
     */
    List<OrderAmountDto> createOrder(ProductOrder order);

    /**
     * 取消订单
     * @param id
     */
    void cancelOrder(Long id);

    /**
     * 微信支付
     * @param dtos
     * @param remoteAddr
     * @return
     */
    Map<String, Object> payWeixin(List<OrderAmountDto> dtos, String remoteAddr,String openid);

    /**
     * 微信回调
     * @param body
     * @return
     */
    String notifyWeixin(String body);

    /**
     * 商户端-管理后台-商品订单列表
     * @param query
     * @return
     */
    Page<ProductOrderBusVo> getProductOrderListBusVo(ProductOrderQuery query);

    /**
     * 商户端-管理后台-商品订单明细
     * @return
     */
    ProductOrderDetailBusVo getProductOrderDetailBusVo(Long id,Integer status);
   
    /**
     * 管理后台-商品订单下的订单明细
     * @param id
     * @param status
     * @return
     */
    ProductOrderDetailSysVo getProductOrderDetailSysVo(Long id);
    /**
     * 发送运单
     * @param id
     */
    void sendProductOrderById(Long id,String shippingSn,String shippingMethod);

    /**
     * 支付流程测试接口
     * @param orderId
     * @param amount
     */
    void testPay(List<OrderAmountDto> dtos);
    
    /**
     * 查询所有待支付的订单
     * @return
     */
    List<ProductOrder> findCancelOrders();

    /**
     * 
     * @param cancelOrderIds
     */
    void batchCancelOrders(List<Long> cancelOrderIds);

    /**
     * PC端扫码支付
     * @param dtos
     * @param remoteAddr
     * @return
     */
    QRCodePayParams payByPc(List<OrderAmountDto> dtos);

    /**
     * 根据统一下单号获取订单支付金额
     * @param outTradeNumber
     */
    BigDecimal getAmountByOutTradeNumber(String outTradeNumber);

    /**
     * 支付宝支付回调
     * @param request
     * @return
     */
    void notifyAlipay(HttpServletRequest request,HttpServletResponse response);

    /**
     * 支付宝二维码轮询接口
     * @param result
     */
    void pollingAliPayQRCodeStatus(DeferredResult<PayQRCodeStatus> result, String outTradeNo);

    /**
     * 微信二維碼支付輪詢接口
     * @param result
     * @param outTradeNumber
     */
    void pollingWeXinPayQRCodeStatus(DeferredResult<PayQRCodeStatus> result, String outTradeNumber);

    /**
     * 支付成功处理
     * @param outTradeNo
     * @param tradeNo
     * @param two
     */
    boolean notifySuccess(String outTradeNo, String tradeNo, Integer type);

    /**
     * 导出商品订单
     */
    void exportProductOrders(ProductOrderQuery query,HttpServletResponse response);

}
