/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.bus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youmu.mall.base.query.PageQuery;
import com.youmu.mall.bus.domain.BusinessType;
import com.youmu.mall.bus.vo.BusinessTypeListVo;

/**
 * 商户类型dao.
 * @author zh
 * @version $Id: BusinessTypeDao.java, v 0.1 2017年2月26日 下午4:07:30 zh Exp $
 */
public interface BusinessTypeDao {

    /**
     * 查询所有的商户类型 
     * @return
     */
    List<BusinessTypeListVo> selectAllBusinessType(@Param("isFilter")Integer isFilter);

    /**
     * 添加一个商户类型
     * @param type
     */
    void insertBusinessType(BusinessType type);

    /**
     * 查询商户类型的数量.
     * @param pageQuery
     * @return
     */
    Long selectBusinessTypeCount(PageQuery pageQuery);

    /**
     * 查询商户类型列表.
     * @param pageQuery
     * @return
     */
    List<BusinessTypeListVo> selectBusinessTypeList(PageQuery pageQuery);

    /**
     * 删除商户行业
     * @param id
     * @return
     */
    int deleteBusinessType(Long id);

    /**
     * 查询商户类型的名称通过名称.
     */
    long countBusinessTypeRepeat(BusinessType type);

    /**
     * 修改商户类型.
     * @param type
     */
    void updateBusinessType(BusinessType type);

    /**
     * 获取商户类型
     * @param id
     * @return
     */
    BusinessTypeListVo getBuisnessType(Long id);

}
