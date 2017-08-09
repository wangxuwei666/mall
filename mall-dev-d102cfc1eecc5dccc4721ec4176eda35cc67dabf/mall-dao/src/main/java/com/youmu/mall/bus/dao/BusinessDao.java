/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.bus.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.youmu.mall.bus.domain.Business;
import com.youmu.mall.bus.query.BusinessQuery;
import com.youmu.mall.bus.vo.BusinessDetailVo;
import com.youmu.mall.bus.vo.BusinessListVo;
import com.youmu.mall.product.query.ProductQuery;
import com.youmu.mall.product.vo.ProductSysVo;

/**
 * 商户数据访问接口.
 * @author zh
 * @version $Id: BusinessDao.java, v 0.1 2017年2月28日 上午9:51:43 zh Exp $
 */
public interface BusinessDao {

    /**
     * 添加一个商户
     * @param business
     */
    void insertBusiness(Business business);
    
    /**
     * 查询重复商户的数量
     * @param business
     */
    Long selectRepeatBusinessCount(Business business);

    /**
     * 查询商户的数量
     * @param query
     * @return
     */
    Long selectBusinessCount(BusinessQuery query);

    /**
     * 查询商户的列表
     * @param query
     */
    List<BusinessListVo> selectBusinessList(BusinessQuery query);
    
    /**
     * 根据id查询商户信息
     * @param businessId
     * @return
     */
    Business getBusinessById(Long businessId);
    
    /**
     * 根据id查询商户的商品数量
     * @param query
     * @return
     */
    Long selectBusinessProductCount(ProductQuery query);
    
    /**
     * 根据id查询商户的商品信息
     * @param businessId
     * @return
     */
    List<ProductSysVo> getBusinessProductsById(ProductQuery query);
    
    /**
     * 修改business
     * @param business
     */
    void updateBusiness(Business business);

    /**
     * 根据商户id查询商户的类别id
     * @param businessId
     * @return
     */
    Long getTypeIdById(Long businessId);

    /**
     * 
     * @param id
     * @return
     */
    BusinessDetailVo selectBusinessDetail(Long id);
    
}
