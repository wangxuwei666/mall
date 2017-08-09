/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.order.service.impl;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.async.DeferredResult;
import com.youmu.common.alipay.utils.AliPayResultHandler;
import com.youmu.common.alipay.utils.AliPayStatus;
import com.youmu.common.alipay.utils.AliPayUtils;
import com.youmu.common.alipay.utils.AlipayResult;
import com.youmu.common.alipay.utils.QRCodePayParams;
import com.youmu.common.cofig.utils.ConfigUtils;
import com.youmu.common.context.GlobalConstant;
import com.youmu.common.context.UserContext;
import com.youmu.common.date.utils.DateUtils;
import com.youmu.common.excel.utils.ExcelUtils;
import com.youmu.common.global.constant.StatusConstant;
import com.youmu.common.order.utils.OrderUtil;
import com.youmu.common.page.utils.Page;
import com.youmu.common.upload.utils.ImageUploadUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.common.wx.utils.WxPayInfo;
import com.youmu.common.wx.utils.WxPayUtil;
import com.youmu.common.wx.utils.WxPublicNumberUtil;
import com.youmu.common.wx.utils.WxTools;
import com.youmu.mall.bus.dao.BusinessDao;
import com.youmu.mall.cart.dao.CartDao;
import com.youmu.mall.coupon.dao.CouponDao;
import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.order.dao.ProductOrderDao;
import com.youmu.mall.order.domain.ProductOrder;
import com.youmu.mall.order.domain.ProductOrderItem;
import com.youmu.mall.order.dto.OrderAmountDto;
import com.youmu.mall.order.dto.OrderExportDto;
import com.youmu.mall.order.dto.OrderQuantityDto;
import com.youmu.mall.order.query.ProductOrderQuery;
import com.youmu.mall.order.service.IProductOrderService;
import com.youmu.mall.order.vo.PayQRCodeStatus;
import com.youmu.mall.order.vo.PayQRCodeStatus.QRCodeStatus;
import com.youmu.mall.order.vo.ProductOrderBusVo;
import com.youmu.mall.order.vo.ProductOrderDetailBusVo;
import com.youmu.mall.order.vo.ProductOrderDetailShopVo;
import com.youmu.mall.order.vo.ProductOrderDetailSysVo;
import com.youmu.mall.order.vo.ProductOrderShopVo;
import com.youmu.mall.order.vo.ProductOrderVo;
import com.youmu.mall.pay.service.IAliPayService;
import com.youmu.mall.points.dao.PointsDao;
import com.youmu.mall.points.dao.PointsRecordDao;
import com.youmu.mall.points.domain.PointsRecord;
import com.youmu.mall.product.dao.ProductDao;
import com.youmu.mall.product.dao.ProductGroupDao;
import com.youmu.mall.product.domain.Product;
import com.youmu.mall.product.domain.ProductGroup;
import com.youmu.mall.product.domain.ProductGroupDetail;
import com.youmu.mall.product.dto.GroupProductIngoDto;
import com.youmu.mall.receiver.dao.ReceiverDao;
import com.youmu.mall.receiver.vo.ReceiverVO;
import com.youmu.mall.redis.login.dao.RedisLoginDao;
import com.youmu.mall.salelog.dao.SalelogDao;
import com.youmu.mall.salelog.domain.Salelog;
import com.youmu.mall.user.dao.UserDao;
import com.youmu.mall.user.domain.BusinessUser;
import com.youmu.mall.user.domain.UserBuyRecord;

/**
 * 
 * @author yujiahao
 * @version $Id: OrderServiceImpl.java, v 0.1 2017年2月28日 下午6:33:12 yujiahao Exp $
 */
@Service
public class ProductOrderServiceImpl implements IProductOrderService, AliPayResultHandler {
    
    private static Logger logger = LoggerFactory.getLogger(ProductOrderServiceImpl.class);

    @Resource
    private ProductOrderDao orderDao;

    @Resource
    private ReceiverDao     receiverDao;

    @Resource
    private ProductDao      productDao;

    @Resource
    private CouponDao       couponDao;

    @Resource
    private CartDao         cartDao;
    
    @Resource
    private RedisLoginDao   redisLoginDao;
    
    @Resource
    private ProductGroupDao productGroupDao;
    
    @Resource
    private BusinessDao     businessDao;
    
    @Resource
    private SalelogDao      salelogDao;
    
    @Resource
    private UserDao         userDao;
        
    @Resource
    private IAliPayService  aliPayService;

    @Resource
    private TaskExecutor    taskExecutor;
    
    @Resource
    private PointsDao       pointsDao;
    
    @Resource
    private PointsRecordDao pointsRecordDao;

    /** 
     * @see com.youmu.mall.user.service.IOrderService#getOrderList(com.youmu.mall.order.query.OrderQuery)
     */
    @Override
    public Page getOrderList(ProductOrderQuery query) {
        Page<ProductOrderVo> page = new Page<>(query.getPageNum(), query.getPageSize());
        page.setData(orderDao.getOrderList(query));
        page.setTotal(orderDao.getByCount(query));
        return page;
    }

    /** 
     * @see com.youmu.mall.order.service.IProductOrderService#getOrderShopVo(com.youmu.mall.order.query.ProductOrderQuery)
     */
    @Override
    public Page<ProductOrderShopVo> getOrderShopVo(ProductOrderQuery productOrderQuery) {
        Page<ProductOrderShopVo> page = new Page<>();
        if(productOrderQuery.getPageSize()!=null && productOrderQuery.getPageNum()!=null){
            page.setPageSize(productOrderQuery.getPageSize());
            page.setPageNum(productOrderQuery.getPageNum());
        }
        productOrderQuery.setUserId(UserContext.getLongUserId());
        page.setTotal(orderDao.getCountByShop(productOrderQuery));
        page.setData(orderDao.getOrderShopVo(productOrderQuery));
        return page;
    }

    /** 
     * @see com.youmu.mall.order.service.IProductOrderService#getOrderDetailShopVo(java.lang.Long)
     */
    @Override
    public ProductOrderDetailShopVo getOrderDetailShopVo(Long orderId) {
        return orderDao.getOrderDetailShopVo(orderId);
    }

