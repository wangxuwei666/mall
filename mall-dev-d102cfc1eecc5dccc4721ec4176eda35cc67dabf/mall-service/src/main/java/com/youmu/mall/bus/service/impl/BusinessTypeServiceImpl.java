/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.bus.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.base.query.PageQuery;
import com.youmu.mall.bus.dao.BusinessTypeDao;
import com.youmu.mall.bus.domain.BusinessType;
import com.youmu.mall.bus.service.IBusinessTypeService;
import com.youmu.mall.bus.vo.BusinessTypeListVo;
import com.youmu.mall.exception.BusinessException;

/**
 * 商户类型服务实现.
 * @author zh
 * @version $Id: BusinessTypeServiceImpl.java, v 0.1 2017年2月26日 下午4:04:42 zh Exp $
 */
@Service
public class BusinessTypeServiceImpl implements IBusinessTypeService {
    
    @Resource
    private BusinessTypeDao businessTypeDao;

    /** 
     * @see com.youmu.mall.bus.service.IBusinessTypeService#listAll()
     */
    @Override
    public List<BusinessTypeListVo> listAll(Integer isFilter) {
        return businessTypeDao.selectAllBusinessType(isFilter);
    }

    /** 
     * @see com.youmu.mall.bus.service.IBusinessTypeService#addBusinessType(com.youmu.mall.bus.domain.BusinessType)
     */
    @Transactional
    @Override
    public void addBusinessType(BusinessType type) {
        long count = businessTypeDao.countBusinessTypeRepeat(type);
        if(count > 0) {
            throw new BusinessException("类型名称重复");
        }
        businessTypeDao.insertBusinessType(type);
    }

    /** 
     * @see com.youmu.mall.bus.service.IBusinessTypeService#listBusinessType(com.youmu.mall.base.query.PageQuery)
     */
    @Override
    public Page<BusinessTypeListVo> listBusinessType(PageQuery pageQuery) {
        Page<BusinessTypeListVo> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        // 1.查询总数量
        Long count = businessTypeDao.selectBusinessTypeCount(pageQuery);
        // 2.查询列表内容
        if(count > 0) {
            page.setTotal(count);
            page.setData(businessTypeDao.selectBusinessTypeList(pageQuery));
        }
        return page;
    }

    /** 
     * @see com.youmu.mall.bus.service.IBusinessTypeService#deleteBusinessType(java.lang.Long)
     */
    @Override
    public void deleteBusinessType(Long id) {
        int row = businessTypeDao.deleteBusinessType(id);
        if(row == 0) {
            throw new BusinessException("已删除或系统默认项不能删除");
        }
    }

    /** 
     * @see com.youmu.mall.bus.service.IBusinessTypeService#updateBusinessType(com.youmu.mall.bus.domain.BusinessType)
     */
    @Override
    public void updateBusinessType(BusinessType type) {
        long count = businessTypeDao.countBusinessTypeRepeat(type);
        if(count > 0) {
            throw new BusinessException("类型名称重复");
        }
        businessTypeDao.updateBusinessType(type);
    }

    /** 
     * @see com.youmu.mall.bus.service.IBusinessTypeService#getBusinessType(java.lang.Long)
     */
    @Override
    public BusinessTypeListVo getBusinessType(Long id) {
        return businessTypeDao.getBuisnessType(id);
    }
}
