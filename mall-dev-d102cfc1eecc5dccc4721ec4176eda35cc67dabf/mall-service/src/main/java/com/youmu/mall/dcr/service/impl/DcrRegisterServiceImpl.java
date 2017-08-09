package com.youmu.mall.dcr.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.dcr.dao.DcrRegisterDao;
import com.youmu.mall.dcr.domain.DCRRegister;
import com.youmu.mall.dcr.query.DcrRegisterQuery;
import com.youmu.mall.dcr.service.DcrRegisterService;

/**
 * 家装登记的服务实现
 * @author wxw
 */
@Service
public class DcrRegisterServiceImpl implements DcrRegisterService{
	@Resource
	private DcrRegisterDao dcrRegisterDao;

	@Override
	public void insertUserD(DCRRegister userd) {
		dcrRegisterDao.insertUserD(userd);
	}

	@Override
	public Page<DCRRegister> getDCRRegister(DcrRegisterQuery dcrRegisterQuery) {
		Page<DCRRegister> page=new Page<>(dcrRegisterQuery.getPageNum(),dcrRegisterQuery.getPageSize());
		page.setData(dcrRegisterDao.getDcrRegisterByType(dcrRegisterQuery));
		page.setTotal(dcrRegisterDao.getCountByType(dcrRegisterQuery));
		return page;
	}

	
	
}

	
