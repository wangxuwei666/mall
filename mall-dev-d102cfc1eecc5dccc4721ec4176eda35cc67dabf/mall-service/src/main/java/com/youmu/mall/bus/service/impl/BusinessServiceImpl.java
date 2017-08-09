/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.bus.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.bus.dao.BusinessDao;
import com.youmu.mall.bus.dao.BussnessBusstypeDao;
import com.youmu.mall.bus.domain.Business;
import com.youmu.mall.bus.domain.BussnessBusstype;
import com.youmu.mall.bus.query.BusinessQuery;
import com.youmu.mall.bus.service.IBusinessService;
import com.youmu.mall.bus.vo.BusinessDetailVo;
import com.youmu.mall.bus.vo.BusinessListVo;
import com.youmu.mall.bus.vo.BussnessBussVO;
import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.product.query.ProductQuery;
import com.youmu.mall.product.vo.ProductSysVo;

/**
 * 商户服务实现类.
 * @author zh
 * @version $Id: BusinessServiceImpl.java, v 0.1 2017年2月28日 上午9:50:34 zh Exp $
 */
@Service
public class BusinessServiceImpl implements IBusinessService {
    
    @Resource
    private BusinessDao businessDao;
    @Resource
    private BussnessBusstypeDao bussnessBusstypeDao;

    /** 
     * @see com.youmu.mall.bus.service.IBusinessService#addBusiness(com.youmu.mall.bus.domain.Business)
     */
    @Transactional
    @Override
    public void addBusiness(Business business,Integer[] businessTypeIds) {
    	
    	// 查询商户是否重复
        Long repeatCount = businessDao.selectRepeatBusinessCount(business);
        if(repeatCount > 0) {
            throw new BusinessException("商户账户或银行账户重复");
        }
        // 不重复 添加商户
        businessDao.insertBusiness(business);
        //去重
        Collection<Integer> businessTypeSet = null;
        if(businessTypeIds!=null&&businessTypeIds.length>0){
            businessTypeSet = new HashSet();
            for (int i = 0; i < businessTypeIds.length; i++) {
                businessTypeSet.add(businessTypeIds[i]);
            }
        }
        bussnessBusstypeDao.insertBusinessBusstyps(business.getId(),businessTypeSet);


    }

    /** 
     * @see com.youmu.mall.bus.service.IBusinessService#listBusiness(com.youmu.mall.bus.query.BusinessQuery)
     */
    @Override
    public Page<BusinessListVo> listBusiness(BusinessQuery query) {
        Page<BusinessListVo> page = new Page<>(query.getPageNum(), query.getPageSize());
        // 查询分页的数量
        Long count = businessDao.selectBusinessCount(query);
        page.setTotal(count);
        // 查询内容
        if(page.getTotal() > 0) {
            page.setData(businessDao.selectBusinessList(query));
        }
        return page;
    }

    /** 
     * @see com.youmu.mall.bus.service.IBusinessService#getBusinessDetail(com.youmu.mall.bus.query.BusinessQuery)
     */
    @Override
    public BusinessDetailVo getBusinessDetail(Long id) {
        return businessDao.selectBusinessDetail(id);
    }

    /** 
     * @see com.youmu.mall.bus.service.IBusinessService#updateBusiness(com.youmu.mall.bus.domain.Business)
     */
    @Override
    public void updateBusiness(Business business) {
        // 查询商户是否重复
        businessDao.updateBusiness(business);
    }

    /** 
     * @see com.youmu.mall.bus.service.IBusinessService#getBusinessById(java.lang.Long)
     */
    @Override
    public Business getBusinessById(Long id) {
        Business businessById = businessDao.getBusinessById(id);
        //获取一组分类信息
        List<BussnessBussVO> bussnessBussVOList = bussnessBusstypeDao.BussnessBussVOList(id);
        businessById.setBussnessBussVOList(bussnessBussVOList);
        return businessById;
    }

    @Override
    public int updatetBusinessType(Long bussnessId, Integer bussnessTpyeIdOld,Integer bussnessTpyeIdNew) {
        int count = bussnessBusstypeDao.getBussnessTypeByIdAndBussnessTpyeId(bussnessId,bussnessTpyeIdNew);
        if(count>0){
            throw new BusinessException("已经有此商户分类");
        }
        bussnessBusstypeDao.updateBussnessBussType(bussnessId,bussnessTpyeIdOld,bussnessTpyeIdNew);
        return 0;
    }

    @Override
    public int delBusinessType(Long bussnessId, Integer bussnessTpyeId) {
        bussnessBusstypeDao.deleteBusinessTypeByBussnessIdAndBussnessTpyeId(bussnessId,bussnessTpyeId);
        return 0;
    }

    @Override
    public int addBusinessType(Integer bussnessId, Integer bussnessTpyeId) {
        int count = bussnessBusstypeDao.getBussnessTypeByIdAndBussnessTpyeId(Long.valueOf(bussnessId),bussnessTpyeId);
        if(count>0){
            throw new BusinessException("已经有此商户分类");
        }
        BussnessBusstype bussnessBusstype = new BussnessBusstype();
        bussnessBusstype.setBusinessId(bussnessId);
        bussnessBusstype.setBusinessTypeId(bussnessTpyeId);
        int i = bussnessBusstypeDao.insertSelective(bussnessBusstype);
        return i;
    }

	@Override
	public Page<ProductSysVo> listBusinessProducts(ProductQuery query) {
		Page<ProductSysVo> page = new Page<>(query.getPageNum(),query.getPageSize());
        page.setData(businessDao.getBusinessProductsById(query));
        page.setTotal(businessDao.selectBusinessProductCount(query));
		return page;
	}

}

