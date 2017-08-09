/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youmu.mall.order.domain.ProductOrderItem;
import com.youmu.mall.order.dto.OrderQuantityDto;
import com.youmu.mall.product.domain.Product;
import com.youmu.mall.product.query.ProductQuery;
import com.youmu.mall.product.vo.ProductDetailShopVo;
import com.youmu.mall.product.vo.ProductSysVo;
import com.youmu.mall.product.vo.ProductVo;

/**
 * 
 * @author yujiahao
 * @version $Id: ProductDao.java, v 0.1 2017年2月25日 下午3:52:53 yujiahao Exp $
 */
public interface ProductDao {

    /**
     * 查询总数
     * @param productQuery
     * @return
     */
    long getByCount(ProductQuery productQuery);

    /**
     * 
     * @param product
     */
    void insertAndGetId(Product product);

    /**
     * 查询是否重复
     * @param product
     * @return
     */
    int getRepeatCount(Product product);

    /**
     * 保存商品图片
     * @param id
     * @param images
     */
    void batchSaveProductImages(@Param("productId")Long id, @Param("list")List<String> images);

    /**
     * 根据id查询商品
     * @param productId
     */
    Product getById(Long productId);

    /**
     * 修改商品
     * @param product
     */
    void update(Product product);

    /**
     * 删除商品图片
     * @param id
     */
    void deleteImagesById(Long id);

    /**
     * 商品上架
     * @param productId
     */
    void putawayById(Long productId);

    /**
     * 商品下架
     * @param productId
     */
    void outstockById(Long productId);

    /**
     * 用户端商品列表
     * @param productQuery
     * @return
     */
    List<ProductVo> getProductVoByType(ProductQuery productQuery);

    /**
     * 查询管理后台商品数量
     * @param productQuery
     * @return
     */
    Long getCountBySysVo(ProductQuery productQuery);

    /**
     * 查询管理后台商品列表
     * @param productQuery
     * @return
     */
    List<ProductSysVo> getProductSysVo(ProductQuery productQuery);

    /**
     * 用户端商品详情
     * @param productId
     * @return
     */
    ProductDetailShopVo getProductDetailShopVo(@Param("productId")Long productId);
    
    /**
     * 商品出库
     * @param productId
     * @param quantity
     */
    void reduceStockById(@Param("productId")long productId, @Param("stockNow")Integer quantity, @Param("version")long version);

    /**
     * 订单取消，商品入库
     * @param dtos
     */
    void batchUpdateQuantity(@Param("list")List<OrderQuantityDto> dtos);

    /**
     * 管理后台-获取商品库存紧张数量
     * @param productQuery
     * @return
     */
    int getShortageOfStockNumber(ProductQuery productQuery);

    /**
     * 更新商品销量
     * @param productId
     */
    void updateSaleCount(@Param("productId")Long productId,@Param("quantity")Integer quantity);

    /**
     * 根据id查询商品明细
     * @param productId
     * @return
     */
    ProductSysVo getProductSysVoById(Long productId);


    
}
