/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.base.query.PageQuery;
import com.youmu.mall.product.domain.ProductGroup;
import com.youmu.mall.product.query.ProductGroupQuery;
import com.youmu.mall.product.vo.GroupDateVo;
import com.youmu.mall.product.vo.ProductGroupEditVo;
import com.youmu.mall.product.vo.ProductGroupShopVo;
import com.youmu.mall.product.vo.ProductGroupSysVo;
import com.youmu.mall.product.vo.ProductSysVo;

/**
 * 商品活动组服务层接口
 * @author yujiahao
 * @version $Id: IProductGroupService.java, v 0.1 2017年3月7日 上午11:18:59 yujiahao Exp $
 */
@Service
public interface IProductGroupService {

    /**
     * 管理后台-促销管理（活动商品组列表）
     * @param pageQuery
     * @return
     */
    Page<ProductGroupSysVo> getProductGroupSysVo(ProductGroupQuery query);

    /**
     * 管理后台-添加活动商品组
     * @param productGroup
     */
    void createProductGroup(ProductGroup productGroup);

    /**
     * 根据活动组id查询明细
     * @param groupId
     * @return
     */
    ProductGroupEditVo getProductGroupDetailSysVoByGroupId(Long groupId, Integer status);

    /**
     * 管理后台-查询当前可参与活动的商品明细
     * @param query
     * @return
     */
    Page<ProductSysVo> getNotReduceProduct(PageQuery query);

    /**
     * 管理后台-修改活动商品组
     * @param productGroup
     */
    void updateProductGroup(ProductGroup productGroup);

    /**
     * 删除活动组
     * @param id
     */
    void deleteProductGroup(Long id);

    /**
     * 用户端 - 活动商品组
     * @param query
     * @return
     */
    Page<ProductGroupShopVo> getProductGroupShopVo(ProductGroupQuery query);

    /**
     * 用户端- 秒杀&特价  根据活动组id查询商品组明细
     * @param groupId
     * @return
     */
    ProductGroupShopVo getProductGroupDetailShopVoById(Long groupId);

    /**
     * 秒杀页面banner(查询当天参加活动的时间段)
     * @return
     */
    List<GroupDateVo> getGroupBannerDate();
    
    /**
     * 用户端 - 秒杀组&积分组
     * @param query
     * @return
     */
    Page<ProductGroupShopVo> getProductGroupGetShopVo(ProductGroupQuery query);
    
    /**
     * 用户端 - 秒杀组&积分组
     * @param query
     * @return
     */
    Page<ProductGroupShopVo> getProductGroupPostShopVo(ProductGroupQuery query);
    
}
