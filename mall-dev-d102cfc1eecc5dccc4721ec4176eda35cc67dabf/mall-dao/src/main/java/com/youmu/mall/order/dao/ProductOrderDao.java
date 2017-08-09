/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.order.dao;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.youmu.mall.order.domain.ProductOrder;
import com.youmu.mall.order.domain.ProductOrderItem;
import com.youmu.mall.order.dto.OrderExportDto;
import com.youmu.mall.order.dto.OrderQuantityDto;
import com.youmu.mall.order.query.ProductOrderQuery;
import com.youmu.mall.order.vo.ProductOrderBusVo;
import com.youmu.mall.order.vo.ProductOrderDetailBusVo;
import com.youmu.mall.order.vo.ProductOrderDetailShopVo;
import com.youmu.mall.order.vo.ProductOrderDetailSysVo;
import com.youmu.mall.order.vo.ProductOrderShopVo;
import com.youmu.mall.order.vo.ProductOrderVo;

/**
 * 
 * @author yujiahao
 * @version $Id: ProductOrderDao.java, v 0.1 2017年2月28日 下午6:39:14 yujiahao Exp $
 */
public interface ProductOrderDao {

    /**
     * 管理后台-订单列表
     * @param query
     * @return
     */
    List<ProductOrderVo> getOrderList(ProductOrderQuery query);

    /**
     * 订单总条数
     * @param query
     * @return
     */
    Long getByCount(ProductOrderQuery query);

    /**
     * 用户端-订单列表
     * @param userId
     * @param status
     * @return
     */
    List<ProductOrderShopVo> getOrderShopVo(ProductOrderQuery productOrderQuery);

    /**
     * 根据状态和用户id查询用户端订单数
     * @param userId
     * @param status
     * @return
     */
    Long getCountByShop(ProductOrderQuery productOrderQuery);

    /**
     * 用户端-商品订单详情
     * @param orderId
     * @return
     */
    ProductOrderDetailShopVo getOrderDetailShopVo(@Param("orderId")Long orderId);

    /**
     * 用户端-下单
     * @param order
     */
    void createOrder(ProductOrder order);

    /**
     * 保存订单子项
     * @param productOrderId 主订单id
     * @param items
     */
    void batchSaveOrderItems(@Param("productOrderId")Long productOrderId, @Param("items")List<ProductOrderItem> items);

    /**
     * 取消订单
     * @param id
     * @param longUserId
     */
    void cancelOrder(@Param("id")Long id, @Param("userId")Long userId);

    /**
     * 查询订单的商品项数量
     * @param id
     * @return
     */
    List<OrderQuantityDto> getQuantityDtoById(@Param("orderId")Long id);

    /**
     * 
     * @param id
     * @return
     */
    ProductOrder getById(@Param("orderId")Long id,@Param("userId")Long userId);

    /**
     * 
     * @param id
     * @return
     */
    ProductOrder getOrder(@Param("orderId")Long id);

    /**
     * 
     * @param order
     */
    void update(ProductOrder order);

    /**
     * @param outTradeNumber 用户 统一下单号
     * @return
     */
    List<ProductOrder> getByOutTradeNumber(@Param("outTradeNumber")String outTradeNumber);

    /**
     * 查询商户订单总数
     * @param query
     * @return
     */
    Long getProductOrderBusVoByCount(ProductOrderQuery query);

    /**
     * 商户端-商品订单-列表
     * @param query
     * @return
     */
    List<ProductOrderBusVo> getProductOrderListBusVo(ProductOrderQuery query);

    /**
     * 根据运单id查询对应商户的运单明细
     * @param id
     * @return
     */
    ProductOrderDetailBusVo getProductOrderDetailBusVo(@Param("id")Long id,@Param("bid")Long bid,@Param("shippingStatus")Integer status);
    
    /**
     * 根据运单id查询运单明细
     * @param id
     * @param status
     * @return
     */
    ProductOrderDetailSysVo getProductOrderDetailSysVo(@Param("id")Long id);
    /**
     * 发送运单
     * @param id
     */
    void sendProductOrderById(@Param("id")Long id,@Param("shippingSn")String shippingSn,@Param("shippingMethod")String shippingMethod);

    /**
     * 查询待退款的订单，系统自动取消
     * @return
     */
    List<ProductOrder> findCancelOrders();

    /**
     * 批量取消订单
     * @param cancelOrderIds
     */
    void batchCancelOrders(@Param("list")List<Long> cancelOrderIds);

    /**
     * 根据统一下单号获取订单支付金额
     * @param outTradeNumber
     * @return
     */
    BigDecimal getAmountByOutTradeNumber(@Param("outTradeNumber")String outTradeNumber);

    /**
     * 根据商户系统订单号查找订单
     * @param outTradeNumber
     * @return
     */
    ProductOrder getBySn(@Param("sn")String sn);

    /**
     * 根据商户提供的运单号查找订单信息
     * @param shippingSn
     * @return
     */
    ProductOrder getInfoByShippingSn(@Param("shippingSn") String shippingSn);
    
    /**
     * 导出待发货商品订单明细
     * @param query
     * @return
     */
    List<OrderExportDto> getExportProductInfo(ProductOrderQuery query);
    
    
}