    /** 
     * @see com.youmu.mall.order.service.IProductOrderService#createOrder(com.youmu.mall.order.domain.ProductOrder)
     */
    @Override
    @Transactional
    public List<OrderAmountDto> createOrder(ProductOrder order) {
        List<OrderAmountDto> dtos = new ArrayList<>();
        Long userId = UserContext.getLongUserId();
        
        /*
         * 业务判断
         */
        if (order == null) {
            throw new ParamException("order不能为空");
        }
        if (order.getItems() == null || order.getItems().size() <= 0) {
            throw new ParamException("商品项不能为空");
        }
        if (order.getReceiverId() == null) {
            throw new ParamException("地址信息不能为空");
        }
        if (order.getTotalAmount() == null) {
            throw new ParamException("商品总金额不能为空");
        }
        if (order.getQuantity() == null || order.getQuantity() == 0) {
            throw new ParamException("商品数量不能为空");
        }
        if (order.getPaidAmount() == null) {
            throw new ParamException("商品实付款不能为空");
        }
        if (order.getPointsValue() == null) {
            throw new ParamException("订单消费积分不能为空");
        }
        if (order.getIsGroupFour() == null) {
            throw new ParamException("判断是否包含积分组不可为空");
        }
        //如果订单支付金额为0元，并且只有一件商品，则说明是一件商品的订单，则直接更改订单状态为 待发货，并且减少活动组库存，增加销售记录
        //若是多个商品，为0元支付，在减少活动组库存，增加销售记录的同时， 还需要为每一个活动商品增加销售日志
        int orderStatus = order.getPaidAmount().compareTo(ValidateUtils.BIGDECIMAL_ZERO) == 0 ? StatusConstant.ONE : StatusConstant.ZERO;
        
        //接收所有的订单子项
        List<ProductOrderItem> items = order.getItems();
        
        //若订单状态为已支付
        if(orderStatus == StatusConstant.ONE){
            //则更新商品销量
            updateSaleCountByOrder(order);
            logger.info("下单操作 , 添加0元活动商品 销售记录 ，  商品的数量为 {} ." , order.getQuantity());
            //更新订单支付时间
            order.setGmtPayFinished(DateUtils.getDate(null));
        }
        order.setStatus(orderStatus);

        //设置地址信息
        Long receiverId = order.getReceiverId();
        List<ReceiverVO> receiverVos = receiverDao.findReceiver(userId, null, receiverId);
        if (receiverVos.size() == 0) {
            throw new ParamException("地址信息不能为空");
        }
        ReceiverVO receiverVo = receiverVos.get(0);
        order.setReceiverId(receiverId);
        order.setReceiverMobile(receiverVo.getPhone());
        order.setReceiverName(receiverVo.getConsignee());
        StringBuffer buffer = new StringBuffer(receiverVo.getProvinceName());
        order.setAddress(buffer.append(receiverVo.getCityName()).append(receiverVo.getDistrictName()).append(receiverVo.getDetailAddress()).toString());
       
      //若前端没有传businessId，后端对商品项进行解析，生成针对businessId的不同订单
      //准备一个商户与商品项对应的map
      Map<Long,List<ProductOrderItem>> shippingMap = new ConcurrentHashMap<>();
      
      //判断每个商品项的商户id，把价格和数量生成一条记录保存在一个商户发货订单中
      for (ProductOrderItem productOrderItem : items) {
          Long businessId = productOrderItem.getBusinessId();
          ValidateUtils.checkNotNull("该商品没有商户id", businessId);
          List<ProductOrderItem> list = shippingMap.get(businessId);
          if(list == null || list.size()==0){
              //若为空，则新创建一个list
              list = new ArrayList<>();
              list.add(productOrderItem);
              shippingMap.put(businessId, list);
          }else{
              //若不为空，则根据商户id获取item list ，添加进list
              list.add(productOrderItem);
          }
      }
      
      //计算总订单金额
      BigDecimal globalAmount = ValidateUtils.BIGDECIMAL_ZERO;
      //计算总运费
      BigDecimal globalFreight = ValidateUtils.BIGDECIMAL_ZERO;
      //设置商户统一下单号（outTradeNumber）
      String outTradeNumber = UUID.randomUUID().toString().replaceAll("-","");
      
      //根据订单子项，与商户id，生成不同的订单
      for (Map.Entry<Long, List<ProductOrderItem>> entry : shippingMap.entrySet()) {
          
          Long businessId = entry.getKey();
          
          //生成一张关于商户的订单
          ProductOrder productOrder = new ProductOrder();
          List<ProductOrderItem> list = entry.getValue();
          productOrder.setBusinessId(businessId);
          productOrder.setAddress(order.getAddress());
          productOrder.setPayMethod(order.getPayMethod());
          productOrder.setReceiverId(order.getReceiverId());
          productOrder.setReceiverMobile(order.getReceiverMobile());
          productOrder.setReceiverName(order.getReceiverName());
          productOrder.setRemark(order.getRemark());
          productOrder.setUserId(userId);
          //接收积分值
          Long pointsValue = order.getPointsValue();
          productOrder.setPointsValue(pointsValue);
          //生成商户自己的订单编号
          productOrder.setSn(OrderUtil.createOrderNumber(userId,businessId));
          //保存统一下单号
          productOrder.setOutTradeNumber(outTradeNumber);
          productOrder.setStatus(order.getStatus());
          productOrder.setIsGroupFour(order.getIsGroupFour());
          productOrder.setItems(list);
          
          //初始化每个商户下商品的价格和数量
          BigDecimal totalAmount = ValidateUtils.BIGDECIMAL_ZERO;
          Integer quantity = 0;
          BigDecimal totalFreight = ValidateUtils.BIGDECIMAL_ZERO;
          
          if(list.size()>0){
              
              for (ProductOrderItem productOrderItem : list) {
            	  
                  
                  // 判断订单价格
                  Product product = productDao.getById(productOrderItem.getProductId());
                  if (product == null || product.getId() == null) {
                      throw new ParamException("未查询到该商品信息");
                  }
                  //上传图片
                  productOrderItem.setThumbnail(ImageUploadUtils.uploadImageDefault(ImageUploadUtils.ORDER_ITEM_IMG, productOrderItem.getThumbnail())); 
                  
                  //计算库存
                  long productId = product.getId();
                  if (product.getStatus() != StatusConstant.ONE) {
                      throw new ParamException("商品id:" + productId + "的商品未上架");
                  }
                  if (product.getTotal() == null || product.getTotal() <= 0 || (product.getTotal() - productOrderItem.getQuantity() < 0)) {
                      throw new ParamException("商品库存不足：" + product.getName() + "的当前库存为：" + product.getTotal());
                  } else {
                      /*
                       * 库存减少：1、判断version字段，减少商品表的库存，并且修改version字段
                       *        2、修改活动商品组-活动组子项 修改表中的存库和version
                       */
                      //库存减少操作
                      productDao.reduceStockById(productId, product.getTotal() - productOrderItem.getQuantity(),product.getVersion());
                      
                      //判断该商品是否参加了活动，若参加，则要减少活动库存
                      GroupProductIngoDto groupDto = productGroupDao.getGroupInfoByProductId(productId);
                      
                      if(groupDto != null){
                          //不为空，则为活动商品
                          long groupId = groupDto.getGroupId();
                          ProductGroup productGroup = productGroupDao.getById(groupId);
                          
                          //如果是秒杀活动
                          if(productGroup.getGroupType() == 1){
                              //判断是否超过截止时间
                              Date gmtEnd = productGroup.getGmtEnd();
                              ValidateUtils.checkNotNull("活动截止日期为空", gmtEnd);
                              if(DateUtils.isOverDate(gmtEnd)){
                                  throw new BusinessException("购买活动已经结束");
                              }
                          }
                          
                          //如果是积分活动  创建积分记录
                         if(productGroup.getGroupType() == StatusConstant.FOUR){
                        	  //初始化积分记录
                              PointsRecord pointsRecord =new PointsRecord();
                              ProductGroupDetail pgd =new ProductGroupDetail();
                              
                              if(pointsDao.getPointsIdById(userId)==null){
                            	  pointsDao.insertPointsById(userId);
                              }
                              
                        	  pointsRecord.setPointsId(pointsDao.getPointsIdById(userId));
                        	  pointsRecord.setUserId(userId);
                        	  pointsRecord.setOrderSn(productOrder.getSn());
                        	  pointsRecord.setPointsType(1);
                        	  pointsRecord.setProductId(productId);
                        	  pgd = productGroupDao.getDetailByProductId(productId);
                        	  pointsRecord.setPointsValue(pgd.getPrice().multiply(new BigDecimal(100)).longValue());
                        	  pointsRecord.setBackdays(pgd.getBackdays());
                        	  //progress = 0-待付款  1-待发货  2-待收货 3-已完成 4-返还中 5-已结清
                        	  pointsRecord.setProgress(StatusConstant.ZERO);
                              
                        	  pointsRecordDao.insertRecord(pointsRecord);
                        	  
                          }
                          //获取此次活动规定用户购买的数量 limitQuantity
                          Integer limitQuantity = productGroup.getLimitQuantity() == null?0:productGroup.getLimitQuantity();
                          if(groupDto != null){
                              if(groupDto.getQuantity() != null && groupDto.getQuantity() > 0 && (groupDto.getQuantity() - productOrderItem.getQuantity() >= 0)){
                                  //判断用户购买数量是否超过活动限制的数量
                                  Integer buyQuantity = productGroupDao.getBuyRecord(groupId,userId);
                                  //数据库中没有记录，代表是第一次参加该活动
                                  if(buyQuantity==null){
                                      buyQuantity = productOrderItem.getQuantity();
                                      //判断是否超过用户购买限制
                                      if(buyQuantity>limitQuantity){
                                          throw new BusinessException("超过本次活动购买数量上限");
                                      }
                                      //添加用户购买该活动组商品的记录
                                      UserBuyRecord record = new UserBuyRecord(groupId,userId,buyQuantity);
                                      productGroupDao.insertBuyRecord(record);
                                  }else{
                                      //判断是否超过
                                	  buyQuantity = buyQuantity + productOrderItem.getQuantity();
                                      if(buyQuantity>limitQuantity){
                                          throw new BusinessException("您已经参加了该商品的促销活动");      
                                      }else {
                                          //更新用户购买活动商品的记录
                                          UserBuyRecord record = new UserBuyRecord(groupId,userId,buyQuantity);
                                          productGroupDao.updateBuyRecord(record);
                                      }
                                  }
                                  //设置当前总商品的剩余库存
                                  groupDto.setQuantity(groupDto.getQuantity() - productOrderItem.getQuantity());
                                  
                                  //减少活动明细表库存
                                  productGroupDao.reduceGroupStockDetailById(groupDto);
                                  //减少活动主表库存
                                  productGroupDao.reduceGroupStockById(groupId,productOrderItem.getQuantity());
                                  
                                  //查询当前活动商品项的库存
                                  Integer currentItemStock = productGroupDao.getRestQuantityByDetailId(groupDto.getGroupDetailId());
                                  if(currentItemStock != null && currentItemStock == 0){
                                      //活动商品刚好抢完，更改抢完时间
                                      productGroupDao.updateFinishDateById(groupDto.getGroupDetailId());
                                  }
                              }else{
                                  throw new BusinessException("当前活动商品库存不足");
                              }
                          }
                      }
                  }
                  
                  //若是从购物车来的商品,还需要清除购物车里的对应记录
                  if (productOrderItem.getCartId() != null) {
                      //判断商品的商户类型
                      //根据购物车id清除该用户的购物车项
                      cartDao.deleteCart(productOrderItem.getCartId(),userId);
                  }
                  
                  BigDecimal price = productOrderItem.getPrice();
                  Integer quantityItem = productOrderItem.getQuantity();
                  BigDecimal totalPrice = new BigDecimal(quantityItem).multiply(price);
                  BigDecimal freight = productOrderItem.getFreight() == null?ValidateUtils.BIGDECIMAL_ZERO:productOrderItem.getFreight();
                  
                  //商品总价格累加
                  totalAmount = totalAmount.add(totalPrice);
                  //商品总数累加
                  quantity += quantityItem;
                  //运费累加
                  totalFreight = totalFreight.add(freight);
              }
          }
          //如果订单不需要支付，则针对每一个商户，添加购买记录
          if(orderStatus == StatusConstant.ONE){
              saveSalelog(ValidateUtils.BIGDECIMAL_ZERO, businessId, quantity, userId);
          }
          //校验用户要消耗的积分值
          BigDecimal pointsValueToBigDecimal = null;
          BigDecimal pointsValueToMoney = null;
          
          if(pointsValue.longValue()>pointsDao.getPointsById(userId).longValue()){
        	  throw new BusinessException("用户积分不足");
          }else{
        	  pointsValueToBigDecimal = new BigDecimal(pointsValue.longValue());
        	  pointsValueToMoney = pointsValueToBigDecimal.divide(new BigDecimal(100));
          }
          
          //计算实付款
          BigDecimal paidAmount = totalAmount.add(order.getFreight()).subtract(pointsValueToMoney).setScale(2, BigDecimal.ROUND_HALF_EVEN);
          productOrder.setFreight(order.getFreight());
          productOrder.setPaidAmount(paidAmount);
          productOrder.setQuantity(quantity);
          productOrder.setTotalAmount(totalAmount);
          
          //1、保存订单
          orderDao.createOrder(productOrder);
          
          //2、保存订单子项
          orderDao.batchSaveOrderItems(productOrder.getId(), list);
          OrderAmountDto dto = new OrderAmountDto(productOrder.getId(),paidAmount);
          dtos.add(dto);
          
          globalAmount = globalAmount.add(paidAmount);
          globalFreight = globalFreight.add(totalFreight);
      }
      if(!ValidateUtils.isEqualsByBigDecimal(globalAmount, order.getPaidAmount())){
          throw new BusinessException("支付金额与订单金额不符");
      }
     // if(!ValidateUtils.isEqualsByBigDecimal(globalFreight, order.getFreight()==null?ValidateUtils.BIGDECIMAL_ZERO:order.getFreight())){
     //     throw new BusinessException("实际运费与订单运费不符");
     // }
      
      return dtos;
      
    }

