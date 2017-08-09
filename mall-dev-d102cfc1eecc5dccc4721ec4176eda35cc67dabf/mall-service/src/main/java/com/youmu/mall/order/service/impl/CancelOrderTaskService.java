/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.youmu.common.date.utils.DateUtils;
import com.youmu.mall.order.dao.ProductOrderDao;
import com.youmu.mall.order.domain.ProductOrder;
import com.youmu.mall.order.dto.OrderQuantityDto;
import com.youmu.mall.order.service.IProductOrderService;
import com.youmu.mall.points.dao.PointsRecordDao;
import com.youmu.mall.product.dao.ProductDao;
import com.youmu.mall.product.dao.ProductGroupDao;
import com.youmu.mall.product.domain.ProductGroup;
import com.youmu.mall.product.dto.GroupProductIngoDto;
import com.youmu.mall.user.domain.UserBuyRecord;


/**
 * 
 * @author yujiahao
 * @version $Id: CancelOrderTaskService.java, v 0.1 2017年4月14日 下午3:22:59 yujiahao Exp $
 */
@Component
public class CancelOrderTaskService {
    private static Logger logger = LoggerFactory.getLogger(CancelOrderTaskService.class);
    
    /**订单超时时间*/
    private static long OUT_OF_PAY_TIMESTAMP = 1000 * 60 * 30;
    
    @Resource
    private IProductOrderService orderService;
    
    @Resource
    private ProductOrderDao orderDao;
    
    @Resource 
    private ProductGroupDao productGroupDao;
    
    @Resource 
    private ProductDao productDao;
    
    @Resource
    private PointsRecordDao pointsRecordDao;
    
//    @Scheduled(cron="0 0 3 * * ?")
    @Scheduled(cron="0 0/30 * * * ?")
    public void cancelOverdueOrder(){
        logger.info("*****************系统取消订单  [start]********************");  
      //查询所有状态为待支付的订单
      List<ProductOrder> orders = orderService.findCancelOrders();
      List<Long> cancelOrderIds = new ArrayList<>(); 
      if(orders.size()>0){
          for(ProductOrder order : orders){
              long differTime = DateUtils.getDifferTime(DateUtils.getDate(order.getGmtCreate()), DateUtils.getDate(null));
              //超过下单时间半个小时，视为过期订单
              if(differTime>OUT_OF_PAY_TIMESTAMP){
                  cancelOrderIds.add(order.getId());
              }
          }
      }
      //订单关联模块处理
      if(cancelOrderIds.size()>0){
          for (Long orderId : cancelOrderIds) {
            //查询要取消的订单的商品项
            List<OrderQuantityDto> dtos = orderDao.getQuantityDtoById(orderId);
            //商品入库
            productDao.batchUpdateQuantity(dtos);
            if(dtos != null && dtos.size()>0){
                //根据商品id和数量，更新正在参与活动的商品的数量
                productGroupDao.batchUpdateGroupItems(dtos);
                
                for (OrderQuantityDto orderQuantityDto : dtos) {
                    //判断该商品是否参加了活动，若参加，则要增加活动库存
                    GroupProductIngoDto groupDto = productGroupDao.getGroupInfoByProductId(orderQuantityDto.getProductId());
                    
                    if(groupDto != null){
                    	ProductGroup productGroup = productGroupDao.getById(groupDto.getGroupId());
                   	 //如果是积分组的商品 还要删除对应的积分记录
                       if( productGroup.getGroupType() == 4){
                       	pointsRecordDao.deleteRecordBySn(orderDao.getOrder(orderId).getSn());
                       }
                       
                     //同时扣除创建订单时增添的用户购买活动组商品数量
                       UserBuyRecord userBuyRecord = new UserBuyRecord(groupDto.getGroupId(),orderDao.getOrder(orderId).getUserId(), orderQuantityDto.getQuantity());
                       productGroupDao.subBuyRecord(userBuyRecord);
                       
                        productGroupDao.increaseStockByGroupId(groupDto.getGroupId(),orderQuantityDto.getQuantity());
                        productGroupDao.clearFinishDate(groupDto.getGroupId(),orderQuantityDto.getProductId());
                    }
                }
            } 
            logger.debug("已经取消订单id为 {} 的订单." , orderId);
          }
          //业务处理完成，将所有订单状态为更改
          orderService.batchCancelOrders(cancelOrderIds);
      }
      logger.info("*****************系统取消订单 [END]*******************");  
    }
}
