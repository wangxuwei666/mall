package com.youmu.mall.dcr.service;

import com.youmu.common.page.utils.Page;

import com.youmu.mall.dcr.domain.DCRRegister;
import com.youmu.mall.dcr.query.DcrRegisterQuery;

/**
 * 家装登记的服务接口
 * @author wxw
 */
public interface DcrRegisterService {
	
	void insertUserD(DCRRegister userd) ;
	
	Page<DCRRegister>  getDCRRegister(DcrRegisterQuery dcrRegisterQuery);

	 
}