    /** 
     * @see com.youmu.mall.order.service.IProductOrderService#cancelOrder(java.lang.Long)
     */
    @Override
    @Transactional
    public void cancelOrder(Long id) {
        Long userId = UserContext.getLongUserId();
        ProductOrder order = orderDao.getById(id,userId);
        if(order == null){
            throw new ParamException("未找到该订单");
        }
        if(order.getStatus()!=StatusConstant.ZERO){
            throw new ParamException("不是待支付订单，不能取消");
        }
        //查询要取消的订单的商品项
        List<OrderQuantityDto> dtos = orderDao.getQuantityDtoById(id);

        //取消订单
        orderDao.cancelOrder(id,userId);
                
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
                    	pointsRecordDao.deleteRecordBySn(order.getSn());
                    }
                    
                    //同时扣除创建订单时增添的用户购买活动组商品数量
                    UserBuyRecord userBuyRecord = new UserBuyRecord(groupDto.getGroupId(), userId, orderQuantityDto.getQuantity());
                    productGroupDao.subBuyRecord(userBuyRecord);
                    
                    productGroupDao.increaseStockByGroupId(groupDto.getGroupId(),orderQuantityDto.getQuantity());
                    productGroupDao.clearFinishDate(groupDto.getGroupId(),orderQuantityDto.getProductId());
                }
            }
        }
        
    }
    
    
    
    /** 
     * @see com.youmu.mall.order.service.IProductOrderService#payWeixinByPc(java.util.List, java.lang.String)
     */
    @Override
    @Transactional
    public QRCodePayParams payByPc(List<OrderAmountDto> dtos) {
        //针对支付服务器的唯一下单号
        String outTradeNumber = null;
        //是否是单个商户的订单
        boolean isOnlyBusiness = false;
        BigDecimal totalAmount = ValidateUtils.BIGDECIMAL_ZERO;
        //若只有一个订单，则该订单只对应唯一商户
        if(dtos.size()==1){
            isOnlyBusiness = true;
        }
        for (OrderAmountDto orderAmountDto : dtos) {
            Long orderId = orderAmountDto.getOrderId();
            BigDecimal amount = orderAmountDto.getPaidAmount();
            /*业务判断*/
            ProductOrder orderInDB = orderDao.getById(orderId, UserContext.getLongUserId());
            if(orderInDB == null){
                throw new ParamException("订单不能为空");
            }
            //判断订单状态是否为待支付
            if (orderInDB.getStatus() != StatusConstant.ZERO) {
                throw new BusinessException("不是待支付，不能付款");
            }
            //判断订单是否超时(30分钟为超时时间)
            if(orderInDB.getGmtCreate()==null){
                throw new ParamException("没有下单时间");
            }
            if(DateUtils.getDateBeforeHalfHour(orderInDB.getGmtCreate())>30){
                throw new ParamException("订单支付超时");
            }
            //判断支付金额是否等于订单实付款金额
            if (!ValidateUtils.isEqualsByBigDecimal(amount,orderInDB.getPaidAmount())) {
                throw new BusinessException("订单金额与实际支付金额不符");
            }
            ValidateUtils.checkNotNull("id为 ：" +orderId +" 的订单，外部订单号为空", orderInDB.getOutTradeNumber());
            
            //判断用户是否 是唯一商户支付的情况，如果是，则以商户的订单号下单，如果不是，则以外部订单号下单
            if(isOnlyBusiness){
                outTradeNumber = orderInDB.getSn();
            }else {
                if(outTradeNumber==null){
                    outTradeNumber = orderInDB.getOutTradeNumber();
                }
                if(!outTradeNumber.equals(orderInDB.getOutTradeNumber())){
                    throw new BusinessException("不对应同一个下单号，不能一起支付");
                }
            }
            /*业务判断 完*/
            
            Long businessId = orderInDB.getBusinessId();
            if(businessId == null){
                throw new BusinessException("该订单没有商户id");
            }
            
            totalAmount = totalAmount.add(amount);
        }
        //封装支付对象
        QRCodePayParams params = new QRCodePayParams();
        params.setOut_trade_no(outTradeNumber);
        params.setTotal_amount(totalAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
        params.setSubject("宝宝余商城商品");
        //返回支付参数
        return params;
    }
    
    /** 
     * @see com.youmu.mall.order.service.IProductOrderService#payWeixin(java.lang.Long, java.lang.Double, java.lang.String)
     */
    @Override
    @Transactional
    public Map<String, Object> payWeixin(List<OrderAmountDto> dtos, String remoteAddr,String openid) {
        
        ValidateUtils.checkNotBlank(openid, "openid不能为空");
        String outTradeNumber = null;
        BigDecimal totalAmount = ValidateUtils.BIGDECIMAL_ZERO;
        for (OrderAmountDto orderAmountDto : dtos) {
            Long orderId = orderAmountDto.getOrderId();
            BigDecimal amount = orderAmountDto.getPaidAmount();
            /*业务判断*/
            ProductOrder orderInDB = orderDao.getById(orderId, UserContext.getLongUserId());
            ValidateUtils.checkNotNull("订单不能为空", orderInDB);
            //判断订单状态是否为待支付
            if (orderInDB.getStatus() != StatusConstant.ZERO) {
                throw new BusinessException("不是待支付，不能付款");
            }
            //判断订单是否超时(30分钟为超时时间)
            ValidateUtils.checkNotNull("没有下单时间", orderInDB.getGmtCreate());
            if(DateUtils.getDateBeforeHalfHour(orderInDB.getGmtCreate())>30){
                throw new ParamException("订单支付超时");
            }
           
            //判断订单消耗的积分
            if(orderInDB.getPointsValue().longValue()>pointsDao.getPointsById(UserContext.getLongUserId()).longValue()){
                throw new BusinessException("用户积分不足");
            }
            //判断支付金额是否等于订单实付款金额
            if (!ValidateUtils.isEqualsByBigDecimal(amount,orderInDB.getPaidAmount())) {
                throw new BusinessException("订单金额与实际支付金额不符");
            }
            ValidateUtils.checkNotNull("id为 ：" +orderId +" 的订单，外部订单号为空", orderInDB.getOutTradeNumber());
            // 在一次完整的支付流程中，为不同的商户设置相同的外部订单号
            if(outTradeNumber==null){
                outTradeNumber = orderInDB.getOutTradeNumber();
            }
            if(!outTradeNumber.equals(orderInDB.getOutTradeNumber())){
                throw new BusinessException("不对应同一个下单号，不能一起支付");
            }
            /*业务判断 完*/
            Long businessId = orderInDB.getBusinessId();
            if(businessId == null){
                throw new BusinessException("该订单没有商户id");
            }
            totalAmount = totalAmount.add(amount); 
        }
        
        logger.info("用户已经绑定微信，openId为："+ openid +" ，进入支付页面。。。。");
        //调用支付接口，返回支付info
        String prepayid = WxPayUtil.payUnifiedorder(
            "微信支付订单", 
            outTradeNumber, 
            totalAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN), 
            remoteAddr,openid,null);
        
        Map<String, Object> weinxinMap = new HashMap<>();
        weinxinMap.put("appId", ConfigUtils.getWeixinAppId());
        weinxinMap.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
        weinxinMap.put("nonceStr", UUID.randomUUID().toString().replaceAll("-",""));
        weinxinMap.put("package", "prepay_id="+prepayid);
        weinxinMap.put("signType", "MD5");
        String paySign = WxTools.getSign(weinxinMap);
        weinxinMap.put("paySign", paySign);
        return weinxinMap;
    }
    
    
    
    @Override
    public String notifyWeixin(String body) {
        Map<String,Object> result = new HashMap();
        Map params = WxTools.getMapByXml(body);
        if(params == null){
            result.put("return_code", "false");
            result.put("return_msg", "参数错误");
            return WxTools.getXml(result);
        }
        
        String sign =WxTools.getSign(params);
        String paramSign = (String) params.get("sign");
        if(paramSign == null ||!paramSign.equals(sign)){
            result.put("return_code", "false");
            result.put("return_msg", "签名错误");
            return WxTools.getXml(result);
        }
        //得到外部订单号
        String outOrderNumber = (String) params.get("out_trade_no");
        String transactionId = (String) params.get("transaction_id");
        
        logger.debug("notifyWeixin()中的参数 outnumber是 {} ,transaction_id是 {} . ",outOrderNumber,transactionId);
        
        if(!this.notifySuccess(outOrderNumber,transactionId,StatusConstant.ONE)){
            result.put("return_code", "false");
            return WxTools.getXml(result);
        }
        result.put("return_code", "success");
        return WxTools.getXml(result);
    }
    
    /**
     * 回调成功,统一业务处理
     * @param outSn
     * @return
     */
    @Transactional
    public boolean notifySuccess(String outTradeNumber, String transactionId, Integer payType){
        String payMethod = null;
        if(payType==null){
            logger.error("没有支付方式");
            return false;
        }else if(payType==StatusConstant.ONE){
            payMethod="微信支付";
        }else if(payType==StatusConstant.TWO){
            payMethod="支付宝支付";
        }
        
        //首先通过传入参数outTradeNumber， 查询数据库中的外部订单号相同的订单，
        //  1.若查到订单，则对这些订单集合进行处理
        //  2.若没查到，则根据商户订单号再进行对比查询
        //      i.若查到订单，则更改订单状态
        //      ii.若没查到订单，则报错
        List<ProductOrder> orders = orderDao.getByOutTradeNumber(outTradeNumber);
        
        if(orders == null || orders.size() == 0){
            ProductOrder order = orderDao.getBySn(outTradeNumber);
            if(order==null){
                logger.error("查询不到该订单。下单失败");
                return false;
            }
            orders.add(order);
        }
        
        //更改订单状态
        for (ProductOrder productOrder : orders) {
            //判断数据库里订单是否更改状态
            if(productOrder.getStatus()==StatusConstant.ONE){
                continue;
            }
            
            if(productOrder.getStatus()==StatusConstant.ZERO){
                //更改商品的销量
                updateSaleCountByOrder(productOrder);
                
                logger.info("微信支付回调接口 --> 订单的商品总数量为 {} , 订单的用户id为{} .",productOrder.getQuantity(),productOrder.getUserId());
                
                //根据每个订单，添加商品销售记录
                saveSalelog(productOrder.getPaidAmount(),productOrder.getBusinessId(),productOrder.getQuantity(),productOrder.getUserId());
                
                //封装订单对象，更新订单
                ProductOrder newOrder = new ProductOrder();
                newOrder.setId(productOrder.getId());
                newOrder.setTransactionId(transactionId);
                newOrder.setStatus(StatusConstant.ONE);
                newOrder.setPayMethod(payMethod);
                logger.info("   微信支付回调接口--> 支付成功，更新主订单 状态为: "+ newOrder.getStatus() +"，订单id: " + productOrder.getId());
                //更新订单
                orderDao.update(newOrder);
                 
              //如果包含积分商品，更新订单的同时更新积分进度
                
                List<OrderQuantityDto> Dtos =orderDao.getQuantityDtoById(productOrder.getId());
                if(Dtos != null && Dtos.size()>0){
                  for (OrderQuantityDto orderQuantityDto : Dtos) {
                  	
                      GroupProductIngoDto groupDto = productGroupDao.getGroupInfoByProductId(orderQuantityDto.getProductId());
                      if(groupDto != null){
                      	ProductGroup productGroup = productGroupDao.getById(groupDto.getGroupId());
                      	 
                          if( productGroup.getGroupType() == 4){
                 	 			pointsRecordDao.updateRecordProgress(productOrder.getSn(),1);
                          }
                      }
                  }
                }
               
               //确认已经付款 此时扣除用户订单中消耗的积分值
                pointsDao.subPointsById(productOrder.getUserId(), productOrder.getPointsValue());
            }
        }
        return true;
    }

    /**
     * 根据已支付订单更改商品的销量
     * @param productOrder
     */
    private void updateSaleCountByOrder(ProductOrder productOrder) {
        List<ProductOrderItem> items = productOrder.getItems();
        for (ProductOrderItem productOrderItem : items) {
            Long productId = productOrderItem.getProductId();
            Integer quantity = productOrderItem.getQuantity();
            productDao.updateSaleCount(productId,quantity);
            logger.info("更新订单商品的销量...商品id为 {} , 商品数量为 {} .", productId, quantity);
        }
    }

    /** 
     * @see com.youmu.mall.order.service.IProductOrderService#getProductOrderListBusVo(com.youmu.mall.order.query.ProductOrderQuery)
     */
    @Override
    public Page<ProductOrderBusVo> getProductOrderListBusVo(ProductOrderQuery query) {
        BusinessUser user = redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), BusinessUser.class);
        query.setBusinessId(user.getBusiness().getId());
        Page<ProductOrderBusVo> page = new Page<>();
        page.setPageNum(query.getPageNum());
        page.setPageSize(query.getPageSize());
        page.setTotal(orderDao.getProductOrderBusVoByCount(query));
        page.setData(orderDao.getProductOrderListBusVo(query));
        return page;
    }

    /** 
     * @see com.youmu.mall.order.service.IProductOrderService#getProductOrderDetailBusVo(java.lang.Long)
     */
    @Override
    public ProductOrderDetailBusVo getProductOrderDetailBusVo(Long id,Integer status) {
        //获取已登录的商户用户的商户id
        BusinessUser user = redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), BusinessUser.class);
        return orderDao.getProductOrderDetailBusVo(id,user.getBusiness().getId(),status);
    }

    /**
     * @see com.youmu.mall.order.service.IProductOrderService#getProductOrderDetailSysVo(Long, Integer)
     */
	@Override
	public ProductOrderDetailSysVo getProductOrderDetailSysVo(Long id) {
		return orderDao.getProductOrderDetailSysVo(id);
	}
    

    /** 
     * @see com.youmu.mall.order.service.IProductOrderService#sendProductOrderById(java.lang.Long)
     */
    @Override
    public void sendProductOrderById(Long id,String shippingSn,String shippingMethod) {
        orderDao.sendProductOrderById(id,shippingSn,shippingMethod);
        //订单发货 如包含积分商品同时更新积分记录进度
        ProductOrder order =orderDao.getInfoByShippingSn(shippingSn);
        if(order == null){
            throw new BusinessException("订单为空");
        }
        List<OrderQuantityDto> Dtos =orderDao.getQuantityDtoById(order.getId());
          if(Dtos != null && Dtos.size()>0){
            for (OrderQuantityDto orderQuantityDto : Dtos) {
            	
                GroupProductIngoDto groupDto = productGroupDao.getGroupInfoByProductId(orderQuantityDto.getProductId());
                if(groupDto != null){
                	ProductGroup productGroup = productGroupDao.getById(groupDto.getGroupId());
                	 
                    if( productGroup.getGroupType() == 4){
           	 			pointsRecordDao.updateRecordProgress(order.getSn(), 2);
                    }
                }
            }
          }
    }

    /** 
     * @see com.youmu.mall.order.service.IProductOrderService#testPay(java.lang.Long, java.math.BigDecimal)
     */
    @Override
    @Transactional
    public void testPay(List<OrderAmountDto> dtos) {
        /*=========== 模拟： payWeixin() =============*/
        String outTradeNumber = null;
        BigDecimal totalAmount = ValidateUtils.BIGDECIMAL_ZERO;
        for (OrderAmountDto orderAmountDto : dtos) {
            Long orderId = orderAmountDto.getOrderId();
            BigDecimal amount = orderAmountDto.getPaidAmount();
            /*业务判断*/
            ProductOrder orderInDB = orderDao.getById(orderId, UserContext.getLongUserId());
            if(orderInDB == null){
                throw new ParamException("订单不能为空");
            }
            //判断订单状态是否为待支付
            if (orderInDB.getStatus() != StatusConstant.ZERO) {
                throw new BusinessException("不是待支付，不能付款");
            }
            //判断订单是否超时(30分钟为超时时间)
            if(orderInDB.getGmtCreate()==null){
                throw new ParamException("没有下单时间");
            }
            if(DateUtils.getDateBeforeHalfHour(orderInDB.getGmtCreate())>30){
                throw new ParamException("订单支付超时");
            }
            //判断订单消耗的积分
            if(orderInDB.getPointsValue().longValue()>pointsDao.getPointsById(UserContext.getLongUserId()).longValue()){
                throw new BusinessException("用户积分不足");
            }
            //判断支付金额是否等于订单实付款金额
            if (!ValidateUtils.isEqualsByBigDecimal(amount,orderInDB.getPaidAmount())) {
                throw new BusinessException("订单金额与实际支付金额不符");
            }
            ValidateUtils.checkNotNull("id为 ：" +orderId +" 的订单，外部订单号为空", orderInDB.getOutTradeNumber());
            if(outTradeNumber==null){
                outTradeNumber = orderInDB.getOutTradeNumber();
            }
            if(!outTradeNumber.equals(orderInDB.getOutTradeNumber())){
                throw new BusinessException("不对应同一个下单号，不能一起支付");
            }
            /*业务判断 完*/
            
            Long businessId = orderInDB.getBusinessId();
            if(businessId == null){
                throw new BusinessException("该订单没有商户id");
            }
            
            totalAmount = totalAmount.add(amount);
        }
        //调用支付接口，返回支付info
//        String prepayid = WxPayUtil.payUnifiedorder("微信支付订单", outTradeNumber, totalAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN), remoteAddr);
//        WeixinInfo weixinInfo = new WeixinInfo(ConfigUtils.getWeixinAppId(),ConfigUtils.getWeixinPartnerId(),prepayid);
//        String sign = WxTools.getSign(MapUtil.beanToMap(weixinInfo));
//        weixinInfo.setSign(sign);
//        return weixinInfo;
        
        /*=========== 模拟： payWeixin() 完=============*/
        
        
        /*  微信回调成功  */
        //所有订单为同一个下单号，模拟支付成功
//        模拟调取微信支付回调：notifySuccess(outTradeNumber,transactionId);
        List<ProductOrder> orders = orderDao.getByOutTradeNumber(outTradeNumber);
        if(orders == null || orders.size() == 0){
            logger.error(("方法 ： notifySuccess() ===>  返回：return false; --> 查询不到该订单。下单失败"));
        }
        
        //更改订单状态
        int i = 1;
        for (ProductOrder productOrder : orders) {
            
            updateSaleCountByOrder(productOrder);
            
            ProductOrder newOrder = new ProductOrder();
            newOrder.setId(productOrder.getId());
            newOrder.setTransactionId("微信返回的transaction_id");
            newOrder.setStatus(StatusConstant.ONE);
            newOrder.setPayMethod("微信支付");
            newOrder.setSn(productOrder.getSn()+i);
            logger.info("   模拟调取微信支付回调：notifySuccess(outTradeNumber) =====> 支付成功，更新主订单 状态为: "+ newOrder.getStatus() +"，订单id: " + productOrder.getId());
            //更新订单
            orderDao.update(newOrder);
            
          
           
            i++;
        }
    }
    
    /** 
     * @see com.youmu.mall.order.service.IProductOrderService#findCancelOrders()
     */
    @Override
    public List<ProductOrder> findCancelOrders() {
        return orderDao.findCancelOrders();
    }

    /** 
     * @see com.youmu.mall.order.service.IProductOrderService#batchCancelOrders(java.util.List)
     */
    @Override
    public void batchCancelOrders(List<Long> cancelOrderIds) {
        orderDao.batchCancelOrders(cancelOrderIds);
    }

    /** 
     * @see com.youmu.mall.order.service.IProductOrderService#getAmountByOutTradeNumber(java.lang.String)
     */
    @Override
    public BigDecimal getAmountByOutTradeNumber(String outTradeNumber) {
        return orderDao.getAmountByOutTradeNumber(outTradeNumber);
    }

    /** 
     * @see com.youmu.mall.order.service.IProductOrderService#notifyAlipay(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void notifyAlipay(HttpServletRequest request,HttpServletResponse response) {
        aliPayService.handlePayNotify(request, response, this);
    }

    /** 
     * @see com.youmu.common.alipay.utils.AliPayResultHandler#handlePayResult(com.youmu.common.alipay.utils.AlipayResult)
     */
    @Override
    @Transactional
    public String handlePayResult(AlipayResult result) {
        /**
         * 处理支付结果.
         * <ul>
         * <li>
         *  1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
         * </li>
         * <li>
         * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
         *   通过sql查询订单是否存在
         * </li>
         * <li>
         * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
         *   校验sellerId是否正确
         * </li>
         * <li>
         * 4、验证app_id是否为该商户本身。
         *   校验app_id是否正确
         *   <br />
         * 上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
         * </li>
         * </ul>
         * @param result
         */
        
        //验证appid
        String appid = ConfigUtils.getAlipayAppId();
        if(!StringUtils.equals(appid, result.getAppId())){
            logger.error("config appid:{}, result appid:{}.", appid, result.getAppId());
            return AliPayUtils.FAILURE;
        }
        
        //验证seller
        String seller = ConfigUtils.getAlipaySellerId();
        if(!StringUtils.equals(seller, result.getSellerId())){
            logger.error("config seller id:{}, result seller id:{}.", seller, result.getSellerId());
            return AliPayUtils.FAILURE;
        }
        
        //验证外部订单号
        String outTradeNo = result.getOutTradeNo();
        List<ProductOrder> orders = orderDao.getByOutTradeNumber(outTradeNo);
        if(orders == null || orders.isEmpty()){
            logger.error("out trade no {} not found.", outTradeNo);
            return AliPayUtils.FAILURE;
        }
        
        //数据库中计算的订单总金额
        BigDecimal amountInDb = ValidateUtils.BIGDECIMAL_ZERO;
        for (ProductOrder productOrder : orders) {
            BigDecimal paidAmount = productOrder.getPaidAmount();
            amountInDb = amountInDb.add(paidAmount);
        }
        
        //判断返回的支付总金额
        BigDecimal dbAmount = amountInDb.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        if(dbAmount.compareTo(new BigDecimal(result.getTotalAmount())) !=0 ){
            logger.error("order total amount{}, result total amount{}.", dbAmount, result.getTotalAmount());
            return AliPayUtils.FAILURE;
        }
        
        //订单业务处理
        if(!notifySuccess(outTradeNo, result.getTradeNo(), StatusConstant.TWO)){
            logger.error("支付宝回调业务处理异常");
            return AliPayUtils.FAILURE;
        }
        logger.info("支付宝回调处理成功");
        return AliPayUtils.SUCCESS;
        
    }

    /** 
     * @see com.youmu.mall.order.service.IProductOrderService#pollingAliPayQRCodeStatus(org.springframework.web.context.request.async.DeferredResult)
     */
    @Override
    @Transactional
    public void pollingAliPayQRCodeStatus(final DeferredResult<PayQRCodeStatus> result, final String outTradeNo) {
        taskExecutor.execute(new Runnable() {
            
            @Override
            public void run() {
                try {
                    // 用支付宝支付宝查询接口 查询该订单的状态
                    AlipayResult alipayResult = AliPayUtils.queryAliPayResult(outTradeNo, null);
                    logger.debug("query alipay {} status {}.", outTradeNo, alipayResult);
                    
                    PayQRCodeStatus payQrCodeStatus = new PayQRCodeStatus();
                    QRCodeStatus qrCodeStatus = PayQRCodeStatus.QRCodeStatus.FAIL;
                    // 返回结果
                    AliPayStatus status = alipayResult.getPayStatus();
                    switch (status) {
                        case TRADE_SUCCESS:
                            qrCodeStatus = QRCodeStatus.SUCCESS;
                            notifySuccess(alipayResult.getOutTradeNo(), alipayResult.getTradeNo(), GlobalConstant.TWO);
                            break;
                            
                        case WAIT_BUYER_PAY:
                            qrCodeStatus = QRCodeStatus.WAIT;
                            break;
                            
                        case TRADE_CLOSED:
                            qrCodeStatus = QRCodeStatus.CANCEL;
                            break;
                            
                        case TRADE_FINISHED:
                            qrCodeStatus = QRCodeStatus.SUCCESS;
                            notifySuccess(alipayResult.getOutTradeNo(), alipayResult.getTradeNo(), GlobalConstant.TWO);
                            break;
                            
                         case TRADE_NOT_FOUND:
                            qrCodeStatus = QRCodeStatus.WAIT;
                            break;
                    }
                    payQrCodeStatus.setQrCodeStatus(qrCodeStatus);
                    logger.debug("polling {} status {}.", outTradeNo, payQrCodeStatus.getQrCodeStatus());
                    result.setResult(payQrCodeStatus);
                } catch (Exception e) {
                    logger.error("支付宝轮询异常", e);
                }
            }
        });
    }

    /**
     * 添加销售记录
     * @param amount 订单总金额
     * @param businessId 该订单对应的商户id
     * @param quantity 商品销售的数量
     * @param userId 用户id
     */
    public void saveSalelog(BigDecimal amount, Long businessId, Integer quantity, Long userId){
        logger.info("************** 添加购买记录 [START]*******************");
        //根据商户id查询商户类型
        Long businessTypeId = businessDao.getTypeIdById(businessId);
        
        //封装销售日志对象
        Salelog salelog = new Salelog();
        salelog.setAmount(amount);
        salelog.setBusinessTypeId(businessTypeId);
        salelog.setQuantity(quantity);
        salelog.setUserId(userId);
        logger.info("添加用户购买记录 =====> ，id为 {} 的用户购买了 ： {} 个商品 ." , userId, quantity);
        salelogDao.insert(salelog);
        logger.info("************** 添加购买记录 [END]*******************");
    }

    /** 
     * @see com.youmu.mall.order.service.IProductOrderService#pollingWeXinPayQRCodeStatus(org.springframework.web.context.request.async.DeferredResult, java.lang.String)
     */
    @Override
    @Transactional
    public void pollingWeXinPayQRCodeStatus(final DeferredResult<PayQRCodeStatus> result, final String outTradeNumber) {
        taskExecutor.execute(new Runnable() {
            
            @Override
            public void run() {
                try {
                    // 从微信查询接口查询数据
                    WxPayInfo info  = WxPublicNumberUtil.getWxOrderStatus(outTradeNumber);
                    
                    Integer status = info.getStatus();
                    logger.debug("query wx pay {} status {}.", outTradeNumber, status);
                    
                    PayQRCodeStatus payQrCodeStatus = new PayQRCodeStatus();
                    QRCodeStatus qrCodeStatus = PayQRCodeStatus.QRCodeStatus.FAIL;
                    // 返回结果
                    if(status == null) {
                        result.setResult(payQrCodeStatus);
                        return;
                    }
                    switch (status) {
                        case GlobalConstant.ZERO:
                            qrCodeStatus = QRCodeStatus.WAIT;
                            break;
                            
                        case GlobalConstant.ONE:
                            qrCodeStatus = QRCodeStatus.SUCCESS;
                            notifySuccess(outTradeNumber, info.getTransactionId(), GlobalConstant.ONE);
                            break;
                        
                        case GlobalConstant.FOUR:
                            qrCodeStatus = QRCodeStatus.CANCEL;
                            break;
                        default:
                            break;
                    }
                    payQrCodeStatus.setQrCodeStatus(qrCodeStatus);
                    result.setResult(payQrCodeStatus);
                } catch (Exception e) {
                    logger.error("微信轮询异常", e);
                }
            }
        });
    }

    /** 
     * @see com.youmu.mall.order.service.IProductOrderService#exportProductOrders(java.lang.Integer)
     */
    @Override
    public void exportProductOrders(ProductOrderQuery query,HttpServletResponse response) {
        BusinessUser user = redisLoginDao.getLoggedUserInfo(UserContext.getUserId(), BusinessUser.class);
        query.setBusinessId(user.getBusiness().getId());
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
        try {
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode("商品待发货订单.xlsx", CharEncoding.UTF_8) + "\"");
            List<OrderExportDto> info = orderDao.getExportProductInfo(query);
            for (OrderExportDto orderExportDto : info) {
                List<ProductOrderItem> items = orderExportDto.getItems();
                if(items.size()>0){
                    StringBuffer buffer = new StringBuffer();
                    for (ProductOrderItem productOrderItem : items) {
                        buffer.append(productOrderItem.getName()).append(" X ").append(productOrderItem.getQuantity()).append(";\n");
                    }
                    int i = buffer.lastIndexOf("\n");
                    buffer.delete(i, i+2);
                    orderExportDto.setProductInfo(buffer.toString());
                }
            }
            ExcelUtils.exportExcel(OrderExportDto.class, info, response.getOutputStream(), "yyyy.MM.dd HH:mm:ss");
        } catch (Exception e) {
            logger.error("导出失败", e);
        }
    }


    
}
