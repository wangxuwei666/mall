/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.bus.service;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.bus.domain.Business;
import com.youmu.mall.bus.query.BusinessQuery;
import com.youmu.mall.bus.vo.BusinessDetailVo;
import com.youmu.mall.bus.vo.BusinessListVo;
import com.youmu.mall.product.query.ProductQuery;
import com.youmu.mall.product.vo.ProductSysVo;

/**
 * 商户的服务.
 * @author zh
 * @version $Id: IBusinessService.java, v 0.1 2017年2月28日 上午9:50:16 zh Exp $
 */
public interface IBusinessService {

	 /**
     * 添加商户账户
     * @param business
     *//*
    void addBusiness(Business business);

    *//**
     * 
     * @param business
     * @return
     *//*
    Page<BusinessListVo> listBusiness(BusinessQuery business);

    *//**
     * 列出某商家的产品列表
     * @param query
     * @return
     */
    Page<ProductSysVo> listBusinessProducts(ProductQuery query);
    
    /**
     * 获取商户的详情.
     * @param query
     * @return
     *//*
    BusinessDetailVo getBusinessDetail(Long query);

    *//**
     * 修改商户的类型
     * @param business
     *//*
    void updateBusiness(Business business);

    *//**
     * 获取一个商户信息.
     * @param id
     * @return
     *//*
    Business getBusinessById(Long id);*/
	
	 /**
     * 添加商户账户
     * @param business
     */
    void addBusiness(Business business,Integer[] businessTypeIds);

    /**
     * 
     * @param business
     * @return
     */
    Page<BusinessListVo> listBusiness(BusinessQuery business);

    /**
     * 获取商户的详情.
     * @param query
     * @return
     */
    BusinessDetailVo getBusinessDetail(Long query);

    /**
     * 修改商户的类型
     * @param business
     */
    void updateBusiness(Business business);

    /**
     * 获取一个商户信息.
     * @param id
     * @return
     */
    Business getBusinessById(Long id);

    /**
     * 修改商户类型
     * @param bussnessId
     * @param bussnessTpyeIdOld
     * @param bussnessTpyeIdNew
     * @return
     */
    int updatetBusinessType(Long bussnessId, Integer bussnessTpyeIdOld,Integer bussnessTpyeIdNew);

    /**
     * 删除一个商户类型
     * @param bussnessId
     * @param bussnessTpyeId
     * @return
     */
    int delBusinessType(Long bussnessId, Integer bussnessTpyeId);

    /**
     * 添加一个商户类型
     * @param bussnessId
     * @param bussnessTpyeId
     * @return
     */
    int addBusinessType(Integer bussnessId, Integer bussnessTpyeId);

}
