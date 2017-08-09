/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.bus.service;

import java.util.List;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.base.query.PageQuery;
import com.youmu.mall.bus.domain.BusinessType;
import com.youmu.mall.bus.vo.BusinessTypeListVo;

/**
 * 商户类型服务接口.
 * @author zh
 * @version $Id: IBusinessTypeService.java, v 0.1 2017年2月26日 下午3:59:59 zh Exp $
 */
public interface IBusinessTypeService {

    /**
     * 列出所有的商户
     * @return
     */
    List<BusinessTypeListVo> listAll(Integer isFilter);

    /**
     * 添加一个商户类型
     * @param type
     * @return
     */
    void addBusinessType(BusinessType type);

    /**
     * 列出商户类型分页
     * @param pageQuery
     * @return
     */
    Page<BusinessTypeListVo> listBusinessType(PageQuery pageQuery);

    /**
     * 删除一个商户类型
     * @param id
     */
    void deleteBusinessType(Long id);

    /**
     * 更新商户类型.
     * @param type
     */
    void updateBusinessType(BusinessType type);

    /**
     * 获取商户类型信息
     * @param id
     */
    BusinessTypeListVo getBusinessType(Long id);
    
}
