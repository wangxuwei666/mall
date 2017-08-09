/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.service;

import java.util.List;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.product.domain.Product;
import com.youmu.mall.product.query.ProductQuery;
import com.youmu.mall.product.vo.ProductDetailShopVo;
import com.youmu.mall.product.vo.ProductSysVo;
import com.youmu.mall.product.vo.ProductVo;

/**
 * 
 * @author yujiahao
 * @version $Id: IProductService.java, v 0.1 2017年2月25日 下午4:44:12 yujiahao Exp $
 */
public interface IProductService {

    /**
     * 新增商品
     * @param product
     */
    void insert(Product product);

    /**
     * 商品上架
     * @param product
     */
    void putawayById(Long productId);

    /**
     * 
     * @param product
     * @param productImgs 
     * @param thumbnail 
     */
    void update(Product product);

    /**
     * 商品下架
     * @param productId
     */
    void outstockById(Long productId);

    /**
     * 用户端商品列表（根据分类查询）
     * @param productQuery
     * @return
     */
    Page<ProductVo> getProductVoByType(ProductQuery productQuery);

    /**
     * 系统后台商品列表
     * @param productQuery
     * @return
     */
    Page<ProductSysVo> getProductSysVo(ProductQuery productQuery);

    /**
     * 用户端-商品详情
     * @param productId
     * @return
     */
    ProductDetailShopVo getProductDetailShopVo(Long productId);

    /**
     * 管理后台-获取商品库存紧张数量
     * @param productQuery
     * @return
     */
    long getShortageOfStockNumber(ProductQuery productQuery);

    /**
     * 管理后台-删除商品
     * @param productId
     */
    void deleteProductById(Long productId);

    /**
     * 根据id查询商品明细
     * @param productId
     * @return
     */
    ProductSysVo getProductSysVoById(Long productId);

    /**
     * 查询商品搜索热词
     * @return
     */
    List<String> findProductSearchHotKeywords();
    
}
