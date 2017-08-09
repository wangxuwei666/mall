/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youmu.mall.base.query.PageQuery;
import com.youmu.mall.order.dto.OrderQuantityDto;
import com.youmu.mall.product.domain.ProductGroup;
import com.youmu.mall.product.domain.ProductGroupDetail;
import com.youmu.mall.product.dto.GroupProductIngoDto;
import com.youmu.mall.product.query.ProductGroupQuery;
import com.youmu.mall.product.vo.GroupDateVo;
import com.youmu.mall.product.vo.ProductGroupDetailShopVo;
import com.youmu.mall.product.vo.ProductGroupEditVo;
import com.youmu.mall.product.vo.ProductGroupShopVo;
import com.youmu.mall.product.vo.ProductGroupSysVo;
import com.youmu.mall.product.vo.ProductSysVo;
import com.youmu.mall.user.domain.UserBuyRecord;

/**
 * 商品活动组数据层
 * @author yujiahao
 * @version $Id: ProductGroupDao.java, v 0.1 2017年3月7日 上午11:27:30 yujiahao Exp $
 */
public interface ProductGroupDao {

    /**
     * 管理后台-商品活动组列表
     * @param query
     * @return
     */
    List<ProductGroupSysVo> getProductGroupSysVo(ProductGroupQuery query);

    /**
     * 管理后台-商品活动组列表总数
     * @param query
     * @return
     */
    Long getProductGroupSysVoByCount(ProductGroupQuery query);
    
    /**
     * 保存活动商品组
     * 
     * @param productGroup
     */
    void insertAndGetId(ProductGroup productGroup);
    
    /**
     * 批量保存商品组商品明细
     * 
     * @param items
     * @param groupId
     */
    void batchSaveGroupItems(@Param("items")List<ProductGroupDetail> items,@Param("groupId")Long groupId);

    /**
     * 根据活动组id查询明细
     * @param groupId
     * @return
     */
    ProductGroupEditVo getProductGroupDetailSysVoByGroupId(@Param("groupId")Long groupId ,@Param("status")Integer status);

    /**
     * 查询当前未参加活动的商品信息
     * @param pageQuery
     * @return
     */
    List<ProductSysVo> getNotReduceProduct(PageQuery pageQuery);

    /**
     * 查询当前未参加活动的商品条数
     * @param query
     * @return
     */
    long getCountByNotReduceProduct(PageQuery query);

    /**
     * 物理删除从表子项
     * @param groupId
     */
    void deteleGroupDetailsByGroupId(Long groupId);

    /**
     * 修改活动商品组 主表
     * @param productGroup
     */
    void update(ProductGroup productGroup);

    /**
     * 根据 商品id 和 商品数量 更新 活动明细表库存
     * @param dtos
     */
    void batchUpdateGroupItems(@Param("list")List<OrderQuantityDto> dtos);

    /**
     * 根据商品id查询是活动信息
     * @param productId
     * @return
     */
    GroupProductIngoDto getGroupInfoByProductId(Long productId);

    /**
     * 
     * @param groupDto
     */
    void reduceGroupStockDetailById(GroupProductIngoDto groupDto);

    /**
     * 根据活动id查询活动组
     * @param groupId
     * @return
     */
    ProductGroup getById(long groupId);

    /**
     * 查询用户已经购买该活动组的商品数量
     * @param groupId
     * @param userId
     * @return
     */
    Integer getBuyRecord(@Param("groupId")long groupId, @Param("userId")Long userId);

    /**
     * 保存用户购买活动商品的记录
     * @param record
     */
    void insertBuyRecord(UserBuyRecord record);

    /**
     * 更新用户购买活动商品的记录
     * @param record
     */
    void updateBuyRecord(UserBuyRecord record);

    /**
     * 取消订单时减少用户购买活动商品的记录
     * @param record
     */
    void subBuyRecord(UserBuyRecord record);
    
    /**
     * 减少活动主表的库存数量
     * @param groupId
     * @param quantity
     */
    void reduceGroupStockById(@Param("groupId")long groupId, @Param("quantity")Integer quantity);

    /**
     * 增加活动主表的库存数量
     * @param groupId
     */
    void increaseStockByGroupId(@Param("groupId")long groupId, @Param("quantity")Integer quantity);

    /**
     * 删除活动组
     * @param id
     */
    void deleteProductGroup(Long id);

    /**
     * 根据活动组id删除活动组明细
     * @param groupId
     */
    void deleteProductGroupDetailsByGroupId(@Param("groupId")Long groupId);
    
    /**
     * 根据活动组明细id删除活动组明细
     * @param id
     */
    void deleteProductGroupDetailsById(Long id);

    /**
     * 用户端 活动商品组展示
     * @param query
     * @return
     */
    List<ProductGroupShopVo> getProductGroupShopVo(ProductGroupQuery query);

    /**
     * 用户端 活动商品组列表总数
     * @param query
     * @return
     */
    Long getCountByProductGroupShopVo(@Param("groupType")Integer groupType);

    /**
     * 用户端- 秒杀&特价  根据活动组id查询商品组明细
     * @param groupId
     * @return
     */
    ProductGroupShopVo getProductGroupDetailShopVoById(@Param("groupId")Long groupId);

    /**
     * 检查时间段是否存在秒杀活动
     * @param gmtStart 开始时间
     * @param gmtEnd    结束时间
     * @return (false可用  true不可用)
     */
    boolean checkDateIsUsed(@Param("gmtStart")Date gmtStart, @Param("gmtEnd")Date gmtEnd);

    /**
     * 查询当前商品组明细项的剩余商品数量
     * @param groupDetailId
     * @return
     */
    Integer getRestQuantityByDetailId(long groupDetailId);

    /**
     * 更新抢完活动商品的时间
     * @param groupDetailId
     */
    void updateFinishDateById(long groupDetailId);

    /**
     * 秒杀页面banner(查询当天参加活动的时间段)
     * @return
     */
    List<GroupDateVo> getGroupBannerDate();

    /**
     * 根据商品id查询正在活动的商品信息
     * @param productId
     * @return
     */
    ProductGroupDetail getDetailInfoByProductId(Long productId);
    
    /**
     * 根据商品id查询积分组商品的信息 
     * @param productId
     * @return
     */
    ProductGroupDetail getDetailByProductId(Long productId);

    /**
     * 商品退单，清除完成秒杀时间
     * @param groupId
     * @param productId
     */
    void clearFinishDate(@Param("groupId")long groupId, @Param("productId")Long productId);

    /**
     * 查询未参加活动的商品id集合
     * @return
     */
	List<Long> getNotReduceProductIds();

    /**
     * 根据活动组id查询活动明细id
     * @param id
     * @return
     */
    List<Long> selectProductGroupDetailsProductIdsByGroupId(Long id);
    
    /**
     * 用户端 活动秒杀组商品
     * @param query
     * @return
     */
     List<ProductGroupShopVo> getProductGroupGetShopVo(ProductGroupQuery query);
     
     /**
      * 用户端 活动秒杀组商品总数
      * @param query
      * @return
      */
     Long getCountByProductGroupGetShopVo(ProductGroupQuery query);

     /**
      * 用户端 活动秒杀组商品
      * @param query
      * @return
      */
      List<ProductGroupShopVo> getProductGroupPostShopVo(ProductGroupQuery query);
      
      /**
       * 用户端 活动秒杀组商品总数
       * @param query
       * @return
       */
      Long getCountByProductGroupPostShopVo(ProductGroupQuery query);

}
